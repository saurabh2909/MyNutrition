package com.project.MyNutritionApplication.mynutrition;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;

/**
 * Created by Saubhagyam on 23/09/2017.
 */

public class Webview_thewwScreen extends Fragment {

    SharedPreferences preferences;
    StringBuilder food;
    String food_str;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_three_screen, container, false);

        final WebView mbrowser = (WebView) view.findViewById(R.id.mwebview_three); //get the WebView from the layout XML

        preferences = getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);


        food = new StringBuilder();
        NewDatabase_CreateTable database_settings = new NewDatabase_CreateTable(getContext());
        Cursor c = database_settings.getMeal(preferences.getString("meal", ""), preferences.getString("day", ""));
        c.moveToFirst();
        if (c.moveToFirst()) {
            do {
                food.append("<tr><td>");
                food.append(c.getString(c.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME)));
                food.append("</td><td>");
                food.append(c.getString(c.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                food.append("g</td></tr>");
            } while (c.moveToNext());
        }

        food_str = "<table border=\"0\">\n" +
                food +
                "</table>\n";

        final String data = "<html>\n" +
                "<body>\n" +
                "\t\n" +
                "\t<center>\n" +
                "\t<h1>" + preferences.getString("meal", "") + "</h1>\n" +
                "</center>\n" +
                food_str +
                "</body>\n" +
                "</html>";


        mbrowser.loadData(data, "text/html", "UTF-8");

        ImageView print = (ImageView) view.findViewById(R.id.print_three);
        print.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWebPagePrint(mbrowser);
            }
        });
        return view;
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

