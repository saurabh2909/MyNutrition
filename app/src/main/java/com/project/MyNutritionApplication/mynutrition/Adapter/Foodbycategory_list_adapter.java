package com.project.MyNutritionApplication.mynutrition.Adapter;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;
import com.project.MyNutritionApplication.mynutrition.Extra.Calculation_class;
import com.project.MyNutritionApplication.mynutrition.Extra.FragmentStack;
import com.project.MyNutritionApplication.mynutrition.Fragment.Screen_five_foodbycategory_fragment;
import com.project.MyNutritionApplication.mynutrition.Fragment.Screen_three_selectfood_fragment;
import com.project.MyNutritionApplication.mynutrition.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;

/**
 * Created by Saubhagyam on 30/08/2017.
 */

public class Foodbycategory_list_adapter extends BaseAdapter {

    Context context;
    TextView foodId, foodName;
    JSONArray jsonArray;
    LinearLayout foodbycategory_root_layout;
    SharedPreferences preferences;



    double protein;
    double carbs ;
    double fats;
    double calories;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    public Foodbycategory_list_adapter(Context context, JSONArray jsonArray,android.support.v4.app.FragmentTransaction fragmentTransaction) {
        this.context = context;
        this.jsonArray = jsonArray;
        this.fragmentTransaction=fragmentTransaction;
    }

    public Foodbycategory_list_adapter() {
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.foodbycategory_txt_layout, parent, false);

        foodName = (TextView) convertView.findViewById(R.id.foodName);
        foodId = (TextView) convertView.findViewById(R.id.foodId);
        foodbycategory_root_layout = (LinearLayout) convertView.findViewById(R.id.foodbycategory_root_layout);

        try {
            final JSONObject obj = (JSONObject) jsonArray.get(position);

            Log.e("object", obj.toString());
            foodId.setText(String.valueOf(obj.getInt("id")));
            foodName.setText(obj.getString("food_name"));
            preferences = context.getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
          /*  LayoutInflater inflater = (LayoutInflater)context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //Inflate the view from a predefined XML layout
            @SuppressLint("ViewHolder") final View layout = inflater.inflate(R.layout.screen6_popup,
                    (ViewGroup) convertView.findViewById(R.id.popup_element));*/
            foodbycategory_root_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(context);
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    String url = null;
                    try {
                        url = " http://www.yogeshinds.com/nutrition/food_calories?food_id=" + ((JSONObject) jsonArray.getJSONObject(position)).getInt("id");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Log.e("list of food url", url);
                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            final NewDatabase_CreateTable database_daySelection = new NewDatabase_CreateTable(context);
                            Log.e("food calories response", s);
                            try {
                                JSONObject object = new JSONObject(s);
                                if (object.getInt("status") == 1) {
                                    JSONArray ary = object.getJSONArray("food_calories_data");
                                    final JSONObject oneObj = ary.getJSONObject(0);


                                    // create a 300px width and 470px height PopupWindow
                                    final Dialog dialog = new Dialog(context);
                                    dialog.setContentView(R.layout.screen6_popup);
                                    dialog.setCanceledOnTouchOutside(false);

                                    progressDialog.dismiss();
                                    dialog.show();

                                    NumberFormat formatter = NumberFormat.getNumberInstance();
                                    formatter.setMinimumFractionDigits(2);
                                    formatter.setMaximumFractionDigits(2);

                                    final TextView screen_six_foodname = (TextView) dialog.findViewById(R.id.screen_six_foodname);
                                    final TextView screen_six_protein = (TextView) dialog.findViewById(R.id.screen_six_protein);
                                    final TextView screen_six_carb = (TextView) dialog.findViewById(R.id.screen_six_carb);
                                    final TextView screen_six_fats = (TextView) dialog.findViewById(R.id.screen_six_fats);
                                    final TextView screen_six_calories = (TextView) dialog.findViewById(R.id.screen_six_calories);

                                    final EditText screen_six_gram_edit = (EditText) dialog.findViewById(R.id.screen_six_gram_edit);
                                    Button screen_six_confirm = (Button) dialog.findViewById(R.id.screen_six_confirm);

                                    screen_six_foodname.setText(oneObj.getString("food_name"));
                                    screen_six_protein.setText(String.valueOf(String.format("%.2f",Float.parseFloat(oneObj.getString("protein")))));
                                    screen_six_carb.setText(String.valueOf(String.format("%.2f",Float.parseFloat(oneObj.getString("carbs")))));
                                    screen_six_fats.setText(String.valueOf(String.format("%.2f",Float.parseFloat(oneObj.getString("fats")))));
                                    screen_six_calories.setText(String.valueOf(String.format("%.2f",Float.parseFloat(oneObj.getString("calories")))));




                                    screen_six_gram_edit.addTextChangedListener(new TextWatcher() {
                                        @Override
                                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                        }

                                        @Override
                                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                                            try {
                                                if (!s.toString().equals("")) {
                                                     protein =  oneObj.getDouble("protein") * Double.parseDouble(s.toString());

                                                     carbs = oneObj.getDouble("carbs") * Double.parseDouble(s.toString());
                                                     fats = oneObj.getDouble("fats") * Double.parseDouble(s.toString());
                                                     calories = oneObj.getDouble("calories") * Double.parseDouble(s.toString());


                                                    screen_six_protein.setText(String.valueOf(String.format("%.02f",protein)));
                                                    screen_six_carb.setText(String.valueOf(String.format("%.02f",carbs)));
                                                    screen_six_fats.setText(String.valueOf(String.format("%.02f",fats)));
                                                    screen_six_calories.setText(String.valueOf(String.format("%.02f",calories)));
                                                }

                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        @Override
                                        public void afterTextChanged(Editable s) {

                                        }
                                    });


                                    screen_six_confirm.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {


                                            try {

                                              /*  SharedPreferences trainingPref=context.getSharedPreferences("trainingPref",Context.MODE_PRIVATE);
                                                SharedPreferences.Editor trainingEditor=trainingPref.edit();


                                                SharedPreferences NontrainingPref=context.getSharedPreferences("NontrainingPref",Context.MODE_PRIVATE);
                                                SharedPreferences.Editor NontrainingEditor=NontrainingPref.edit();

                                                if (preferences.getString("day","").equals("training")){
                                                    float trainingProtein=trainingPref.getFloat("protein",0.0f);
                                                    float trainingcarbs=trainingPref.getFloat("carbs",0.0f);
                                                    float trainingFats=trainingPref.getFloat("fats",0.0f);
                                                    float trainingCalories=trainingPref.getFloat("calories",0.0f);

                                                    trainingEditor.putFloat("protein",Float.valueOf(String.valueOf(oneObj.getDouble("protein")))+trainingProtein);
                                                    trainingEditor.putFloat("carbs",Float.valueOf(String.valueOf(oneObj.getDouble("carbs")))+trainingProtein);
                                                    trainingEditor.putFloat("fats",Float.valueOf(String.valueOf(oneObj.getDouble("fats")))+trainingProtein);
                                                    trainingEditor.putFloat("calories",Float.valueOf(String.valueOf(oneObj.getDouble("calories")))+trainingProtein).apply();

//                                                    trainingEditor.putFloat("protein")
                                                }else {
                                                    float NontrainingProtein=NontrainingPref.getFloat("protein",0.0f);
                                                    float Nontrainingcarbs=NontrainingPref.getFloat("carbs",0.0f);
                                                    float NontrainingFats=NontrainingPref.getFloat("fats",0.0f);
                                                    float NontrainingCalories=NontrainingPref.getFloat("calories",0.0f);

                                                    NontrainingEditor.putFloat("protein",Float.valueOf(String.valueOf(oneObj.getDouble("protein")))+NontrainingProtein);
                                                    NontrainingEditor.putFloat("carbs",Float.valueOf(String.valueOf(oneObj.getDouble("carbs")))+NontrainingProtein);
                                                    NontrainingEditor.putFloat("fats",Float.valueOf(String.valueOf(oneObj.getDouble("fats")))+NontrainingProtein);
                                                    NontrainingEditor.putFloat("calories",Float.valueOf(String.valueOf(oneObj.getDouble("calories")))+NontrainingProtein).apply();

                                                }
*/


/*
                                                database_daySelection.insertContact(preferences.getString("day", ""), preferences.getString("meal", ""), preferences.getString("food", ""), oneObj.getString("food_name")
                                                        , preferences.getString("food_category", ""), oneObj.getDouble("protein"), oneObj.getDouble("carbs")
                                                        , oneObj.getDouble("fats"),oneObj.getDouble("calories"), Float.parseFloat(screen_six_gram_edit.getText().toString()));*/



                                                if (protein>0d) {
                                                    database_daySelection.insertMeal(preferences.getString("day", ""), preferences.getString("meal", ""), preferences.getString("food", ""), oneObj.getString("food_name")
                                                            , preferences.getString("food_category", ""),(float) protein,(float) carbs
                                                            ,(float) fats,(float) calories, Float.parseFloat(screen_six_gram_edit.getText().toString()));
                                                }
                                                Calculation_class calculation_class=new Calculation_class(context);

                                                calculation_class.TableCalculation(preferences.getString("day", ""));
                                                fragmentTransaction.replace(R.id.viewpager, FragmentStack.getInstance().pop()).commit();
//                                                fragmentTransaction.replace(R.id.viewpager,new Screen_three_selectfood_fragment()).commit();

//                                                Screen_five_foodbycategory_fragment screen_five_foodbycategory_fragment=new Screen_five_foodbycategory_fragment();
//                                                screen_five_foodbycategory_fragment.dismissPopup();


                                            } catch (JSONException e) {

                                                e.printStackTrace();
                                            }
                                          /*  for (int i=1;i<=16;i++){
database_daySelection.deleteContact(i);
                                            }*/
                                            dialog.dismiss();
                                        }
                                    });

                                 /*   Cursor rs = database_daySelection.getData(preferences.getString("food", ""), preferences.getString("day", ""), preferences.getString("meal", ""));
                                    *//*Cursor rs = database_daySelection.getData("select * from "+database_daySelection.TABLE_NAME+" where "+database_daySelection.MEAL_COL_FOOD+"='"+preferences.getString("food","")+"' AND " +
                                            " "+database_daySelection.MEAL_COL_MEAL+"='"+preferences.getString("meal","")+"' AND "+database_daySelection.MEAL_COL_DAYSELECT+"='"+preferences.getString("day","")+"'");*//*
//                                           Cursor rs = database_daySelection.getData("Food_1","training","Meal_1");
                                    rs.moveToFirst();
                                    int i = rs.getColumnCount();
                                    for (int p = 0; p < i; p++) {
                                        Log.e("1 id response id", rs.getString(0));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_FOOD)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_MEAL)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_FOOD_CATEGORY)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_DAYSELECT)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_PROTEIN)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_CALORIES)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_CARBS)));
                                        Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_FATS)));

                                    }
//                                    Log.e("1 id response ", rs.getString(rs.getColumnIndex(database_daySelection.MEAL_COL_ID)));

                                    if (!rs.isClosed()) {
                                        rs.close();
                                    }*/
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(context, volleyError.toString(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    RequestQueue rQueue = Volley.newRequestQueue(context);
                    request.setRetryPolicy(new DefaultRetryPolicy(
                            3000,
                            5,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    rQueue.add(request);
                }

            });


        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }
}
