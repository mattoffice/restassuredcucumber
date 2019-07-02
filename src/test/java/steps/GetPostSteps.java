package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.CoreMatchers;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class GetPostSteps {

    private static ResponseOptions<Response> response;

    @Given("^I perform GET operation for \"([^\"]*)\"$")
    public void iPerformGETOperationFor(String url) throws Throwable {

        response = RestAssuredExtension.GetOps(url);
    }


    @Then("^I should see the author name as \"([^\"]*)\"$")
    public void iShouldSeeTheAuthorNameAs(String authorName) throws Throwable {
        BDDStyledMethod.ContainsInCollection();
    }

    @Then("^I should see the author names$")
    public void iShouldSeeTheAuthorNames() {
        BDDStyledMethod.ContainsInCollection();
    }

    @Then("^I should see the GET parameter$")
    public void iShouldSeeTheGETParameter() {
        BDDStyledMethod.PerformPathParameterGet();
    }

    @Then("^I should see the GET query parameter$")
    public void iShouldSeeTheGETQueryParameter() {
        BDDStyledMethod.PerformQueryParameterGet();
    }

    @Given("^I perform POST operation for \"([^\"]*)\"$")
    public void iPerformPOSTOperationFor(String arg0) throws Throwable {
        BDDStyledMethod.PerformPOSTWithBodyParameter();
    }

    @Given("^I perform POST operation for \"([^\"]*)\" with body$")
    public void iPerformPOSTOperationForWithBody(String url, DataTable table ) throws Throwable {
        List<List<String>>data = table.raw();
        //Set body
        HashMap<String, String> body = new HashMap<String, String>();
        body.put("name", data.get(1).get(0));

        //PathParams
        HashMap<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("profileNo", data.get(1).get(1));

        //Perform post operation
         response = RestAssuredExtension.POSTOpsWithBodyAndPathParams(url, pathParams, body);
    }

    @Then("^I should see the body name as \"([^\"]*)\"$")
    public void iShouldSeeTheBodyNameAs(String name) throws Throwable {
        assertThat(response.getBody().jsonPath().get("name"), CoreMatchers.<Object>equalTo(name));
    }
}
