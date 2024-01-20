package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class LocationApiStepDefinitions {

    private static final String baseURI = "https://api.zippopotam.us";
    private Response apiResponse;

    @Given("^I make a request to the location API$")
    public void makeRequestToLocationAPI() {
        RestAssured.baseURI = baseURI;
    }

    @When("^I make a GET request to the endpoint \"([^\"]*)\"$")
    public void makeGetRequestToEndpoint(String endpoint) {
        apiResponse = given().get("/" + endpoint);
    }

    @Then("^the response status code should be (\\d+)$")
    public void verifyResponseStatusCode(int expectedStatusCode) {
        assertEquals(expectedStatusCode, apiResponse.getStatusCode());
    }

    @Then("^the response should have post code \"([^\"]*)\"$")
    public void verifyPostCode(String expectedPostCode) {
        String actualPostCode = JsonPath.from(apiResponse.getBody().asString()).getString("'post code'");
        assertEquals(expectedPostCode, actualPostCode);
    }

    @Then("^the response should have country \"([^\"]*)\"$")
    public void verifyCountry(String expectedCountry) {
        String actualCountry = apiResponse.then().extract().path("country");
        assertEquals(expectedCountry, actualCountry);
    }

    @Then("^the response should have country abbreviation \"([^\"]*)\"$")
    public void verifyCountryAbbreviation(String expectedCountryAbbreviation) {
        String actualCountryAbbreviation = apiResponse.then().extract().path("'country abbreviation'");
        assertEquals(expectedCountryAbbreviation, actualCountryAbbreviation);
    }

    @Then("^the response should have place name \"([^\"]*)\"$")
    public void verifyPlaceName(String expectedPlaceName) {
        String actualPlaceName = apiResponse.then().extract().path("places[0].'place name'");
        assertEquals(expectedPlaceName, actualPlaceName);
    }

    @Then("^the response should have longitude \"([^\"]*)\"$")
    public void verifyLongitude(String expectedLongitude) {
        String actualLongitude = apiResponse.then().extract().path("places[0].longitude");
        assertEquals(expectedLongitude, actualLongitude);
    }

    @Then("^the response should have latitude \"([^\"]*)\"$")
    public void verifyLatitude(String expectedLatitude) {
        String actualLatitude = apiResponse.then().extract().path("places[0].latitude");
        assertEquals(expectedLatitude, actualLatitude);
    }

}

