package com.tscan.app.UI_listeners;

public class UI_Listener_Recording {
    private Integer category_selected;
    private String Type_selected;
    private String remedial_selected;


    private OnFoodCategorySelected foodCategorySelected;
    private OnFoodTypeSelected foodTypeSelected;
    private OnRemedialActionSelected remedialActionSelected;


    public void callFoodCategorySelected(Integer categorySelected, String food_category_name, int category_pass_temperature){
        category_selected = categorySelected;

        if(foodCategorySelected != null) foodCategorySelected.onFoodCategorySelected(categorySelected, food_category_name, category_pass_temperature);
    }

    public OnFoodCategorySelected getfoodCategorySelected(){
        return foodCategorySelected;
    }

    public void setfoodCategorySelected(OnFoodCategorySelected food_Category_Selected){
        this.foodCategorySelected = food_Category_Selected;
    }

    public interface OnFoodCategorySelected{
        void onFoodCategorySelected(Integer category_selected, String food_category_name, int category_pass_temperature);
    }




    public void callFoodTypeSelected(int type_selected_id,  String TypeSelected_name, int type_pass_temperature){
        Type_selected = TypeSelected_name;

        if(foodTypeSelected != null) foodTypeSelected.onFoodTypeSelected(type_selected_id, TypeSelected_name, type_pass_temperature);
    }

    public OnFoodTypeSelected getfoodTypeSelected(){
        return foodTypeSelected;
    }

    public void setfoodTypeSelected(OnFoodTypeSelected onFoodTypeSelected){
        this.foodTypeSelected = onFoodTypeSelected;
    }

    public interface OnFoodTypeSelected{
        void onFoodTypeSelected(int type_selected_id, String type_selected, int type_pass_temperature);
    }




    public void callRemedialActionSelected(String remedialSelected_name, int remedialSelected_id, int corrective_action_haccp_task_result_type_id){
        remedial_selected = remedialSelected_name;

        if(remedialActionSelected != null) remedialActionSelected.onRemedialActionSelected(remedialSelected_name, remedialSelected_id, corrective_action_haccp_task_result_type_id);
    }

    public OnRemedialActionSelected getRemedialActionSelected(){
        return remedialActionSelected;
    }

    public void setRemedialActionSelected(OnRemedialActionSelected onRemedialActionSelected){
        this.remedialActionSelected = onRemedialActionSelected;
    }

    public interface OnRemedialActionSelected{
        void onRemedialActionSelected(String remedialActionSelected, int remedialSelected_id, int corrective_action_haccp_task_result_type_id);
    }




}
