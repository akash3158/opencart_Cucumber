package Factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	public static WebDriver driver;
	public static Logger logger;
	public static Properties p;

	public static WebDriver launchBrowser() throws IOException {

		if (getProp().getProperty("execution_env").equalsIgnoreCase("remote")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			if (getProp().getProperty("os").equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN10);
			} else if (getProp().getProperty("os").equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}

			switch (getProp().getProperty("browser").toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("Chrome");
				break;

			case "edge":
				capabilities.setBrowserName("MicroSoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("Firefox");
				break;

			default:
				System.out.println("No Matching Browser");
				break;
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		} else if (getProp().getProperty("execution_env").equalsIgnoreCase("local")) {
			switch (getProp().getProperty("browser").toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;

			case "edge":
				driver = new EdgeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;

			default:
				System.out.println("No Matching Browser");
				break;
			}
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;
	}

	public static WebDriver getDriver() {
		return driver;

	}

	public static Properties getProp() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\main\\resources\\Config.properties");

		p = new Properties();
		p.load(file);
		return p;

	}

	public static Logger getlogger() {
		logger = LogManager.getLogger();
		return logger;
	}

	public static String randomString() {
		String ramdomString = RandomStringUtils.randomAlphabetic(5);
		return ramdomString;
	}

	public static String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public static String randomAlphaNumeric() {

		String str = RandomStringUtils.randomAlphabetic(3);
		String num = RandomStringUtils.randomNumeric(3);

		return (str + "@" + num);
	}

}
