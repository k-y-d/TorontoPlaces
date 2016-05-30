package yangdevatca.com.torontoplaces.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place {
    //properties matching with JSON string
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("lng")
    @Expose
    public String lng;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("title")
    @Expose
    public String title;

    //properties for app features
    public boolean favorite;

    public int photoResId;
}
