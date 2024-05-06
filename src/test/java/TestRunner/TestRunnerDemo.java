package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(//features = { ".//Features/LoginTest.feature"},
					//features = { ".//Features/Registration.feature"},
					features= {".//Features/LoginTest.feature",".//Features/Registration.feature"},
								//features= {"@target/rerun.txt"},
								glue = "StepDefinations",
								plugin= {"pretty","html:Reports/myreports.html",
										"rerun:target/rerun.txt",
										"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:Reports/extentreports.html"
										},
								dryRun = false,
								monochrome = true,
								publish = true
								//tags = @sanity,
								//tags= @regression,
								//tags=@sanity @regression,
								//tags=@sanity not @regression
								)
public class TestRunnerDemo {

}
