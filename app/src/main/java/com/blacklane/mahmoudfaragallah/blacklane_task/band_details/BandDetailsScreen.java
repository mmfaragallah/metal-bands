package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import android.widget.ImageView;

import com.blacklane.mahmoudfaragallah.blacklane_task.R;
import com.blacklane.mahmoudfaragallah.blacklane_task.base.BaseActivity;
import com.blacklane.mahmoudfaragallah.blacklane_task.glide.GlideApp;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandDetailsData;
import com.blacklane.mahmoudfaragallah.blacklane_task.util.LogUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

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
    @BindView(R.id.band_logo)
    ImageView bandLogo;

    @BindView(R.id.band_photo)
    ImageView bandPhoto;
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

        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.progress_animation);

        Glide.with(this)
                .load(bandDetailsData.getBandLogo())
                .apply(requestOptions)
                .into(bandLogo);

        GlideApp.with(this)
                .load(bandDetailsData.getBandPhoto())
                .apply(requestOptions)
                .into(bandPhoto);

    }
    //endregion
}