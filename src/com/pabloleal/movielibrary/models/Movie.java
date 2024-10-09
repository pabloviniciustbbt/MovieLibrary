package com.pabloleal.movielibrary.models;

import com.google.gson.annotations.SerializedName;

public class Movie {

    @SerializedName("Title")
    private String titulo;
    @SerializedName("Director")
    private String diretor;
    @SerializedName("Year")
    private int anoLancamento;
    @SerializedName("Plot")
    private String sinopse;
    @SerializedName("Runtime")
    private String tempoDuracao;

    @Override
    public String toString() {
        return  "\nTitulo: " + titulo +
                "\nDiretor: " + diretor +
                "\nAno de Lancamento: " + anoLancamento +
                "\nSinopse: " + sinopse +
                "\nTempo de Duracao: " + tempoDuracao;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }

        if (obj == null || getClass() != obj.getClass()){
            return false;
        }

        Movie movie = (Movie) obj;
        return titulo.equals(movie.titulo)
                && diretor.equals(movie.diretor)
                && anoLancamento == movie.anoLancamento;
    }


}
