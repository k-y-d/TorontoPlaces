package yangdevatca.com.torontoplaces;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;

import yangdevatca.com.torontoplaces.Models.Place;
import yangdevatca.com.torontoplaces.Models.PlaceList;

import static org.junit.Assert.assertEquals;

public class PlaceListUnitTest {
    @Test
    public void jsonArrayToObjectArray(){
    }

    @Test
    public void jsonFromEmptyString(){
        Gson gson = new Gson();
        int[] favIds = gson.fromJson("[]", int[].class);
        assertEquals("", favIds.length, 0);
    }
}
