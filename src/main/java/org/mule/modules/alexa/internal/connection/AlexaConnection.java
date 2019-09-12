/**
 * (c) 2003-2017 MuleSoft, Inc. The software in this package is published under the terms of the Commercial Free Software license V.1 a copy of which has been included with this distribution in the LICENSE.md file.
 */

package org.mule.modules.alexa.internal.connection;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeoutException;

import org.mule.modules.alexa.internal.error.AlexaApiErrorType;
import org.mule.modules.alexa.internal.exceptions.AlexaApiException;
import org.mule.modules.alexa.internal.util.AlexaRequestBuilder;
import org.mule.runtime.core.api.util.IOUtils;
import org.mule.runtime.http.api.HttpConstants;
import org.mule.runtime.http.api.HttpService;
import org.mule.runtime.http.api.client.HttpClient;
import org.mule.runtime.http.api.client.HttpClientConfiguration;
import org.mule.runtime.http.api.domain.entity.ByteArrayHttpEntity;
import org.mule.runtime.http.api.domain.entity.HttpEntity;
import org.mule.runtime.http.api.domain.message.request.HttpRequest;
import org.mule.runtime.http.api.domain.message.request.HttpRequestBuilder;
import org.mule.runtime.http.api.domain.message.response.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class represents an extension connection just as example (there is no
 * real connection with anything here c:).
 */
public class AlexaConnection {
	private static final Logger LOGGER = LoggerFactory.getLogger(AlexaRequestBuilder.class);

	private String accessToken;

	private HttpClient httpClient;

	public AlexaConnection(String accessToken, HttpService httpService) {
		this.accessToken = accessToken;
		initHttpClient(httpService);
	}

	public String getAccessToken() {
		return "Bearer " + accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String sendRequest(HttpConstants.Method method, String uri, String content) {
		LOGGER.info("Sending request to Alexa with uri {} , method {}", method, uri);
		String result;
		try {
			HttpRequestBuilder builder = HttpRequest.builder();

			HttpEntity httpEntity = buildEntity(method, content);
			if (httpEntity != null) {
				builder.entity(httpEntity);
			}

			HttpResponse response = httpClient.send(
					builder.method(method).uri(uri).addHeader("Authorization", getAccessToken()).build(), 5000, true,
					null);

			result = parseResponse(response);
			LOGGER.info("RESPONSE FROM ALEXA --- > {} " , result);
		} catch (IOException e) {
			// TODO: handle exception
			LOGGER.error("Got Error  {} while consuming api response in  operation {} and uri {}", e, method, uri);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.EXECUTION);
		} catch (TimeoutException e) {
			LOGGER.error("Got Error  {} while consuming api response in  operation {} and uri {}", e, method, uri);
			throw new AlexaApiException(e.getMessage(), AlexaApiErrorType.CONNECTION_EXCEPTION);
		}
		return result;
	}

	private void initHttpClient(HttpService httpService) {
		HttpClientConfiguration.Builder builder = new HttpClientConfiguration.Builder();
		builder.setName("Alexa");
		httpClient = httpService.getClientFactory().create(builder.build());
		httpClient.start();
	}

	public void disconnect() {
		httpClient.stop();
	}

	private HttpEntity buildEntity(HttpConstants.Method method, String content) {

		switch (method) {
		case GET:
		case DELETE:
			return null;
		case PUT:
		case POST:
			return new ByteArrayHttpEntity(content.getBytes());

		default:
			return new ByteArrayHttpEntity(content.getBytes());
		}
	}

	private String parseResponse(HttpResponse response) throws IOException {

		int status = response.getStatusCode();
		HttpEntity entity = response.getEntity();
		InputStream in = entity.getContent();
		String res = IOUtils.toString(in);
		//TODO Here we get {}(empty bracket from alexa), so returning result as below
		/*if (status == 202) {
			res = "Request accepted successfully";
		}*/
		LOGGER.debug("Response from ALexa: {} status code {}", res, status);

		return res;
	}

}
