package com.android.twolevelnestedrecyclerview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        else{
            holder.arrowLayout.setVisibility(View.GONE);
            holder.view1.setVisibility(View.GONE);
        }

        if (cardIsOpen){
            holder.recyclerView.setVisibility(View.GONE);       // by default the card is closed so downArrow visible
            holder.view1.setVisibility(View.GONE);
        }

        holder.arrowLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cardIsOpen){
                    holder.downArrow.setVisibility(View.VISIBLE);
                    holder.upArrow.setVisibility(View.GONE);
                    holder.recyclerView.setVisibility(View.GONE);       // close card
                    holder.view1.setVisibility(View.GONE);
                    cardIsOpen = false;
                }else {
                    holder.downArrow.setVisibility(View.GONE);
                    holder.upArrow.setVisibility(View.VISIBLE);
                    holder.recyclerView.setVisibility(View.VISIBLE);
                    holder.view1.setVisibility(View.VISIBLE);
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
        ImageView imageView,downArrow,upArrow;
        CardView cardLayout;
        LinearLayout arrowLayout;
        View view1;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            imageView = itemView.findViewById(R.id.imageView);
            cardLayout = itemView.findViewById(R.id.cardLayout);
            arrowLayout = itemView.findViewById(R.id.arrowLayout);
            upArrow = itemView.findViewById(R.id.upArrow);
            view1 = itemView.findViewById(R.id.view1);
            recyclerView = itemView.findViewById(R.id.recyclerView2);
            downArrow = itemView.findViewById(R.id.downArrow);
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
        }
    }
}
