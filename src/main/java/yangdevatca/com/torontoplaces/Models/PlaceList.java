package yangdevatca.com.torontoplaces.Models;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import yangdevatca.com.torontoplaces.R;

public class PlaceList {
    private static String placeJson = "[{\"id\":1,\"lng\":\"-79.385865\",\"lat\":\"43.642424\",\"url\":\"http://www.ripleyaquariums.com/canada/\",\"title\":\"Ripley's Aquarium\"},{\"id\":2,\"lng\":\"-79.387057\",\"lat\":\"43.642566\",\"url\":\"http://www.cntower.ca/en-ca/home.html\",\"title\":\"CN Tower\"},{\"id\":3,\"lng\":\"-79.18589\",\"lat\":\"43.817699\",\"url\":\"http://www.torontozoo.com/\",\"title\":\"Toronto Zoo\"},{\"id\":4,\"lng\":\"-79.394777\",\"lat\":\"43.66771\",\"url\":\"http://www.rom.on.ca/en\",\"title\":\"Royal Ontario Museum\"},{\"id\":5,\"lng\":\"-79.392512\",\"lat\":\"43.653607\",\"url\":\"http://www.ago.net/\",\"title\":\"Art Gallery of Ontario\"},{\"id\":6,\"lng\":\"-79.453206\",\"lat\":\" 43.725887\",\"url\":\"http://yorkdale.com/\",\"title\":\"Yorkdale Mall\"},{\"id\":7,\"lng\":\"-79.381455\",\"lat\":\"43.653597\",\"url\":\"http://www.torontoeatoncentre.com/en/Pages/default.aspx\",\"title\":\"Eaton Center\"},{\"id\":8,\"lng\":\"-79.38409\",\"lat\":\" 43.65344\",\"url\":\"http://www.toronto.ca/\",\"title\":\"City Hall\"},{\"id\":9,\"lng\":\"-79.377264\",\"lat\":\"43.646988\",\"url\":\"http://www.hhof.com/\",\"title\":\"Hockey Hall of Fame\"},{\"id\":10,\"lng\":\"-79.379099\",\"lat\":\"43.643466\",\"url\":\"http://www.theaircanadacentre.com/\",\"title\":\"Air Canada Center\"}]";
    private static Place[] places;
    private static String favoriteString;
    private static DataChange listener;
    private PlaceList(){
    }

    public static String getFavoriteString(){
        List<Integer> favs = new ArrayList<>();
        for(Place place : places){
            if(place.favorite){
                favs.add(place.id);
            }
        }

        Gson gson = new Gson();
        String json = gson.toJson(favs);

        return json;
    }

    public static void setDataChangeListener(DataChange lis){
        listener = lis;
    }

    public static Place[] getPlaceArray(Resources res, String favoriteIds){
        if(places == null)
            init(res);

        Gson gson = new Gson();
        int[] favIds = gson.fromJson(favoriteIds, int[].class);

        for(Place place : places) {
            place.favorite = hasValue(favIds, place.id);
        }

        return places;
    }

    public static void changeFavorite(int id){
        for(Place place : places){
            if(place.id == id){
                place.favorite = !place.favorite;
                if(listener != null){
                    listener.notifyPlaceListChange();
                }
                return;
            }
        }
    }

    private static void init(Resources res){
        int[] photoIds = res.getIntArray(R.array.photo_ids);
        int[] resIds = getPhotoResIdArray(res);

        Gson gson = new Gson();
        places = gson.fromJson(placeJson, Place[].class);

        for (Place place : places) {
            int index = getIndexByValue(photoIds, place.id);
            if(index >=0) {
                place.photoResId = resIds[index];
            }else{
                place.photoResId = -1;
            }
        }
    }

    private static boolean hasValue(int[] arr, int val){
        for( int v : arr){
            if(v == val)
                return true;
        }

        return false;
    }

    private static int getIndexByValue(int[] arr, int val){
        for (int i = 0, len = arr.length; i < len; i++) {
            if(arr[i] == val){
                return i;
            }
        }
        return -1;
    }

    private static int[] getPhotoResIdArray(Resources res){
        TypedArray photos = res.obtainTypedArray(R.array.photos);
        int len = photos.length();
        int[] resIds = new int[len];

        for (int i = 0; i < len; i++) {
            resIds[i] = photos.getResourceId(i, -1);
        }

        return resIds;
    }

}
