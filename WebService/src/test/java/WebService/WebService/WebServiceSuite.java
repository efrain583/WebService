package WebService.WebService;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WebServiceSuite extends WebServiceBase{

	@Test(priority = 4, dataProvider = "securedUrlProvider")
	public void TC_ValidateSecureURL(String secureUrl) {
	/*
	 * Will intentionally Fail	
	 */
		Assert.assertEquals(sendGetRequest(secureUrl), 200, "Secure URL  - Status Code Verification");
		Assert.assertEquals(getSuccessStatus(), true, "Secure URL - Success Status Verification");
	}

	@Test(priority = 2, dataProvider = "freeUrlProvider")
	public void TC_ValidateFreeURL(String freeUrl) {
		
		Assert.assertEquals(sendGetRequest(freeUrl), 200, "Free URL  - Status Code Verification");
		Assert.assertEquals(getSuccessStatus(), true, "Free URL - Success Status Verification");
	}

	@Test(priority = 3, dataProvider = "noKeyProvider")
	public void TC__ValidateNoKey(String noKeyUrl) {
		
		sendGetRequest(noKeyUrl);
		Assert.assertEquals(sendGetRequest(noKeyUrl), 200, "No Key  - Status Code Verification");
		Assert.assertEquals(getSuccessStatus(), false, "No Key - Success Status Verification");
	}

	@Test(priority = 1, dataProvider = "invalidKeyProvider")
	public void TC_ValidateInvalidKey(String invalidKeyUrl) {
		
		Assert.assertEquals(sendGetRequest(invalidKeyUrl), 200, "Invalid Key  - Status Code Verification");
		Assert.assertEquals(getSuccessStatus(), false, "Invalid Key - Success Status Verification");
	}

	@Test(priority = 5, dataProvider = "invalidDateProvider")
	public void TC_ValidateInvalidDate(String invalidDate) {
		
		Assert.assertEquals(sendGetRequest(invalidDate), 200, "Invalid Date - Status Code Verification");
		Assert.assertEquals(getSuccessStatus(), false, "Invalid Date - Success Status Verification");
	}
}
