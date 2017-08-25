package com.shop.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.Consts;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.enums.ParamType;

/**
 * 功能说明：http的工具类 单例模式
 * 
 * @author xiang.wei
 *
 */
public class HttpUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private PoolingHttpClientConnectionManager connectionManager;

	private static RequestConfig requestConfig;

	private static HttpUtil newHttpUtil = new HttpUtil();

	private static IdleConnectionMonitorThread ict;

	private HttpUtil() {
		connectionManager = new PoolingHttpClientConnectionManager();
		// 默认最大连接数
		int defaultMaxTotal = 3000;
		connectionManager.setMaxTotal(defaultMaxTotal);
		// 每个路由默认连接数
		int defaultMaxPerRoute = 400;
		connectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		/* 默认从连接池中获取连接的超时时间为2秒 */
		int defaultConnectRequestTimeOut = 2 * 1000;
		/* 默认传输超时时间为10秒 */
		int defaultSocketTimeOut = 10 * 1000;
		// 默认连接超时时间为10秒
		int defaultConnectTimeOut = 10 * 1000;
		requestConfig = RequestConfig.custom()
				.setSocketTimeout(defaultSocketTimeOut)
				.setConnectionRequestTimeout(defaultConnectRequestTimeOut)
				.setConnectTimeout(defaultConnectTimeOut).build();
		ict = new IdleConnectionMonitorThread(connectionManager);
		// 每隔5分钟清理一下请求
		int defaultCleatTime = 5 * 1000;
		ict.setClearTime(defaultCleatTime);
		ict.start();
	}

	public String sendByJson(String url, String json) {
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager).build();
		HttpPost httpPost = new HttpPost(url); // 创建HttpPost对象
		httpPost.setConfig(requestConfig);
		httpPost.setEntity(new StringEntity(json, ContentType.create(
				"application/json", Consts.UTF_8)));
		StringBuffer result = new StringBuffer();
		BufferedReader buffer = null;
		InputStreamReader isr = null;
		String line = null;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpPost);
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				InputStream input = response.getEntity().getContent();
				isr = new InputStreamReader(input);
				buffer = new BufferedReader(isr, 10 * 1024);
				while ((line = buffer.readLine()) != null) {
					result.append(line).append(
							System.getProperty("line.separator"));
				}
				result.delete(result.lastIndexOf(System
						.getProperty("line.separator")), result.length());
			} else {
				logger.error("Http请求返回码错误：{}", code);
			}
		} catch (ClientProtocolException e) {
			logger.error("Http请求错误：{}", e);
		} catch (IOException e) {
			logger.error("IO错误：{}", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (buffer != null) {
					buffer.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result.toString();
	}

	/**
	 * 发送https的post请求，根据paramType确定数据类型，如json，xml，form表单，默认采用HTTP长连接
	 * 
	 * @param url
	 * @param str
	 * @param paramType
	 * @return
	 * @author xiang.wei
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @date 2017年3月26日 下午10:13:27
	 */
	public String sendHttpsPost(String url, String str, String paramType)
			throws KeyManagementException, NoSuchAlgorithmException,
			KeyStoreException {
		return sendHttpsPost(url, str, paramType, true);
	}

	/**
	 * 发送https的post请求，根据paramType确定数据类型，如json，xml，form表单
	 * 
	 * @param url
	 * @param str
	 * @param paramType
	 * @param isKeepAlived
	 * @return
	 * @author xiang.wei
	 * @throws KeyStoreException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @date 2017年3月26日 下午10:13:56
	 */
	public String sendHttpsPost(String url, String str, String paramType,
			boolean isKeepAlived) throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		SSLConnectionSocketFactory sslsf;
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
				new TrustStrategy() {
					// 信任所有
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				}).build();
		sslsf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager)
				.setSSLSocketFactory(sslsf).build();
		HttpPost httpPost = new HttpPost(url); // 创建HttpPost对象
		httpPost.setConfig(requestConfig);
		switch (paramType) {
		case ParamType.JSON:
			httpPost.setEntity(new StringEntity(str, ContentType.create(
					"application/json", Consts.UTF_8)));
			break;
		case ParamType.XML:
			httpPost.setEntity(new StringEntity(str, ContentType.create(
					"application/xml", Consts.UTF_8)));
			break;
		case ParamType.FORM:
			httpPost.setEntity(new StringEntity(str, ContentType.create(
					"x-www-form-urlencoded", Consts.UTF_8)));
			break;
		}
		StringBuffer result = new StringBuffer();
		BufferedReader buffer = null;
		InputStreamReader isr = null;
		String line;
		CloseableHttpResponse response = null;
		try {
			// 是否采用长连接
			if (!isKeepAlived) {
				httpPost.setProtocolVersion(HttpVersion.HTTP_1_0);
				httpPost.addHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_CLOSE);
			}
			response = httpClient.execute(httpPost);
			int status = response.getStatusLine().getStatusCode();
			if (status == HttpStatus.SC_OK) {
				InputStream input = response.getEntity().getContent();
				isr = new InputStreamReader(input);
				buffer = new BufferedReader(isr, 10 * 1024);
				while ((line = buffer.readLine()) != null) {
					result.append(line).append(
							System.getProperty("line.separator"));
				}
				result.delete(result.lastIndexOf(System
						.getProperty("line.separator")), result.length());
			} else {
				logger.error("http请求返回码错误{}", status);
			}
		} catch (ClientProtocolException e) {
			logger.error("Http请求错误：", e);
		} catch (IOException e) {
			logger.error("IO错误：", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
				if (buffer != null) {
					buffer.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				logger.error("IO错误：{}", e);
			}
		}
		return result.toString();
	}

	/**
	 * 发送https的get请求，参数url如：https://api.weixin.qq.com/cgi-bin/token?grant_type=
	 * client_credential&appid=xxx&secret=xxx
	 * 
	 * @param url
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @author xiang.wei
	 * @date 2017年3月27日 下午8:48:38
	 */
	public String sendHttpsGet(String url) throws KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {
		SSLConnectionSocketFactory sslsf;
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null,
				new TrustStrategy() {
					// 信任所有
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				}).build();
		sslsf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom()
				.setConnectionManager(connectionManager)
				.setSSLSocketFactory(sslsf).build();

		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(requestConfig);

		StringBuffer result = new StringBuffer();
		BufferedReader buffer = null;
		InputStreamReader isr = null;
		String line;
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(httpGet);
			int code = response.getStatusLine().getStatusCode();
			if (code == HttpStatus.SC_OK) {
				InputStream input = response.getEntity().getContent();
				isr = new InputStreamReader(input);
				buffer = new BufferedReader(isr, 10 * 1024);

				result = new StringBuffer();
				while ((line = buffer.readLine()) != null) {
					result.append(line);
					result.append(System.getProperty("line.separator"));
				}
				result.delete(result.lastIndexOf(System
						.getProperty("line.separator")), result.length());
			} else {
				logger.error("Http请求返回码错误：" + code);
			}
		} catch (ClientProtocolException e) {
			logger.error("Http请求错误：", e);
		} catch (IOException e) {
			logger.error("IO错误：", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}

				if (buffer != null) {
					buffer.close();
				}
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				logger.error("错误：{}", e);
			}

		}
		return result.toString();

	}

	public static HttpUtil getInstance() {
		return newHttpUtil;
	}

	/**
	     * 
	     */
	public static void destroy() {
		ict.shutdown();
	}

	/**
	 * 该线程负责清理失效连接和空闲过长连接
	 * 
	 * @author xiang.wei
	 *
	 */
	private static class IdleConnectionMonitorThread extends Thread {
		private final HttpClientConnectionManager connMgr;
		private volatile boolean shutdown;
		private int clearTime = 500;

		public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
			super();
			this.connMgr = connMgr;
		}

		public void setClearTime(int clearTime) {
			this.clearTime = clearTime;
		}

		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(clearTime);
						// 关闭失效链接
						connMgr.closeExpiredConnections();
						// 关闭空闲超过30秒的连接
						connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}
	}

}
