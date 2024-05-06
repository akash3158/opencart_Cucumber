package StepDefinations;

import java.util.Map;

import org.junit.Assert;

import Factory.BaseClass;
import PageObjects.AccountRegistrationPage;
import PageObjects.HomePage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistartionStep {
	HomePage hp;
	AccountRegistrationPage regpage;

	@Given("user navigate to Registration Account page")
	public void user_navigate_to_registration_account_page() {
		BaseClass.getlogger().info("****** Registration Account page******** ");
		BaseClass.getlogger().info("Goto my account-->Click on registration .. ");
		hp = new HomePage(BaseClass.getDriver());
		hp.ClickOnMyAccountElement();
		hp.clickOnRegistorElement();

	}

	@When("user enter following details below")
	public void user_enter_following_details_below(DataTable dataTable)  {

		BaseClass.getlogger().info("provid all information below........ ");

		Map<String, String> dataMap = dataTable.asMap(String.class, String.class);

		regpage = new AccountRegistrationPage(BaseClass.getDriver());
		regpage.setFirstName(dataMap.get("firstName"));
		regpage.setLastName(dataMap.get("lastName"));
		regpage.setEmailID(BaseClass.randomString() + "@gmail.com");
		regpage.setTelePhoneNumber(dataMap.get("Telephone"));
		regpage.setPassword(dataMap.get("password"));
		regpage.setConfirmPassword(dataMap.get("password"));

		BaseClass.getlogger().info("successfully detials provided ");
	}

	@When("user should be select privacy policy")
	public void user_should_be_select_privacy_policy() {
		BaseClass.getlogger().info("clicked on privacy policy");
		regpage.selectCheckBox();

	}

	@When("user should be click on continue button")
	public void user_should_be_click_on_continue_button() {
		regpage=new AccountRegistrationPage(BaseClass.getDriver());
		regpage.clickOnContinue();
		BaseClass.getlogger().info("Clicked on Continue Button");
	}

	@Then("user account should get created successfully")
	public void user_account_should_get_created_successfully() throws InterruptedException {
		 Thread.sleep(5000);
		BaseClass.getlogger().info("validating Account created successfully");
		String msg = regpage.setConfirmationMsg();
		Assert.assertEquals("Your Account Has Been Created!", msg);

		BaseClass.getlogger().info("*********** finish ***********");
	}

}
