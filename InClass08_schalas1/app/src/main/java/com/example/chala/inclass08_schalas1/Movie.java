package com.example.chala.inclass08_schalas1;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by chala on 3/13/2017.
 */

public class Movie implements Serializable/*,Comparable*/
{
    String name,description,genre,rating,year,imdb;




    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Movie(String name, String description, String genre, String rating, String year, String imdb) {
        this.name = name;

        this.description = description;
        this.genre = genre;
        this.rating = rating;
        this.year = year;
        this.imdb = imdb;
    }

    public static Comparator<Movie> sortByYear = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {

            return (Integer.parseInt(m1.getYear())-Integer.parseInt(m2.getYear()));
        }
    };

    public static Comparator<Movie> sortByRating = new Comparator<Movie>() {
        @Override
        public int compare(Movie m1, Movie m2) {
            return (Integer.parseInt(m2.getRating())-Integer.parseInt(m1.getRating()));
        }
    };


    /*@Override
    public int compareTo(Object o)
    {
        Movie m1 = (Movie)o;
        return (Integer.parseInt(this.getYear())-Integer.parseInt(m1.getYear()));
    }*/
}

