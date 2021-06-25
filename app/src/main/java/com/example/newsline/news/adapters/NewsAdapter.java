package com.example.newsline.news.adapters;

import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.newsline.R;
import com.example.newsline.news.Hit;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    public interface OnNewsAdapterListener {
        void onItemClick(Hit item);
    }

    private final List<Hit> mValues;
    private OnNewsAdapterListener listener;

    public NewsAdapter(OnNewsAdapterListener listener, List<Hit> items) {
        mValues = items;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Picasso.get().load(Uri.parse(mValues.get(position).getImageUrl())).into(holder.imageView);
        holder.tvTitle.setText(mValues.get(position).getTitle());
        holder.tvDescription.setText(mValues.get(position).getDescription());
        holder.tvPubDate.setText(mValues.get(position).getPubDate());
        holder.tvAuthors.setText(mValues.get(position).getAuthors().toString());

        holder.mView.setOnClickListener(v -> {
            listener.onItemClick(mValues.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public final ImageView imageView;
        public final TextView tvTitle, tvDescription, tvPubDate, tvAuthors;

        public Hit mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            imageView = view.findViewById(R.id.imageView);
            tvTitle = view.findViewById(R.id.tvTitle);
            tvDescription = view.findViewById(R.id.tvDescription);
            tvPubDate = view.findViewById(R.id.tvPubDate);
            tvAuthors = view.findViewById(R.id.tvAuthors);
        }

    }
}
