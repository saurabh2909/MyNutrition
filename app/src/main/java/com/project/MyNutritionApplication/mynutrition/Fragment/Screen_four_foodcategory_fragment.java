package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.project.MyNutritionApplication.mynutrition.Extra.FragmentStack;
import com.project.MyNutritionApplication.mynutrition.R;

/**
 * Created by Saubhagyam on 28/08/2017.
 */

public class Screen_four_foodcategory_fragment extends Fragment {
    TextView screen_four_foodtxt;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    FrameLayout fruits,grains,vegetables,protein_foods,others;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.screen_four_selectfood_category,container,false);

        preferences=getContext().getSharedPreferences("foodSelection", Context.MODE_PRIVATE);
        editor=preferences.edit();
        screen_four_foodtxt= (TextView) view.findViewById(R.id.screen_four_foodtxt);
        fruits= (FrameLayout) view.findViewById(R.id.fruits);
        vegetables= (FrameLayout) view.findViewById(R.id.vegetables);
        grains= (FrameLayout) view.findViewById(R.id.grains);
        protein_foods= (FrameLayout) view.findViewById(R.id.protein_foods);
        others= (FrameLayout) view.findViewById(R.id.others);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
        screen_four_foodtxt.setText(preferences.getString("food","").replace("_"," "));


        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food_category","Fruits").apply();
//                // FragmentStack.getInstance().push(new Screen_four_foodcategory_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen_five_foodbycategory_fragment()).commit();
            }
        });
        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food_category","Vegetables").apply();
                // FragmentStack.getInstance().push(new Screen_four_foodcategory_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen_five_foodbycategory_fragment()).commit();
            }
        });
        grains.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food_category","Grains").apply();
                // FragmentStack.getInstance().push(new Screen_four_foodcategory_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen_five_foodbycategory_fragment()).commit();
            }
        });
        protein_foods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food_category","Protein Foods").apply();
                // FragmentStack.getInstance().push(new Screen_four_foodcategory_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen_five_foodbycategory_fragment()).commit();
            }
        });
        others.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("food_category","Others").apply();
                // FragmentStack.getInstance().push(new Screen_four_foodcategory_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen_five_foodbycategory_fragment()).commit();
            }
        });
    }
}
