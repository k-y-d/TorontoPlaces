package yangdevatca.com.torontoplaces.Services;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.functions.Func1;
import yangdevatca.com.torontoplaces.Models.BixiStationList;
import yangdevatca.com.torontoplaces.Models.StationBean;

public class BixiStationsLoadService implements LoadBixiStationsService {
    private static final String BASE_URL = "http://feeds.bikesharetoronto.com/";

    @Override
    public Observable<BixiStationList> fetchBixiStationList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        StationAPI service = retrofit.create(StationAPI.class);
//        Observable<List<StationBean>> list = service.getStations()
//            .map(new Func1<BixiStationList, List<StationBean>>() {
//            @Override
//            public List<StationBean> call(BixiStationList bixiStationList) {
//                return bixiStationList.stationBeanList;
//            }
//        });

        return service.getStations();
    }

    public interface StationAPI {
        @GET("stations/stations.json")
        Observable<BixiStationList> getStations();
    }
}
