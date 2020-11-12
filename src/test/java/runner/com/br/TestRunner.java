package runner.com.br;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"src/test/resources/features"},
		plugin = {
					"pretty",
					"html:target/cucumber-report",
					"json:target/cumcuber-report/cucumber.json"
				},
		glue = {""},
		tags = {"@cadastro"}
		)
public class TestRunner {

}
