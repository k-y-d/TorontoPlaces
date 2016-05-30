package yangdevatca.com.torontoplaces.Services;

import java.util.List;

import rx.Observable;
import yangdevatca.com.torontoplaces.Models.BixiStationList;
import yangdevatca.com.torontoplaces.Models.StationBean;

public interface LoadBixiStationsService {
    public Observable<BixiStationList> fetchBixiStationList();
}
