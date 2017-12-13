package com.metalbands.mahmoudfaragallah.band_details;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.metalbands.mahmoudfaragallah.R;
import com.metalbands.mahmoudfaragallah.model.data_models.BandAlbum;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mahmoud on 09-12-2017.
 */

public class AlbumsListAdapter extends RecyclerView.Adapter<AlbumsListAdapter.ViewHolder> {

    //region objects
    private List<BandAlbum> albums;
    //endregion

    //region constructor
    AlbumsListAdapter() {
        this.albums = new ArrayList<>();
    }
    //endregion

    /**
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public AlbumsListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_item_layout, parent, false);

        return new AlbumsListAdapter.ViewHolder(view);
    }

    /**
     * @param viewHolder
     * @param position   the position of the item to be drawn
     */
    @Override
    public void onBindViewHolder(AlbumsListAdapter.ViewHolder viewHolder, int position) {

        BandAlbum album = albums.get(position);

        viewHolder.title.setText(album.getTitle());
        viewHolder.type.setText(album.getType());
        viewHolder.year.setText(album.getYear());
    }

    /**
     * ViewHolder class
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public View itemView;

        @BindView(R.id.album_title)
        TextView title;

        @BindView(R.id.album_type)
        TextView type;

        @BindView(R.id.album_year)
        TextView year;

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
     * @param albums
     */
    public void updateAlbumsList(List<BandAlbum> albums) {
        this.albums = albums;
        notifyDataSetChanged();
    }

    /**
     * @return
     */
    @Override
    public int getItemCount() {
        return albums.size();
    }

}