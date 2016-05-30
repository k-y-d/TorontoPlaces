package yangdevatca.com.torontoplaces.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StationBean {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("stationName")
    @Expose
    public String stationName;
    @SerializedName("availableDocks")
    @Expose
    public Integer availableDocks;
    @SerializedName("totalDocks")
    @Expose
    public Integer totalDocks;
    @SerializedName("latitude")
    @Expose
    public Double latitude;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName("statusValue")
    @Expose
    public String statusValue;
    @SerializedName("statusKey")
    @Expose
    public Integer statusKey;
    @SerializedName("availableBikes")
    @Expose
    public Integer availableBikes;
    @SerializedName("lastCommunicationTime")
    @Expose
    public String lastCommunicationTime;
    @SerializedName("landMark")
    @Expose
    public Integer landMark;

    /**
     * unused properties
     */
//    @SerializedName("stAddress1")
//    @Expose
//    public Object stAddress1;
//    @SerializedName("stAddress2")
//    @Expose
//    public Object stAddress2;
//    @SerializedName("city")
//    @Expose
//    public Object city;
//    @SerializedName("postalCode")
//    @Expose
//    public Object postalCode;
//    @SerializedName("location")
//    @Expose
//    public Object location;
//    @SerializedName("altitude")
//    @Expose
//    public Object altitude;
//    @SerializedName("testStation")
//    @Expose
//    public Boolean testStation;

}

