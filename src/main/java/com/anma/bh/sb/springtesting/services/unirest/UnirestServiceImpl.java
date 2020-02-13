package com.anma.bh.sb.springtesting.services.unirest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

public class UnirestServiceImpl implements UnirestService {

    @Override
    public HttpResponse<JsonNode> getResponse(String url) throws UnirestException {

        HttpResponse<JsonNode> response = Unirest.get(url).asJson();

        return response;

    }

    @Override
    public String getBody(String url) throws UnirestException {

        return Unirest.get(url).asJson().getBody().toString();

    }
}
