package steps;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.hamcrest.core.IsNot;
import utilities.RestAssuredExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;

public class POSTProfileStep {

    public static ResponseOptions<Response> response;

    @Given("^Ensure I perform POST operation for \"([^\"]*)\" with body as$")
    public void ensureIPerformPOSTOperationForWithBodyAs(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> body = new HashMap<String, String>();
        body.put("id", data.get(1).get(0));
        body.put("title", data.get(1).get(1));
        body.put("author", data.get(1).get(2));

        RestAssuredExtension.PostOpsWithBody(url, body);
    }

    @And("^I perform a DELETE operation for \"([^\"]*)\"$")
    public void iPerformADELETEOperationFor(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("postid", data.get(1).get(0));

        RestAssuredExtension.DeleteOpsWithPathParams(url, pathParams);
    }

    @And("^I perform a GET operation with path parameter for \"([^\"]*)\"$")
    public void iPerformAGETOperationWithPathParameterFor(String url, DataTable table) throws Throwable {
        List<List<String>> data = table.raw();

        Map<String, String> pathParams = new HashMap<String, String>();
        pathParams.put("postid", data.get(1).get(0));

        response = RestAssuredExtension.GetWithPathParams(url, pathParams);
    }

    @Then("^I should not see body with title \"([^\"]*)\"$")
    public void iShouldNotSeeBodyWithTitle(String title) throws Throwable {
        assertThat(response.getBody().jsonPath().get("title"), IsNot.<Object>not(title));
    }
}
