package com.mediaphile_bit272.mediaphilev2;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MovieDisplayFragment extends Fragment {
	
	TextView movieTitleTextView;
	TextView movieReleasedTextView;
	TextView movieMpaaRatingEditText;
	TextView movieGenreEditText;
	TextView movieRuntimeEditText;
	TextView movieCastTextView;
	TextView moviePlotTextView;
	TextView movieFormatEditText;
	TextView movieFileSizeEditText;
	TextView moviePathEditText;
	TextView moviePersonalNoteTextView;
	
	@Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		 
		  View view = inflater.inflate(R.layout.layout_moviedisplayfragment, null);  
		  
	      return view;
	   }
	 public void onActivityCreated(Bundle savedInstanceState) {
		 super.onActivityCreated(savedInstanceState);
		 
		 Bundle bundle = this.getArguments();		  
		  
		  movieTitleTextView = (TextView) getView().findViewById(R.id.movieTitleTextView);
		  movieReleasedTextView = (TextView) getView().findViewById(R.id.movieReleasedTextView);
		  movieMpaaRatingEditText = (TextView) getView().findViewById(R.id.movieMpaaRatingEditText);
		  movieGenreEditText = (TextView) getView().findViewById(R.id.movieGenreEditText);
		  movieRuntimeEditText = (TextView) getView().findViewById(R.id.movieRuntimeEditText);
		  movieCastTextView = (TextView) getView().findViewById(R.id.movieCastTextView);
		  moviePlotTextView = (TextView) getView().findViewById(R.id.moviePlotTextView);
		  movieFormatEditText = (TextView) getView().findViewById(R.id.movieFormatEditText);
		  movieFileSizeEditText = (TextView) getView().findViewById(R.id.movieFileSizeEditText);
		  moviePathEditText = (TextView) getView().findViewById(R.id.moviePathEditText);
		  moviePersonalNoteTextView = (TextView) getView().findViewById(R.id.moviePersonalNoteTextView);
		  
		  DatabaseHandler db = new DatabaseHandler(getView().getContext());
		  
		  Movie thisMovie = db.getMovie(bundle.getInt("movie"));
		  
		  movieTitleTextView.setText(thisMovie.getTitle());
		  movieReleasedTextView.setText(thisMovie.getReleaseDate());
		  movieMpaaRatingEditText.setText(thisMovie.getMpaa());
		  movieGenreEditText.setText(thisMovie.getGenre());
		  movieRuntimeEditText.setText(thisMovie.getRuntime());
		  movieCastTextView.setText(thisMovie.getCast());
		  moviePlotTextView.setText(thisMovie.getPlot());
		  movieFormatEditText.setText(thisMovie.getFormat());
		  movieFileSizeEditText.setText(thisMovie.getSize());
		  moviePathEditText.setText(thisMovie.getPath());
		  moviePersonalNoteTextView.setText(thisMovie.getNote());
		 
		 Log.d("ACTIVITY!! ", "The MovieDisplayFragment activity has been created.");
		 
	 }

}
