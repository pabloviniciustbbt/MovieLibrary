package com.pabloleal.movielibrary.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pabloleal.movielibrary.models.Movie;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonServices {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public Movie jsonConverter(String responseBody){
        return gson.fromJson(responseBody, Movie.class);
    }

    //JsonWriter - método que irá escrever em json os filmes que eu quero assistir e os que eu já assisti
    public void jsonWriter(List<Movie> movieList, String filename){
        try {
            FileWriter writer = new FileWriter(filename + ".json");
            writer.write(gson.toJson(movieList));
            writer.close();
            System.out.println("\n===============================================");
            System.out.println("     Lista " + filename +" salva com Sucesso!");
            System.out.println("===============================================");
        } catch (IOException e) {
            throw new RuntimeException("\nErro ao escrever o arquivo JSON: " + e.getMessage());
        }

    }
}
