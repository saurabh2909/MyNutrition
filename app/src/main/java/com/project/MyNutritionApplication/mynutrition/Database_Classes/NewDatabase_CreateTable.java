package com.project.MyNutritionApplication.mynutrition.Database_Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Saubhagyam on 04/09/2017.
 */

public class NewDatabase_CreateTable extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Nutrition.db";
    private static final String TABLE_NAME2 = "Setting";
    public static final String SETTING_ID = "id";
    public static final String SETTING_DAYSELECT = "day_type";
    public static final String SETTING_TITLE = "title";
    public static final String SETTING_PROTEIN = "protein";
    public static final String SETTING_CARB = "carb";
    public static final String SETTING_FAT = "fat";
    public static final String SETTING_CALORY = "calories";


    public static final String TABLE_NAME1 = "MealSelection";
    public static final String MEAL_COL_ID = "id";
    public static final String MEAL_COL_DAYSELECT = "day_type";
    public static final String MEAL_COL_MEAL = "meal";
    public static final String MEAL_COL_FOOD = "food";
    public static final String MEAL_COL_FOOD_NAME = "food_name";
    public static final String MEAL_COL_FOOD_CATEGORY = "food_cateory";
    public static final String MEAL_COL_PROTEIN = "protein";
    public static final String MEAL_COL_CARBS = "carbs";
    public static final String MEAL_COL_FATS = "fats";
    public static final String MEAL_COL_CALORIES = "calories";
    public static final String MEAL_COL_GRAMS = "grams";

    Context context;

    public NewDatabase_CreateTable(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table   " + TABLE_NAME1 +
                        " (" + MEAL_COL_ID + " integer primary key," +
                        MEAL_COL_DAYSELECT + " text," +
                        MEAL_COL_MEAL + " text," +
                        MEAL_COL_FOOD + " text, " +
                        MEAL_COL_FOOD_NAME + " text," +
                        MEAL_COL_FOOD_CATEGORY + " text," +
                        MEAL_COL_PROTEIN + " varchar," +
                        MEAL_COL_CARBS + " varchar," +
                        MEAL_COL_FATS + " varchar," +
                        MEAL_COL_CALORIES + " varchar," +
                        MEAL_COL_GRAMS + " varchar)"
        );

        db.execSQL(
                "create table   " + TABLE_NAME2 +
                        " (" + SETTING_ID + " integer primary key," +
                        SETTING_DAYSELECT + " text," +
                        SETTING_TITLE + " text," +
                        SETTING_PROTEIN + " varchar," +
                        SETTING_CARB + " varchar," +
                        SETTING_FAT + " varchar," +
                        SETTING_CALORY + " varchar)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME2);

    }

    public boolean deleteDatabase(Context context) {
        return context.deleteDatabase(DATABASE_NAME);
    }


    public boolean insertSetting(String DayName, String Title, Float protein, Float carbs, Float fats, Float calories) {

        SQLiteDatabase db1 = this.getReadableDatabase();

//        preferences = context.getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        Cursor res = getSetting(DayName, Title);
        res.moveToFirst();
        Log.e("result count ", String.valueOf(res.getColumnCount()));
        if (res.moveToFirst()) {
            Toast.makeText(context, "Records are updated.", Toast.LENGTH_SHORT).show();
            updateSetting(Integer.parseInt(res.getString(0)), DayName, Title, protein, carbs, fats, calories);
            res.close();

        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(SETTING_DAYSELECT, DayName);
            contentValues.put(SETTING_TITLE, Title);
            contentValues.put(SETTING_PROTEIN, protein);
            contentValues.put(SETTING_CARB, carbs);
            contentValues.put(SETTING_FAT, fats);
            contentValues.put(SETTING_CALORY, calories);

            db.insert(TABLE_NAME2, null, contentValues);

            SettingnumberOfRows();

        }
        return true;
    }

    public Cursor getSetting(String DaySelect, String DayTitle) {
//    public Cursor getData(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                SETTING_ID,
                SETTING_DAYSELECT,
                SETTING_TITLE,
                SETTING_PROTEIN,
                SETTING_CARB,
                SETTING_FAT,
                SETTING_CALORY};

        String WHERE = SETTING_DAYSELECT + "='" + DaySelect + "' AND " + SETTING_TITLE + "='" + DayTitle+ "'";


        if(db == null || !db.isOpen()) {
            db = getReadableDatabase();
        }

        if(!db.isReadOnly()) {
            db.close();
            db = getReadableDatabase();
        }
//        Cursor res = db.rawQuery(query, null);
        return db.query(TABLE_NAME2, columns, WHERE, null, null, null, null);
    }

  /*  public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME+" where id="+id+"", null );
        return res;
    }*/


    public int SettingnumberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME2);
        Log.e("number of rows : ", String.valueOf(numRows));
        return numRows;
    }

    public boolean updateSetting(Integer id,String DayName, String Title, Float protein, Float carbs, Float fats, Float calories)  {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SETTING_DAYSELECT, DayName);
        contentValues.put(SETTING_TITLE, Title);
        contentValues.put(SETTING_PROTEIN, protein);
        contentValues.put(SETTING_CARB, carbs);
        contentValues.put(SETTING_FAT, fats);
        contentValues.put(SETTING_CALORY, calories);
        db.update(TABLE_NAME2, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteSetting(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME2,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }



    //**********************************************************************************



    public boolean insertMeal(String DayName, String MealName, String Food, String FoodName, String FoodCate, Float protein, Float carbs, Float fats, Float calories, Float grams) {

        SQLiteDatabase db1 = this.getReadableDatabase();

//        preferences = context.getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        Cursor res =getMeal(Food,DayName,MealName);
        res.moveToFirst();
        Log.e("result count ",String.valueOf(res.getColumnCount()));
        if (res.moveToFirst()) {
            Toast.makeText(context, "Records are updated.", Toast.LENGTH_SHORT).show();
            updateMeal(Integer.parseInt(res.getString(0)), DayName, MealName, Food, FoodName, FoodCate, protein, carbs, fats, calories, grams);
            res.close();

        } else {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MEAL_COL_DAYSELECT, DayName);
            contentValues.put(MEAL_COL_MEAL, MealName);
            contentValues.put(MEAL_COL_FOOD, Food);
            contentValues.put(MEAL_COL_FOOD_NAME, FoodName);
            contentValues.put(MEAL_COL_FOOD_CATEGORY, FoodCate);
            contentValues.put(MEAL_COL_PROTEIN, protein);
            contentValues.put(MEAL_COL_CARBS, carbs);
            contentValues.put(MEAL_COL_FATS, fats);
            contentValues.put(MEAL_COL_CALORIES, calories);
            contentValues.put(MEAL_COL_GRAMS, grams);

            db.insert(TABLE_NAME1, null, contentValues);

            MealnumberOfRows();

        }
        return true;
    }



    public Cursor getMeal(String Food, String DaySelect, String Meal) {
//    public Cursor getData(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MEAL_COL_ID,
                MEAL_COL_DAYSELECT,
                MEAL_COL_MEAL,
                MEAL_COL_FOOD,
                MEAL_COL_FOOD_CATEGORY,
                MEAL_COL_FOOD_NAME,
                MEAL_COL_PROTEIN,
                MEAL_COL_CARBS,
                MEAL_COL_FATS,
                MEAL_COL_CALORIES,
                MEAL_COL_GRAMS};

        String WHERE=MEAL_COL_FOOD+"='"+Food+"' AND "+MEAL_COL_DAYSELECT+"='"+DaySelect+"' AND "+MEAL_COL_MEAL+"='"+Meal+"'";
        Cursor r=db.query(TABLE_NAME1,columns,WHERE,null,null,null,null);

//        Cursor res = db.rawQuery(query, null);
        return r;
    }


    public Cursor getMeal( String DaySelect) {
//    public Cursor getData(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MEAL_COL_ID,
                MEAL_COL_DAYSELECT,
                MEAL_COL_MEAL,
                MEAL_COL_FOOD,
                MEAL_COL_FOOD_CATEGORY,
                MEAL_COL_FOOD_NAME,
                MEAL_COL_PROTEIN,
                MEAL_COL_CARBS,
                MEAL_COL_FATS,
                MEAL_COL_CALORIES,
                MEAL_COL_GRAMS};

        String WHERE=MEAL_COL_DAYSELECT+"='"+DaySelect+"'";
        Cursor r=db.query(TABLE_NAME1,columns,WHERE,null,null,null,null);

//        Cursor res = db.rawQuery(query, null);
        return r;
    }
    public Cursor getFood( String DaySelect) {
//    public Cursor getData(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MEAL_COL_ID,
                MEAL_COL_DAYSELECT,
                MEAL_COL_MEAL,
                MEAL_COL_FOOD,
                MEAL_COL_FOOD_CATEGORY,
                MEAL_COL_FOOD_NAME,
                MEAL_COL_PROTEIN,
                MEAL_COL_CARBS,
                MEAL_COL_FATS,
                MEAL_COL_CALORIES,
                MEAL_COL_GRAMS};

        String WHERE=MEAL_COL_DAYSELECT+"='"+DaySelect+"'";
        Cursor r=db.query(TABLE_NAME1,columns,WHERE,null,null,null,null);

        ;
        Cursor r1=db.rawQuery("SELECT SUM("+MEAL_COL_GRAMS+") FROM "+TABLE_NAME1+" GROUP BY "+MEAL_COL_FOOD_NAME+" ",columns);

//        Cursor res = db.rawQuery(query, null);    WHERE day_type='"+DaySelect+"'
        return r1;
    }
    public Cursor getMeal( String meal,String day) {
//    public Cursor getData(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MEAL_COL_ID,
                MEAL_COL_DAYSELECT,
                MEAL_COL_MEAL,
                MEAL_COL_FOOD,
                MEAL_COL_FOOD_CATEGORY,
                MEAL_COL_FOOD_NAME,
                MEAL_COL_PROTEIN,
                MEAL_COL_CARBS,
                MEAL_COL_FATS,
                MEAL_COL_CALORIES,
                MEAL_COL_GRAMS};

        String WHERE=MEAL_COL_DAYSELECT+"='"+day+"' AND "+MEAL_COL_MEAL+"='"+meal+"'";
        Cursor r=db.query(TABLE_NAME1,columns,WHERE,null,null,null,null);

//        Cursor res = db.rawQuery(query, null);
        return r;
    }




    public Cursor getMeal1( String food,String day) {
//    public Cursor getData(String query) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {MEAL_COL_ID,
                MEAL_COL_DAYSELECT,
                MEAL_COL_MEAL,
                MEAL_COL_FOOD,
                MEAL_COL_FOOD_CATEGORY,
                MEAL_COL_FOOD_NAME,
                MEAL_COL_PROTEIN,
                MEAL_COL_CARBS,
                MEAL_COL_FATS,
                MEAL_COL_CALORIES,
                MEAL_COL_GRAMS};

        String WHERE=MEAL_COL_DAYSELECT+"='"+day+"' AND "+MEAL_COL_FOOD_NAME+"='"+food+"'";
        Cursor r=db.query(TABLE_NAME1,columns,WHERE,null,null,null,null);

//        Cursor res = db.rawQuery(query, null);
        return r;
    }

    public int MealnumberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME1);
        Log.e("number of rows : ", String.valueOf(numRows));
        return numRows;
    }

    public boolean updateMeal(Integer id, String DayName, String MealName, String Food, String FoodName, String FoodCate, Float protein, Float carbs, Float fats, Float calories, Float grams) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MEAL_COL_ID, id);
        contentValues.put(MEAL_COL_DAYSELECT, DayName);
        contentValues.put(MEAL_COL_MEAL, MealName);
        contentValues.put(MEAL_COL_FOOD, Food);
        contentValues.put(MEAL_COL_FOOD_NAME, FoodName);
        contentValues.put(MEAL_COL_FOOD_CATEGORY, FoodCate);
        contentValues.put(MEAL_COL_PROTEIN, protein);
        contentValues.put(MEAL_COL_CARBS, carbs);
        contentValues.put(MEAL_COL_FATS, fats);
        contentValues.put(MEAL_COL_CALORIES, calories);
        contentValues.put(MEAL_COL_GRAMS, grams);
        db.update(TABLE_NAME1, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMeal(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME1,
                "id = ? ",
                new String[]{Integer.toString(id)});
    }
}
