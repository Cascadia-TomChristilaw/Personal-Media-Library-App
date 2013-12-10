package com.mediaphile_bit272.mediaphilev2;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

/*
 * To make this class work you must implement the interface MovieMaker and TitleMaker in the class that you want to call, probably in the main class. 
 * 
 * Import this package as well, "import com.mediaphile_bit272.mediaphiev2;"
 * 
 * After you implement both MovieMaker and TitleMaker you must @Override the methods, onMovieMade for MovieMaker and onArrayMade for TitleMaker
 * 
 * In the class that you want to call the RestHandler in you must start the listeners with 
 * 
 * MovieMaker:
 * myMovieMaker = (MovieMaker) this;
 * 
 * TitleMaker:
 * myTitles = (TitleMaker) this;
 * 
 * And the class must call:
 * MovieMaker myMovieMaker;
 * TitleMaker myTitles;
 * At the top before anything else.
 * 
 * Once you have created a new RestHandler with the non default constructor you call .execute() to start the thread. 
 * 
 * 
 * 
 * 
 */
public class RestHandler extends AsyncTask<Object, Object, String> {
	String[] _listOfTitles;
	String allText;
	public Boolean _search;
	public String FINAL_MOVIE;
	URI _url;
	MovieMaker _mm;
	TitleMaker _tm;

	//The movie can come in as a normal title like "The Matrix" vs "The%20Matrix", the method getBetterTitle() will fix the %20 for spaces.
	//Search = true will toggle the searching feature which will then make return the array in onArrayMade. false will make the movie class and work inside of onMovieMade
	//MovieMaker and TitleMaker are both needed to make this work, just pass them.
	
	public RestHandler(String movie, Boolean search, MovieMaker mm,
			TitleMaker tm) {
		this._search = search;
		this.FINAL_MOVIE = getBetterTitle(movie);
		_mm = mm;
		_tm = tm;
		try {
			if (search) {
				this._url = new URI("http://www.omdbapi.com/?s=" + FINAL_MOVIE);
			} else {
				this._url = new URI("http://www.omdbapi.com/?t=" + FINAL_MOVIE);
			}
		} catch (Exception e) {
			this._url = null;
		}
	}
	//Fixes the original string that is passed to make the url work returns a string
	
	private String getBetterTitle(String _movie) {
		String[] treatedInput = _movie.split(" ");
		String finalInput = "";
		if (treatedInput.length > 0) {
			for (int i = 0; i < treatedInput.length - 1; i++)
				finalInput += (treatedInput[i] + "%20");
		}
		return (finalInput + treatedInput[treatedInput.length - 1]);
	}
	//populates the text from the web service to work.
	protected String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);

			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	//calls the rest service and passes the text back
	@Override
	protected String doInBackground(Object... arg0) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet = new HttpGet(_url);
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			text = getASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		return text;
	}
	//This will decide if we are searching or if we are making a movie object
	@Override
	public void onPostExecute(String result) {
		if (_search) {
			makeAnArray(result);
		} else {
			makeAMovie(result);
		}
	}
	//this takes the string and parses the string into a json object then into a json array then into a string array
	public void makeAnArray(String toParse) {
		JSONObject serverJSON = null;
		String[] arr = new String[5];
		try {
			serverJSON = new JSONObject(toParse);
			JSONArray newOne = new JSONArray(serverJSON.getString("Search"));
			for (int i = 0; i < 5; i++) {
				// JSONObject temp = new JSONObject(newOne.get(i));
				String temp = newOne.getString(i);
				JSONObject jObject = new JSONObject(temp);
				arr[i] = jObject.getString("Title") + "("
						+ jObject.getString("Year") + ")";
			}
		} catch (JSONException e1) {
			Log.v("Method makeAnArray in class RestHandler ", "Failed");
			e1.printStackTrace();
		}
		_tm.onArrayMade(arr);
	}
	//this takes a string parses it to json and pulls out information that we will use to populate different fields in the movie class
	public void makeAMovie(String toParse) {

		String title = null;
		String release_date = null;
		String year = null;
		String mpaa_rating = null;
		String genre = null;
		String cast = null;
		String plot = null;
		String runtime = null;
		String image_url = null;

		// Start JSON handling
		try {
			JSONObject toBeHandled = new JSONObject(toParse);
			title = toBeHandled.getString("Title");
			release_date = toBeHandled.getString("Released");
			year = toBeHandled.getString("Year");
			mpaa_rating = toBeHandled.getString("Rated");
			genre = toBeHandled.getString("Genre");
			cast = toBeHandled.getString("Actors");
			plot = toBeHandled.getString("Plot");
			runtime = toBeHandled.getString("Runtime");
			image_url = toBeHandled.getString("Poster");
			Log.v("JsonObject:", " Handled!");

		} catch (JSONException e) {
			Log.v("Method makeAMovie in class RestHandler ", "Failed");
			e.printStackTrace();
		}// end
			// Create new movie and populate it's fields.
		Movie toReturn = new Movie(title, release_date, year, mpaa_rating,
				genre, cast, plot, runtime, image_url);
		// Send the movie to the movie handler
		_mm.onMovieMade(toReturn);
	}

	// Interfaces
	public interface MovieMaker {
		public void onMovieMade(Movie toGive);
	}

	public interface TitleMaker {
		public void onArrayMade(String[] arr);
	}

}
