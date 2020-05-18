package com.f_a.project_android_unknown.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.f_a.project_android_unknown.model.ModelLogin;
import com.f_a.project_android_unknown.model.ModelMovie;
import com.f_a.project_android_unknown.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ListViewHolder> implements Filterable {
    private List<ModelMovie> LMMovie;
    private List<ModelMovie> LMMovieFull;

    public AdapterMovie(List<ModelMovie> list) {
        this.LMMovie = list;
        LMMovieFull = new ArrayList<>(list);
    }

    private OnItemClickCallback onItemClickCallback;
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_bioskop, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        ModelMovie modelMovie = LMMovie.get(position);

        Glide.with(holder.itemView.getContext()).load(modelMovie.getPhoto()).into(holder.imgPhoto);

        holder.tvName.setText(modelMovie.getName());
        holder.tvPrice.setText(modelMovie.getPrice());
        holder.tvTime.setText(modelMovie.getTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(LMMovie.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return LMMovie.size();
    }

    @Override
    public Filter getFilter() {
        return FilterMovie;
    }

    private Filter FilterMovie = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ModelMovie> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(LMMovieFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ModelMovie item : LMMovieFull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            LMMovie.clear();
            LMMovie.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvPrice, tvTime;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvPrice = itemView.findViewById(R.id.tv_item_price);
            tvTime = itemView.findViewById(R.id.tv_item_time);
        }
    }

    public interface OnItemClickCallback {
        void onItemClicked(ModelMovie data);
    }
}
