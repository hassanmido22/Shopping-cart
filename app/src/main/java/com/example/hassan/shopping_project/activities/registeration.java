package com.example.hassan.shopping_project.activities;

import android.content.Intent;
import android.support.v4.app.Person;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hassan.shopping_project.R;
import com.example.hassan.shopping_project.classes.person;

import com.example.hassan.shopping_project.classes.DBHelper;

import java.util.regex.Pattern;

public class registeration extends AppCompatActivity implements View.OnClickListener{

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    EditText username , pass , age , email ,confirm_pass;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);


        username = (EditText) findViewById(R.id.user_name);
        pass = (EditText) findViewById(R.id.password);
        confirm_pass = (EditText) findViewById(R.id.conf_password);
        email = (EditText) findViewById(R.id.email);
        age = (EditText) findViewById(R.id.age);
        register = (Button) findViewById(R.id.register);

        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        person p ;
        DBHelper d = new DBHelper(this);
        switch (v.getId())
        {
            case R.id.register:
                try
                {
                    int validation = 0 ;
                    String unamee = username.getText().toString();
                    String passs = pass.getText().toString();
                    String C_passs = confirm_pass.getText().toString();
                    String emaill = email.getText().toString();
                    int agee = Integer.parseInt(age.getText().toString());

                    if(unamee.length()>15 || unamee.length()<5)
                    {
                        Toast.makeText(this, "Enter a valid username !", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        validation++;
                    }


                    if(passs.equals(C_passs) && PASSWORD_PATTERN.matcher(passs).matches())
                    {
                        validation++;
                    }
                    else
                    {
                        Toast.makeText(this, "Enter a valid password !", Toast.LENGTH_SHORT).show();
                    }

                    if(Patterns.EMAIL_ADDRESS.matcher(emaill).matches())
                    {

                        validation++;
                    }
                    else
                        Toast.makeText(this, "Enter a valid email !", Toast.LENGTH_SHORT).show();


                    if(agee > 110 || agee < 5)
                    {
                        Toast.makeText(this, "Enter a valid age !", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        validation++;
                    }

                    if(validation == 4)
                    {
                        p = new person(unamee,passs,emaill,agee);
                        d.PersonInsert(p);
                        Intent i = new Intent(this, login.class);
                        startActivity(i);
                    }

                }

                catch (Exception e)
                {
                    Toast.makeText(this, "retry register", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
