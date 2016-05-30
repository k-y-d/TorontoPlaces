package yangdevatca.com.torontoplaces;


import org.junit.Test;

import java.util.List;

import rx.functions.Action1;
import yangdevatca.com.torontoplaces.Models.BixiStationList;
import yangdevatca.com.torontoplaces.Models.StationBean;
import yangdevatca.com.torontoplaces.Services.BixiStationsLoadService;
import yangdevatca.com.torontoplaces.Services.LoadBixiStationsService;

import static org.junit.Assert.assertEquals;

public class BixiStationUnitTest {
    @Test
    public void fetchStationList(){
        LoadBixiStationsService service = new BixiStationsLoadService();

        service.fetchBixiStationList().subscribe(new Action1<BixiStationList>() {
            @Override
            public void call(BixiStationList list) {
                System.out.println("executionTime = " + list.executionTime);
                assertEquals("list size", list.stationBeanList.size(), 80);

                StationBean stationBean = null;
                for(StationBean sbean : list.stationBeanList){
                    if(sbean.id == 5){
                        stationBean = sbean;
                        break;
                    }
                }
                assertEquals("sample", stationBean.stationName.equals("Church St / Alexander St"), true);
            }
        });
    }
}
