package com.mediaphile_bit272.mediaphilev2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListMoviesFragment extends Fragment {
	  
	 @Override
	  public View onCreateView(LayoutInflater inflater, ViewGroup container,
	    Bundle savedInstanceState) {
		  View view = null;
		  
		  view = inflater.inflate(R.layout.layout_listmoviesfragment, null);
		  
	      return view;
	   }

	  }