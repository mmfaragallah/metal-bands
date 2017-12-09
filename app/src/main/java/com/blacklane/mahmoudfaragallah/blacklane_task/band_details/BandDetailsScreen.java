package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.blacklane.mahmoudfaragallah.blacklane_task.R;
import com.blacklane.mahmoudfaragallah.blacklane_task.base.BaseActivity;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandAlbum;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandInfo;
import com.blacklane.mahmoudfaragallah.blacklane_task.util.LogUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class BandDetailsScreen extends BaseActivity implements BandDetailsContract.View {

    //region constants
    public static final String BAND_ID = "band_id";
    //endregion

    //region objects
    private String bandId;
    private AlbumsListAdapter listAdapter;
    private BandDetailsContract.Presenter presenter;
    //endregion

    //region screen views
    @BindView(R.id.band_photo)
    ImageView bandPhoto;

    @BindView(R.id.band_country)
    TextView bandCountry;

    @BindView(R.id.band_status)
    TextView bandStatus;

    @BindView(R.id.band_genre)
    TextView bandGenre;

    @BindView(R.id.band_lyrical_themes)
    TextView bandLyricalThemes;

    @BindView(R.id.band_years_of_activity)
    TextView bandYearsOfActivity;

    @BindView(R.id.albums_list)
    RecyclerView albumsListView;
    //endregion

    //region BaseActivity methods
    @Override
    protected int getLayout() {
        return R.layout.activity_band_details_screen;
    }

    @Override
    protected void initializeObjects() {
        bandId = getIntent().getStringExtra(BAND_ID);
        listAdapter = new AlbumsListAdapter();
        presenter = new BandDetailsPresenter(this);
    }

    @Override
    protected void initializeViews() {
        presenter.getBandById(bandId);

        // use this setting to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        albumsListView.setHasFixedSize(true);
        albumsListView.setLayoutManager(new LinearLayoutManager(this));
        albumsListView.setAdapter(listAdapter);
    }
    //endregion

    //region view callbacks
    @Override
    public void noDetailsResults(String bandId) {
        LogUtil.showToast(this, "There are no details record for band id: " + bandId);
    }

    @Override
    public void bindBandPhoto(String bandPhotoUrl) {

        Picasso.with(this)
                .load(bandPhotoUrl)
                .placeholder(R.drawable.image_loading_animation)
//                .error(R.drawable.user_placeholder_error)
                .into(bandPhoto);
    }

    @Override
    public void bindBandName(String bandName) {
        getSupportActionBar().setTitle(bandName);
    }

    @Override
    public void bindBandInfo(BandInfo bandInfo) {
        bandCountry.setText(bandInfo.getCountryOfOrigin());
        bandStatus.setText(bandInfo.getStatus());
        bandGenre.setText(bandInfo.getGenre());
        bandLyricalThemes.setText(bandInfo.getLyricalThemes());
        bandYearsOfActivity.setText(bandInfo.getYearsActive());
    }

    @Override
    public void bindBandAlbumsList(List<BandAlbum> albums) {
        listAdapter.updateAlbumsList(albums);
    }

    //endregion
}