package runner_specs;

import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue ={"com.jpm.backend.steps"},
        plugin = {
                "pretty",
                "json:target/cucumber.json",
        }
)
public class CucumberRunnerIT {

}
//mvn clean verify "-Dcucumber.options=--tags @smoke --glue com.jpm.backend.steps"