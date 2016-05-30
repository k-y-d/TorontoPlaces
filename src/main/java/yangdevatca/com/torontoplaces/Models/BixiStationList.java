package yangdevatca.com.torontoplaces.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;
        import java.util.ArrayList;
        import java.util.List;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class BixiStationList {

    @SerializedName("executionTime")
    @Expose
    public String executionTime;
    @SerializedName("stationBeanList")
    @Expose
    public List<StationBean> stationBeanList = new ArrayList<StationBean>();

}
