package com.example.chala.inclass08_schalas1;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;


public class ShowByRating extends Fragment {

    TextView title, description, genre, rating, year, imdb;
    Button finish ;
    ImageView first,last,previous,next;
    static ArrayList<Movie> myAllMovies ;
    int index = 0;
    int j = 5;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShowByRating() {
        // Required empty public constructor
    }


    public static ShowByRating newInstance(String param1, String param2) {
        ShowByRating fragment = new ShowByRating();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_by_rating, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Collections.sort(myAllMovies,Movie.sortByRating);
        title = (TextView) getActivity().findViewById(R.id.Title_ID_SR);
        description = (TextView) getActivity().findViewById(R.id.Desc_ID_SR);
        genre = (TextView) getActivity().findViewById(R.id.Genre_ID_SR);
        rating = (TextView) getActivity().findViewById(R.id.Rating_ID_SR);
        year = (TextView) getActivity().findViewById(R.id.Year_ID_SR);
        imdb = (TextView) getActivity().findViewById(R.id.IMDB_ID_SR);
        finish = (Button) getActivity().findViewById(R.id.finish_id_sr);
        Movie m2 = myAllMovies.get(0);
        Log.d("demo","size"+myAllMovies.size());
        Log.d("demo","name of m2"+m2.getName());
        title.setText(m2.getName());
        description.setText(m2.getDescription());
        genre.setText(m2.getGenre());

        rating.setText((m2.getRating())+"/"+Integer.toString(j));
        year.setText(m2.getYear());
        imdb.setText(m2.getImdb());
        last = (ImageView) getActivity().findViewById(R.id.last_sr);
        first = (ImageView) getActivity().findViewById(R.id.first_sr);
        previous = (ImageView) getActivity().findViewById(R.id.previous_sr);
        next = (ImageView) getActivity().findViewById(R.id.next_sr);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index>0){
                    index = index-1;
                    title.setText(myAllMovies.get(index).getName());
                    description.setText(myAllMovies.get(index).getDescription());
                    genre.setText(myAllMovies.get(index).getGenre());
                    rating.setText(myAllMovies.get(index).getRating()+"/"+Integer.toString(j));
                    year.setText(myAllMovies.get(index).getYear());
                    imdb.setText(myAllMovies.get(index).getImdb());
                }
                else
                {
                    //Log.d("demo","InPreviousElse");
                    Toast.makeText(getActivity(),"This is the first Movie",Toast.LENGTH_LONG).show();
                }
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(index<(myAllMovies.size()-1)) {
                    index = index+1;
                    title.setText(myAllMovies.get(index).getName());
                    description.setText(myAllMovies.get(index).getDescription());
                    genre.setText(myAllMovies.get(index).getGenre());
                    rating.setText(myAllMovies.get(index).getRating()+"/"+Integer.toString(5));
                    year.setText(myAllMovies.get(index).getYear());
                    imdb.setText(myAllMovies.get(index).getImdb());
                }else
                {

                    Toast.makeText(getActivity(),"This is the last Movie",Toast.LENGTH_LONG).show();

                }


            }
        });
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(index==0)
                {
                    Toast.makeText(getActivity(),"This is the first Movie",Toast.LENGTH_LONG).show();
                }else
                {
                    index = 0;
                    title.setText(myAllMovies.get(0).getName());
                    description.setText(myAllMovies.get(0).getDescription());
                    genre.setText(myAllMovies.get(0).getGenre());
                    rating.setText(myAllMovies.get(0).getRating()+"/"+Integer.toString(j));
                    year.setText(myAllMovies.get(0).getYear());
                    imdb.setText(myAllMovies.get(0).getImdb());

                }


            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(index==myAllMovies.size()-1)
                {
                    Toast.makeText(getActivity(),"This is the Last Movie",Toast.LENGTH_LONG).show();
                }else
                {
                    index = myAllMovies.size()-1;
                    title.setText(myAllMovies.get(index).getName());
                    description.setText(myAllMovies.get(index).getDescription());
                    genre.setText(myAllMovies.get(index).getGenre());
                    rating.setText(myAllMovies.get(index).getRating()+"/"+Integer.toString(j));
                    year.setText(myAllMovies.get(index).getYear());
                    imdb.setText(myAllMovies.get(index).getImdb());

                }

            }
        });


        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.ryfinish();
            }
        });

    }

    public void setRating(ArrayList<Movie> m){
        myAllMovies=m;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);
        void ryfinish();
    }
}
