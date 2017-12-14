package com.metalbands.mahmoudfaragallah.band_details;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.base.BaseActivity;
import com.metalbands.mahmoudfaragallah.custom_views.KeyValueView;
import com.metalbands.mahmoudfaragallah.model.data_models.BandAlbum;
import com.metalbands.mahmoudfaragallah.model.data_models.BandInfo;
import com.metalbands.mahmoudfaragallah.util.LogUtil;
import com.metalbands.mahmoudfaragallah.util.ProgressDialogFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

public class BandDetailsScreen extends BaseActivity implements BandDetailsContract.View {

    //region objects
    private AlbumsListAdapter listAdapter;
    private ProgressDialogFragment progressDialog;
    private BandDetailsContract.Presenter presenter;
    //endregion

    //region screen views
    @BindView(R.id.band_photo)
    ImageView bandPhoto;

    @BindView(R.id.band_country)
    KeyValueView bandCountry;

    @BindView(R.id.band_status)
    KeyValueView bandStatus;

    @BindView(R.id.band_genre)
    KeyValueView bandGenre;

    @BindView(R.id.band_lyrical_themes)
    KeyValueView bandLyricalThemes;

    @BindView(R.id.band_years_of_activity)
    KeyValueView bandYearsOfActivity;

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
        listAdapter = new AlbumsListAdapter();
        progressDialog = new ProgressDialogFragment();
        presenter = new BandDetailsPresenter(this);
    }

    @Override
    protected void initializeViews() {

        presenter.getBandDetails();

        // use this setting to improve performance if you know that changes in content do not change the layout size of the RecyclerView
        albumsListView.setHasFixedSize(true);
        albumsListView.setLayoutManager(new LinearLayoutManager(this));
        albumsListView.setAdapter(listAdapter);
    }
    //endregion


    //region view callbacks
    @Override
    public void showProgressDialog() {
        progressDialog.show(getSupportFragmentManager(), "tag");
    }

    @Override
    public void dismissProgressDialog() {
        progressDialog.dismiss();
    }

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
        bandCountry.setValue(bandInfo.getCountryOfOrigin());
        bandStatus.setValue(bandInfo.getStatus());
        bandGenre.setValue(bandInfo.getGenre());
        bandLyricalThemes.setValue(bandInfo.getLyricalThemes());
        bandYearsOfActivity.setValue(bandInfo.getYearsActive());
    }

    @Override
    public void bindBandAlbumsList(List<BandAlbum> albums) {
        listAdapter.updateAlbumsList(albums);
    }

    //endregion
}