package com.jpm.backend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;


public class ApiServices {

    private final RequestSpecification httpRequest;
    public static final String URI_UNDER_TEST = "https://jsonplaceholder.typicode.com/";
    public ApiServices()
    {
        RestAssured.baseURI = URI_UNDER_TEST;
        httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json; charset=UTF-8");
    }

    public Response get(String endpoint)
    {
        return httpRequest.get(endpoint);

    }

    public Response post(String endpoint, JSONObject jsonObject)
    {

        httpRequest.body("[" +jsonObject.toString()+"]");

        return httpRequest.post(endpoint);
    }

    public Response put(String url,JSONObject jsonObject){
        httpRequest.body(jsonObject);
        return httpRequest.put(url);

    }
    public Response patch(String url, JSONObject jsonObject)
    {
        httpRequest.header("Content-Type", "application/json; charset=UTF-8");
        httpRequest.body(jsonObject);
        return httpRequest.patch(url);
    }
    public  Response delete(String url)
    {
        return httpRequest.delete(url);

    }

    public Response callValidMethod(String url, String method, JSONObject jsonObject)
    {

        switch (method)
        {
            case "put":
                        return  put(url,jsonObject);

            case "patch":
                        return  patch(url,jsonObject);

            case "delete":
                        return  delete(url);

        }
        return null;
    }

}
