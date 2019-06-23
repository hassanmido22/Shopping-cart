package com.example.hassan.shopping_project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hassan.shopping_project.R;
import com.example.hassan.shopping_project.classes.DBHelper;

public class shopping_itemss extends AppCompatActivity implements View.OnClickListener {

    Button food , sports , cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_itemss);

        DBHelper db = new DBHelper(this);
        food = (Button) findViewById(R.id.food);
        sports = (Button) findViewById(R.id.sports);
        cart = (Button) findViewById(R.id.cart);

        food.setOnClickListener(this);
        sports.setOnClickListener(this);
        cart.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.food:
                i = new Intent(this,FoodActivity.class);
                startActivity(i);
                break;

            case R.id.sports:
                i = new Intent(this,SportsActivity.class);
                startActivity(i);
                break;

            case R.id.cart:
                i = new Intent(this,CartActivity.class);
                startActivity(i);
                break;
        }
    }
}
