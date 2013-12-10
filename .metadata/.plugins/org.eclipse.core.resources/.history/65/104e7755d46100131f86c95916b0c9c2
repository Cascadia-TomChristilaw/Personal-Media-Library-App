package com.mediaphile_bit272.mediaphilev2;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovieFragment extends Fragment {
	
	EditText movieTitleEditText;
	Button titleSearchButton;
	Button saveMovieButton;
	EditText releasedEditText;
	EditText mpaaRatingEditText;
	EditText genreEditText;
	EditText runtimeEditText;
	EditText castEditText;
	EditText plotEditText;
	EditText imageUrlEditText;
	EditText formatEditText;
	EditText fileSizeEditText;
	EditText pathEditText;
	EditText personalNoteEditText;	
	
	String[] matrixSearchResults = {"The Matrix (1999)", "The Matrix Reloaded (2003)", "The Matrix Revolutions (2003)", "The Matrix Revisited (2001)", "Armitage III: Poly Matrix (1997)"};
	
	Movie matrixMovie = new Movie("The Matrix", "31 Mar 1999",
			"1999", "R", "Action, Adventure, Sci-Fi",
			"Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving",
			"A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.",
			"2 h 16 min", "http://ia.media-imdb.com/images/M/MV5BMjEzNjg1NTg2NV5BMl5BanBnXkFtZTYwNjY3MzQ5._V1_SX300.jpg");
	
	OnClickListener titleSearchButtonListener = new OnClickListener() {
		@Override  public void onClick(View v) {
			String searchString = movieTitleEditText.getText().toString();
			
			Toast.makeText(getActivity(), "Your search string is " + searchString, Toast.LENGTH_LONG).show();
			
			AlertDialog.Builder movieChoices = new AlertDialog.Builder(getActivity());
			movieChoices.setTitle("Select A Movie").setItems(matrixSearchResults, new DialogInterface.OnClickListener() {
				@Override  public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getActivity(), "You selected " + matrixSearchResults[which], Toast.LENGTH_LONG).show();
				}
			});
			movieChoices.show();
		}  
	};
	   
	 
	 OnClickListener saveMovieButtonListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				DatabaseHandler db = new DatabaseHandler(getView().getContext());
				
				Movie movieToSave = new Movie(movieTitleEditText.getText().toString(), releasedEditText.getText().toString(), releasedEditText.getText().toString().split(" ")[2],
						mpaaRatingEditText.getText().toString(), genreEditText.getText().toString(), castEditText.getText().toString(), 
						plotEditText.getText().toString(), runtimeEditText.getText().toString(), formatEditText.getText().toString(),
						fileSizeEditText.getText().toString(), pathEditText.getText().toString(), imageUrlEditText.getText().toString(),
						personalNoteEditText.getText().toString());
				db.addMovie(movieToSave);
				
				Log.d("SUCCESS!! ", "The save movie button has been clicked.");
			}
			 
		 };
	  
	 @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		 
		  View view = inflater.inflate(R.layout.layout_addmoviefragment, null);				 
	      return view;
	   }
	 public void onActivityCreated(Bundle savedInstanceState) {
		 super.onActivityCreated(savedInstanceState);
		 
		 movieTitleEditText = (EditText) getView().findViewById(R.id.movieTitleEditText);
		 releasedEditText = (EditText) getView().findViewById(R.id.releasedEditText);
		 mpaaRatingEditText = (EditText) getView().findViewById(R.id.mpaaRatingEditText);
		 genreEditText = (EditText) getView().findViewById(R.id.genreEditText);
		 runtimeEditText = (EditText) getView().findViewById(R.id.runtimeEditText);
		 castEditText = (EditText) getView().findViewById(R.id.castEditText);
		 plotEditText = (EditText) getView().findViewById(R.id.plotEditText);
		 imageUrlEditText = (EditText) getView().findViewById(R.id.imageUrlEditText);
		 formatEditText = (EditText) getView().findViewById(R.id.formatEditText);
		 fileSizeEditText = (EditText) getView().findViewById(R.id.fileSizeEditText);
		 pathEditText = (EditText) getView().findViewById(R.id.pathEditText);
		 personalNoteEditText = (EditText) getView().findViewById(R.id.personalNoteEditText);
		 
		 titleSearchButton = (Button) getView().findViewById(R.id.titleSearchButton);
		 titleSearchButton.setOnClickListener(titleSearchButtonListener);
		 
		 saveMovieButton = (Button) getView().findViewById(R.id.saveMovieButton);
		 saveMovieButton.setOnClickListener(saveMovieButtonListener);
		 
		 Log.d("ACTIVITY!! ", "The AddMovieFragment activity has been created.");
		 
	 }
	 

	 
}