package com.project.MyNutritionApplication.mynutrition.Extra;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;

import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;

/**
 * Created by Saubhagyam on 02/09/2017.
 */

public class Calculation_class {

    Context context;



    public Calculation_class(Context context) {
        this.context = context;
    }

    NewDatabase_CreateTable database_daySelection;
    public void TableCalculation(String day){
        database_daySelection=new NewDatabase_CreateTable(context);


        SharedPreferences trainingPref=context.getSharedPreferences("trainingPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor trainingEditor=trainingPref.edit();


        SharedPreferences NontrainingPref=context.getSharedPreferences("NontrainingPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor NontrainingEditor=NontrainingPref.edit();

        Cursor c=database_daySelection.getMeal(day);
//        c.moveToFirst();


        if (c.moveToFirst())
        {if (day.equals("training")){
            Float protein = 0.0f;
            Float carbs= 0.0f;
            Float fats= 0.0f;
            Float calories= 0.0f;
            do{

                    protein=protein+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_PROTEIN));
                    carbs=carbs+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_CARBS));
                    fats=fats+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_FATS));
                    calories=calories+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_CALORIES));


            }while (c.moveToNext());
            trainingEditor.putFloat("protein",protein);
            trainingEditor.putFloat("carbs",carbs);
            trainingEditor.putFloat("fats",fats);
            trainingEditor.putFloat("calories",calories).apply();

        }else {
            Float protein= 0.0f;
            Float carbs= 0.0f;
            Float fats= 0.0f;
            Float calories= 0.0f;
            do{

                protein=protein+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_PROTEIN));
                carbs=carbs+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_CARBS));
                fats=fats+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_FATS));
                calories=calories+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_CALORIES));


            }while (c.moveToNext());
            NontrainingEditor.putFloat("protein",protein);
            NontrainingEditor.putFloat("carbs",carbs);
            NontrainingEditor.putFloat("fats",fats);
            NontrainingEditor.putFloat("calories",calories).apply();
        }
        }
    }

    public void TableMealCalculation(String day,String meal){
        database_daySelection=new NewDatabase_CreateTable(context);


        SharedPreferences trainingPref=context.getSharedPreferences("mealPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor trainingEditor=trainingPref.edit();



        Cursor c=database_daySelection.getMeal(meal,day);
//        c.moveToFirst();


        if (c.moveToFirst())
        {
            Float proteinMeal = 0.0f;
            Float carbsMeal= 0.0f;
            Float fatsMeal= 0.0f;
            Float caloriesMeal= 0.0f;
            do{

                proteinMeal=proteinMeal+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_PROTEIN));
                carbsMeal=carbsMeal+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_CARBS));
                fatsMeal=fatsMeal+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_FATS));
                caloriesMeal=caloriesMeal+c.getFloat(c.getColumnIndex(database_daySelection.MEAL_COL_CALORIES));


            }while (c.moveToNext());
            trainingEditor.putFloat("protein",proteinMeal);
            trainingEditor.putFloat("carbs",carbsMeal);
            trainingEditor.putFloat("fats",fatsMeal);
            trainingEditor.putFloat("calories",caloriesMeal).apply();


        }else {
            trainingEditor.clear().apply();
        }
    }

}
