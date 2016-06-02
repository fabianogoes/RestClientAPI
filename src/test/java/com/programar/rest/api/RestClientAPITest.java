package com.programar.rest.api;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class RestClientAPITest {

	@Test
	public void testRequestGET() throws IOException {
		RequestEntity requestEntity = new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080, "/springrest");
		RestClientAPI client = new RestClientAPI(requestEntity);
		ResponseEntity responseEntity = client.get();

		Assert.assertEquals("[GET] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
	}

	@Test
	public void testRequestPOST() throws IOException {
		String pathVariable = "abc";
		RequestEntity requestEntity = new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080);
		requestEntity.setPathTarget("/springrest/" + pathVariable);
		RestClientAPI client = new RestClientAPI(requestEntity);
		ResponseEntity responseEntity = client.post();

		Assert.assertEquals("[POST] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
	}

	@Test
	public void testRequestPUT() throws IOException {
		String pathVariable = "abc";
		RequestEntity requestEntity = new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080);
		requestEntity.setPathTarget("/springrest/" + pathVariable);
		RestClientAPI client = new RestClientAPI(requestEntity);
		ResponseEntity responseEntity = client.post();
		Assert.assertEquals("[POST] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
		
		String pathVariableUpdateFrom = "abc";
		String pathVariableUpdateTo = "aaa-updated";
		requestEntity.setPathTarget("/springrest/" + pathVariableUpdateFrom + "/" + pathVariableUpdateTo);
		client = new RestClientAPI(requestEntity);
		responseEntity = client.put();

		Assert.assertEquals("[PUT] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
	}
	
	@Test
	public void testRequestDELETE() throws IOException {
		String pathVariable = "abc";
		RequestEntity requestEntity = new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080);
		requestEntity.setPathTarget("/springrest/" + pathVariable);
		RestClientAPI client = new RestClientAPI(requestEntity);
		ResponseEntity responseEntity = client.post();
		Assert.assertEquals("Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());

		responseEntity = client.delete();
		Assert.assertEquals("[DELETE] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
	}


	@Test
	public void testRequestGETSerializeBodyJsonToList() throws IOException {
		RequestEntity requestEntity = new RequestEntity(ProtocolType.HTTP, "93.188.161.66", 8080, "/springrest");
		RestClientAPI client = new RestClientAPI(requestEntity);
		ResponseEntity responseEntity = client.get();
		Assert.assertEquals("[GET] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
		
		 List<String> list = new Gson().fromJson(responseEntity.getHttpBody(), new TypeToken<List<String>>(){}.getType());
		 Assert.assertNotNull("List not should null", list);
		 Assert.assertTrue("list returned should be type <java.util.List>", list instanceof List<?>);
		for (String item : list) {
			System.out.println(item);
		}
	}
	
	@Test
	public void testRequestGetWithHeaders() throws IOException{
		RequestEntity requestEntity = new RequestEntity(ProtocolType.HTTP, "192.9.200.20", 8080, "/restservicetest");
		
		requestEntity.getHeaders().put("token_id", "123.123.123.123");
		requestEntity.getHeaders().put("token_client", "4444");
		RestClientAPI client = new RestClientAPI(requestEntity);
		ResponseEntity responseEntity = client.get();

		Assert.assertEquals("[GET] - Status code should be OK", HttpStatus.OK, responseEntity.getHttpStatus());
	}
	
}
