package com.example.hassan.shopping_project.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hassan.shopping_project.R;
import com.example.hassan.shopping_project.classes.DBHelper;
import com.example.hassan.shopping_project.classes.orders;
import com.example.hassan.shopping_project.classes.categories;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linearLayout_parent , linearLayout_ch1 ;
    TextView txt_cat , txt_data ;
    Button addtohistory ;
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        try {

            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.cart);

            categories c ;
            String num ;

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

            addtohistory = new Button(this);

            for (int i = 0; i < db.num_of_cats(); i++) {

                // initialization of items :

                c = db.retrieve_data().get(i);
                if(db.is_inserted_cart()) {
                    num = db.retrieve_orders_cart().get(i);
                }
                else
                {
                    num = "empty";
                }


                linearLayout_parent = new LinearLayout(this);
                linearLayout_ch1 = new LinearLayout(this);
                txt_cat = new TextView(this);
                txt_data = new TextView(this);

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
                txt_cat.setLayoutParams(txt_lp);
                txt_cat.setText(c.getCategory());
                txt_cat.setTextColor(getResources().getColor(R.color.colorPrimary));
                txt_cat.setTextSize(25);
                txt_cat.setTypeface(Typeface.MONOSPACE);

                LinearLayout.LayoutParams edit_lp = new LinearLayout.LayoutParams(0,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                edit_lp.gravity = Gravity.CENTER;
                edit_lp.weight = 30;

                txt_data.setLayoutParams(edit_lp);
                txt_data.setText(num);
                txt_data.setTextSize(20);
                txt_data.setGravity(1);
                txt_data.setTextColor(getResources().getColor(R.color.colorPrimary));
                txt_data.setTypeface(Typeface.MONOSPACE);


                // add all views to the parent layout

                linearLayout.addView(linearLayout_parent);

                linearLayout_parent.addView(txt_cat);
                linearLayout_parent.addView(txt_data);

            }

            LinearLayout.LayoutParams add_lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            add_lp.setMargins(DP_20,DP_20,DP_20,DP_20);
            add_lp.gravity=Gravity.CENTER;

            addtohistory.setLayoutParams(add_lp);
            addtohistory.setPadding(DP_20,DP_5,DP_20,DP_5);
            addtohistory.setText("Add to history");
            addtohistory.setAllCaps(false);
            addtohistory.setTextSize(19);
            addtohistory.setTypeface(Typeface.MONOSPACE);
            addtohistory.setBackgroundColor(getResources().getColor(R.color.colorAccent2));
            addtohistory.setTextColor(getResources().getColor(R.color.colorPrimary));
            addtohistory.setId(R.id.add);

            addtohistory.setOnClickListener(this);

            linearLayout.addView(addtohistory);
    }
    catch (Exception e)
    {
        Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
    }

    }


    @Override
    public void onClick(View v) {
    orders order;
        switch (v.getId())
        {
            case R.id.add:
                try {
                    order = new orders(true);
                    db.insert_order_history(order);
                    Intent i = new Intent(this,shopping_itemss.class);
                    startActivity(i);
                }
                catch (Exception e)
                {

                }
        }
    }
}
