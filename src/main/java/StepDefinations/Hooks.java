package StepDefinations;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Factory.BaseClass;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

	WebDriver driver;
	Properties p;

	@Before
	public void setUp() throws IOException {
		driver = BaseClass.launchBrowser();
		p = BaseClass.getProp();
		driver.get(p.getProperty("baseUrl"));
		driver.manage().window().maximize();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@AfterStep
	public void addScreenshot(Scenario scenario) {

		// this cucumber junit report
		if (scenario.isFailed()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			byte[] screenshots = ts.getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshots, "image/png", scenario.getName());
		}
	}

}
