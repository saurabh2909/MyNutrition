package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.project.MyNutritionApplication.mynutrition.Adapter.Foodbycategory_list_adapter;
import com.project.MyNutritionApplication.mynutrition.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Saubhagyam on 30/08/2017.
 */

public class Screen_five_foodbycategory_fragment extends Fragment {
View view;
ListView list_foodbycategory;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView screen_five_food_txt;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.screen_five_select_foodbucategory_fragment,container,false);
        list_foodbycategory= (ListView) view.findViewById(R.id.list_foodbycategory);
        screen_five_food_txt= (TextView) view.findViewById(R.id.screen_five_food_txt);
        preferences=getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        editor=preferences.edit();

        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        final ProgressDialog progressDialog=new ProgressDialog(getContext());
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();

        Log.e("food category",preferences.getString("food_category",""));
        screen_five_food_txt.setText(preferences.getString("food_category","").replace("_"," "));
        String url="http://www.yogeshinds.com/nutrition/list_of_food?category_name="+preferences.getString("food_category","").replace(" ","%20");
        Log.e("list of food url",url);
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {


                try {
                    JSONObject object=new JSONObject(s);
                    if (object.getInt("status")==1){
                        Log.e("list of food",s);
                        progressDialog.dismiss();
                        JSONArray jsonArray= object.getJSONArray("food");

                        Foodbycategory_list_adapter foodbycategory_list_adapter=new Foodbycategory_list_adapter(getContext(),jsonArray,fragmentTransaction);
                        list_foodbycategory.setAdapter(foodbycategory_list_adapter);
                        foodbycategory_list_adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError volleyError) {
            Toast.makeText(getContext(), volleyError.toString(), Toast.LENGTH_SHORT).show();
        }
    }) ;

    RequestQueue rQueue = Volley.newRequestQueue(getContext());
request.setRetryPolicy(new DefaultRetryPolicy(
        3000,
        5,
        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        rQueue.add(request);

    }
    public void dismissPopup(){
//        getContext().onBackPressed();
    }
}
