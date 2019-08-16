/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.DeadEvent;

public class AlexaRequestUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRequestUtility.class);
	private static final int CONNECTION_REQUEST_TIMEOUT_MS = 5000;
	private static final int CONNTECTION_TIMEOUT_MS = 5000;
	private static final int SOCKET_TIMEOUT_MS = 5000;

	/**
	 * Return {@code String} response got form Alexa server, requires basic
	 * authentication.
	 * 
	 * @param urlString
	 * @param basicAuth
	 * @param skillId
	 * @return String
	 * @throws AlexaApiException
	 */
	public String doGet(String urlString, String basicAuth, String skillId) throws AlexaApiException {

		LOGGER.info("GET operation with params: urlString {} skillId {}", urlString, skillId);
		CloseableHttpClient client = HttpClients.createDefault();

		try {

			urlString = 	String.format(urlString, skillId);
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

			} catch (IOException e) {
				// TODO: handle exception
				LOGGER.error("Got Error while consuming api response in GET operation {}", e);
				throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.EXECUTION);
			}

			finally {
				try {
					response.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		} catch (IOException e) {

			LOGGER.error("Got Error while Executing GET method {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.CONNECTION_EXCEPTION);
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

	public String doPost(String urlString, String basicAuth, String requestJson) throws AlexaApiException {

		LOGGER.info("POST operation with params: urlString {} requestJson {}", urlString, requestJson);
		CloseableHttpClient client = HttpClients.createDefault();
		try {

			HttpPost post = new HttpPost(urlString);

			post.setHeader("Authorization", basicAuth);
			post.setHeader("Content-Type", "application/json");

			EntityBuilder builder = EntityBuilder.create();
			builder.setText(requestJson);
			builder.setContentType(ContentType.APPLICATION_JSON);
			post.setEntity(builder.build());

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

			} catch (IOException e) {
				// TODO: handle exception
				LOGGER.error("Got Error while consuming api response in GET operation {}", e);
				throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.EXECUTION);
			}

			finally {
				try {
					response.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		} catch (IOException e) {

			LOGGER.error("Got Error while Executing GET method {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.CONNECTION_EXCEPTION);
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

	public String doPut(String urlString, String basicAuth, String requestJson) throws AlexaApiException {

		LOGGER.info("PUT operation with params: urlString {} requestJson {}", urlString, requestJson);
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

			} catch (IOException e) {
				// TODO: handle exception
				LOGGER.error("Got Error while consuming api response in PUT operation {}", e);
				throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.EXECUTION);
			}

			finally {
				try {
					response.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		} catch (IOException e) {

			LOGGER.error("Got Error while Executing PUT method {}", e);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.CONNECTION_EXCEPTION);
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

	private CloseableHttpClient createCustomClient() {
		final RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNTECTION_TIMEOUT_MS)
				.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT_MS).setSocketTimeout(SOCKET_TIMEOUT_MS).build();
		final CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
		return httpclient;
	}

}
