package com.android.twolevelnestedrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterSubSubCategory extends RecyclerView.Adapter<RecyclerAdapterSubSubCategory.ViewHolder> {

    Context context;
    List<SubSubCategoryPojo> subSubCategoryPojoList = new ArrayList<>();

    public RecyclerAdapterSubSubCategory(Context context, List<SubSubCategoryPojo> subSubCategoryPojoList) {
        this.context = context;
        this.subSubCategoryPojoList = subSubCategoryPojoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_sub_category_list, parent, false);
        return new RecyclerAdapterSubSubCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.subSubCategoryTitle.setText(subSubCategoryPojoList.get(position).getSubSubCategoryName());
    }

    @Override
    public int getItemCount() {
        return subSubCategoryPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView subSubCategoryTitle;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subSubCategoryTitle = itemView.findViewById(R.id.sub_sub_category);
            imageView = itemView.findViewById(R.id.imageView);


        }
    }
}
