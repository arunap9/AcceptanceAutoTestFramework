package com.jpm.backend.steps;

import com.jpm.backend.ApiServices;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.response.Response;
import org.apache.http.HttpException;
import org.json.JSONArray;

import java.util.Map;

import static com.jpm.backend.Utils.parseJSON;
import static org.hamcrest.Matchers.isOneOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Commentsendpoint {
    private ApiServices  apiServices;
    private Response response;
    @Given("setup for comments endpoint completed")
    public void setupForCommentsEndpointCompleted() {
        apiServices = new ApiServices();
    }

    @Given("endpoint {string} is hit")
    public void endpointIsHit(String endpoint) throws HttpException {
        response = apiServices.get(endpoint);
    }

    @Then("Comments Success response is returned")
    public void commentsSuccessResponseIsReturned() throws HttpException{
        assertThat(response.getStatusCode(),isOneOf(200,201));
    }

    @Given("nested call for {string} endpoint for {string} is made")
    public void nestedCallForEndpointForIsMade(String endpoint, String nestendpoint, Map<String,String> params) throws HttpException{
        String postid="";
        for (Map.Entry<String, String> pair : params.entrySet()) {
             postid= pair.getValue();
        }

        response = apiServices.get(endpoint+"/"+postid+nestendpoint);
    }

    @And("all the comments on the post can be retrieved")
    public void allTheCommentsOnThePostCanBeRetrieved() {
        var body = response.getBody();
        JSONArray postcomments = parseJSON(body.asString());
        assertTrue(postcomments.length()>0);
    }

    @And("all the comments on all posts can be retrieved")
    public void allTheCommentsOnAllPostsCanBeRetrieved() {
        var body = response.getBody();
        JSONArray postscomments = parseJSON(body.asString());
        assertTrue(postscomments.length()>0);
    }
}
