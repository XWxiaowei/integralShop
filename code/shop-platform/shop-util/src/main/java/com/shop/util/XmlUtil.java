package com.shop.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class XmlUtil {
	private static Logger logger = LoggerFactory.getLogger(XmlUtil.class);

	/**
	 * map转化成xml
	 * 
	 * @param map
	 *            待转化的map
	 * @param rooNode
	 *            xml的根节点
	 * @param encoding
	 *            编码格式
	 * @return
	 * @author xiang.wei
	 * @date 2017年3月27日 下午9:09:24
	 */
	public static String map2Xml(Map map, String rooNode, String encoding) {
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding(encoding);
		Element catalogElement = document.addElement(rooNode);
		for (Object obj : map.keySet()) {
			map2Dom(catalogElement, obj.toString(), map.get(obj));
		}
		return document.asXML();
	}

	public static Map xml2Map(String xml) {
		SAXReader reader = new SAXReader();
		// 预防xml, xxe注入攻击
		reader.setValidation(false);
		try {
			reader.setFeature(
					"http://apache.org/xml/features/nonvalidating/load-external-dtd",
					false);
		} catch (SAXException e) {
			e.printStackTrace();
		}
		Map map = new HashMap();
		try {
			Document doc = DocumentHelper.parseText(xml);
			Element root = doc.getRootElement();
			map = dom2Map(root);
		} catch (DocumentException e) {
			logger.error("xml文件解析失败{}", e);
		}
		return map;
	}

	private static Map dom2Map(Element e) {
		Map map = new HashMap();
		List list = e.elements();
		if (list != null && list.size() > 0) {
			for (Object object : list) {
				Element iter = ((Element) object);
				List mapList = new ArrayList();
				if (iter.elements().size() > 0) {
					Map m = dom2Map(iter);
					String str = iter.getName();
					if (map.get(str) != null) {
						Object obj = map.get(iter.getName());
						if (obj instanceof List) {
							mapList = (List) obj;
						} else {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), m);
					}
				} else {
					if (map.get(iter.getName()) != null) {
						Object obj = map.get(iter.getName());
						if (obj instanceof List) {
							mapList = (List) obj;
							mapList.add(iter.getTextTrim());
						} else {
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getTextTrim());
						}
						map.put(iter.getName(), mapList);
					} else {
						map.put(iter.getName(), iter.getTextTrim());
					}
				}
			}
		} else {
			map.put(e.getName(), e.getTextTrim());
		}
		return map;
	}

	private static void map2Dom(Element catalogElement, String key, Object obj) {
		Element child = catalogElement.addElement(key);
		if (obj instanceof Map) {
			Map objMap = (Map) obj;
			for (Object objTemp : objMap.keySet()) {
				map2Dom(child, objTemp.toString(), objMap.get(objTemp));
			}
		} else if (obj instanceof List) {
			List objList = (List) obj;
			for (Object object : objList) {
				map2Dom(catalogElement, key, object);

			}
		} else {
			if (obj != null && obj.toString().length() > 0) {
				child.addText(obj.toString());
			} else {
				child.addText("");
			}
		}
	}
}
