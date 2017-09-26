package com.project.MyNutritionApplication.mynutrition.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;
import com.project.MyNutritionApplication.mynutrition.R;

public class Mailing extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailing);


      /*  Intent i=getIntent();
        Log.e("meal_total_protein",i.getStringExtra("meal_total_protein"));
        Log.e("meal_total_carbs",i.getStringExtra("meal_total_carbs"));
        Log.e("meal_total_fats",i.getStringExtra("meal_total_fats"));
        Log.e("meal_total_calories",i.getStringExtra("meal_total_calories"));
        Log.e("meal_goal_protein",i.getStringExtra("meal_goal_protein"));
        Log.e("meal_goal_carbs",i.getStringExtra("meal_goal_carbs"));
        Log.e("meal_goal_fats",i.getStringExtra("meal_goal_fats"));
        Log.e("meal_goal_calories",i.getStringExtra("meal_goal_calories"));
        Log.e("varience_protein",i.getStringExtra("varience_protein"));
        Log.e("varience_carbs",i.getStringExtra("varience_carbs"));
        Log.e("varience_fats",i.getStringExtra("varience_fats"));
        Log.e("varience_calories",i.getStringExtra("varience_calories"));*/


  /*      SharedPreferences pref=getSharedPreferences("foodSelection", Context.MODE_PRIVATE);

        NewDatabase_CreateTable newDatabase_createTable=new NewDatabase_CreateTable(getApplicationContext());
        Cursor c=newDatabase_createTable.getMeal(pref.getString("meal",""),pref.getString("day",""));

        do {
            Log.e("food "+c.getString(4).split(" "),c.getString(4));
            Log.e("food"+c.getString(0),c.getString(4));
        }while (c.moveToNext());



         *//*   Log.e("food1_grams",);
            Log.e("food2_grams",);
            Log.e("food3_grams",);
            Log.e("food4_grams",);
            Log.e("food5_grams",);
            Log.e("food6_grams",);
            Log.e("food7_grams",);
            Log.e("food8_grams",);*//*


    */

        Button mailing_send= (Button) findViewById(R.id.mailing_send);
        mailing_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Mailing.this, "Email sent..!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }
}
