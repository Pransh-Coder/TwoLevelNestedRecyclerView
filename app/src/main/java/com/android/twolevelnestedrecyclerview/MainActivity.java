package com.android.twolevelnestedrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    RequestQueue requestQueue;

    List<CategoryPojo>categoryPojoList = new ArrayList<>();
    List<SubCategoryPojo> subCategoryPojoList = new ArrayList<>();
    List<SubSubCategoryPojo> subSubCategoryPojoArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.rozaanaonline.com/product/api/sub-category/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.e("onResponse", ""+response);

                JSONArray jsonArray = null;
                try {
                    jsonArray = new JSONArray(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i <jsonArray.length() ; i++) {

                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String nam = jsonObject.getString("name");
                        String img = jsonObject.getString("image");

                        //Log.e("categoryVal", ""+nam+"  "+img);

                        CategoryPojo categoryPojo = new CategoryPojo();
                        categoryPojo.setCategoryName(nam);
                        categoryPojo.setCategoryImage(img);

                        categoryPojoList.add(categoryPojo);

                        JSONArray jsonArray1 = jsonObject.getJSONArray("children");

                        for (int j = 0; j <jsonArray1.length() ; j++) {

                            JSONObject jsonObject1 = jsonArray1.getJSONObject(j);

                            subCategoryPojoList = new ArrayList<>();

                            String nameSub = jsonObject1.getString("name");
                            String imageSub = jsonObject1.getString("image");

                            //Log.e("subCategoryVal", ""+nameSub+"  "+imageSub);

                            SubCategoryPojo subCategoryPojo = new SubCategoryPojo();
                            subCategoryPojo.setSubCategoryName(nameSub);
                            subCategoryPojo.setSubCategoryImage(imageSub);

                            subCategoryPojoList.add(subCategoryPojo);

                            categoryPojo.setSubCategoryPojosList(subCategoryPojoList);

                            //categoryPojoList.add(categoryPojo);
                            //subCategoryPojoList.add(subCategoryPojo);

                            JSONArray jsonArray2 = jsonObject1.getJSONArray("children");

                            for (int k = 0; k <jsonArray2.length();k++) {

                                JSONObject jsonObject2 = jsonArray2.getJSONObject(k);

                                subSubCategoryPojoArrayList = new ArrayList<>();

                                String nameSubSub = jsonObject2.getString("name");
                                String imgSubSub = jsonObject2.getString("image");

                                SubSubCategoryPojo subSubCategoryPojo = new SubSubCategoryPojo();
                                subSubCategoryPojo.setSubSubCategoryName(nameSubSub);
                                subSubCategoryPojo.setSubSubCategoryImage(imgSubSub);

                                subSubCategoryPojoArrayList.add(subSubCategoryPojo);

                                subCategoryPojo.setSubSubCategoryPojosList(subSubCategoryPojoArrayList);

                                //subCategoryPojoList.add(subCategoryPojo);

                            }
                            //Log.e("subSubCategPojoListSize", ""+subSubCategoryPojoArrayList.size());

                        }
                        //Log.e("subCategoryPojoListSize", ""+subCategoryPojoList.size());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Log.e("categoryPojoListSize", ""+categoryPojoList.size());
                }
                Log.e("categoryPojoListSize", ""+categoryPojoList.size());
                Log.e("subCategoryPojoListSize", ""+subCategoryPojoList.size());
                Log.e("subSubCategPojoListSize", ""+subSubCategoryPojoArrayList.size());
                adapter = new RecyclerAdapterCategory(MainActivity.this,categoryPojoList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(stringRequest);

    }
}