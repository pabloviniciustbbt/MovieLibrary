package com.pabloleal.movielibrary.services;

import com.pabloleal.movielibrary.exceptions.MovieException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiServices {

    public String textFormatting(String movieInput) {
        return movieInput.replaceAll(" ", "+");
    }

    public String movieRequest(String movieInput){
        String responseBody;

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://www.omdbapi.com/?t="+ movieInput +"&apikey=e2ed9928")).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();

            if(responseBody.contains("Movie not found!")){
                throw new MovieException("\nStatus Code: 404 - O Filme consultado não foi encontrado");
            }
        } catch (MovieException e){
            System.out.println(e.getMessage());
            return null;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
        return responseBody;
    }

}