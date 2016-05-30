package yangdevatca.com.torontoplaces;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import yangdevatca.com.torontoplaces.Models.Place;
import yangdevatca.com.torontoplaces.Presenters.ListPresenter;
import yangdevatca.com.torontoplaces.Presenters.PlaceListPresenter;
import yangdevatca.com.torontoplaces.Views.PlaceListView;

public class PlaceListActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, PlaceListView {
    private static final String TAG = "TOTAPP";
    private static final String STATE_RECYCLER_VIEW_LAYOUT = "RV_LAYOUT";
    private static final String PERSIST_FAVORITE = "FAVORITE";

    private MyAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean linearLayout = true;
    private ListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        presenter = new PlaceListPresenter(this);

        //setup RecyclerView
        if(savedInstanceState != null) {
            super.onRestoreInstanceState(savedInstanceState);
            linearLayout = savedInstanceState.getBoolean(STATE_RECYCLER_VIEW_LAYOUT, true);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.place_list);
        mRecyclerView.setHasFixedSize(true);

        setmLayoutManager(linearLayout);

        mAdapter = new MyAdapter(presenter.getPlaceArray(getResources()));
        mRecyclerView.setAdapter(mAdapter);


        //for test
        getPhotoResIdArray();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(STATE_RECYCLER_VIEW_LAYOUT, linearLayout);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    }

    @Override
    protected void onStop() {
        super.onStop();
        saveAppState();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.place_list, menu);

        //set the menu title/icon accordingly based on current RecyclerView layout
        MenuItem item = menu.findItem(R.id.action_linear_layout);
        item.setTitle(linearLayout ? R.string.action_grid_layout : R.string.action_linear_layout);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_linear_layout:
                linearLayout = !linearLayout;
                item.setTitle(linearLayout ? R.string.action_grid_layout : R.string.action_linear_layout);
                setmLayoutManager(linearLayout);
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //private methods
    private void setmLayoutManager(boolean linear){
        if(linear){
            mLayoutManager = new LinearLayoutManager(this);
        }else {
            mLayoutManager = new GridLayoutManager(this, 2);
        }
        mRecyclerView.setLayoutManager(mLayoutManager);

    }

    private void getPhotoResIdArray(){
        Resources res = getResources();
        TypedArray photos = res.obtainTypedArray(R.array.photos);
        Drawable drawable = photos.getDrawable(0);
    }

    private void saveAppState(){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(PERSIST_FAVORITE, presenter.getFavoritesString());
        editor.commit();
    }

    //methods implementing Interface PlaceListView
    @Override
    public String getPersistedFavoritesString() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getString(PERSIST_FAVORITE, "");
    }

    @Override
    public void notifyDataSetChanged() {
        mAdapter.notifyDataSetChanged();
    }

    //RecyclerView.Adapter
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private Place[] mDataset;

        public class ViewHolder extends RecyclerView.ViewHolder {
            private TextView tvTitle;
            private ImageView ivPhoto;
            private ImageButton ibtnFav;

            public ViewHolder(View v) {
                super(v);
                tvTitle = (TextView) v.findViewById(R.id.place_title);
                ivPhoto = (ImageView) v.findViewById(R.id.place_image);
                ivPhoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(v.getTag(R.id.view_tag_url).toString())));
                    }
                });

                ibtnFav = (ImageButton) v.findViewById(R.id.place_favorite);
                ibtnFav.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.changeFavorite((int)v.getTag(R.id.view_tag_id));
                        Log.d(TAG, "Favorite ImageButton, onClick, id = " + (int)v.getTag(R.id.view_tag_id));
                    }
                });
            }
        }

        public MyAdapter(Place[] myDataset) {
            mDataset = myDataset;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);

            ViewHolder vh = new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvTitle.setText(mDataset[position].title);
            holder.ivPhoto.setImageResource(mDataset[position].photoResId > 0 ? mDataset[position].photoResId : R.drawable.torontoskyline);
            holder.ivPhoto.setTag(R.id.view_tag_url, mDataset[position].url);
            holder.ibtnFav.setTag(R.id.view_tag_id, mDataset[position].id);
            DrawableCompat.wrap(holder.ibtnFav.getDrawable()).setTint(mDataset[position].favorite ? Color.RED : Color.GRAY);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }
}
