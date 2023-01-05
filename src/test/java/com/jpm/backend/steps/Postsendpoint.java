package com.jpm.backend.steps;

import com.jpm.backend.ApiServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.apache.http.HttpException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.jpm.backend.Utils.parseJSON;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Postsendpoint {
    private ApiServices  apiServices;
    private Response response;

    @Given("setup for posts endpoint completed")
    public void setupForPostsEndpointCompleted() throws HttpException{
           apiServices = new ApiServices();

    }

    @Given("GET call for endpoint {string} is made")
    public void postEndpointIsHit(String endpoint)  throws HttpException
    {
        response = apiServices.get(endpoint);

    }

    @Then("post Success response is returned")
    public void postSuccessResponseIsReturned() throws HttpException{
        assertThat(response.getStatusCode(),isOneOf(200,201));
    }


    @Given("a user POST using endpoint {string}")
    public void aUserMakesA(String endpoint,Map<String,String> params)  throws HttpException{
         response=apiServices.post(endpoint,new JSONObject(new HashMap<>(params)));
    }

    @Then("post unSuccess response is returned")
    public void postUnSuccessResponseIsReturned() throws HttpException{
        assertNotEquals("post expected to fail",response.getStatusCode(),is(201));
    }

    @Given("a user calls endpoint {string} using method {string} for <userId>")
    public void aUserMakesAForUserId(String endpoint,String method, Map<String,String> params)  throws HttpException{
        String Idvalue="";
             for (Map.Entry<String, String> pair : params.entrySet()) {
                if (pair.getKey().compareToIgnoreCase("userId") == 0)
                    Idvalue = pair.getValue();
            }

        response = apiServices.callValidMethod(endpoint+"/"+Idvalue,method,new JSONObject(new HashMap<>(params)));

    }

    @Given("administrator wants to retrieve {string} for given user")
    public void administratorWantsToRetrieveForGivenUser(String endpoint, Map<String,String> params) throws HttpException{

        StringBuilder queryparam
                = new StringBuilder("?");
        for (Map.Entry<String, String> pair : params.entrySet()) {
             queryparam.append(pair.getKey());
             queryparam.append("=");
             queryparam.append(pair.getValue());
        }
        response = apiServices.get(endpoint+queryparam);
    }

    @And("all the given user posts are retrieved")
    public void allTheGivenUserPostsAreRetrieved() throws HttpException{

        var body = response.getBody();
        JSONArray userposts = parseJSON(body.asString());
        assertTrue(userposts.length()>0);
    }

    @And("all the posts are retrieved")
    public void allThePostsAreRetrieved() throws HttpException{
        var body = response.getBody();
        JSONArray userposts = parseJSON(body.asString());
        assertTrue(userposts.length()>0);
    }
}
