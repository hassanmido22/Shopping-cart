package com.example.hassan.shopping_project.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hassan.shopping_project.R;
import com.example.hassan.shopping_project.classes.DBHelper;

public class login extends AppCompatActivity implements View.OnClickListener{

    EditText user_name , password ;
    Button login , register ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_name = (EditText) findViewById(R.id.user_editText);
        password = (EditText) findViewById(R.id.Pass_editText);
        login = (Button) findViewById(R.id.log_btn);
        register = (Button) findViewById(R.id.reg_btn);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId())
        {
            case R.id.log_btn:
                try
                {
                    DBHelper db = new DBHelper(this);
                    String uname = user_name.getText().toString();
                    String pass = password.getText().toString();

                    String result = db.s_login(uname);

                    if (result.equals(pass)) {
                        db.Login_insert(uname);
                        if(db.Login_search() == 1)
                        {
                            db.Category_insert_chick();
                            db.Category_insert_beef();
                            db.Category_insert_rice();
                            Toast.makeText(this, "Inserted !", Toast.LENGTH_SHORT).show();
                        }

                        i = new Intent(this,shopping_itemss.class);
                        startActivity(i);

                    }

                    else {
                        Toast.makeText(this, "Email or password don't match", Toast.LENGTH_SHORT).show();
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(this, 1 +"--"+ e+"", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.reg_btn:
                i = new Intent(this,registeration.class);
                startActivity(i);
                break;
            }
    }
}
