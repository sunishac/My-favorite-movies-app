package com.example.chala.inclass08_schalas1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyFavoritesMovie.OnFragmentInteractionListener,AddMovie.OnFragmentInteractionListener,EditMovie.OnFragmentInteractionListener,DeleteMovie.OnFragmentInteractionListener,ShowByYear.OnFragmentInteractionListener,ShowByRating.OnFragmentInteractionListener {
    ArrayList<Movie> movies=new ArrayList<Movie>();
    CharSequence[] moviesChar;
    int index=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setActionBarTitle("My Favorite Movies");
        getFragmentManager().beginTransaction().add(R.id.activity_main,new MyFavoritesMovie(),"add frag").commit();
    }

    @Override
    public void onFragmentInteraction(String str) {
        if(str.equals("add")){
            getSupportActionBar().setTitle("Add Movie");
            getFragmentManager().beginTransaction().replace(R.id.activity_main,new AddMovie(),"add frag").commit();
            Log.d("demo","add");
        }
        else if(str.equals("edit")){
            if(movies.size()>0){
                moviesChar=new CharSequence[movies.size()];
                for(int j =0;j<movies.size();j++)
                {
                    Movie m2 = (Movie) movies.get(j);
                    moviesChar[j] =  m2.getName();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick a Movie");
                builder.setCancelable(false);

                getSupportActionBar().setTitle("Edit Movie");
                getFragmentManager().beginTransaction().replace(R.id.activity_main,new EditMovie(),"edit frag").commit();

                builder.setItems(moviesChar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        final Movie m3= (Movie) movies.get(i);
                        index = i;
                        Log.d("demo",""+m3.getName());
                        //EditMovie f=new EditMovie();
                        EditMovie f=(EditMovie) getFragmentManager().findFragmentByTag("edit frag");
                        f.setMovie(m3);

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }else{
                Toast.makeText(this,"No items in movies",Toast.LENGTH_LONG).show();
            }
        }else if(str.equals("delete")){
            if(movies.size()>0){
                Log.d("demo","de");
                moviesChar=new CharSequence[movies.size()];

                for(int j =0;j<movies.size();j++)
                {
                    Movie m3 = (Movie) movies.get(j);
                    moviesChar[j] =  m3.getName();

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Pick a Movie");
                builder.setCancelable(false);
                builder.setItems(moviesChar, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Movie m1 = (Movie) movies.get(i);
                        String movieName = m1.getName();
                        movies.remove(i);
                        Toast.makeText(MainActivity.this,"Movie with movie name " +movieName + "is deleted",Toast.LENGTH_LONG).show();

                    }
                });

                AlertDialog alert = builder.create();
                alert.show();

            }else{
                Toast.makeText(this,"No items in movies",Toast.LENGTH_LONG).show();
            }
        }else if(str.equals("year")){
            if(movies.size()>0){
                ShowByYear f=new ShowByYear();
                f.setYear(movies);
                getSupportActionBar().setTitle("Movies By Year");
                getFragmentManager().beginTransaction().replace(R.id.activity_main,new ShowByYear(),"year frag").commit();
                Log.d("demo","year");
            }else{
                Toast.makeText(MainActivity.this,"No movies to display",Toast.LENGTH_LONG).show();
            }

        }else if(str.equals("rating")){
            if(movies.size()>0){
                ShowByRating f=new ShowByRating();
                f.setRating(movies);
                getSupportActionBar().setTitle("Movies By Rating");
                getFragmentManager().beginTransaction().replace(R.id.activity_main,new ShowByRating(),"rating frag").commit();
                Log.d("demo","rating");
            }else{
                Toast.makeText(MainActivity.this,"No movies to display",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void ryfinish() {
        getFragmentManager().beginTransaction().replace(R.id.activity_main,new MyFavoritesMovie(),"rating frag").commit();
    }

    @Override
    public void editchanges(Movie m) {
        movies.remove(index);
        movies.add(m);
        getFragmentManager().beginTransaction().replace(R.id.activity_main,new MyFavoritesMovie(),"add frag").commit();
    }

    @Override
    public void addmoviemethod(Movie m) {
        movies.add(m);
        getFragmentManager().beginTransaction().replace(R.id.activity_main,new MyFavoritesMovie(),"add frag").commit();
    }

    /*public interface toedit(){

    }*/

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }
}
