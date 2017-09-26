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
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.project.MyNutritionApplication.mynutrition.Extra.FragmentStack;
import com.project.MyNutritionApplication.mynutrition.R;

/**
 * Created by Saubhagyam on 24/08/2017.
 */

public class Screen_one_DaySelection_fragment extends Fragment {

    Button nontraining_days,training_days,day_goal;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    SharedPreferences pref;
SharedPreferences.Editor editor;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.screen_one_dayselection_fragment,container,false);

        training_days= (Button) view.findViewById(R.id.training_days);
        nontraining_days= (Button) view.findViewById(R.id.nontraining_days);
        day_goal= (Button) view.findViewById(R.id.day_goal);

pref=getContext().getSharedPreferences("foodSelection",Context.MODE_PRIVATE);
editor=pref.edit();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        fragmentManager=getFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();


        SharedPreferences trainingPref=getContext().getSharedPreferences("trainingPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor trainingEditor=trainingPref.edit();


        SharedPreferences NontrainingPref=getContext().getSharedPreferences("NontrainingPref",Context.MODE_PRIVATE);
        SharedPreferences.Editor NontrainingEditor=NontrainingPref.edit();

        trainingEditor.clear().apply();
        NontrainingEditor.clear().apply();

        training_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getContext(), "training days", Toast.LENGTH_SHORT).show();
//                fragmentTransaction.setCustomAnimations(R.anim.set_in_right,android.R.anim.);
                editor.putString("day","training").apply();
                FragmentStack.getInstance().push(new Screen_one_DaySelection_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen2_selectMeal()).commit();
            }
        });

        nontraining_days.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "nontraining days", Toast.LENGTH_SHORT).show();
//                fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,R.anim.set_in_right);
                editor.putString("day","non-training").apply();
                FragmentStack.getInstance().push(new Screen_one_DaySelection_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Screen2_selectMeal()).commit();
            }
        });   day_goal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "nontraining days", Toast.LENGTH_SHORT).show();
//                fragmentTransaction.setCustomAnimations(android.R.anim.slide_out_right,R.anim.set_in_right);
                editor.putString("day","daygoal").apply();
                FragmentStack.getInstance().push(new Screen_one_DaySelection_fragment());
                fragmentTransaction.setCustomAnimations(R.anim.set_in_right, R.anim.set_left_out);
                fragmentTransaction.replace(R.id.viewpager,new Setting_setGoals_tab_layout_Fragment()).commit();
            }
        });


    }
}
