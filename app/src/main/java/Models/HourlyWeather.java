package Models;

import com.google.gson.annotations.SerializedName;

public class HourlyWeather {
  @SerializedName("dt")
  int timestamp;

  @SerializedName("temp")
  double temperature;

  @SerializedName("feels_like")
  double feelsLike;

  double pressure;
  int humidity;

  @SerializedName("dew_point")
  double dewPoint;

  int clouds;

  @SerializedName("wind_speed")
  double windSpeed;

  WeatherDescription weather;
}
