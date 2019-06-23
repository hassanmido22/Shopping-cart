package com.example.hassan.shopping_project.activities;

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

public class update_profile extends AppCompatActivity implements View.OnClickListener {

    EditText username , email , pass , age ;
    Button save;
    DBHelper db =new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        username = (EditText) findViewById(R.id.username_edit);
        email = (EditText) findViewById(R.id.email_edit);
        pass = (EditText) findViewById(R.id.password_edit);
        age = (EditText) findViewById(R.id.age_edit);

        save = (Button) findViewById(R.id.save_btn);
        save.setOnClickListener(this);

        try {
            person p = new person();

            db.retrieve_person_data(p);

            pass.setText(p.getPass());
            username.setText(p.getUsername());
            age.setText(p.getAge()+"");
            email.setText(p.getEmail());
        }
        catch (Exception e)
        {
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        person k ;
        switch (R.id.save_btn)
        {
            case R.id.save_btn:
                try{

                    String username_edit = username.getText().toString();
                    String pass_edit = pass.getText().toString();
                    String email_edit = email.getText().toString();
                    int age_edit = Integer.parseInt(age.getText().toString());
                    k=new person(username_edit,pass_edit,email_edit,age_edit);
                    db.PersonUpdate(k);
                    Intent i = new Intent(this,profile.class);
                    startActivity(i);
                }
                catch(Exception e)
                {
                    Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
