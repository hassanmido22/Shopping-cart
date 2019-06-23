package com.example.hassan.shopping_project.classes;

public class categories {

    private String  category , c_name ;
    private int c_id ;

    public categories() {

    }

    public categories(String category , String c_name , int c_id )
    {
        this.c_id = c_id;
        this.category = category;
        this.c_name = c_name;
    }

    public categories(String category , String c_name )
    {
        this.category = category;
        this.c_name = c_name;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }
    public void setC_name(String c_name) {
        this.c_name = c_name;
    }
    public void setCategory(String category) {
        this.category = category;
    }


    public int getC_id() {
        return c_id;
    }
    public String getC_name()
    {
        return c_name;
    }
    public String getCategory()
    {
        return category;
    }

}
