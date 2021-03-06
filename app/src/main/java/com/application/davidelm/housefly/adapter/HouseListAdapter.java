package com.application.davidelm.housefly.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.davidelm.housefly.model.House;
import com.application.subitoit.githubstargazers.R;
import com.bumptech.glide.Glide;

import java.util.List;


public class HouseListAdapter extends RecyclerView.Adapter<HouseListAdapter.ViewHolder> {
    private List<?> items;

    public HouseListAdapter(List<?> devices) {
        items = devices;
    }

    @Override
    public HouseListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.stargazer_item, viewGroup, false);
        return new HouseListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder vh, int position) {
        House stargazer = (House) items.get(position);
        setAvatar(vh, stargazer.getAvatarUrl());
        vh.usernameTextview.setText(stargazer.getLogin());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    /**
     * set avatar on user - Glide lib to handle pics
     * @param vh
     * @param avatarUrl
     */
    private void setAvatar(ViewHolder vh, String avatarUrl) {
        if (avatarUrl == null) {
            Glide.clear(vh.avatarImageView);
            return;
        }

        Glide.with(vh.itemView.getContext())
                .load(avatarUrl)
                .placeholder(R.mipmap.github_placeholder)
                .into(vh.avatarImageView);
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView usernameTextview;
        private final ImageView avatarImageView;

        /**
         *
         * @param view
         */
        private ViewHolder(View view) {
            super(view);
            usernameTextview = (TextView) view.findViewById(R.id.usernameTextViewId);
            avatarImageView = (ImageView) view.findViewById(R.id.avatarImageViewId);
        }


    }

}
