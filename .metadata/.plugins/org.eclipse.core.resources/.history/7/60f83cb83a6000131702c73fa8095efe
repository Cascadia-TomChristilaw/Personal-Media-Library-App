package com.mediaphile_bit272.mediaphilev2;

import java.io.IOException;
import java.io.InputStream;

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

public class RestHandler {
	/*
	 * Returns a string array of the first 5 titles
	 */
	public String[] callRestforSearch(String input) {
		String[] titles = new String[5];

		// error handling for longer names
		String delims = "[ ]+";
		String[] treatedInput = input.split(delims);
		String finalInput = input;
		if (treatedInput.length > 0) {
			for (int i = 1; i <= treatedInput.length - 1; i++)
				finalInput = treatedInput[i] + "%20";
		}
		String allTitles = runInBackground(finalInput, true);
		JSONArray toBeHandled = null;
		try {
			toBeHandled = new JSONArray(allTitles);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < titles.length; i++) {
			titles[i] = toBeHandled.optString(i);
		}
		return titles;
	}

	/*
	 * parameter is the input from the user Call this method to populate a new
	 * movie after the user has chosen which movie they want to add to the list.
	 */
	public Movie callRestToPopulateNewMovie(String input) {

		// ErrorHandling for longer names
		String delims = "[ ]+";
		String[] treatedInput = input.split(delims);
		String finalInput = input;

		if (treatedInput.length > 0) {
			for (int i = 1; i <= treatedInput.length - 1; i++)
				finalInput = treatedInput[i] + "%20";
		}

		// end
		String returnedMovies = runInBackground(finalInput, false);
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
			JSONObject toBeHandled = new JSONObject(returnedMovies);
			title = toBeHandled.getString("Title");
			release_date = toBeHandled.getString("Released");
			year = toBeHandled.getString("Year");
			mpaa_rating = toBeHandled.getString("Rated");
			genre = toBeHandled.getString("Genre");
			cast = toBeHandled.getString("Actors");
			plot = toBeHandled.getString("Plot");
			runtime = toBeHandled.getString("Runtime");
			image_url = toBeHandled.getString("Poster");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// end

		// Create new movie and populate it's fields.
		Movie movie = new Movie(title, release_date, year, mpaa_rating, genre,
				cast, plot, runtime, image_url);
		return movie;
	}

	/*
	 * should never be called outside of this class, used to handle the text
	 * from the rest from the website
	 */
	private String GetASCIIContentFromEntity(HttpEntity entity)
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

	/*
	 * Should never be called outside of this class, used only to call the rest.
	 * boolean is used for search or movie
	 */
	private String runInBackground(String movie, Boolean search) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		HttpGet httpGet;
		if (search) {
			httpGet = new HttpGet("http://www.pmbapi.com/?s=" + movie);
		} else {
			httpGet = new HttpGet("http://www.pmbapi.com/?t=" + movie);
		}
		String text = null;
		try {
			HttpResponse response = httpClient.execute(httpGet, localContext);

			HttpEntity entity = response.getEntity();

			text = GetASCIIContentFromEntity(entity);

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

		return text;

	}

}
