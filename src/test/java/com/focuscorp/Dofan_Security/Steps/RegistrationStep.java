package com.focuscorp.Dofan_Security.Steps;
import io.cucumber.java.en.*;

public class RegistrationStep {


    @Given("user on the homepage")
    public void user_on_the_homepage() {
        // Write code here that turns the phrase above into concrete actions
    }



    @Given("user clicks on {string}")
    public void user_clicks_on(String string) {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("user clicks on create new account")
    public void user_clicks_on_create_new_account() {
        // Write code here that turns the phrase above into concrete actions
    }
    @When("user enters the following details")
    public void user_enters_the_following_details(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
    }

    @Then("the account is created successfully")
    public void the_account_is_created_successfully() {
        // Write code here that turns the phrase above into concrete actions
    }








    @Then("a tooltip is displayed {string}")
    public void a_tooltip_is_displayed(String string) {
        // Write code here that turns the phrase above into concrete actions
    }








    @When("user enters wrong characters")
    public void user_enters_wrong_characters() {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("error message is displayed")
    public void error_message_is_displayed() {
        // Write code here that turns the phrase above into concrete actions
    }







    @When("user enters blank details for Register")
    public void user_enters_blank_details_for_register() {
        // Write code here that turns the phrase above into concrete actions
    }






    @Then("the user will be redirected to Dofan registration")
    public void the_user_will_be_redirected_to_dofan_registration() {
        // Write code here that turns the phrase above into concrete actions
    }

    @When("clicks on register button")
    public void clicks_on_register_button() {
        // Write code here that turns the phrase above into concrete actions
    }







}
