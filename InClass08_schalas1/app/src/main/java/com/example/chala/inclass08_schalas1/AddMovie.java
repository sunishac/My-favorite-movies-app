package com.example.chala.inclass08_schalas1;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class AddMovie extends Fragment {

    EditText name,description,year,imdb;
    Spinner genre;
    SeekBar rating;
    TextView ratingValue;
    int rate;
    String rateStr;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddMovie() {
        // Required empty public constructor
    }


    public static AddMovie newInstance(String param1, String param2) {
        AddMovie fragment = new AddMovie();
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
        return inflater.inflate(R.layout.fragment_add_movie, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    /*public void onButtonPressed(String str) {
        if (mListener != null) {
            mListener.onFragmentInteraction("str");
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
        //getActivity().getSupportActionBar().setTitle("Add Movie");
        name = (EditText) getActivity().findViewById(R.id.addName);
        description = (EditText) getActivity().findViewById(R.id.addDesc);
        year = (EditText) getActivity().findViewById(R.id.addYear);
        imdb = (EditText) getActivity().findViewById(R.id.addIMDB);
        genre = (Spinner) getActivity().findViewById(R.id.spinner1);
        rating = (SeekBar) getActivity().findViewById(R.id.seekBar1);
        ratingValue = (TextView) getActivity().findViewById(R.id.seekbarValue);
        ratingValue.setText(Integer.toString(0));
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.genre,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genre.setAdapter(adapter);


        rating.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                rate = i+0;
                rateStr = String.valueOf(rate);
                ratingValue.setText(rateStr);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        getActivity().findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().length()==0 || description.getText().toString().length()==0 ||
                        genre.getSelectedItem().toString().equals("Select") || year.getText().toString().length()==0
                        || imdb.getText().toString().length()==0 || ratingValue.length()==0 || year.length()<4 || rate==0){
                    if(name.getText().toString().length()==0)
                    {
                        Toast.makeText(getActivity(),"Please enter appropriate name",Toast.LENGTH_LONG).show();
                    }else if(description.getText().toString().length()==0)
                    {
                        Toast.makeText(getActivity(),"Please enter appropriate description",Toast.LENGTH_LONG).show();
                    }else if(genre.getSelectedItem().toString().equals("Select"))
                    {
                        Toast.makeText(getActivity(),"Please enter appropriate genre",Toast.LENGTH_LONG).show();
                    }else if(year.getText().toString().length()==0)
                    {
                        Toast.makeText(getActivity(),"Please enter appropriate year",Toast.LENGTH_LONG).show();
                    }else if(imdb.getText().toString().length()==0)
                    {
                        Toast.makeText(getActivity(),"Please select imdb value",Toast.LENGTH_LONG).show();
                    }else if(year.length()<4)
                    {
                        Toast.makeText(getActivity(),"Please enter appropriate year value",Toast.LENGTH_LONG).show();
                    }else if(rate == 0)
                    {
                        Toast.makeText(getActivity(),"Please enter appropriate rating value",Toast.LENGTH_LONG).show();
                    }
                    Toast.makeText(getActivity(),"Please enter appropriate value",Toast.LENGTH_LONG).show();
                }else {
                    String name1 = name.getText().toString();
                    String description1 = description.getText().toString();
                    String genre1 = genre.getSelectedItem().toString();

                    String year1 = year.getText().toString();
                    String imdb1 = imdb.getText().toString();
                    rateStr = (String) ratingValue.getText();

                    Movie m1 = new Movie(name1, description1, genre1, rateStr, year1, imdb1);
                    mListener.addmoviemethod(m1);
                }
            }
        });
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String str);
        void addmoviemethod(Movie m);
    }
}
