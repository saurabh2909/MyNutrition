package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.project.MyNutritionApplication.mynutrition.Activity.Mailing;
import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;
import com.project.MyNutritionApplication.mynutrition.Extra.Calculation_class;
import com.project.MyNutritionApplication.mynutrition.Extra.FragmentStack;
import com.project.MyNutritionApplication.mynutrition.R;
import com.project.MyNutritionApplication.mynutrition.Webview;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by Saubhagyam on 28/08/2017.
 */

public class Screen_three_selectfood_fragment extends Fragment {

    LinearLayout food1, food2, food3, food4, food5, food6, food7, food8;
    TextView food1_txt, food2_txt, food3_txt, food4_txt, food5_txt, food6_txt, food7_txt, food8_txt;
    TextView food1_txt_gms, food2_txt_gms, food3_txt_gms, food4_txt_gms, food5_txt_gms, food6_txt_gms, food7_txt_gms, food8_txt_gms;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView screen_three_meal_txt;

    LinearLayout MealTable;

    ImageView image;
    LinearLayout screenthree_print_layout;

    TextView table_daytotal_protein, table_daytotal_carbs, table_daytotal_fats, table_daytotal_calories;
    TextView table_daygoal_protein, table_daygoal_carbs, table_daygoal_fats, table_daygoal_calories;
    TextView table_varience_protein, table_varience_carbs, table_varience_fats, table_varience_calories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_three_select_food_fragment, container, false);

        food1 = (LinearLayout) view.findViewById(R.id.food_1);
        food2 = (LinearLayout) view.findViewById(R.id.food_2);
        food3 = (LinearLayout) view.findViewById(R.id.food_3);
        food4 = (LinearLayout) view.findViewById(R.id.food_4);
        food5 = (LinearLayout) view.findViewById(R.id.food_5);
        food6 = (LinearLayout) view.findViewById(R.id.food_6);
        food7 = (LinearLayout) view.findViewById(R.id.food_7);
        food8 = (LinearLayout) view.findViewById(R.id.food_8);

        food1_txt = (TextView) view.findViewById(R.id.food1_txt);
        food2_txt = (TextView) view.findViewById(R.id.food2_txt);
        food3_txt = (TextView) view.findViewById(R.id.food3_txt);
        food4_txt = (TextView) view.findViewById(R.id.food4_txt);
        food5_txt = (TextView) view.findViewById(R.id.food5_txt);
        food6_txt = (TextView) view.findViewById(R.id.food6_txt);
        food7_txt = (TextView) view.findViewById(R.id.food7_txt);
        food8_txt = (TextView) view.findViewById(R.id.food8_txt);


        food1_txt_gms = (TextView) view.findViewById(R.id.food1_txt_gms);
        food2_txt_gms = (TextView) view.findViewById(R.id.food2_txt_gms);
        food3_txt_gms = (TextView) view.findViewById(R.id.food3_txt_gms);
        food4_txt_gms = (TextView) view.findViewById(R.id.food4_txt_gms);
        food5_txt_gms = (TextView) view.findViewById(R.id.food5_txt_gms);
        food6_txt_gms = (TextView) view.findViewById(R.id.food6_txt_gms);
        food7_txt_gms = (TextView) view.findViewById(R.id.food7_txt_gms);
        food8_txt_gms = (TextView) view.findViewById(R.id.food8_txt_gms);

        MealTable = (LinearLayout) view.findViewById(R.id.MealTable);



        final ImageView imageView = (ImageView) view.findViewById(R.id.image);


        screen_three_meal_txt = (TextView) view.findViewById(R.id.screen_three_meal_txt);
        preferences = getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        editor = preferences.edit();


        table_daytotal_protein = (TextView) view.findViewById(R.id.table_daytotal_protein);
        table_daytotal_carbs = (TextView) view.findViewById(R.id.table_daytotal_carbs);
        table_daytotal_fats = (TextView) view.findViewById(R.id.table_daytotal_fats);
        table_daytotal_calories = (TextView) view.findViewById(R.id.table_daytotal_calories);

        table_daygoal_protein = (TextView) view.findViewById(R.id.table_daygoal_protein);
        table_daygoal_carbs = (TextView) view.findViewById(R.id.table_daygoal_carbs);
        table_daygoal_fats = (TextView) view.findViewById(R.id.table_daygoal_fats);
        table_daygoal_calories = (TextView) view.findViewById(R.id.table_daygoal_calories);


        table_varience_protein = (TextView) view.findViewById(R.id.table_varience_protein);
        table_varience_carbs = (TextView) view.findViewById(R.id.table_varience_carbs);
        table_varience_fats = (TextView) view.findViewById(R.id.table_varience_fats);
        table_varience_calories = (TextView) view.findViewById(R.id.table_varience_calories);


        NewDatabase_CreateTable database_settings = new NewDatabase_CreateTable(getContext());

        String meal = preferences.getString("meal", "");
        String m1;
        if (meal.equalsIgnoreCase("Pre-Workout meal")) {
            m1 = "Meal 4";
        } else if (meal.equalsIgnoreCase("Post-Workout meal")) {
            m1 = "Meal 5";
        } else m1 = meal;
        Cursor cursor1 = database_settings.getSetting(preferences.getString("day", ""), m1);
        cursor1.moveToFirst();
        if (cursor1.moveToFirst()) {
            table_daygoal_protein.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_PROTEIN)));
            table_daygoal_carbs.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CARB)));
            table_daygoal_fats.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_FAT)));
            table_daygoal_calories.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CALORY)));


        }

        SharedPreferences trainingPref = getContext().getSharedPreferences("mealPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor trainingEditor = trainingPref.edit();


        Calculation_class calculation_class = new Calculation_class(getContext());
//String meal=preferences.getString("meal", "");
        String m2;
        if (meal.equalsIgnoreCase("Pre-Workout meal")) {
            m2 = "Meal 4";
        } else if (meal.equalsIgnoreCase("Post-Workout meal")) {
            m2 = "Meal 5";
        } else m2 = meal;

        calculation_class.TableMealCalculation(preferences.getString("day", ""), m2);


        float trainingProtein = trainingPref.getFloat("protein", 0.0f);
        float trainingcarbs = trainingPref.getFloat("carbs", 0.0f);
        float trainingFats = trainingPref.getFloat("fats", 0.0f);
        float trainingCalories = trainingPref.getFloat("calories", 0.0f);


        table_daytotal_protein.setText(String.valueOf(String.format("%.02f", trainingProtein)));
        table_daytotal_carbs.setText(String.valueOf(String.format("%.02f", trainingcarbs)));
        table_daytotal_fats.setText(String.valueOf(String.format("%.02f", trainingFats)));
        table_daytotal_calories.setText(String.valueOf(String.format("%.02f", trainingCalories)));

     /*   }else {
            float NontrainingProtein=NontrainingPref.getFloat("protein",0.0f);
            float Nontrainingcarbs=NontrainingPref.getFloat("carbs",0.0f);
            float NontrainingFats=NontrainingPref.getFloat("fats",0.0f);
            float NontrainingCalories=NontrainingPref.getFloat("calories",0.0f);

            table_daytotal_protein.setText(String.valueOf(String.format("%.02f",NontrainingProtein)));
            table_daytotal_carbs.setText(String.valueOf(String.format("%.02f",Nontrainingcarbs)));
            table_daytotal_fats.setText(String.valueOf(String.format("%.02f",NontrainingFats)));
            table_daytotal_calories.setText(String.valueOf(String.format("%.02f",NontrainingCalories)));
        }
*/

        final float varienceProtein = Float.valueOf(table_daygoal_protein.getText().toString()) - Float.valueOf(table_daytotal_protein.getText().toString());
        float varienceCarbs = Float.valueOf(table_daygoal_carbs.getText().toString()) - Float.valueOf(table_daytotal_carbs.getText().toString());
        float varienceFats = Float.valueOf(table_daygoal_fats.getText().toString()) - Float.valueOf(table_daytotal_fats.getText().toString());
        float varienceCalories = Float.valueOf(table_daygoal_calories.getText().toString()) - Float.valueOf(table_daytotal_calories.getText().toString());

        table_varience_protein.setText(String.valueOf(varienceProtein));
        table_varience_carbs.setText(String.valueOf(varienceCarbs));
        table_varience_fats.setText(String.valueOf(varienceFats));
        table_varience_calories.setText(String.valueOf(varienceCalories));





        return view;
    }

    public void writeToFile(String data)
    {
        // Get the directory for the user's public pictures directory.
        final File path = new File(Environment.getExternalStorageDirectory() + "/MyNutrition");

        // Make sure the path directory exists.
        if(!path.exists())
        {
            // Make it, if it doesn't exit
            path.mkdirs();
        }

        final File file = new File(path,   "print.html");

        // Save your stream, don't forget to flush() it before closing it.

        try
        {
            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);

            myOutWriter.close();

            fOut.flush();
            fOut.close();
            Toast.makeText(getContext(), "Html ready", Toast.LENGTH_SHORT).show();


        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
  /*  private void writeToFile(String data,Context context) {
        try {
            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File("/mnt/sdcard" + "/MyNutrition");
            myDir.mkdirs();

            File file = new File(myDir, "/print.html");
            Log.i("file name ", "" + file);
            if (file.exists())
                file.delete();

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(root + "/MyNutrition/" + "print.html", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }*/


   /* public class pdfmaker extends AsyncTask<Void, Void, Void> {

*//*        ProgressDialog pd;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       *//**//* pd=new ProgressDialog(getContext());
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("PDF is getting generated for print. Please wait...");
        pd.show();*//**//*

//        Toast.makeText(getContext(), "PDF is getting generated for print. Please wait...", Toast.LENGTH_SHORT).show();
    }*//*

        @Override
        protected Void doInBackground(Void... voids) {


            String state = Environment.getExternalStorageState();
            if (!Environment.MEDIA_MOUNTED.equals(state)) {
            }

//Create a directory for your PDF
            File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOCUMENTS), "Nutrition 123");
            if (!pdfDir.exists()) {
                pdfDir.mkdir();
            }


//Then take the screen shot
            Bitmap screen;
            screenthree_print_layout.setDrawingCacheEnabled(true);
            screen = Bitmap.createBitmap(screenthree_print_layout.getDrawingCache());
            screenthree_print_layout.setDrawingCacheEnabled(false);

//Now create the name of your PDF file that you will generate
            File pdfFile = new File(pdfDir, "print.pdf");


            String root = Environment.getExternalStorageDirectory().toString();
            File myDir = new File(root + "/MyNutrition");
            myDir.mkdirs();
            Random generator = new Random();
            int n = 10000;
            n = generator.nextInt(n);
            String fname = "Image-" + n + ".jpg";
            File file = new File(myDir, fname);
            Log.i("file name ", "" + file);
            if (file.exists())
                file.delete();
            try {
                FileOutputStream out = new FileOutputStream(file);
                screen.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            screen.compress(Bitmap.CompressFormat.PNG, 10, stream);
            byte[] byteArray = stream.toByteArray();
            if (write("print", byteArray)) {
                Toast.makeText(getContext(),
                        "pdf created", Toast.LENGTH_SHORT)
                        .show();
            } else {
                Toast.makeText(getContext(), "I/O error",
                        Toast.LENGTH_SHORT).show();
            }


            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/MyNutrition/" + "/print.pdf"
            ));
//                Uri uri = Uri.fromFile(new File(myDir,  file.toString()));
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }*/


    /*public Boolean write(String fname, byte[] fcontent) {
        try {
            String fpath = Environment.getExternalStorageDirectory() + "/MyNutrition/" + fname + ".pdf";
            File file = new File(fpath);

            if (!file.exists()) {
                file.createNewFile();
            }

            Rectangle pageSize = new Rectangle(1200f, 1950f);


            Document document = new Document(pageSize, 36f, 72f, 108f, 180f);

            PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));
            document.open();

            addImage(document, fcontent);
            document.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }*/

   /* private static void addImage(Document document, byte[] byteArray) {
        Image image = null;
        try {
            image = Image.getInstance(byteArray);
        } catch (BadElementException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }*/


   /* private void storeImage(Bitmap image) {
        File pictureFile = getOutputMediaFile();
        if (pictureFile == null) {
            Log.d("screen three",
                    "Error creating media file, check storage permissions: ");// e.getMessage());
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
        } catch (FileNotFoundException e) {
            Log.d("screen three", "File not found: " + e.getMessage());
        } catch (IOException e) {
            Log.d("screen three", "Error accessing file: " + e.getMessage());
        }
    }*/


   /* private File getOutputMediaFile() {
        // To be safe, you should check that the SDCard is mounted
        // using Environment.getExternalStorageState() before doing this.
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + getContext().getPackageName()
                + "/Files");

        // This location works best if you want the created images to be shared
        // between applications and persist after your app has been uninstalled.

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date());
        File mediaFile;
        String mImageName = "MI_" + timeStamp + ".jpg";
        mediaFile = new File(mediaStorageDir.getPath() + File.separator + mImageName);
        return mediaFile;
    }*/

    @Override
    public void onResume() {
        super.onResume();
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        screen_three_meal_txt.setText(preferences.getString("meal", ""));

        ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();


        String meal = preferences.getString("meal", "");


        final NewDatabase_CreateTable database_daySelection = new NewDatabase_CreateTable(getContext());

        final Cursor cursor1 = database_daySelection.getMeal("Food 1", preferences.getString("day", ""), meal);
        cursor1.moveToFirst();
        if (cursor1.moveToFirst()) {
            food1_txt.setText(cursor1.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food1_txt_gms.setText(cursor1.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }

        final Cursor cursor2 = database_daySelection.getMeal("Food 2", preferences.getString("day", ""), meal);
        cursor2.moveToFirst();
        if (cursor2.moveToFirst()) {
            food2_txt.setText(cursor2.getString(cursor2.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food2_txt_gms.setText(cursor2.getString(cursor2.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }

        final Cursor cursor3 = database_daySelection.getMeal("Food 3", preferences.getString("day", ""), meal);
        cursor3.moveToFirst();
        if (cursor3.moveToFirst()) {
            food3_txt.setText(cursor3.getString(cursor3.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food3_txt_gms.setText(cursor3.getString(cursor3.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }


        final Cursor cursor4 = database_daySelection.getMeal("Food 4", preferences.getString("day", ""), meal);
        cursor4.moveToFirst();
        if (cursor4.moveToFirst()) {
            food4_txt.setText(cursor4.getString(cursor4.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food4_txt_gms.setText(cursor4.getString(cursor4.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }

        final Cursor cursor5 = database_daySelection.getMeal("Food 5", preferences.getString("day", ""), meal);
        cursor5.moveToFirst();
        if (cursor5.moveToFirst()) {
            food5_txt.setText(cursor5.getString(cursor5.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food5_txt_gms.setText(cursor5.getString(cursor5.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }


        final Cursor cursor6 = database_daySelection.getMeal("Food 6", preferences.getString("day", ""), meal);
        cursor6.moveToFirst();
        if (cursor6.moveToFirst()) {
            food6_txt.setText(cursor6.getString(cursor6.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food6_txt_gms.setText(cursor6.getString(cursor6.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }

        final Cursor cursor7 = database_daySelection.getMeal("Food 7", preferences.getString("day", ""), meal);
        cursor7.moveToFirst();
        if (cursor7.moveToFirst()) {
            food7_txt.setText(cursor7.getString(cursor7.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food7_txt_gms.setText(cursor7.getString(cursor7.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }

        final Cursor cursor8 = database_daySelection.getMeal("Food 8", preferences.getString("day", ""), meal);
        cursor8.moveToFirst();
        if (cursor8.moveToFirst()) {
            food8_txt.setText(cursor8.getString(cursor8.getColumnIndex(database_daySelection.MEAL_COL_FOOD_NAME)));
            food8_txt_gms.setText(cursor8.getString(cursor8.getColumnIndex(database_daySelection.MEAL_COL_GRAMS)) + "g");
        }

        progressDialog.dismiss();
        food1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 1").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });

        food1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 1")) {
                    Log.e("food id", cursor1.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor1.getInt(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });

        food2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 2").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });

        food2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 2")) {
                    Log.e("food id", cursor2.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor2.getInt(cursor2.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });
        food3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 3").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });
        food3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 3")) {
                    Log.e("food id", cursor3.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor3.getInt(cursor3.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });

        food4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 4").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });

        food4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 4")) {
                    Log.e("food id", cursor4.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor4.getInt(cursor4.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });
        food5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 5").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });
        food5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 5")) {
                    Log.e("food id", cursor5.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor5.getInt(cursor5.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });

        food6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 6").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });
        food6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 6")) {
                    Log.e("food id", cursor1.getString(cursor6.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor6.getInt(cursor6.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });
        food7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 7").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });
        food7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 7")) {
                    Log.e("food id", cursor7.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor7.getInt(cursor7.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });
        food8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food", "Food 8").apply();
                FragmentStack.getInstance().push(new Screen_three_selectfood_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_four_foodcategory_fragment()).commit();
            }
        });
        food8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (!food1_txt.getText().toString().equalsIgnoreCase("select food 8")) {
                    Log.e("food id", cursor8.getString(cursor1.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                    final AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                            .setMessage("Are you sure you want to delete this record?")
                            .setTitle("Delete record confirmation").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    database_daySelection.deleteMeal(cursor8.getInt(cursor8.getColumnIndex(database_daySelection.MEAL_COL_ID)));
                                    fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
                                }
                            })
                            .setNegativeButton("No", null).show();

                }

                return false;
            }
        });

    }
}
