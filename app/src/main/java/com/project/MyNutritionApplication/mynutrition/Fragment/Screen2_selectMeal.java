package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.project.MyNutritionApplication.mynutrition.Activity.Mailing;
import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;
import com.project.MyNutritionApplication.mynutrition.Extra.Calculation_class;
import com.project.MyNutritionApplication.mynutrition.Extra.FragmentStack;
import com.project.MyNutritionApplication.mynutrition.R;
import com.project.MyNutritionApplication.mynutrition.Webview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Saubhagyam on 26/08/2017.
 */

public class Screen2_selectMeal extends Fragment {

    LinearLayout Meal_one_layout, Meal_two_layout, Meal_three_layout, Meal_four_layout, Meal_five_layout;
    TextView meal_four, meal_five, main_day;
    SharedPreferences.Editor editor;

    StringBuilder meal1, meal2, meal3, meal4, meal5, totalFood;

    String meal1_tbale, meal2_tbale, meal3_tbale, meal4_tbale, meal5_tbale;

    String meal_4, meal_5, TITLE;


    Button screen_three_email, screen_three_print;
    TextView two_table_daytotal_protein, two_table_daytotal_carbs, two_table_daytotal_fats, two_table_daytotal_calories;
    TextView two_table_daygoal_protein, two_table_daygoal_carbs, two_table_daygoal_fats, two_table_daygoal_calories;
    TextView two_table_varience_protein, two_table_varience_carbs, two_table_varience_fats, two_table_varience_calories;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.screentwo_select_meal_fragment, container, false);
        final SharedPreferences preferences = getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        editor = preferences.edit();
        SharedPreferences trainingPref = getContext().getSharedPreferences("trainingPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor trainingEditor = trainingPref.edit();

        screen_three_email = (Button) view.findViewById(R.id.screen_three_email);
        screen_three_print = (Button) view.findViewById(R.id.screen_three_print);
        SharedPreferences NontrainingPref = getContext().getSharedPreferences("NontrainingPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor NontrainingEditor = NontrainingPref.edit();

//        trainingEditor.clear().apply();
//        NontrainingEditor.clear().apply();

        Calculation_class calculation_class = new Calculation_class(getContext());

        calculation_class.TableCalculation(preferences.getString("day", ""));
        Meal_one_layout = (LinearLayout) view.findViewById(R.id.Meal_one_layout);
        Meal_two_layout = (LinearLayout) view.findViewById(R.id.Meal_two_layout);
        Meal_three_layout = (LinearLayout) view.findViewById(R.id.Meal_three_layout);
        Meal_four_layout = (LinearLayout) view.findViewById(R.id.Meal_four_layout);
        Meal_five_layout = (LinearLayout) view.findViewById(R.id.Meal_five_layout);

        meal_four = (TextView) view.findViewById(R.id.meal_four);
        meal_five = (TextView) view.findViewById(R.id.meal_five);
        main_day = (TextView) view.findViewById(R.id.main_day);

        two_table_daytotal_protein = (TextView) view.findViewById(R.id.two_table_daytotal_protein);
        two_table_daytotal_carbs = (TextView) view.findViewById(R.id.two_table_daytotal_carbs);
        two_table_daytotal_fats = (TextView) view.findViewById(R.id.two_table_daytotal_fats);
        two_table_daytotal_calories = (TextView) view.findViewById(R.id.two_table_daytotal_calories);

        two_table_daygoal_protein = (TextView) view.findViewById(R.id.two_table_daygoal_protein);
        two_table_daygoal_carbs = (TextView) view.findViewById(R.id.two_table_daygoal_carbs);
        two_table_daygoal_fats = (TextView) view.findViewById(R.id.two_table_daygoal_fats);
        two_table_daygoal_calories = (TextView) view.findViewById(R.id.two_table_daygoal_calories);


        two_table_varience_protein = (TextView) view.findViewById(R.id.two_table_varience_protein);
        two_table_varience_carbs = (TextView) view.findViewById(R.id.two_table_varience_carbs);
        two_table_varience_fats = (TextView) view.findViewById(R.id.two_table_varience_fats);
        two_table_varience_calories = (TextView) view.findViewById(R.id.two_table_varience_calories);


//        editor=preferences.edit();
        NewDatabase_CreateTable database_settings = new NewDatabase_CreateTable(getActivity());


        Cursor cursor1 = database_settings.getSetting(preferences.getString("day", ""), "Day Goal");
        cursor1.moveToFirst();
        if (cursor1.moveToFirst()) {
            two_table_daygoal_protein.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_PROTEIN)));
            two_table_daygoal_carbs.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CARB)));
            two_table_daygoal_fats.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_FAT)));
            two_table_daygoal_calories.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CALORY)));


        }


        if (preferences.getString("day", "").equals("training")) {
            float trainingProtein = trainingPref.getFloat("protein", 0.0f);
            float trainingcarbs = trainingPref.getFloat("carbs", 0.0f);
            float trainingFats = trainingPref.getFloat("fats", 0.0f);
            float trainingCalories = trainingPref.getFloat("calories", 0.0f);


            two_table_daytotal_protein.setText(String.valueOf(String.format("%.02f", trainingProtein)));
            two_table_daytotal_carbs.setText(String.valueOf(String.format("%.02f", trainingcarbs)));
            two_table_daytotal_fats.setText(String.valueOf(String.format("%.02f", trainingFats)));
            two_table_daytotal_calories.setText(String.valueOf(String.format("%.02f", trainingCalories)));

        } else {
            float NontrainingProtein = NontrainingPref.getFloat("protein", 0.0f);
            float Nontrainingcarbs = NontrainingPref.getFloat("carbs", 0.0f);
            float NontrainingFats = NontrainingPref.getFloat("fats", 0.0f);
            float NontrainingCalories = NontrainingPref.getFloat("calories", 0.0f);

            two_table_daytotal_protein.setText(String.valueOf(String.format("%.02f", NontrainingProtein)));
            two_table_daytotal_carbs.setText(String.valueOf(String.format("%.02f", Nontrainingcarbs)));
            two_table_daytotal_fats.setText(String.valueOf(String.format("%.02f", NontrainingFats)));
            two_table_daytotal_calories.setText(String.valueOf(String.format("%.02f", NontrainingCalories)));
        }


        float varienceProtein = Float.valueOf(two_table_daygoal_protein.getText().toString()) - Float.valueOf(two_table_daytotal_protein.getText().toString());
        float varienceCarbs = Float.valueOf(two_table_daygoal_carbs.getText().toString()) - Float.valueOf(two_table_daytotal_carbs.getText().toString());
        float varienceFats = Float.valueOf(two_table_daygoal_fats.getText().toString()) - Float.valueOf(two_table_daytotal_fats.getText().toString());
        float varienceCalories = Float.valueOf(two_table_daygoal_calories.getText().toString()) - Float.valueOf(two_table_daytotal_calories.getText().toString());

        two_table_varience_protein.setText(String.valueOf(varienceProtein));
        two_table_varience_carbs.setText(String.valueOf(varienceCarbs));
        two_table_varience_fats.setText(String.valueOf(varienceFats));
        two_table_varience_calories.setText(String.valueOf(varienceCalories));

        if (preferences.getString("day", "").equalsIgnoreCase("training")) {
        } else {
            main_day.setText("Non-training Day");
            meal_four.setText("Meal 4");
            meal_five.setText("Meal 5");
        }

        FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Meal_one_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("meal", "Meal 1").apply();
                FragmentStack.getInstance().push(new Screen2_selectMeal());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();

            }
        });
        Meal_two_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putString("meal", "Meal 2").apply();
                FragmentStack.getInstance().push(new Screen2_selectMeal());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
            }
        });

        Meal_three_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("meal", "Meal 3").apply();
                FragmentStack.getInstance().push(new Screen2_selectMeal());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
            }
        });

        Meal_four_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("meal", meal_four.getText().toString()).apply();
                FragmentStack.getInstance().push(new Screen2_selectMeal());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
            }
        });

        Meal_five_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("meal", meal_five.getText().toString()).apply();
                FragmentStack.getInstance().push(new Screen2_selectMeal());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Screen_three_selectfood_fragment()).commit();
            }
        });
//       SharedPreferences preferences = getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);

        screen_three_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NewDatabase_CreateTable database_settings = new NewDatabase_CreateTable(getContext());

                meal1 = new StringBuilder();
                meal2 = new StringBuilder();
                meal3 = new StringBuilder();
                meal4 = new StringBuilder();
                meal5 = new StringBuilder();
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
                        meal1.append(cursor1.getString(cursor1.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME))+"  ");
                        meal1.append("</td><td>");
                        meal1.append(cursor1.getString(cursor1.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                        meal1.append("g</td></tr>");
                meal1.append("<br />");
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
                        meal2.append(cursor2.getString(cursor2.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME))+"  ");
                        meal2.append("</td><td>");
                        meal2.append(cursor2.getString(cursor2.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                        meal2.append("g</td></tr>");
                meal2.append("<br />");
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
                        meal3.append(cursor3.getString(cursor3.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME))+"  ");
                        meal3.append("</td><td>");
                        meal3.append(cursor3.getString(cursor3.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                        meal3.append("g</td></tr>");
                meal3.append("<br />");
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
                        meal4.append(cursor4.getString(cursor4.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME))+"  ");
                        meal4.append("</td><td>");
                        meal4.append(cursor4.getString(cursor4.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                        meal4.append("g</td></tr>");
                meal3.append("<br />");
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
                        meal5.append(cursor5.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_FOOD_NAME))+"  ");
                        meal5.append("</td><td>");
                        meal5.append(cursor5.getString(cursor5.getColumnIndex(database_settings.MEAL_COL_GRAMS)));
                        meal5.append("g</td></tr>");
                meal3.append("<br />");
                    } while (cursor5.moveToNext());
                }
                meal5_tbale = "<table border=\"0\">\n" +
                        meal5 +
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
                        "\n" +
                        "</body>\n" +
                        "</html>";


                Intent intent = new Intent(Intent.ACTION_SEND);
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
                startActivity(Intent.createChooser(intent, "Send Mail"));

            }
        });
               /* final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.activity_mailing);
                dialog.setCanceledOnTouchOutside(false);

                dialog.show();

                Button ok = (Button) dialog.findViewById(R.id.mailing_send);

                final LinearLayout mail_layout = (LinearLayout) dialog.findViewById(R.id.mail_layout);
                final ProgressBar mail_progressbar = (ProgressBar) dialog.findViewById(R.id.mail_progressbar);

                final EditText mail_name_edit = (EditText) dialog.findViewById(R.id.mail_name_edit);
                final EditText mail_mail_edit = (EditText) dialog.findViewById(R.id.mail_mail_edit);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        mail_layout.setVisibility(View.GONE);
                        mail_progressbar.setVisibility(View.VISIBLE);
                        String url = "http://www.yogeshinds.com/nutrition/send_email";?" +
                                "email=" +mail_mail_edit.getText().toString()+
                                "&meal_total_protein=" +table_daytotal_protein.getText().toString() +
                                "&meal_total_carbs=" +table_daytotal_carbs.getText().toString()   +
                                "&meal_total_fats=" +table_daytotal_fats.getText().toString()    +
                                "&meal_total_calories=" +table_daytotal_calories.getText().toString()+
                                "&meal_goal_protein=" +table_daygoal_protein.getText().toString() +
                                "&meal_goal_carbs=" +table_daygoal_carbs.getText().toString()   +
                                "&meal_goal_fats=" +table_daygoal_fats.getText().toString()    +
                                "&meal_goal_calories=" +table_daygoal_calories.getText().toString()+
                                "&varience_protein=" +table_varience_protein.getText().toString() +
                                "&varience_carbs=" +table_varience_carbs.getText().toString()   +
                                "&varience_fats=" +table_varience_fats.getText().toString()    +
                                "&varience_calories=" +table_varience_calories.getText().toString()+
                                "&food1_grams=5.6" +
                                "&food2_grams=1.6" +
                                "&food3_grams=4.96" +
                                "&food4_grams=" +
                                "&food5_grams=" +
                                "&food6_grams=55.6" +
                                "&food7_grams=1.5" +
                                "&food8_grams=1.8";

                        Log.e("mail url", url);

                        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                try {
                                    JSONObject res = new JSONObject(response);
                                    if (res.getInt("status") == 1) {
                                        Toast.makeText(getContext(), "Email sent..!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                dialog.dismiss();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("name", mail_name_edit.getText().toString());
                                params.put("email", mail_mail_edit.getText().toString());
                                params.put("meal_total_protein", table_daytotal_protein.getText().toString());
                                params.put("meal_total_carbs", table_daytotal_carbs.getText().toString());
                                params.put("meal_total_fats", table_daytotal_fats.getText().toString());
                                params.put("meal_total_calories", table_daytotal_calories.getText().toString());
                                params.put("meal_goal_protein", table_daygoal_protein.getText().toString());
                                params.put("meal_goal_carbs", table_daygoal_carbs.getText().toString());
                                params.put("meal_goal_fats", table_daygoal_fats.getText().toString());
                                params.put("meal_goal_calories", table_daygoal_calories.getText().toString());
                                params.put("varience_protein", table_varience_protein.getText().toString());
                                params.put("varience_carbs", table_varience_carbs.getText().toString());
                                params.put("varience_fats", table_varience_fats.getText().toString());
                                params.put("varience_calories", table_varience_calories.getText().toString());
                                params.put("food1_grams",String.valueOf(1.8));
                                params.put("food2_grams" ,String.valueOf(1.8));
                                params.put("food3_grams" ,String.valueOf(1.8));
                                params.put("food4_grams" ,String.valueOf(1.8));
                                params.put("food5_grams" ,String.valueOf(1.8));
                                params.put("food6_grams",String.valueOf(1.8));
                                params.put("food7_grams" ,String.valueOf(1.8));
                                params.put("food8_grams",String.valueOf(1.8));

                                if (!food1_txt.getText().toString().equalsIgnoreCase("Select Food 1")) {
                                    params.put("food1_grams", food1_txt_gms.getText().toString());
                                    params.put("food1_name", food1_txt.getText().toString());
                                }

                                if (!food2_txt.getText().toString().equalsIgnoreCase("Select Food 2")) {
                                    params.put("food2_grams", food2_txt_gms.getText().toString());
                                    params.put("food2_name", food2_txt.getText().toString());
                                }

                                if (!food3_txt.getText().toString().equalsIgnoreCase("Select Food 3")) {
                                    params.put("food3_grams", food3_txt_gms.getText().toString());
                                    params.put("food3_name", food3_txt.getText().toString());
                                }

                                if (!food4_txt.getText().toString().equalsIgnoreCase("Select Food 4")) {
                                    params.put("food4_grams", food4_txt_gms.getText().toString());
                                    params.put("food4_name", food4_txt.getText().toString());
                                }

                                if (!food5_txt.getText().toString().equalsIgnoreCase("Select Food 5")) {
                                    params.put("food5_grams", food5_txt_gms.getText().toString());
                                    params.put("food5_name", food5_txt.getText().toString());
                                }

                                if (!food6_txt.getText().toString().equalsIgnoreCase("Select Food 6")) {
                                    params.put("food6_grams", food6_txt_gms.getText().toString());
                                    params.put("food6_name", food6_txt.getText().toString());
                                }

                                if (!food7_txt.getText().toString().equalsIgnoreCase("Select Food 7")) {
                                    params.put("food7_grams", food7_txt_gms.getText().toString());
                                    params.put("food7_name", food7_txt.getText().toString());
                                }
                                if (!food8_txt.getText().toString().equalsIgnoreCase("Select Food 8")) {
                                    params.put("food8_grams", food8_txt_gms.getText().toString());
                                    params.put("food8_name", food8_txt.getText().toString());
                                }
                                return params;
                            }
                        };

                        Volley.newRequestQueue(getContext()).add(sr);

                    }
                });

                if (MealTable != null) {
                    Bitmap image = Bitmap.createBitmap(MealTable.getWidth(),
                            MealTable.getHeight(), Bitmap.Config.ARGB_8888);
                    Canvas b = new Canvas(image);
                    MealTable.draw(b);
//                    imageView.setImageBitmap(image);
                }

                Intent i = new Intent(getContext(), Mailing.class);
                i.putExtra("meal_total_protein", table_daytotal_protein.getText().toString());
                i.putExtra("meal_total_carbs", table_daytotal_carbs.getText().toString());
                i.putExtra("meal_total_fats", table_daytotal_fats.getText().toString());
                i.putExtra("meal_total_calories", table_daytotal_calories.getText().toString());

                i.putExtra("meal_goal_protein", table_daygoal_protein.getText().toString());
                i.putExtra("meal_goal_carbs", table_daygoal_carbs.getText().toString());
                i.putExtra("meal_goal_fats", table_daygoal_fats.getText().toString());
                i.putExtra("meal_goal_calories", table_daygoal_calories.getText().toString());

                i.putExtra("varience_protein", table_varience_protein.getText().toString());
                i.putExtra("varience_carbs", table_varience_carbs.getText().toString());
                i.putExtra("varience_fats", table_varience_fats.getText().toString());
                i.putExtra("varience_calories", table_varience_calories.getText().toString());

                if (!food1_txt.getText().toString().equalsIgnoreCase("Select Food 1"))
                    i.putExtra("food1_grams", food1_txt_gms.getText().toString());

                if (!food2_txt.getText().toString().equalsIgnoreCase("Select Food 2"))
                    i.putExtra("food2_grams", food2_txt_gms.getText().toString());

                if (!food3_txt.getText().toString().equalsIgnoreCase("Select Food 3"))
                    i.putExtra("food3_grams", food3_txt_gms.getText().toString());

                if (!food4_txt.getText().toString().equalsIgnoreCase("Select Food 4"))
                    i.putExtra("food4_grams", food4_txt_gms.getText().toString());

                if (!food5_txt.getText().toString().equalsIgnoreCase("Select Food 5"))
                    i.putExtra("food5_grams", food5_txt_gms.getText().toString());

                if (!food6_txt.getText().toString().equalsIgnoreCase("Select Food 6"))
                    i.putExtra("food6_grams", food6_txt_gms.getText().toString());

                if (!food7_txt.getText().toString().equalsIgnoreCase("Select Food 7"))
                    i.putExtra("food7_grams", food7_txt_gms.getText().toString());

                if (!food8_txt.getText().toString().equalsIgnoreCase("Select Food 8"))
                    i.putExtra("food8_grams", food8_txt_gms.getText().toString());

                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }*/


        screen_three_print.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {


                Toast.makeText(getContext(), "Please wait while we make pdf.", Toast.LENGTH_SHORT).show();

                FragmentStack.getInstance().push(new Screen2_selectMeal());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager, new Webview()).commit();


               /* String state = Environment.getExternalStorageState();
                if (!Environment.MEDIA_MOUNTED.equals(state)) {
                }

//Create a directory for your PDF
                File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                        Environment.DIRECTORY_DOCUMENTS), "Nutrition 123");
                if (!pdfDir.exists()){
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
//                    pd.dismiss();
                    Toast.makeText(getContext(),
                            "pdf created", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(getContext(), "I/O error",
                            Toast.LENGTH_SHORT).show();
                }




                Intent intent = new Intent(Intent.ACTION_VIEW);
                Uri uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory()+"/MyNutrition/"+"/print.pdf"
                ));
//                Uri uri = Uri.fromFile(new File(myDir,  file.toString()));
                intent.setDataAndType(uri, "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);*/

//                new pdfmaker().execute();

            }
        });


        return view;
    }
}
