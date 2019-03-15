package WebService.WebService;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

// Web Service Helper
public class WSH {
	
	public static Logger logger = null;
	
	public static void initTest(String suiteName) {
		
		//System.setProperty("com.jcabi.log.coloring", "true");
//		PropertyConfigurator.configure("C:\\Users\\efrain583\\dev\\WebService\\src\\test\\java\\WebService\\WebService\\log4j.properties");
		logger = Logger.getLogger(suiteName);
		Logger.getRootLogger().getLoggerRepository().resetConfiguration();
		Logger rLogger = Logger.getRootLogger();
		rLogger.setLevel(Level.DEBUG);
		PatternLayout genericPatternLayout = new PatternLayout("%t %d{yyyy-MM-dd HH:mm:ss.SSS} %-5p %c %x - %m%n");

		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(genericPatternLayout);
		consoleAppender.activateOptions();
		
		FileAppender  fileAppender = new FileAppender();
		fileAppender.setName("FileLogger");
		fileAppender.setFile("WebService.log");
		fileAppender.setLayout(genericPatternLayout);
		fileAppender.activateOptions();
		
		rLogger.addAppender(consoleAppender);
		rLogger.addAppender(fileAppender);
	}
	
	static public boolean jsonParseSuccess(JSONObject jso) {
		boolean succStatus = jso.getBoolean("success");
		WSH.logger.info("Success : " + succStatus);
		return succStatus;
	} 

}
