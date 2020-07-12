package com.example.simpleweather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
  WeatherViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ViewModelProvider.Factory factory = new ViewModelProvider.NewInstanceFactory();
    viewModel = new ViewModelProvider(this, factory).get(WeatherViewModel.class);

    viewModel.getWeather().observe(this, responseMutableLiveData -> {
      Log.i("app", responseMutableLiveData.toString());
    });
  }
}