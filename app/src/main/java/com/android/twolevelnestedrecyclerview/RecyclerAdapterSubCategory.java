package com.android.twolevelnestedrecyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterSubCategory  extends RecyclerView.Adapter<RecyclerAdapterSubCategory.ViewHolder> {

    Context context;
    List<SubCategoryPojo> subCategoryPojoList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    boolean cardIsOpen = true;

    public RecyclerAdapterSubCategory(Context context, List<SubCategoryPojo> subCategoryPojoList) {
        this.context = context;
        this.subCategoryPojoList=subCategoryPojoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_list, parent, false);
        return new RecyclerAdapterSubCategory.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("adapterSubCategory",""+subCategoryPojoList.size());
        holder.subCategoryTitle.setText(subCategoryPojoList.get(position).getSubCategoryName());

        if (subCategoryPojoList.get(position).getSubSubCategoryPojosList()!=null){

            adapter = new RecyclerAdapterSubSubCategory(context,subCategoryPojoList.get(position).getSubSubCategoryPojosList());
            //Log.e("om_bind_view_size:",""+categoryPojoList.get(position).getFormLists().size());
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
        return subCategoryPojoList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;
        TextView subCategoryTitle;
        ImageView imageView,downArrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            subCategoryTitle = itemView.findViewById(R.id.subCategoryTitle);
            imageView = itemView.findViewById(R.id.imageView);

            recyclerView = itemView.findViewById(R.id.recyclerView3);
            layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            downArrow = itemView.findViewById(R.id.downArrow);


        }
    }

}
