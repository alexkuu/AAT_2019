package cucumberTests.stepdefs;

import com.codeborne.selenide.Condition;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import service.ui.DriverManager;

import java.util.ArrayList;

import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static service.assertions.Assertion.assertEquals;

public class StepDefinitions {
    @Given("^I am on the \"([^\"]*)\" page on URL \"([^\"]*)\"$")
    public void i_am_on_the_page_on_URL(String title, String url) {
        open(url);
        assertEquals(DriverManager.get().getTitle(), title);
    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String type, String value) {
        $(byAttribute("type", type)).setValue(value);
    }

    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String buttonText) {
        $(byText(buttonText)).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String msg) {
        $(byText(msg)).should(Condition.visible);
    }

    @Then("^I should see the \"([^\"]*)\" button$")
    public void i_should_see_the_button(String arg1) throws Throwable {
        String z = "a";
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @When("^I fill text field \"([^\"]*)\" with \"([^\"]*)\"$")
    public void iFillTextFieldWith(String id, String value) {
        $("#" + id).shouldBe(Condition.visible).setValue(value);
    }

    @When("^I fill text field \"([^\"]*)\" with \"([^\"]*)\" and hit enter$")
    public void iFillTextFieldWithAndHitEnter(String arg0, String arg1) throws Throwable {
        $("#" + arg0).shouldBe(Condition.visible).setValue(arg1).pressEnter();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^I click on the \"([^\"]*)\" link$")
    public void iClickOnTheLink(String arg0) throws Throwable {
        $(byText(arg0)).shouldBe(Condition.visible).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("^I switch to the last tab$")
    public void iSwitchToTheLastTab() {
        ArrayList<String> tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
        DriverManager.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }

    @Then("^I close last tab$")
    public void iCloseLastTab() {
        ArrayList<String> tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
        DriverManager.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
        DriverManager.getDriver().close();
        tabs = new ArrayList<String>(DriverManager.getDriver().getWindowHandles());
        DriverManager.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }
}