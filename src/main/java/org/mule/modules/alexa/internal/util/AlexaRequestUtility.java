package org.mule.modules.alexa.internal.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AlexaRequestUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRequestUtility.class);

	public String doGet(String urlString, String basicAuth, String skillId) throws Exception {

		LOGGER.info("GET operation with params: urlString {} skillId {}",urlString,skillId);
		CloseableHttpClient client = HttpClients.createDefault();

		try {

			HttpGet get = new HttpGet(urlString);

			get.setHeader("Authorization", basicAuth);
			get.setHeader("Content-Type", "application/json");

			CloseableHttpResponse response = client.execute(get);
			int code = response.getStatusLine().getStatusCode();

			try {

				HttpEntity entity = response.getEntity();
				if (code >= 200 && code < 300) {
					return entity != null ? EntityUtils.toString(entity) : null;
				} else {
					// throwing error if we get 400 and 500
					return entity != null ? EntityUtils.toString(entity) : null;
				}

			} finally {
				response.close();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while connecting to Alexa",e);
			// TODO: handle exception
			// re throwing the exception in case of any failure while connecting api
			throw e;
		}

		finally {

			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}

	}

	public String doPost(String urlString, String basicAuth, String requestJson) throws Exception {

		LOGGER.info("POST operation with params: urlString {} requestJson {}",urlString,requestJson);
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			HttpPost post = new HttpPost(urlString);

			post.setHeader("Authorization", basicAuth);
			post.setHeader("Content-Type", "application/json");

			StringEntity reqEntity = new StringEntity(requestJson);
			post.setEntity(reqEntity);

			CloseableHttpResponse response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();

			try {

				HttpEntity resEntity = response.getEntity();
				if (code >= 200 && code < 300) {
					return resEntity != null ? EntityUtils.toString(resEntity) : null;
				} else {
					// throwing error if we get 400 and 500
					return resEntity != null ? EntityUtils.toString(resEntity) : null;
				}

			} finally {
				response.close();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while connecting to Alexa",e);
			// TODO: handle exception
			// re throwing the exception in case of any failure while connecting api
			throw e;
		}

		finally {

			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}

		}

	}

	public String doPut(String urlString, String basicAuth, String requestJson) throws Exception {
		
		LOGGER.info("PUT operation with params: urlString {} requestJson {}",urlString,requestJson);
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			HttpPut post = new HttpPut(urlString);

			post.setHeader("Authorization", basicAuth);
			post.setHeader("Content-Type", "application/json");

			StringEntity reqEntity = new StringEntity(requestJson);
			post.setEntity(reqEntity);

			CloseableHttpResponse response = client.execute(post);
			int code = response.getStatusLine().getStatusCode();

			try {

				HttpEntity resEntity = response.getEntity();
				if (code >= 200 && code < 300) {
					return resEntity != null ? EntityUtils.toString(resEntity) : null;
				} else {
					// Handle the error response error if we get 400 and 500
					return resEntity != null ? EntityUtils.toString(resEntity) : null;
				}

			} finally {
				response.close();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while connecting to Alexa",e);
			// TODO: handle exception
			throw e;
		}

		finally {

			try {
				client.close();
			} catch (IOException e) {
				// TODO no need to handle this just closing the connection
			}

		}
	}

}
