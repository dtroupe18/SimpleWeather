package com.example.simpleweather;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Models.CurrentWeatherResponse;

import com.google.gson.Gson;

public class WeatherViewModel extends ViewModel {
  private static final String TAG = WeatherViewModel.class.getSimpleName();

  private String apiKey = BuildConfig.ApiKey;

  private MutableLiveData<CurrentWeatherResponse> responseMutableLiveData;

  public LiveData<CurrentWeatherResponse> getWeather() {
    if (responseMutableLiveData == null) {
      responseMutableLiveData = new MutableLiveData<CurrentWeatherResponse>();
      fetchWeather();
    }

    return responseMutableLiveData;
  }

  private void fetchWeather() {
    DownloadTask task = new DownloadTask();
    String url = "https://api.openweathermap.org/data/2.5/onecall?lat=34.05&lon=-118.24&exclude=minutely&units=imperial&appid=";
    url += apiKey;

    task.execute(url);
  }

  public class DownloadTask extends AsyncTask<String, Void, String> {
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

        Log.e(TAG, "response: " + result.toString());
        return result.toString();

      } catch (Exception e) {
        e.printStackTrace();
        return null;
      }
    }

    @Override
    protected void onPostExecute(String s) {
      super.onPostExecute(s);

      Log.i("JSON", s);

      Gson gson = new Gson();
      CurrentWeatherResponse model = gson.fromJson(s, CurrentWeatherResponse.class);

      Log.i("After JSON", model.toString());

      responseMutableLiveData.postValue(model);
    }
  }
}
