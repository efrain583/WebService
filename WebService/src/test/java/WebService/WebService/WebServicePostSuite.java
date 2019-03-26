package WebService.WebService;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServicePostSuite extends WebServiceBase{
	
	@Test(enabled=true, priority=1, dataProvider = "postDataProvider")
	public void TC_BasicPost(String postURL, HashMap<String, String> headers, String jsonString){

		System.out.println("URL : " + postURL);
		for(Map.Entry<String, String> currSet : headers.entrySet()){
			System.out.println("Current Set : " + currSet.getKey() + ", " + currSet.getValue());
		}
		System.out.println("json : " + jsonString);
		
		Assert.assertEquals(sendPostRequest(postURL, jsonString, headers), 201,"Send Post Request Verification");
		
		Assert.assertEquals(parseResponseName(), "Efrain", "Post Response Name Verification");
		Assert.assertTrue(parseResponseId() > 0, "Post Response Id > 0 Verification");

	}

}
