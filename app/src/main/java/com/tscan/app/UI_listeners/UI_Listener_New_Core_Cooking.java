package com.tscan.app.UI_listeners;

public class UI_Listener_New_Core_Cooking {
    private Integer category_selected;
    private String Type_selected;


    private OnFoodCategorySelected foodCategorySelected;
    private OnFoodTypeSelected foodTypeSelected;


    public void callFoodCategorySelected(Integer categorySelected, String food_category_name){
        category_selected = categorySelected;

        if(foodCategorySelected != null) foodCategorySelected.onFoodCategorySelected(categorySelected, food_category_name);
    }

    public OnFoodCategorySelected getfoodCategorySelected(){
        return foodCategorySelected;
    }

    public void setfoodCategorySelected(OnFoodCategorySelected food_Category_Selected){
        this.foodCategorySelected = food_Category_Selected;
    }

    public interface OnFoodCategorySelected{
        void onFoodCategorySelected(Integer category_selected, String food_category_name);
    }




    public void callFoodTypeSelected(String TypeSelected){
        Type_selected = TypeSelected;

        if(foodTypeSelected != null) foodTypeSelected.onFoodTypeSelected(TypeSelected);
    }

    public OnFoodTypeSelected getfoodTypeSelected(){
        return foodTypeSelected;
    }

    public void setfoodTypeSelected(OnFoodTypeSelected onFoodTypeSelected){
        this.foodTypeSelected = onFoodTypeSelected;
    }

    public interface OnFoodTypeSelected{
        void onFoodTypeSelected(String type_selected);
    }
}
