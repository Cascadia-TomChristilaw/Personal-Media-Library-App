package com.mediaphile_bit272.mediaphilev2;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends FragmentActivity {
 
 // if run on phone, isSinglePane = true
 // if run on tablet, isSinglePane = false
 static boolean isSinglePane;
 
 static String[] options ={
   "List Movies", "Add Movie"};

 public static class MyListFragment extends ListFragment {

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
   // TODO Auto-generated method stub
   super.onActivityCreated(savedInstanceState);
   
   ListAdapter myArrayAdapter = 
     new ArrayAdapter<String>(
       getActivity(), android.R.layout.simple_list_item_1, options);
   setListAdapter(myArrayAdapter);
   
  }

  @Override
  public void onListItemClick(ListView l, View v, int position, long id) {
   
   String clickedDetail = (String)l.getItemAtPosition(position);
   
   if(isSinglePane == true){
    /*
     * The second fragment not yet loaded. 
     * Load MyDetailFragment by FragmentTransaction, and pass 
     * data from current fragment to second fragment via bundle.
     */
    MyDetailFragment myDetailFragment = new MyDetailFragment();
    Bundle bundle = new Bundle();
    bundle.putString("KEY_DETAIL", clickedDetail);
    myDetailFragment.setArguments(bundle);
    FragmentTransaction fragmentTransaction =
      getActivity().getFragmentManager().beginTransaction();
    fragmentTransaction.replace(R.id.phone_container, myDetailFragment);
    fragmentTransaction.commit();
    
   }else{
    /*
     * Activity have two fragments. Pass data between fragments
     * via reference to fragment
     */
    
    //get reference to MyDetailFragment
    MyDetailFragment myDetailFragment =
      (MyDetailFragment)getFragmentManager().findFragmentById(R.id.detail_fragment);
    myDetailFragment.updateDetail(clickedDetail);
    
   }
  }

 }
 
 public static class MyDetailFragment extends Fragment {
  
  LinearLayout detailContainer;

  @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR1)
@Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {
	  View view = null;
	  Bundle bundle = getArguments();
	  String detail = bundle.getString("KEY_DETAIL", "no argument pass");
   // TODO Auto-generated method stub
	  if (detail.matches("List Movies")) {
		  view = inflater.inflate(R.layout.layout_detailfragment, null);
	  }
	  else if (detail.matches("Add Movie")) {
		  view = inflater.inflate(R.layout.layout_addmoviefragment, null);
	  }
	  
  
//    textDetail.setText(detail);
    	return view;
   }
  
protected void updateDetail(String detail) {
	  
	// Create fragment and give it an argument specifying the article it should show
	MyDetailFragment newFragment = new MyDetailFragment();
	Bundle bundle = new Bundle();
    bundle.putString("KEY_DETAIL", detail);
    newFragment.setArguments(bundle);

	FragmentTransaction transaction = getFragmentManager().beginTransaction();

	// Replace whatever is in the fragment_container view with this fragment,
	// and add the transaction to the back stack so the user can navigate back
	transaction.replace(R.id.detail_fragment, newFragment);
	transaction.addToBackStack(null);

	// Commit the transaction
	transaction.commit();
	  
	  	 
//	   
//		MyDetailFragment myDetailFragment = new MyDetailFragment();
//	    Bundle bundle = new Bundle();
//	    bundle.putString("KEY_DETAIL", detail);
//		myDetailFragment.setArguments(bundle);
//	  	FragmentTransaction transaction = getFragmentManager().beginTransaction();
//
//	  	// Replace whatever is in the fragment_container view with this fragment,
//	  	// and add the transaction to the back stack
//	  	transaction.replace(R.id.detail_fragment, myDetailFragment);
//	  	transaction.addToBackStack(null);
//
//	  	// Commit the transaction
//	  	transaction.commit();
	  }
   
  }
 
 

  

 

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_main);
  
  View v = findViewById(R.id.phone_container);
  if(v == null){
   //it's run on tablet
   isSinglePane = false;
   /*
    * MyListFragment and MyDetailFragment have been loaded in XML,
    * no need load.
    */
   
  }else{
   //it's run on phone
   //Load MyListFragment programmatically
   isSinglePane = true;
   
   if(savedInstanceState == null){
    //if's the first time created
    MyListFragment myListFragment = new MyListFragment();
    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    fragmentTransaction.add(R.id.phone_container, myListFragment);
    fragmentTransaction.commit();
   }
  }
 }

}