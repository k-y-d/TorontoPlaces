package yangdevatca.com.torontoplaces.Presenters;

import android.content.res.Resources;

import yangdevatca.com.torontoplaces.Models.Place;

public interface ListPresenter {
    public String getFavoritesString();

    public Place[] getPlaceArray(Resources res);

    public void changeFavorite(int id);
}
