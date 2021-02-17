package com.focuscorp.Dofan_Security;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(  features = "Feature"
        ,  glue = "com.focuscorp.Dofan_Security.Steps")

//Specifying pretty as a format option ensure that HTML report will be generated.
//When we specify html:target/Destination - It will generate the HTML report

//inside the Destination folder, in the target folder of the maven project.

public class TestRunnerForUsers {
}
