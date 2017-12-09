package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import android.widget.ImageView;
import android.widget.TextView;

import com.blacklane.mahmoudfaragallah.blacklane_task.R;
import com.blacklane.mahmoudfaragallah.blacklane_task.base.BaseActivity;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandDetailsData;
import com.blacklane.mahmoudfaragallah.blacklane_task.util.LogUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;

public class BandDetailsScreen extends BaseActivity implements BandDetailsContract.View {

    //region constants
    public static final String BAND_ID = "band_id";
    //endregion

    //region objects
    private String bandId;
    private BandDetailsData bandDetails;
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
    //endregion

    //region BaseActivity methods
    @Override
    protected int getLayout() {
        return R.layout.activity_band_details_screen;
    }

    @Override
    protected void initializeObjects() {
        presenter = new BandDetailsPresenter(this);
        bandId = getIntent().getStringExtra(BAND_ID);
    }

    @Override
    protected void initializeViews() {
        presenter.getBandById(bandId);
    }
    //endregion

    //region view callbacks
    @Override
    public void noDetailsResults(String bandId) {
        LogUtil.showToast(this, "There are no details record for band id: " + bandId);
    }

    @Override
    public void setBandDetailsData(BandDetailsData bandDetailsData) {

        Picasso.with(this)
                .load(bandDetailsData.getBandPhoto())
                .placeholder(R.drawable.image_loading_animation)
//                .error(R.drawable.user_placeholder_error)
                .into(bandPhoto);


        getSupportActionBar().setTitle(bandDetailsData.getBandName());

        if (bandDetailsData.getBandInfo() != null) {
            bandCountry.setText(bandDetailsData.getBandInfo().getCountryOfOrigin());
            bandStatus.setText(bandDetailsData.getBandInfo().getStatus());
            bandGenre.setText(bandDetailsData.getBandInfo().getGenre());
            bandLyricalThemes.setText(bandDetailsData.getBandInfo().getLyricalThemes());
            bandYearsOfActivity.setText(bandDetailsData.getBandInfo().getYearsActive());
        }

    }
    //endregion
}