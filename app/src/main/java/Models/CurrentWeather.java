package Models;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
  @SerializedName("dt")
  int timestamp;

  int sunrise;
  int sunset;

  @SerializedName("temp")
  double temperature;

  @SerializedName("feels_like")
  double feelsLike;

  double pressure;
  int humidity;

  @SerializedName("dew_point")
  double dewPoint;

  @SerializedName("uiv")
  double uvIndex;

  int clouds;
  int visibility;

  @SerializedName("wind_speed")
  double windSpeed;

  WeatherDescription weather;
}
