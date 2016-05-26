package com.programar.rest.api;

import java.io.IOException;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client API Rest
 * 
 * @author Fabiano Góes - 26/05/2016
 * @category REST
 * @version 1.0
 *
 */
public class RestClientAPI {

	private static final Logger logger = LoggerFactory.getLogger(RestClientAPI.class);
	
	private RequestEntity restEntity;
	private HttpClient httpclient;
	private HttpHost hostTarget;

	private ResponseEntity restEntityResponse = new ResponseEntity();
	private HttpResponse httpResponse;

	public RestClientAPI(RequestEntity requestEntity) {
		this.restEntity = requestEntity;
	}

	/**
	 * 
	 * @param method - example RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE 
	 * @return ResponseEntity
	 * @throws IOException
	 */
	private ResponseEntity executeRequest(RequestMethod method) throws IOException {
		logger.info( "executeRequest( "+method.name()+" )" );
		
		this.httpclient = HttpClientBuilder.create().build();
		this.hostTarget = new HttpHost(this.restEntity.getHostServer(), this.restEntity.getHostPort());
		try {
			HttpRequestBase httpRequest;
			switch (method) {
			case GET:
				httpRequest = new HttpGet(this.restEntity.getPathTarget());
				break;
			case POST:
				httpRequest = new HttpPost(this.restEntity.getPathTarget());
				break;
			case PUT:
				httpRequest = new HttpPut(this.restEntity.getPathTarget());
				break;
			case DELETE:
				httpRequest = new HttpDelete(this.restEntity.getPathTarget());
				break;
			default:
				httpRequest = new HttpGet(this.restEntity.getPathTarget());
				break;
			}

			this.httpResponse = this.httpclient.execute(this.hostTarget, httpRequest);
			this.restEntityResponse
					.setHttpStatus(HttpStatus.valueOf(this.httpResponse.getStatusLine().getStatusCode()));
			this.restEntityResponse.setHeaders(this.httpResponse.getAllHeaders());
			if (this.httpResponse.getEntity() != null) {
				this.restEntityResponse.setHttpBody(EntityUtils.toString(this.httpResponse.getEntity()));
			}
		} catch (Exception e) {
			logger.error( e.getMessage() );
			this.restEntityResponse.setExceptionMessage(e.getMessage());
		} finally {
			CloseableHttpClient http = HttpClientBuilder.create().setProxy(this.hostTarget).build();
			http.close();
			logger.info( "http closed" );
		}
		return this.restEntityResponse;
	}

	/**
	 * Example:
	 *	RestClientAPI client = new RestClientAPI( new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080, "/springrest") );
	 *	ResponseEntity responseEntity = client.get();
	 * @return ResponseEntity
	 * @throws IOException
	 */
	public ResponseEntity get() throws IOException {
		return this.executeRequest(RequestMethod.GET);
	}

	/**
	 * Example:
	 *  String pathVariable = "/abc";
	 *	RestClientAPI client = new RestClientAPI( new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080, "/springrest"+pathVariable) );
	 *	ResponseEntity responseEntity = client.post();
	 * @return ResponseEntity
	 * @throws IOException
	 */
	public ResponseEntity post() throws IOException {
		return this.executeRequest(RequestMethod.POST);
	}

	/**
	 * Example:
	 *  String pathVariableUpdateFrom = "/abc";
	 *  String pathVariableUpdateTo = "/abc";
	 *  String pathTarget = "/springrest"+pathVariableUpdateFrom + "/" + pathVariableUpdateTo;
	 *	RestClientAPI client = new RestClientAPI( new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080, pathTarget) );
	 *	ResponseEntity responseEntity = client.put();
	 * @return ResponseEntity
	 * @throws IOException
	 */
	public ResponseEntity put() throws IOException {
		return this.executeRequest(RequestMethod.PUT);
	}

	/**
	 * Example:
	 *  String pathVariable = "/abc";
	 *	RestClientAPI client = new RestClientAPI( new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080, "/springrest"+pathVariable) );
	 *	ResponseEntity responseEntity = client.delete();
	 * @return ResponseEntity
	 * @throws IOException
	 */
	public ResponseEntity delete() throws IOException {
		return this.executeRequest(RequestMethod.DELETE);
	}

}
