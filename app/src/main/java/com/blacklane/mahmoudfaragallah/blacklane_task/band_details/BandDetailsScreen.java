package com.blacklane.mahmoudfaragallah.blacklane_task.band_details;

import com.blacklane.mahmoudfaragallah.blacklane_task.R;
import com.blacklane.mahmoudfaragallah.blacklane_task.base.BaseActivity;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.BandDetailsData;
import com.blacklane.mahmoudfaragallah.blacklane_task.util.LogUtil;

public class BandDetailsScreen extends BaseActivity implements BandDetailsContract.View {

    //region constants
    public static final String BAND_ID = "band_id";
    //endregion

    //region objects
    private String bandId;
    private BandDetailsData bandDetails;
    private BandDetailsContract.Presenter presenter;
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

    }
    //endregion
}