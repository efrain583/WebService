package WebService.WebService;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

public class WebServiceBase {

	private final String securedURL =  "https://data.fixer.io/api/latest?access_key=e7a8100699ac1f9e1e9e69a0e028baca";
	private final String freeURL =  "http://data.fixer.io/api/latest?access_key=e7a8100699ac1f9e1e9e69a0e028baca";  
	private final String invalidKeyURL =  "http://data.fixer.io/api/latest?access_key=baca";  
	private final String noKeyURL =  "http://data.fixer.io/api/latest";  
	private final String invalidDate =  "http://data.fixer.io/api/fatest?access_key=e7a8100699ac1f9e1e9e69a0e028baca";  

	
	HttpClient client;
	HttpGet getReq;
	HttpResponse response;
	String respStr = "";
	JSONObject jso;
	
	@BeforeTest
	public void beforeTest() {
		WSH.initTest(this.getClass().getSimpleName());
		WSH.logger.info("WebService Test Started");
		client = HttpClients.custom().build();
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		WSH.logger.info(" RESPONSE STR : " + respStr);
		jso = new JSONObject(respStr);
		WSH.logger.info(" JSON Object : " + respStr);
		
		return statusCode;
		
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
		data[0][0] = noKeyURL;
		return data;
	}
	@DataProvider
	public Object[][] invalidDateProvider(){
		Object [][] data = new Object[1][1];
		data[0][0] = invalidDate;
		return data;
	}

	
}