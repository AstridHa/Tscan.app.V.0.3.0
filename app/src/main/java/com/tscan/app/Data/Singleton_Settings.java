package com.tscan.app.Data;

public class Singleton_Settings {

    private static Singleton_Settings settings_instance;
    public Singleton_Settings() {}

    public synchronized static Singleton_Settings getsettings_instance() {
        if (settings_instance == null) {
            settings_instance = new Singleton_Settings();
        }
        return settings_instance;
    }

    public static void setsettings_instance(Singleton_Settings settings_instance) {
        Singleton_Settings.settings_instance = settings_instance;
    }


    /** SETTINGS DATA **/
    private String token;
    private String mobile_device_name;
    private String mobile_device_serial_number;
    private int company_id;
    private String company_name;
    private int department_id;
    private String department_name;
    private String iss;
    private String exp;
    private String iat;
    /** CURRENT USER INFORMATIONS **/
    private String current_user;
    /** FOOD CATEGORY **/
//    private String food_category;



    /** SETTINGS DATA **/
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile_device_name() {
        return mobile_device_name;
    }

    public void setMobile_device_name(String mobile_device_name) {
        this.mobile_device_name = mobile_device_name;
    }

    public String getMobile_device_serial_number() {
        return mobile_device_serial_number;
    }

    public void setMobile_device_serial_number(String mobile_device_serial_number) {
        this.mobile_device_serial_number = mobile_device_serial_number;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getIat() {
        return iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }



    /** CURRENT USER INFORMATIONS **/
    public String getCurrent_user() {
        return current_user;
    }

    public void setCurrent_user(String current_user) {
        this.current_user = current_user;
    }


//    /** FOOD CATEGORY **/
//    public String getFood_category() {
//        return food_category;
//    }
//
//    public void setFood_category(Integer food_category) {
//        this.food_category = food_category;
//    }
}
