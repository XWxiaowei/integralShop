package com.shop.admin.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.shop.enums.CatalogLevel;
import com.shop.enums.DeleteFlag;
import com.shop.enums.SellStatus;
import com.shop.model.ProductQuery;
import com.shop.model.ShopCatalog;
import com.shop.model.ShopMerchant;
import com.shop.model.ShopProduct;
import com.shop.service.ShopCatalogService;
import com.shop.service.ShopMerchantService;
import com.shop.service.ShopProductService;
import com.shop.util.PageTagQuerys;
import com.shop.util.UIDUtil;

@Controller
@RequestMapping("/manage/tab2/product")
public class AdminProductController extends BaseController {
	private final Logger logger = LoggerFactory
			.getLogger(AdminProductController.class);
	@Autowired
	private ShopProductService productService;
	@Autowired
	private ShopMerchantService merchantService;
	@Autowired
	private ShopCatalogService catalogService;

	private Map<String, Object> selectMap = new HashMap<>(); // 用于存放下拉框的查询条件

	/**
	 * @Desc 首次进入产品列表页面
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午12:09:59
	 */
	@RequestMapping(value = "/index")
	public String index(PageTagQuerys pageTagQuerys, Model model,
			HttpServletRequest request) {
		getBindData(model);
		if (pageTagQuerys != null) {
			pageTagQuerys = new PageTagQuerys();
		}
		model.addAttribute("pageTagQuerys", pageTagQuerys);
		// 修改断网之后的提示
		if (StringUtils.isNotBlank(request.getParameter("editResult"))) {
			model.addAttribute("editResult", request.getParameter("editResult"));
		}
		logger.info("商品列表默认页面返回成功");
		return "manage/product/list";
	}

	/**
	 * @Desc 查询产品列表
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午12:17:47
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/query")
	public String query(PageTagQuerys pageTagQuerys, Model model,
			HttpServletRequest request, ProductQuery productQuery) {
		getBindData(model);
		setSelectedCondition(request, productQuery);
		productQuery.setIpageIndex((pageTagQuerys.getCurr() - 1)
				* pageTagQuerys.getSize());
		productQuery.setIpageSize(pageTagQuerys.getSize());
		Object[] iPagination = productService.queryPaginationProductPage(
				productQuery, pageTagQuerys.getCurr() - 1,
				pageTagQuerys.getSize());
		Integer iTotalRecord = 0;
		List<ShopProduct> productList = new ArrayList<>();
		if (iPagination != null) {
			iTotalRecord = (Integer) iPagination[0];
			productList = (List<ShopProduct>) iPagination[1];
		}
		// 修改商品类型为大类
		if (productList != null) {
			for (ShopProduct product : productList) {
				long cCatalogId = product.getCatalogId();
				long pCatalogId = catalogService.getByCatalogId(cCatalogId)
						.getParentCatalogid();
				product.setCatalogId(pCatalogId);
			}
		}
		if (iTotalRecord > 0) {
			pageTagQuerys.setTotal(iTotalRecord);
		}
		model.addAttribute("productQuery", productQuery);
		model.addAttribute("productList", productList);
		model.addAttribute("pageTagQuerys", pageTagQuerys);
		return "manage/product/list";
	}

	/**
	 * @Desc 新增商品
	 * @param request
	 * @param response
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午5:39:23
	 */
	@RequestMapping(value = "/add")
	public String addProduct(HttpServletRequest request,
			HttpServletResponse response) {
		return "redirect:edit.html";
	}

	@RequestMapping(value = "/edit")
	public String editProdut(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		getBindData(model);
		String productId = request.getParameter("productId");
		String returnUrl = "manage/product/list";
		if (StringUtils.isNotBlank(productId)) {
			ShopProduct product = productService.selectByProductId(Long
					.valueOf(productId));
			if (product == null) {
				model.addAttribute("usersEdit", "该商品已被删除");
				return returnUrl;
			}
			// 上架商品不能编辑
			if (product.getOnsell() == SellStatus.ONSELL.getCode()) {
				model.addAttribute("usersEdit", "上架商品不能编辑");
				return returnUrl;
			}
			if (product.getDeleteFlag() == DeleteFlag.YES.getCode()) {
				model.addAttribute("usersEdit", "已删除商品不能编辑");
				return returnUrl;
			}
			if (product.getCatalogId() != null) {
				ShopCatalog parentCatalog = catalogService
						.getByCatalogId(product.getCatalogId());
				model.addAttribute("parentCatalogId", parentCatalog.getParentCatalogid());
				List<ShopCatalog> secondCatalogList = catalogService
						.getByParentId(parentCatalog.getCatalogId());
				if (CollectionUtils.isEmpty(secondCatalogList)) {
					logger.error("未找到二级类目");
				}
				model.addAttribute("secondCatalogList", secondCatalogList);
			}
			model.addAttribute("product", product);
		}
		return "manage/product/edit";
	}

	/**
	 * @Desc 商品删除
	 * @param request
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午5:39:03
	 */
	@RequestMapping(value = "/delete")
	public String deleteProduct(HttpServletRequest request, Model model) {
		String productId = request.getParameter("productId");
		if (StringUtils.isBlank(productId)) {

		}
		ShopProduct record = new ShopProduct();
		record.setProductId(Long.valueOf(productId));
		record.setDeleteFlag(1);
		int num = productService.updateByProductId(record);
		if (num != 1) {
			model.addAttribute("usersEdit", "商品更新失败");
		}
		return "manage/product/list";
	}

	/**
	 * @Desc 商品下架
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午5:38:40
	 */
	@RequestMapping(value = "/onsell")
	public String onsell(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Long productId = Long.valueOf(request.getParameter("productId"));
		ShopProduct product = productService.selectByProductId(productId);
		if (product == null) {
			logger.error("找不到对应的产品");
			model.addAttribute("usersEdit", "找不到对应的产品");
		}
		int onsell = Integer.valueOf(request.getParameter("onsell"));
		product.setOnsell(onsell);
		int resCode = productService.updateByProductId(product);
		model.addAttribute("onsellCode", resCode);
		return "manage/product/list";
	}

	/**
	 * @Desc 保存新增或者修改的商品
	 * @param request
	 * @param response
	 * @param product
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月15日 下午4:45:21
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String saveProduct(HttpServletRequest request, Model model,
			ShopProduct product) {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		String titlePath = null;
		String logoPath = null;
		// 检查form是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				// 由CommonsMultipartFile继承而来,拥有上面的方法.
				MultipartFile file = multipartRequest.getFile(iter.next());
				// 如果文件不为空，则进行处理
				if (file.isEmpty()) {
					break;
				}
				// 对图片文件名进行处理，取得最后的6个字符，然后以"."为分隔符取得文件后缀
				String originalName = file.getOriginalFilename();
				// 获得图片的格式
				String fileSuffix = originalName.substring(
						originalName.lastIndexOf("."), originalName.length());
				String randomFileName = UIDUtil.next();
				String fileName = randomFileName + fileSuffix;
				if (file.getName() != null && "file1".equals(file.getName())) {
					titlePath = fileName;
				}
				if (file.getName() != null && "file2".equals(file.getName())) {
					logoPath = fileName;
				}
				String filePath = getBasePicPath() + fileName;
				File localFile = new File(filePath);
				File baseFile = new File(getBasePicPath());
				try {
					if (!baseFile.exists() && !baseFile.isDirectory()) {
						baseFile.mkdirs();
					}
					file.transferTo(localFile);
				} catch (Exception e) {
					logger.error("图片路径创建失败,{}", e);
				}

			}
			logger.info("图片上传成功");
			product.setTitlePicture(titlePath);
			product.setLogoPicture(logoPath);
			// 处理商品详情中的图片
			if (product.getInfo() != null) {
				String picUrl = product.getInfo().replace("../pics", "");
				product.setInfo(picUrl);
			}
			productService.updateInfo(product);
			logger.info("商品保存成功, productid={}", product.getProductId());
		}
		return "redirect:list.html?editResult=1";
	}
	/**
	 * 功能说明：查询二级类目
	 * @param request
	 * @param catalogId
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月16日 下午10:30:37
	 */
	@RequestMapping(value = "/selectCatalog.do")
	public @ResponseBody List<ShopCatalog> selectCatalog(HttpServletRequest request, Long catalogId) {
		return catalogService.getByParentId(catalogId);
	}
	@RequestMapping(value = "/showImage")
	public void showImage(HttpServletRequest request,
			HttpServletResponse response, String filePath) {
		String absolutePath = null;
		// 对路径做过滤，格式为数字.文件类型 不满足则显示默认图片
		if (StringUtils.isEmpty(filePath)
				|| filePath.length() > 60
				|| !(filePath
						.matches("(../pics/){0,1}(ueditor/){0,1}(banner/){0,1}[0-9]{0,8}(\\.|/.){0,1}[1-9][0-9]{10,20}(\\.|/.)(jpg|gif|png|jpeg|bmp)"))) {
			try {
				absolutePath = java.net.URLDecoder.decode(this.getClass()
						.getClassLoader().getResource("").getPath(), "utf-8")
						+ File.separator + "default.png";
			} catch (UnsupportedEncodingException e) {
				logger.error("图片名称转化失败{}", e);
			}
		} else {
			absolutePath = getBasePicPath() + filePath;// TODO:不需要放置到session
		}
		// 用于读取诸如图像数据之类的原始字节流
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			// 根据文件类型设置response格式
			String suffix = absolutePath
					.substring(filePath.lastIndexOf(".") + 1);
			String responseType = "image/jpeg";
			switch (suffix) {
			case "png":
				responseType = "image/png";
				break;
			case "gif":
				responseType = "image/gif";
				break;
			default:
				break;
			}
			response.setContentType(responseType);
			fis = new FileInputStream(absolutePath);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 1024];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
			}
		} catch (IOException e) {
			logger.error("showIamge图片显示失败,{}", e);
		} finally {
			try {
				if (os != null) {
					//刷新此输出流并强制写出所有缓冲的输出字节。
					os.flush();
					os.close();
				}
				if (fis!=null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 功能说明：选择参数传递
	 * 
	 * @param request
	 * @param model
	 * @author xiang.wei
	 * @date 2017年4月15日 下午3:58:24
	 */
	private void setSelectedCondition(HttpServletRequest request,
			ProductQuery productQuery) {
		if (productQuery==null) {
			productQuery=new ProductQuery();
		}
		// 接收参数
		if (StringUtils.isNotBlank(request.getParameter("query1"))) {
			productQuery.setMerchantId(Long.valueOf(request
					.getParameter("query1")));
		}
		if (StringUtils.isNotBlank(request.getParameter("query2"))) {
			productQuery.setProductName(request.getParameter("query2"));
		}
		if (StringUtils.isNotBlank(request.getParameter("query3"))) {
			productQuery.setCatalogId(Long.valueOf(request
					.getParameter("query3")));
		}
		if (StringUtils.isNotBlank(request.getParameter("query4"))) {
			productQuery.setCreateTimeStart(request.getParameter("query4"));
		}
		if (StringUtils.isNotBlank(request.getParameter("query5"))) {
			productQuery.setCreateTimeEnd(request.getParameter("query5"));
		}
	}

	/**
	 * @Desc 获取下拉框绑定的数据
	 * @param model
	 * @return
	 * @author xiang.wei
	 * @date 2017年4月14日 下午1:12:49
	 */
	private void getBindData(Model model) {
		// 查询所有的商户
		List<ShopMerchant> merchantList = merchantService.selectAll();
		// 查询所有的一级分类
		List<ShopCatalog> firstCatalogList = catalogService
				.selectByLevel(CatalogLevel.FIRST.getCode());
		selectMap.put("merchants", merchantList);
		selectMap.put("catalogs", firstCatalogList);
		model.addAttribute("merchants", merchantList);
		model.addAttribute("catalogs", firstCatalogList);
	}

}
