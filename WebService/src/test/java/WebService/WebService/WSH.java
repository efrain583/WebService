package WebService.WebService;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

// Web Service Helper
public class WSH {
	
	public static Logger logger = null;
	
	public static void initTest(String suiteName) {
		
		//System.setProperty("com.jcabi.log.coloring", "true");
		logger = Logger.getLogger(suiteName);
		PropertyConfigurator.configure("C:\\Users\\efrain583\\dev\\WebService\\src\\test\\java\\WebService\\WebService\\log4j.properties");
	}
	
	static public boolean jsonParseSuccess(JSONObject jso) {
		boolean succStatus = jso.getBoolean("success");
		WSH.logger.info("Success : " + succStatus);
		return succStatus;
	} 

}
