package com.example.hassan.shopping_project.classes;

public class orders {

    private int c_id , o_id , p_id , num_of_item;
    boolean state ;

    public orders(int c_id, int o_id, int p_id, int num_of_item) {
        this.c_id = c_id;
        this.o_id = o_id;
        this.p_id = p_id;
        this.num_of_item = num_of_item;
        state = false;
    }

    public orders(int num_of_item) {
        this.num_of_item = num_of_item;
    }

    public orders(boolean state) {
        this.state = state;
    }

    public orders() {

    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public void setNum_of_item(int num_of_item) {
        this.num_of_item = num_of_item;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getC_id() {
        return c_id;
    }

    public int getNum_of_item() {
        return num_of_item;
    }

    public int getO_id() {
        return o_id;
    }

    public int getP_id() {
        return p_id;
    }

    public boolean getState() {
        return state;
    }
}
