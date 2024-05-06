package StepDefinations;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import Factory.BaseClass;
import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import Utility.DataReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	WebDriver driver;
	HomePage hp;
	LoginPage logintest;
	MyAccountPage map;

    List<HashMap<String, String>> datamap ;

	@Given("user should be on home page")
	public void user_should_be_on_home_page() {
		BaseClass.getlogger().info("****** Login Account page******** ");
		BaseClass.getlogger().info("Goto my account-->Click on Login.. ");

		hp = new HomePage(BaseClass.getDriver());
		hp.ClickOnMyAccountElement();
		hp.clickOnLoginLink();

	}

	@Given("User navigate to  the Login Page")
	public void user_navigate_to_the_login_page() {
		BaseClass.getlogger().info("validate Login Account ");

		String act_Title = BaseClass.getDriver().getTitle();
		String exp_Title = "Account Login";

		Assert.assertEquals(act_Title, exp_Title);

	}

	@When("user should enter the Email as {string} and password as {string}")
	public void user_should_enter_the_email_as_and_password_as_test(String email, String pwd) throws IOException {
		BaseClass.getlogger().info("provide email and password here ..... ");
		logintest = new LoginPage(BaseClass.getDriver());
		logintest.setEmailId(BaseClass.getProp().getProperty("email"));
		logintest.setPassword(BaseClass.getProp().getProperty("password"));
		BaseClass.getlogger().info("Successfully filled email and password ");
	}

	@When("user should be click login button")
	public void user_should_be_click_login_button() {
		
		BaseClass.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logintest.clickOnLoginBtn();
		
		BaseClass.getlogger().info(" clicked on login button");
	}

	@Then("user should be redirect to MyAccount page")
	public void user_should_be_redirect_to_my_account_page() {
		
		BaseClass.getlogger().info(" validating MyAccount page");
		
		 map = new MyAccountPage(BaseClass.getDriver());
		boolean status = map.myaccountTxt();

		Assert.assertEquals(true, status);
	}

	//  Testing testCase using Scenario outline 

	@Given("the user navigates to login page")
	public void the_user_navigates_to_login_page() {
		BaseClass.getlogger().info("this is Scenario Outline.... ");
		hp = new HomePage(BaseClass.getDriver());
		hp.ClickOnMyAccountElement();
		hp.clickOnLoginLink();
		BaseClass.getlogger().info(" user is on Login page");
	}

	@When("user enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String pwd) {
		BaseClass.getlogger().info(" provide email and password here");
		logintest = new LoginPage(BaseClass.getDriver());
		logintest.setEmailId(email);
		logintest.setPassword(pwd);
		
		BaseClass.getlogger().info(" sucessfully provided...");
	}

	@When("the user clicks on the Login button")
	public void the_user_clicks_on_the_login_button() {
		logintest.clickOnLoginBtn();
		BaseClass.getlogger().info("clicked on login button ");
	}

	@Then("the user should be redirected to the MyAccount Page")
	public void the_user_should_be_redirected_to_the_my_account_page() {
		BaseClass.getlogger().info("Validating My Account Page here.... ");
		 map = new MyAccountPage(BaseClass.getDriver());
		boolean status = map.myaccountTxt();
		Assert.assertEquals(true, status);
	}
	
	
	// Data Driver Test Case........................................
	@Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
	public void the_user_should_be_redirected_to_the_my_account_page_by_passing_email_and_password_with_excel_row(String rows) {
		BaseClass.getlogger().info("****** Data Driver Test Case******** ");
		datamap=DataReader.data(System.getProperty("user.dir")+"\\TestData\\Opencart_LoginData.xlsx", "Sheet1");

        int index=Integer.parseInt(rows)-1;
        String email= datamap.get(index).get("username");
        String pwd= datamap.get(index).get("password");
        String exp_res= datamap.get(index).get("res");

        logintest=new LoginPage(BaseClass.getDriver());
        logintest.setEmailId(email);
        logintest.setPassword(pwd);

        logintest.clickOnLoginBtn();
        map=new MyAccountPage(BaseClass.getDriver());
        try
        {
            boolean targetpage=map.myaccountTxt();
            System.out.println("target page: "+ targetpage);
            if(exp_res.equals("Valid"))
            {
                if(targetpage==true)
                {
                    MyAccountPage myaccpage=new MyAccountPage(BaseClass.getDriver());
                    myaccpage.clickOnLogOutBtn();
                    Assert.assertTrue(true);
                }
                else
                {
                    Assert.assertTrue(false);
                }
            }

            if(exp_res.equals("Invalid"))
            {
                if(targetpage==true)
                {
                    map.clickOnLogOutBtn();
                    Assert.assertTrue(false);          

                }
                else
                {
                    Assert.assertTrue(true);
                }
            }


        }
        catch(Exception e)
        {

            Assert.assertTrue(false);
        }
        BaseClass.getlogger().info("******   Finish    ******* ");
      }
	
	
	
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

