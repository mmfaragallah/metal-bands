package com.blacklane.mahmoudfaragallah.blacklane_task.bands_search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blacklane.mahmoudfaragallah.blacklane_task.R;
import com.blacklane.mahmoudfaragallah.blacklane_task.model.data_models.MetalBand;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsListAdapter extends RecyclerView.Adapter<BandsListAdapter.ViewHolder> implements View.OnClickListener {

    //region objects
    private BandsSearchContract.View bandsListView;
    private List<MetalBand> bands;
    //endregion

    public BandsListAdapter(BandsSearchContract.View bandsListView) {
        this.bands = new ArrayList<>();
        this.bandsListView = bandsListView;
    }

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BandsListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.band_item_layout, parent, false);

        view.setOnClickListener(this);

        return new ViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position   the position of the item to be drawn
     */
    @Override
    public void onBindViewHolder(BandsListAdapter.ViewHolder viewHolder, int position) {

        MetalBand band = bands.get(position);

        viewHolder.name.setText(band.getName());
        viewHolder.genre.setText(band.getGenre());
        viewHolder.country.setText(band.getCountry());

        viewHolder.itemView.setTag(band.getId());
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {

        String bandId = (String) view.getTag();
        bandsListView.onBandClick(bandId);
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        @BindView(R.id.band_name)
        TextView name;

        @BindView(R.id.band_genre)
        TextView genre;

        @BindView(R.id.band_country)
        TextView country;

        /**
         * @param itemView
         */
        public ViewHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;

            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * @param bands
     */
    public void updateBandsList(List<MetalBand> bands) {
        this.bands = bands;
        notifyDataSetChanged();
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return bands.size();
    }
}