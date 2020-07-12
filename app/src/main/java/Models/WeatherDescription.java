package Models;

import com.google.gson.annotations.SerializedName;

public class WeatherDescription {
  int id;

  @SerializedName("main")
  String mainDescription;

  @SerializedName("description")
  String description;

  String icon;
}