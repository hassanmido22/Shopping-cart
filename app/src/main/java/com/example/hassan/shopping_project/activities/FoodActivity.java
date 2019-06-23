package com.example.hassan.shopping_project.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hassan.shopping_project.classes.categories;
import com.example.hassan.shopping_project.classes.orders;

import com.example.hassan.shopping_project.R;
import com.example.hassan.shopping_project.classes.DBHelper;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class FoodActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayout_parent , linearLayout_ch1 ;
    TextView txt ;
    ArrayList<EditText> data_editText_array = new ArrayList<>();
    Button plus_btn ,minus_btn , add ;
    DBHelper db = new DBHelper(this);
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
try {


    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.lin);

    categories c ;

    //Toast.makeText(this, db.orders(), Toast.LENGTH_SHORT).show();

    // parent linear layout that contains the row :

    int DP_15 = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 15, getResources()
                    .getDisplayMetrics());

    int DP_5 = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 5, getResources()
                    .getDisplayMetrics());

    int DP_20 = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 20, getResources()
                    .getDisplayMetrics());


    int DP_2 = (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 2, getResources()
                    .getDisplayMetrics());

    add = new Button(this);


    for (int i = 0; i < db.num_of_cats(); i++) {

        // initialization of items :

        c = db.retrieve_data().get(i);

        linearLayout_parent = new LinearLayout(this);
        linearLayout_ch1 = new LinearLayout(this);
        txt = new TextView(this);
        EditText data_editText = new EditText(this);
        plus_btn = new Button(this);
        minus_btn = new Button(this);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        lp.setMargins(DP_20, DP_15, DP_20, DP_15);
        linearLayout_parent.setLayoutParams(lp);
        linearLayout_parent.setPadding(DP_5, DP_5, DP_5, DP_5);
        linearLayout_parent.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        linearLayout_parent.setWeightSum(90);
        linearLayout_parent.setOrientation(LinearLayout.HORIZONTAL);

        // left text that identifies the category name :

        LinearLayout.LayoutParams txt_lp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        txt_lp.weight = 60;
        txt_lp.gravity = Gravity.CENTER;
        txt.setLayoutParams(txt_lp);
        txt.setText(c.getCategory());
        txt.setTextColor(getResources().getColor(R.color.colorPrimary));
        txt.setTextSize(25);
        txt.setTypeface(Typeface.MONOSPACE);

        // layout that contains the buttons to increase or decrease 1 time :
/*
        LinearLayout.LayoutParams ch1_lp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.MATCH_PARENT);

        ch1_lp.weight = 20;
        linearLayout_ch1.setLayoutParams(ch1_lp);
        linearLayout_ch1.setWeightSum(10);
        linearLayout_ch1.setOrientation(LinearLayout.VERTICAL);

*/
        // the two buttons that increases or decreases 1 time :

        /*
        LinearLayout.LayoutParams btn_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                0);

        btn_lp.weight = 5;
        btn_lp.setMargins(DP_2, DP_2, DP_2, DP_2);
        plus_btn.setLayoutParams(btn_lp);
        minus_btn.setLayoutParams(btn_lp);

        plus_btn.setTypeface(Typeface.MONOSPACE);
        minus_btn.setTypeface(Typeface.MONOSPACE);

        plus_btn.setText("add");
        minus_btn.setText("minus");

        plus_btn.setTextColor(getResources().getColor(R.color.colorPrimary));
        minus_btn.setTextColor(getResources().getColor(R.color.colorPrimary));

        plus_btn.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
        minus_btn.setBackgroundColor(getResources().getColor(R.color.colorAccent2));

*/

        // edit text that get all data

        LinearLayout.LayoutParams edit_lp = new LinearLayout.LayoutParams(0,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        edit_lp.gravity = Gravity.CENTER;
        edit_lp.weight = 30;

        data_editText.setLayoutParams(edit_lp);
        data_editText.setText("0");
        data_editText.setTextSize(25);
        data_editText.setGravity(1);
        data_editText.setTextColor(getResources().getColor(R.color.colorPrimary));
        data_editText.setTypeface(Typeface.MONOSPACE);

        data_editText_array.add(data_editText);

        // add all views to the parent layout

        linearLayout.addView(linearLayout_parent);

        linearLayout_parent.addView(txt);

        //linearLayout_ch1.addView(plus_btn);
        //linearLayout_ch1.addView(minus_btn);

        //linearLayout_parent.addView(linearLayout_ch1);

        linearLayout_parent.addView(data_editText);

    }


    LinearLayout.LayoutParams add_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    add_lp.setMargins(DP_20,DP_5,DP_20,DP_5);
    add_lp.gravity=Gravity.CENTER;

    add.setLayoutParams(add_lp);
    add.setPadding(DP_20,DP_5,DP_20,DP_5);
    add.setText("add");
    add.setTextSize(17);
    add.setTypeface(Typeface.MONOSPACE);
    add.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
    add.setTextColor(getResources().getColor(R.color.colorPrimary));
    add.setId(R.id.add);

    add.setOnClickListener(this);

    linearLayout.addView(add);


}
catch (Exception e)
{
    Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId())
        {
            case R.id.history:
                i = new Intent(this,history.class);
                startActivity(i);
                break;
            case R.id.profile:
                i = new Intent(this,profile.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);


    }

    @Override
    public void onClick(View v) {
        orders order ;
        switch (v.getId()) {
            case R.id.add:
                try {
                    if (!db.is_inserted_cart()) {
                        if (Integer.parseInt(data_editText_array.get(0).getText().toString()) >= 0) {
                            order = new orders(db.get_CID("Chicken"), Integer.parseInt(db.max()) + 1, db.login_ID(),
                                    Integer.parseInt(data_editText_array.get(0).getText().toString()));
                            db.order_insert(order);
                        } else {
                            Toast.makeText(this, "Enter positive number !", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (Integer.parseInt(data_editText_array.get(1).getText().toString()) >= 0) {
                            order = new orders(db.get_CID("Beef"), Integer.parseInt(db.max()) + 1, db.login_ID(),
                                    Integer.parseInt(data_editText_array.get(1).getText().toString()));
                            db.order_insert(order);

                        } else {
                            Toast.makeText(this, "Enter positive number !", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        if (Integer.parseInt(data_editText_array.get(2).getText().toString()) >= 0) {
                            order = new orders(db.get_CID("rice"), Integer.parseInt(db.max()) + 1, db.login_ID(),
                                    Integer.parseInt(data_editText_array.get(2).getText().toString()));
                            db.order_insert(order);
                        } else {
                            Toast.makeText(this, "Enter positive number !", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    else
                    {
                        if (Integer.parseInt(data_editText_array.get(0).getText().toString()) >= 0) {
                            order = new orders(Integer.parseInt(data_editText_array.get(0).getText().toString()));
                            db.order_update_chick(order);
                        }
                        else {
                            Toast.makeText(this, "Enter positive number !", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        if (Integer.parseInt(data_editText_array.get(1).getText().toString()) >= 0) {
                            order = new orders(Integer.parseInt(data_editText_array.get(1).getText().toString()));
                            db.order_update_beef(order);

                        } else {
                            Toast.makeText(this, "Enter positive number !", Toast.LENGTH_SHORT).show();
                            break;
                        }


                        if (Integer.parseInt(data_editText_array.get(2).getText().toString()) >= 0) {

                            order = new orders(Integer.parseInt(data_editText_array.get(2).getText().toString()));
                            db.order_update_rice(order);
                        } else {
                            Toast.makeText(this, "Enter positive number !", Toast.LENGTH_SHORT).show();
                            break;
                        }

                    }

                    Intent i = new Intent(this, shopping_itemss.class);
                    startActivity(i);
                }
                catch (Exception e)
                {
                    Toast.makeText(this, "Enter in empty fields !", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

