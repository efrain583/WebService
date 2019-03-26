package WebService.WebService;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.spi.LoggerRepository;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class WebServiceBase {

	private final String securedURL =  "https://data.fixer.io/api/latest?access_key=e7a8100699ac1f9e1e9e69a0e028baca";
	private final String freeURL =  "http://data.fixer.io/api/latest?access_key=e7a8100699ac1f9e1e9e69a0e028baca";  
	private final String invalidKeyURL =  "http://data.fixer.io/api/latest?access_key=baca";  
	private final String noKeyURL =  "http://data.fixer.io/api/latest";  
	private final String invalidDate =  "http://data.fixer.io/api/fatest?access_key=e7a8100699ac1f9e1e9e69a0e028baca";  
	
	private final String postUrl = "https://reqres.in/api/users";

	
	CloseableHttpClient client;
	HttpGet getReq;
	HttpPost postReq;
	HttpResponse response;
	String respStr = "";
	JSONObject jso;
	
	@BeforeClass
	public void beforeClass() {
		WSH.initTest(this.getClass().getSimpleName());
		WSH.logger.info("WebService Test Started");
		client = HttpClients.custom().build();
	}
	@AfterClass
	public void afterClass() throws IOException{
		client.close();
	}
	
	
	public int sendGetRequest(String url){
		getReq = new HttpGet(url);
		int statusCode = 0;
		try {
			response = client.execute(getReq);
			
		statusCode = response.getStatusLine().getStatusCode();	
		WSH.logger.info(" Status Code : " + statusCode);
			
		respStr = EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		WSH.logger.info(" RESPONSE STR : " + respStr);
		jso = new JSONObject(respStr);
		WSH.logger.info(" JSON Object : " + respStr);
		
		return statusCode;
		
	}
	
	public  int sendPostRequest(String url, String payLoad, HashMap <String, String> headersMap){
		
		postReq = new HttpPost(url);
		
		// TODO lambda expression Test
//		headersMap.forEach((key,value) -> postReq.addHeader(key, value));

		for(Map.Entry<String, String> currentHeader : headersMap.entrySet()){
			
//		postReq.addHeader("Content-Type", "application/json");
			postReq.addHeader(currentHeader.getKey(), currentHeader.getValue());
			
		}
		postReq.addHeader("Content-Type", "application/json");

		HttpEntity entity;

		int statusCode = 0;
		String contentStr = "";
		
		try {
			entity = new StringEntity(payLoad);
			postReq.setEntity(entity);
			response = client.execute(postReq);
			statusCode = response.getStatusLine().getStatusCode();
			contentStr = EntityUtils.toString(response.getEntity());
			jso = new JSONObject(contentStr);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		WSH.logger.info("POST STATUS : " + statusCode + " POST REQUEST RESPONSE : " + contentStr);
		jsonParseAndPrintPostResponse();
		return statusCode;
		
	}
	
	private void jsonParseAndPrintPostResponse() {
		WSH.logger.info("created At=" + jso.getString("createdAt") + " id=" +jso.getString("id") + " job=" + jso.getString("job") + " name=" + jso.getString("name"));
	}

	public String  parseResponseName() {
		return WSH.jsonParseString(jso, "name");
	}

	public int  parseResponseId() {
		return Integer.parseInt(WSH.jsonParseString(jso, "id"));
	}

	public boolean  getSuccessStatus() {

		return WSH.jsonParseSuccess(jso);
	}

	@DataProvider
	public Object[][] securedUrlProvider(){
		Object [][] data = new Object[1][1];
		data[0][0] = securedURL;
		return data;
	}
	
	@DataProvider
	public Object[][] freeUrlProvider(){
		Object [][] data = new Object[1][1];
		data[0][0] = freeURL;
		return data;
	}
	@DataProvider
	public Object[][] noKeyProvider(){
		Object [][] data = new Object[1][1];
		data[0][0] = noKeyURL;
		return data;
	}
	@DataProvider
	public Object[][] invalidKeyProvider(){
		Object [][] data = new Object[1][1];
		data[0][0] = invalidKeyURL;
		return data;
	}
	@DataProvider
	public Object[][] invalidDateProvider(){
		Object [][] data = new Object[1][1];
		data[0][0] = invalidDate;
		return data;
	}
	@DataProvider
	public Object[][] postDataProvider(){

		
		Map<String, String> postHeader = new HashMap<String, String>();
		postHeader.put("Content-Type", "application/json");

		JsonUser jsonUser = new JsonUser("Efrain", "Tester");
		ObjectMapper jasonObject = new ObjectMapper();

		String jsonString = "";
		
		try {
			jasonObject.writeValue(new File(System.getProperty("user.dir") + "/" + "user.json"), jsonUser);
			jsonString = jasonObject.writeValueAsString(jsonUser);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Object [][] data =new Object[1][3];
		data[0][0] = postUrl;
		data[0][1] = postHeader;
		data[0][2] = jsonString;
		
		return data;
		
		
	}

	
}