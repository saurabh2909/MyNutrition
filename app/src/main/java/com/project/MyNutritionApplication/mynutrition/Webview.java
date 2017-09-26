package com.project.MyNutritionApplication.mynutrition;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;
import com.project.MyNutritionApplication.mynutrition.Extra.PrintClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Webview extends Fragment {

    StringBuilder meal1, meal2, meal3, meal4, meal5, mealtotal, totalFood;

    String meal1_tbale, meal2_tbale, meal3_tbale, meal4_tbale, meal5_tbale;

    String meal_4, meal_5, TITLE;

    SharedPreferences preferences;
    float grams;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_webview, container, false);
        final WebView mbrowser = (WebView) v.findViewById(R.id.mwebview); //get the WebView from the layout XML


        NewDatabase_CreateTable database_settings = new NewDatabase_CreateTable(getContext());
        preferences = getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        meal1 = new StringBuilder();
        meal2 = new StringBuilder();
        meal3 = new StringBuilder();
        meal4 = new StringBuilder();
        meal5 = new StringBuilder();
        mealtotal = new StringBuilder();
        totalFood = new StringBuilder();


        if (preferences.getString("day", "").equals("non-training")) {
            meal_4 = "Meal 4";
            meal_5 = "Meal 5";
            TITLE = "Non-Training Day";
        } else {
            meal_4 = "Pre-workout Meal";
            meal_5 = "Post-workout Meal";
            TITLE = "Training Day";
        }

        final Cursor cursor1 = database_settings.getMeal("Meal 1", preferences.getString("day", ""));
        cursor1.moveToFirst();
        if (cursor1.moveToFirst()) {

            do {


                meal1.append("<tr><td>");
                meal1.append(cursor1.getString(cursor1.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                meal1.append("</td><td>");
                meal1.append(cursor1.getString(cursor1.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                meal1.append("g</td></tr>");
//                meal1.append("<br />");


            } while (cursor1.moveToNext());
        }


        meal1_tbale = "<table border=\"0\">\n" +
                meal1 +
                "</table>\n";
        final Cursor cursor2 = database_settings.getMeal("Meal 2", preferences.getString("day", ""));
        cursor2.moveToFirst();

        if (cursor2.moveToFirst()) {
            do {

                meal2.append("<tr><td>");
                meal2.append(cursor2.getString(cursor2.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                meal2.append("</td><td>");
                meal2.append(cursor2.getString(cursor2.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                meal2.append("g</td></tr>");
//                meal2.append("<br />");
            } while (cursor2.moveToNext());
        }

        meal2_tbale = "<table border=\"0\">\n" +
                meal2 +
                "</table>\n";

        final Cursor cursor3 = database_settings.getMeal("Meal 3", preferences.getString("day", ""));
        cursor3.moveToFirst();
        if (cursor3.moveToFirst()) {
            do {

                meal3.append("<tr><td>");
                meal3.append(cursor3.getString(cursor3.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                meal3.append("</td><td>");
                meal3.append(cursor3.getString(cursor3.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                meal3.append("g</td></tr>");
//                meal3.append("<br />");
            } while (cursor3.moveToNext());
        }
        meal3_tbale = "<table border=\"0\">\n" +
                meal3 +
                "</table>\n";


        final Cursor cursor4 = database_settings.getMeal(meal_4, preferences.getString("day", ""));
        cursor4.moveToFirst();
        if (cursor4.moveToFirst()) {
            do {

                meal4.append("<tr><td>");
                meal4.append(cursor4.getString(cursor4.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                meal4.append("</td><td>");
                meal4.append(cursor4.getString(cursor4.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                meal4.append("g</td></tr>");
//                meal3.append("<br />");
            } while (cursor4.moveToNext());
        }
        meal4_tbale = "<table border=\"0\">\n" +
                meal4 +
                "</table>\n";


        final Cursor cursor5 = database_settings.getMeal(meal_5, preferences.getString("day", ""));
        cursor5.moveToFirst();
        if (cursor5.moveToFirst()) {
            do {

                meal5.append("<tr><td>");
                meal5.append(cursor5.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                meal5.append("</td><td>");
                meal5.append(cursor5.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                meal5.append("g</td></tr>");
//                meal3.append("<br />");
            } while (cursor5.moveToNext());
        }
        meal5_tbale = "<table border=\"0\">\n" +
                meal5 +
                "</table>\n";

        Cursor cursor7 = database_settings.getMeal(preferences.getString("day", ""));
List<String> foods=new ArrayList<>();
        if (cursor7.moveToFirst()) {
            do {
                if (!foods.contains(cursor7.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)))) {
                    foods.add(cursor7.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                    Log.e("cursor 7 name ", cursor7.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                    final Cursor cursor6 = database_settings.getMeal1(cursor7.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)), preferences.getString("day", ""));
                    cursor6.moveToFirst();
                    if (cursor6.moveToFirst()) {
                        do {
                            Log.e("cursor 6 grams", String.valueOf(cursor6.getFloat(cursor6.getColumnIndex(database_settings.MEAL_COL_GRAMS))));
                            grams = grams + Float.parseFloat(cursor6.getString(cursor6.getColumnIndex(database_settings.MEAL_COL_GRAMS)));

//                meal3.append("<br />");
                        } while (cursor6.moveToNext());


                    }
                    mealtotal.append("<tr><td>");
                    mealtotal.append(cursor7.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                    mealtotal.append("</td><td>");
                    mealtotal.append(grams);
                    mealtotal.append("g</td></tr>");
                    grams = 0.0f;
                }
            } while (cursor7.moveToNext());

        }
            String meal_total_tbale = "<table border=\"0\">\n" +
                    mealtotal +
                    "</table>\n";




            final String data = "<html>\n" +
                    "<body>\n" +
                    "\t\n" +
                    "\t<center>\n" +
                    "\t<h1>" + TITLE + "</h1>\n" +
                    "</center>\n" +
                    "\n" +
                    "<h3>Meal 1:</h3>\n" +
                    "\n" +
                    "\n" +
                    meal1_tbale +
                    "\n" +
                    "<h3>Meal 2:</h3>\n" +
                    "\n" +
                    "\n" +
                    meal2_tbale +
                    "\n" +
                    "<h3>Meal 3:</h3>\n" +
                    "\n" +
                    "\n" +
                    meal3_tbale +
                    "\n" +
                    "<h3>" + meal_4 + ":</h3>\n" +
                    "\n" +
                    "\n" +
                    meal4_tbale +
                    "\n" +
                    "<h3>" + meal_5 + ":</h3>\n" +
                    "\n" +
                    "\n" +
                    meal5_tbale +
                    "\n" +meal_total_tbale+
                    "</body>\n" +
                    "</html>";


            mbrowser.loadData(data, "text/html", "UTF-8");

            ImageView print = (ImageView) v.findViewById(R.id.print);
            print.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    createWebPagePrint(mbrowser);
              /*  Intent intent = new Intent(Intent.ACTION_SEND);
                String[] recipients = {"jindanikuldeep@gmail.com"};
                String[] cc = {"mynutrition123@gmail.com"};
                intent.putExtra(Intent.EXTRA_SUBJECT, "The Subject");
                intent.putExtra(Intent.EXTRA_CC, cc);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(
                        Intent.EXTRA_TEXT,
                        Html.fromHtml(new StringBuilder()
                                .append(data)
                                .append("<small><p>More content</p></small>")
                                .toString())
                );
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Send Mail"));*/

                    /*final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND_MULTIPLE);
                    emailIntent.setType("text/html");
                    emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,
                            emailTo);
                    emailIntent.putExtra(android.content.Intent.EXTRA_CC,
                            emailCc);
                    emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
                            Html.fromHtml(data));
                  *//*  emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,
                            Html.fromHtml(emailText));*//*

                   startActivity(Intent.createChooser(emailIntent, "email"));*/
                    /*final Intent shareIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "The Subject");
                    shareIntent.putExtra(
                            Intent.EXTRA_TEXT,
                            Html.fromHtml(new StringBuilder()
                                    .append("<p><b>Some Content</b></p>")
                                    .append("<small><p>More content</p></small>")
                                    .toString())
                    );*/

                }
            });

            return v;

        }


    public void createWebPagePrint(WebView webView) {
        /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            return;*/
        PrintManager printManager = (PrintManager) getContext().getSystemService(Context.PRINT_SERVICE);
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();
        String jobName = " Document";
        PrintAttributes.Builder builder = new PrintAttributes.Builder();
        builder.setMediaSize(PrintAttributes.MediaSize.ISO_A5);
        PrintJob printJob = printManager.print(jobName, printAdapter, builder.build());

        if (printJob.isCompleted()) {
            Toast.makeText(getContext(), "print_complete", Toast.LENGTH_LONG).show();
        } else if (printJob.isFailed()) {
            Toast.makeText(getContext(), "print_failed", Toast.LENGTH_LONG).show();
        }
        // Save the job object for later status checking
    }
}
