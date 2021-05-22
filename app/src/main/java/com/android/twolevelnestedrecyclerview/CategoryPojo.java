package com.android.twolevelnestedrecyclerview;

import java.util.ArrayList;
import java.util.List;

public class CategoryPojo {

    String name,image;
    List<SubCategoryPojo> subCategoryPojosList;

    public String getCategoryName() {
        return name;
    }

    public void setCategoryName(String name) {
        this.name = name;
    }

    public String getCategoryImage() {
        return image;
    }

    public void setCategoryImage(String image) {
        this.image = image;
    }

    public List<SubCategoryPojo> getSubCategoryPojosList() {
        return subCategoryPojosList;
    }

    public void setSubCategoryPojosList(List<SubCategoryPojo> subCategoryPojosList) {
        this.subCategoryPojosList = subCategoryPojosList;
    }
}
