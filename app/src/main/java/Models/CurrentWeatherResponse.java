package Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CurrentWeatherResponse {
  @SerializedName("lat")
  double latitude;

  @SerializedName("lon")
  double longitude;

  String timezone;

  @SerializedName("timezone_offset")
  int timezoneOffset;

  @SerializedName("current")
  CurrentWeather currentWeather;

  @SerializedName("hourly")
  public List<HourlyWeather> hourlyWeatherList = null;
}