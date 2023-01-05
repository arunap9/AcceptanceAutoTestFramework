package com.jpm.backend.steps;

import com.jpm.backend.ApiServices;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import  io.restassured.response.Response;
import org.apache.http.HttpException;
import org.json.JSONArray;

import java.util.Map;

import static com.jpm.backend.Utils.parseJSON;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class Usersendpoint {
    private ApiServices apiServices;
    private Response response;

    @Given("setup for users endpoint completed")
    public void setupForUsersEndpointCompleted() {
        apiServices = new ApiServices();
    }

    @Given("users endpoint {string} is hit")
    public void usersEndpointIsHit(String endpoint) throws HttpException {
        response = apiServices.get(endpoint);
    }


    @Then("Success response is returned")
    public void successResponseIsReturned() throws HttpException{
        assertThat(response.getStatusCode(),is(200));
    }

    @And("all the users details can be retrieved")
    public void allTheUsersDetailsCanBeRetrieved() throws HttpException{
        var body = response.getBody();
        JSONArray userslist = parseJSON(body.asString());
        assertTrue(userslist.length()>0);
    }

    @Given("GET method call is made for endpoint {string} for <userId>")
    public void getMethodCallIsMadeForEndpointForUserId(String endpoint, Map<String,String> params) throws HttpException {
        StringBuilder queryparam
                = new StringBuilder("?");
        for (Map.Entry<String, String> pair : params.entrySet()) {
            queryparam.append(pair.getKey());
            queryparam.append("=");
            queryparam.append(pair.getValue());
        }
        response = apiServices.get(endpoint+queryparam);
    }

    @And("all the given user details can be retrieved")
    public void allTheGivenUserDetailsCanBeRetrieved() throws HttpException{
        var body = response.getBody();
        JSONArray userdetail = parseJSON(body.asString());
        assertTrue(userdetail.length()>0);
    }
}
