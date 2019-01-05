package com.feedfacts.example.factfeeds.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.feedfacts.example.factfeeds.R;
import com.feedfacts.example.factfeeds.model.FactFeedRow;
import com.feedfacts.example.factfeeds.network.NetworkImageLoader;

import java.util.List;

public class FactFeedAdapter extends RecyclerView.Adapter<FactFeedAdapter.ViewHolder>  {
    private List<FactFeedRow> mFeedRows;

    public FactFeedAdapter(List<FactFeedRow> mFeedRows) {
        this.mFeedRows = mFeedRows;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.fact_feed_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final FactFeedRow row = mFeedRows.get(position);
        final Context context = holder.imgFactFeed.getContext();

        setRowTitle(holder.txtTitle, row.getTitle(), context);
        setRowDescription(holder.txtDescription, row.getDescription(), context);

        //Loads the image.
        final NetworkImageLoader networkImageLoader =
                new NetworkImageLoader.ImageLoaderBuilder(row.getImageHref(), context,
                        holder.imgFactFeed).build();
        networkImageLoader.load();
    }

    /**
     * Sets feeds title.
     *
     * @param tvTitle
     * @param title
     * @param context
     */
    private void setRowTitle(TextView tvTitle, String title, Context context) {
        tvTitle.setText(TextUtils.isEmpty(title) ? context.getString(R.string.no_title) : title);
    }

    /**
     * Sets feeds description.
     *
     * @param tvTitle
     * @param title
     * @param context
     */
    private void setRowDescription(TextView tvTitle, String title, Context context) {
        tvTitle.setText(TextUtils.isEmpty(title) ? context.getString(R.string.no_description) : title);
    }

    @Override
    public int getItemCount() {
        return null != mFeedRows ? mFeedRows.size() : 0;
    }

    public void refreshList(List<FactFeedRow> rows) {
        this.mFeedRows = rows;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFactFeed;
        TextView txtDescription;
        TextView txtTitle;

        ViewHolder(View view) {
            super(view);

            imgFactFeed = view.findViewById(R.id.fact_image);
            txtDescription = view.findViewById(R.id.txt_fact_description);
            txtTitle = view.findViewById(R.id.txt_title);

        }
    }
}
