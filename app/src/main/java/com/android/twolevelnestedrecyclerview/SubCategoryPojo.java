package com.android.twolevelnestedrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryPojo {

    String name,image;
    List<SubSubCategoryPojo> subSubCategoryPojosList;

    public String getSubCategoryName() {
        return name;
    }

    public void setSubCategoryName(String name) {
        this.name = name;
    }

    public String getSubCategoryImage() {
        return image;
    }

    public void setSubCategoryImage(String image) {
        this.image = image;
    }

    public List<SubSubCategoryPojo> getSubSubCategoryPojosList() {
        return subSubCategoryPojosList;
    }

    public void setSubSubCategoryPojosList(List<SubSubCategoryPojo> subSubCategoryPojosList) {
        this.subSubCategoryPojosList = subSubCategoryPojosList;
    }
}
