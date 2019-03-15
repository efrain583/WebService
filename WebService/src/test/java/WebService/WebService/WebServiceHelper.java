package WebService.WebService;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

public class WebServiceHelper {
	
	public static Logger logger = null;
	
	public static void initTest(String suiteName) {
		
		logger = Logger.getLogger(suiteName);
//		PropertyConfigurator.configure("C:\\Users\\efrain583\\dev\\WebService\\src\\test\\java\\WebService\\WebService\\log4j.properties");
		Logger.getRootLogger().getLoggerRepository().resetConfiguration();
		Logger rLogger = Logger.getRootLogger();
		rLogger.setLevel(Level.DEBUG);
		PatternLayout genericPatternLayout = new PatternLayout("%t %d{yyyy-MM-dd HH:mm:ss.SSS} %-5p %c %x - %m%n");

		ConsoleAppender consoleAppender = new ConsoleAppender();
		consoleAppender.setLayout(genericPatternLayout);
		
		FileAppender  fileAppender = new FileAppender();
		fileAppender.setFile("WebService.log");
		fileAppender.setLayout(genericPatternLayout);
		
		rLogger.addAppender(consoleAppender);
		rLogger.addAppender(fileAppender);
		
		
	}

}
