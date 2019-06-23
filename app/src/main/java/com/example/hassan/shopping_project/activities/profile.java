package com.example.hassan.shopping_project.activities;

import android.app.Person;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hassan.shopping_project.R;

import com.example.hassan.shopping_project.classes.DBHelper;
import com.example.hassan.shopping_project.classes.person;


public class profile extends AppCompatActivity implements View.OnClickListener{

    TextView username , email , pass , age ;
    Button update ,back;
    DBHelper db =new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        username = (TextView) findViewById(R.id.username_txt);
        email = (TextView) findViewById(R.id.email_txt);
        pass = (TextView) findViewById(R.id.password_txt);
        age = (TextView) findViewById(R.id.age_txt);

        update = (Button) findViewById(R.id.update_btn);
        back = (Button) findViewById(R.id.back_btn);

        update.setOnClickListener(this);
        back.setOnClickListener(this);


        person p = new person();

        db.retrieve_person_data(p);

        pass.setText(p.getPass());
        username.setText(p.getUsername());
        age.setText(p.getAge()+"");
        email.setText(p.getEmail());

    }

    @Override
    public void onClick(View v) {
        Intent i;

        switch (v.getId())
        {
            case R.id.update_btn:
                i = new Intent(this,update_profile.class);
                startActivity(i);
                finish();
                break;

            case R.id.back_btn:
                i = new Intent(this,FoodActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}
