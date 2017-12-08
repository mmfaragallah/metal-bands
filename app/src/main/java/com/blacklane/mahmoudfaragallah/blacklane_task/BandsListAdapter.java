package com.blacklane.mahmoudfaragallah.blacklane_task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blacklane.mahmoudfaragallah.blacklane_task.model.MetalBand;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud on 08-12-2017.
 */

public class BandsListAdapter extends RecyclerView.Adapter<BandsListAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<MetalBand> bands;

    public BandsListAdapter(Context context, List<MetalBand> bands) {
        this.context = context;
        this.bands = bands;
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

        viewHolder.itemView.setTag(band.getName());
    }

    /**
     * @param view
     */
    @Override
    public void onClick(View view) {

//        String vehicleId = (String) view.getTag();
//        UIManager.goToGoogleMapScreen(context, bands, vehicleId);
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

    @Override
    public int getItemCount() {
        return bands.size();
    }
}