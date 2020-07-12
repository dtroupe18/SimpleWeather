package API;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherDownloadTask extends AsyncTask<String, Void, String> {
  @Override
  protected String doInBackground(String... urls) {
    StringBuilder result = new StringBuilder();
    URL url;
    HttpURLConnection urlConnection = null;

    try {
      url = new URL(urls[0]);
      urlConnection = (HttpURLConnection) url.openConnection();
      InputStream inputStream = urlConnection.getInputStream();
      InputStreamReader streamReader = new InputStreamReader(inputStream);
      int data = streamReader.read();

      while(data != -1) {
        char current = (char) data;
        result.append(current);

        data = streamReader.read();
      }

      return result.toString();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Override
  protected void onPostExecute(String s) {
    super.onPostExecute(s);



    try {
      JSONObject jsonObject = new JSONObject(s);
      JSONObject currentJson = jsonObject.getJSONObject("current");
      JSONArray weatherArray = currentJson.getJSONArray("weather");

      for(int i = 0; i < weatherArray.length(); i++) {
        JSONObject jsonElement = weatherArray.getJSONObject(i);

        Log.i("main", jsonElement.getString("main"));
        Log.i("description", jsonElement.getString("description"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}