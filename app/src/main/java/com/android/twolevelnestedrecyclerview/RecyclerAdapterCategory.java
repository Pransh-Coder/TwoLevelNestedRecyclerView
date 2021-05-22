package com.android.twolevelnestedrecyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterCategory extends RecyclerView.Adapter<RecyclerAdapterCategory.ViewHolder> {

    List<CategoryPojo> categoryPojoList = new ArrayList<>();
    Context context;
    boolean cardIsOpen = true;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public RecyclerAdapterCategory(Context context, List<CategoryPojo>categoryPojoList) {
        Log.d("RecyclerAdapterCategory","constructor called");
        this.context = context;
        this.categoryPojoList = categoryPojoList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list, parent, false);
        return new RecyclerAdapterCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("insideOnBind",""+categoryPojoList.size());
        holder.categoryTitle.setText(categoryPojoList.get(position).getCategoryName());
        //holder.tot_temp.setText(categoryPojoList.get(position).getTotal_templates());

        if (categoryPojoList.get(position).getSubCategoryPojosList()!=null){

            adapter = new RecyclerAdapterSubCategory(context,categoryPojoList.get(position).getSubCategoryPojosList());
            //Log.e("RecyclerAdapterCategory",""+categoryPojoList.get(position).getSubCategoryPojosList().size());
            holder.recyclerView.setAdapter(adapter);
        }

        if (cardIsOpen){
            holder.recyclerView.setVisibility(View.GONE);
        }

        holder.downArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardIsOpen){
                    holder.recyclerView.setVisibility(View.GONE);       // close card
                    cardIsOpen = false;
                }else {
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    cardIsOpen = true;
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryPojoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView categoryTitle;
        ImageView imageView,downArrow;
        CardView cardLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            imageView = itemView.findViewById(R.id.imageView);
            cardLayout = itemView.findViewById(R.id.cardLayout);
            recyclerView = itemView.findViewById(R.id.recyclerView2);
            downArrow = itemView.findViewById(R.id.downArrow);
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
