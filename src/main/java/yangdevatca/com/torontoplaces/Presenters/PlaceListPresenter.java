package yangdevatca.com.torontoplaces.Presenters;

import android.content.res.Resources;

import yangdevatca.com.torontoplaces.Models.DataChange;
import yangdevatca.com.torontoplaces.Models.Place;
import yangdevatca.com.torontoplaces.Models.PlaceList;
import yangdevatca.com.torontoplaces.Views.PlaceListView;

public class PlaceListPresenter implements ListPresenter, DataChange{
    PlaceListView mView;

    public PlaceListPresenter(PlaceListView view){
        mView = view;
        PlaceList.setDataChangeListener(this);
    }

    @Override
    public String getFavoritesString() {
        return PlaceList.getFavoriteString();
    }

    @Override
    public Place[] getPlaceArray(Resources res) {
        String json = mView.getPersistedFavoritesString() == "" ? "[]" : mView.getPersistedFavoritesString();
        return PlaceList.getPlaceArray(res, json);
    }

    @Override
    public void changeFavorite(int id) {
        PlaceList.changeFavorite(id);
    }

    @Override
    public void notifyPlaceListChange() {
        mView.notifyDataSetChanged();
    }
}
