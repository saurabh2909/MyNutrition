package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.MyNutritionApplication.mynutrition.Database_Classes.NewDatabase_CreateTable;
import com.project.MyNutritionApplication.mynutrition.R;

/**
 * Created by Saubhagyam on 02/09/2017.
 */

public class Setting_setGoal_tab_child_Fragment_nontraining extends Fragment {

    ImageView btn_daygoal, btn_meal1, btn_meal2, btn_meal3, btn_meal4, btn_meal5;

    TextView protein_daygoal, carbs_daygoal, fats_daygoal, calaries_daygoal;
    TextView protein_meal1, carbs_meal1, fats_meal1, calaries_meal1;
    TextView protein_meal2, carbs_meal2, fats_meal2, calaries_meal2;
    TextView protein_meal3, carbs_meal3, fats_meal3, calaries_meal3;
    TextView protein_meal4, carbs_meal4, fats_meal4, calaries_meal4;
    TextView protein_meal5, carbs_meal5, fats_meal5, calaries_meal5;

    EditText protein_edit_daygoal, carbs_edit_daygoal, fats_edit_daygoal, calaries_edit_daygoal;
    EditText protein_edit_meal1, carbs_edit_meal1, fats_edit_meal1, calaries_edit_meal1;
    EditText protein_edit_meal2, carbs_edit_meal2, fats_edit_meal2, calaries_edit_meal2;
    EditText protein_edit_meal3, carbs_edit_meal3, fats_edit_meal3, calaries_edit_meal3;
    EditText protein_edit_meal4, carbs_edit_meal4, fats_edit_meal4, calaries_edit_meal4;
    EditText protein_edit_meal5, carbs_edit_meal5, fats_edit_meal5, calaries_edit_meal5;

    int bit_daygoal, bit_meal1, bit_meal2, bit_meal3, bit_meal4, bit_meal5;

    NewDatabase_CreateTable database_daySelection;

    TextView Meal4_textview, Meal5_textview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_setgoals_tab_child_fragment, container, false);


        database_daySelection = new NewDatabase_CreateTable(getActivity());
        btn_daygoal = (ImageView) view.findViewById(R.id.btn_daygoal);
        btn_meal1 = (ImageView) view.findViewById(R.id.btn_meal1);
        btn_meal2 = (ImageView) view.findViewById(R.id.btn_meal2);
        btn_meal3 = (ImageView) view.findViewById(R.id.btn_meal3);
        btn_meal4 = (ImageView) view.findViewById(R.id.btn_meal4);
        btn_meal5 = (ImageView) view.findViewById(R.id.btn_meal5);

        protein_daygoal = (TextView) view.findViewById(R.id.protein_daygoal);
        protein_meal1 = (TextView) view.findViewById(R.id.protein_meal1);
        protein_meal2 = (TextView) view.findViewById(R.id.protein_meal2);
        protein_meal3 = (TextView) view.findViewById(R.id.protein_meal3);
        protein_meal4 = (TextView) view.findViewById(R.id.protein_meal4);
        protein_meal5 = (TextView) view.findViewById(R.id.protein_meal5);

        carbs_daygoal = (TextView) view.findViewById(R.id.carbs_daygoal);
        carbs_meal1 = (TextView) view.findViewById(R.id.carbs_meal1);
        carbs_meal2 = (TextView) view.findViewById(R.id.carbs_meal2);
        carbs_meal3 = (TextView) view.findViewById(R.id.carbs_meal3);
        carbs_meal4 = (TextView) view.findViewById(R.id.carbs_meal4);
        carbs_meal5 = (TextView) view.findViewById(R.id.carbs_meal5);

        fats_daygoal = (TextView) view.findViewById(R.id.fats_daygoal);
        fats_meal1 = (TextView) view.findViewById(R.id.fats_meal1);
        fats_meal2 = (TextView) view.findViewById(R.id.fats_meal2);
        fats_meal3 = (TextView) view.findViewById(R.id.fats_meal3);
        fats_meal4 = (TextView) view.findViewById(R.id.fats_meal4);
        fats_meal5 = (TextView) view.findViewById(R.id.fats_meal5);

        calaries_daygoal = (TextView) view.findViewById(R.id.calaries_daygoal);
        calaries_meal1 = (TextView) view.findViewById(R.id.calaries_meal1);
        calaries_meal2 = (TextView) view.findViewById(R.id.calaries_meal2);
        calaries_meal3 = (TextView) view.findViewById(R.id.calaries_meal3);
        calaries_meal4 = (TextView) view.findViewById(R.id.calaries_meal4);
        calaries_meal5 = (TextView) view.findViewById(R.id.calaries_meal5);


        //****** EditText find view********


        protein_edit_daygoal = (EditText) view.findViewById(R.id.protein_edit_daygoal);
        protein_edit_meal1 = (EditText) view.findViewById(R.id.protein_edit_meal1);
        protein_edit_meal2 = (EditText) view.findViewById(R.id.protein_edit_meal2);
        protein_edit_meal3 = (EditText) view.findViewById(R.id.protein_edit_meal3);
        protein_edit_meal4 = (EditText) view.findViewById(R.id.protein_edit_meal4);
        protein_edit_meal5 = (EditText) view.findViewById(R.id.protein_edit_meal5);

        carbs_edit_daygoal = (EditText) view.findViewById(R.id.carbs_edit_daygoal);
        carbs_edit_meal1 = (EditText) view.findViewById(R.id.carbs_edit_meal1);
        carbs_edit_meal2 = (EditText) view.findViewById(R.id.carbs_edit_meal2);
        carbs_edit_meal3 = (EditText) view.findViewById(R.id.carbs_edit_meal3);
        carbs_edit_meal4 = (EditText) view.findViewById(R.id.carbs_edit_meal4);
        carbs_edit_meal5 = (EditText) view.findViewById(R.id.carbs_edit_meal5);

        fats_edit_daygoal = (EditText) view.findViewById(R.id.fats_edit_daygoal);
        fats_edit_meal1 = (EditText) view.findViewById(R.id.fats_edit_meal1);
        fats_edit_meal2 = (EditText) view.findViewById(R.id.fats_edit_meal2);
        fats_edit_meal3 = (EditText) view.findViewById(R.id.fats_edit_meal3);
        fats_edit_meal4 = (EditText) view.findViewById(R.id.fats_edit_meal4);
        fats_edit_meal5 = (EditText) view.findViewById(R.id.fats_edit_meal5);

        calaries_edit_daygoal = (EditText) view.findViewById(R.id.calaries_edit_daygoal);
        calaries_edit_meal1 = (EditText) view.findViewById(R.id.calaries_edit_meal1);
        calaries_edit_meal2 = (EditText) view.findViewById(R.id.calaries_edit_meal2);
        calaries_edit_meal3 = (EditText) view.findViewById(R.id.calaries_edit_meal3);
        calaries_edit_meal4 = (EditText) view.findViewById(R.id.calaries_edit_meal4);
        calaries_edit_meal5 = (EditText) view.findViewById(R.id.calaries_edit_meal5);


        Meal5_textview = (TextView) view.findViewById(R.id.Meal5_textview);
        Meal4_textview = (TextView) view.findViewById(R.id.Meal4_textview);


                Meal5_textview.setText("Meal 5");
                Meal4_textview.setText("Meal 4");

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();


        NewDatabase_CreateTable database_settings = new NewDatabase_CreateTable(getContext());


        Cursor cursor1 = database_settings.getSetting("non-training", "Day Goal");
        cursor1.moveToFirst();
        if (cursor1.moveToFirst()) {
            protein_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CARB)));
            fats_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CALORY)));

            protein_edit_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_edit_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CARB)));
            fats_edit_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_edit_daygoal.setText(cursor1.getString(cursor1.getColumnIndex(database_settings.SETTING_CALORY)));
        }


        Cursor cursor2 = database_settings.getSetting("non-training", "Meal 1");
        cursor2.moveToFirst();
        if (cursor2.moveToFirst()) {
            protein_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_CARB)));
            fats_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_CALORY)));

            protein_edit_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_edit_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_CARB)));
            fats_edit_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_edit_meal1.setText(cursor2.getString(cursor2.getColumnIndex(database_settings.SETTING_CALORY)));
        }


        Cursor cursor3 = database_settings.getSetting("non-training", "Meal 2");
        cursor3.moveToFirst();
        if (cursor3.moveToFirst()) {
            protein_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_CARB)));
            fats_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_CALORY)));

            protein_edit_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_edit_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_CARB)));
            fats_edit_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_edit_meal2.setText(cursor3.getString(cursor3.getColumnIndex(database_settings.SETTING_CALORY)));
        }


        Cursor cursor4 = database_settings.getSetting("non-training", "Meal 3");
        cursor4.moveToFirst();
        if (cursor4.moveToFirst()) {
            protein_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_CARB)));
            fats_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_CALORY)));

            protein_edit_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_edit_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_CARB)));
            fats_edit_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_edit_meal3.setText(cursor4.getString(cursor4.getColumnIndex(database_settings.SETTING_CALORY)));
        }

        Cursor cursor5 = database_settings.getSetting("non-training", "Meal 4");
        cursor2.moveToFirst();
        if (cursor5.moveToFirst()) {
            protein_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_CARB)));
            fats_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_CALORY)));

            protein_edit_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_edit_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_CARB)));
            fats_edit_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_edit_meal4.setText(cursor5.getString(cursor5.getColumnIndex(database_settings.SETTING_CALORY)));
        }


        Cursor cursor6 = database_settings.getSetting("non-training", "Meal 5");
        cursor6.moveToFirst();
        if (cursor6.moveToFirst()) {
            protein_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_CARB)));
            fats_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_CALORY)));

            protein_edit_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_PROTEIN)));
            carbs_edit_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_CARB)));
            fats_edit_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_FAT)));
            calaries_edit_meal5.setText(cursor6.getString(cursor6.getColumnIndex(database_settings.SETTING_CALORY)));
        }


        btn_daygoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit_daygoal == 0) {
                    bit_daygoal = 1;
                    btn_daygoal.setImageDrawable(getResources().getDrawable(R.drawable.save));
                    calaries_daygoal.setVisibility(View.GONE);
                    protein_daygoal.setVisibility(View.GONE);
                    fats_daygoal.setVisibility(View.GONE);
                    carbs_daygoal.setVisibility(View.GONE);


                    calaries_edit_daygoal.setVisibility(View.VISIBLE);
                    protein_edit_daygoal.setVisibility(View.VISIBLE);
                    fats_edit_daygoal.setVisibility(View.VISIBLE);
                    carbs_edit_daygoal.setVisibility(View.VISIBLE);

                    calaries_edit_daygoal.setText(calaries_daygoal.getText().toString());
                    protein_edit_daygoal.setText(protein_daygoal.getText().toString());
                    fats_edit_daygoal.setText(carbs_daygoal.getText().toString());
                    carbs_edit_daygoal.setText(carbs_daygoal.getText().toString());


                } else {
                    bit_daygoal = 0;
                    btn_daygoal.setImageDrawable(getResources().getDrawable(R.drawable.edit));
                    calaries_daygoal.setVisibility(View.VISIBLE);
                    protein_daygoal.setVisibility(View.VISIBLE);
                    fats_daygoal.setVisibility(View.VISIBLE);
                    carbs_daygoal.setVisibility(View.VISIBLE);


                    calaries_edit_daygoal.setVisibility(View.GONE);
                    protein_edit_daygoal.setVisibility(View.GONE);
                    fats_edit_daygoal.setVisibility(View.GONE);
                    carbs_edit_daygoal.setVisibility(View.GONE);

                    calaries_daygoal.setText(calaries_edit_daygoal.getText().toString());
                    carbs_daygoal.setText(carbs_edit_daygoal.getText().toString());
                    fats_daygoal.setText(fats_edit_daygoal.getText().toString());
                    protein_daygoal.setText(protein_edit_daygoal.getText().toString());


                    database_daySelection.insertSetting("non-training", "Day Goal", Float.parseFloat(protein_edit_daygoal.getText().toString()), Float.parseFloat(carbs_edit_daygoal.getText().toString()), Float.parseFloat(fats_edit_daygoal.getText().toString()), Float.parseFloat(calaries_edit_daygoal.getText().toString()));


                }
            }
        });


        btn_meal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit_meal1 == 0) {
                    bit_meal1 = 1;
                    btn_meal1.setImageDrawable(getResources().getDrawable(R.drawable.save));

                    calaries_meal1.setVisibility(View.GONE);
                    protein_meal1.setVisibility(View.GONE);
                    fats_meal1.setVisibility(View.GONE);
                    carbs_meal1.setVisibility(View.GONE);


                    calaries_edit_meal1.setVisibility(View.VISIBLE);
                    protein_edit_meal1.setVisibility(View.VISIBLE);
                    fats_edit_meal1.setVisibility(View.VISIBLE);
                    carbs_edit_meal1.setVisibility(View.VISIBLE);

                    calaries_edit_meal1.setText(calaries_meal1.getText().toString());
                    protein_edit_meal1.setText(protein_meal1.getText().toString());
                    fats_edit_meal1.setText(carbs_meal1.getText().toString());
                    carbs_edit_meal1.setText(carbs_meal1.getText().toString());
                } else {
                    bit_meal1 = 0;
                    btn_meal1.setImageDrawable(getResources().getDrawable(R.drawable.edit));

                    calaries_meal1.setVisibility(View.VISIBLE);
                    protein_meal1.setVisibility(View.VISIBLE);
                    fats_meal1.setVisibility(View.VISIBLE);
                    carbs_meal1.setVisibility(View.VISIBLE);


                    calaries_edit_meal1.setVisibility(View.GONE);
                    protein_edit_meal1.setVisibility(View.GONE);
                    fats_edit_meal1.setVisibility(View.GONE);
                    carbs_edit_meal1.setVisibility(View.GONE);

                    calaries_meal1.setText(calaries_edit_meal1.getText().toString());
                    carbs_meal1.setText(carbs_edit_meal1.getText().toString());
                    fats_meal1.setText(fats_edit_meal1.getText().toString());
                    protein_meal1.setText(protein_edit_meal1.getText().toString());

                    database_daySelection.insertSetting("non-training", "Meal 1", Float.parseFloat(protein_edit_meal1.getText().toString()), Float.parseFloat(carbs_edit_meal1.getText().toString()), Float.parseFloat(fats_edit_meal1.getText().toString()), Float.parseFloat(calaries_edit_meal1.getText().toString()));
                }
            }
        });

        btn_meal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit_meal2 == 0) {
                    bit_meal2 = 1;
                    btn_meal2.setImageDrawable(getResources().getDrawable(R.drawable.save));

                    calaries_meal2.setVisibility(View.GONE);
                    protein_meal2.setVisibility(View.GONE);
                    fats_meal2.setVisibility(View.GONE);
                    carbs_meal2.setVisibility(View.GONE);


                    calaries_edit_meal2.setVisibility(View.VISIBLE);
                    protein_edit_meal2.setVisibility(View.VISIBLE);
                    fats_edit_meal2.setVisibility(View.VISIBLE);
                    carbs_edit_meal2.setVisibility(View.VISIBLE);

                    calaries_edit_meal2.setText(calaries_meal2.getText().toString());
                    protein_edit_meal2.setText(protein_meal2.getText().toString());
                    fats_edit_meal2.setText(carbs_meal2.getText().toString());
                    carbs_edit_meal2.setText(carbs_meal2.getText().toString());
                } else {
                    bit_meal2 = 0;
                    btn_meal2.setImageDrawable(getResources().getDrawable(R.drawable.edit));

                    calaries_meal2.setVisibility(View.VISIBLE);
                    protein_meal2.setVisibility(View.VISIBLE);
                    fats_meal2.setVisibility(View.VISIBLE);
                    carbs_meal2.setVisibility(View.VISIBLE);


                    calaries_edit_meal2.setVisibility(View.GONE);
                    protein_edit_meal2.setVisibility(View.GONE);
                    fats_edit_meal2.setVisibility(View.GONE);
                    carbs_edit_meal2.setVisibility(View.GONE);

                    calaries_meal2.setText(calaries_edit_meal2.getText().toString());
                    carbs_meal2.setText(carbs_edit_meal2.getText().toString());
                    fats_meal2.setText(fats_edit_meal2.getText().toString());
                    protein_meal2.setText(protein_edit_meal2.getText().toString());

                    database_daySelection.insertSetting("non-training", "Meal 2", Float.parseFloat(protein_edit_meal2.getText().toString()), Float.parseFloat(carbs_edit_meal2.getText().toString()), Float.parseFloat(fats_edit_meal2.getText().toString()), Float.parseFloat(calaries_edit_meal2.getText().toString()));
                }
            }
        });

        btn_meal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit_meal3 == 0) {
                    bit_meal3 = 1;
                    btn_meal3.setImageDrawable(getResources().getDrawable(R.drawable.save));

                    calaries_meal3.setVisibility(View.GONE);
                    protein_meal3.setVisibility(View.GONE);
                    fats_meal3.setVisibility(View.GONE);
                    carbs_meal3.setVisibility(View.GONE);


                    calaries_edit_meal3.setVisibility(View.VISIBLE);
                    protein_edit_meal3.setVisibility(View.VISIBLE);
                    fats_edit_meal3.setVisibility(View.VISIBLE);
                    carbs_edit_meal3.setVisibility(View.VISIBLE);

                    calaries_edit_meal3.setText(calaries_meal3.getText().toString());
                    protein_edit_meal3.setText(protein_meal3.getText().toString());
                    fats_edit_meal3.setText(carbs_meal3.getText().toString());
                    carbs_edit_meal3.setText(carbs_meal3.getText().toString());
                } else {
                    bit_meal3 = 0;
                    btn_meal3.setImageDrawable(getResources().getDrawable(R.drawable.edit));

                    calaries_meal3.setVisibility(View.VISIBLE);
                    protein_meal3.setVisibility(View.VISIBLE);
                    fats_meal3.setVisibility(View.VISIBLE);
                    carbs_meal3.setVisibility(View.VISIBLE);


                    calaries_edit_meal3.setVisibility(View.GONE);
                    protein_edit_meal3.setVisibility(View.GONE);
                    fats_edit_meal3.setVisibility(View.GONE);
                    carbs_edit_meal3.setVisibility(View.GONE);

                    calaries_meal3.setText(calaries_edit_meal3.getText().toString());
                    carbs_meal3.setText(carbs_edit_meal3.getText().toString());
                    fats_meal3.setText(fats_edit_meal3.getText().toString());
                    protein_meal3.setText(protein_edit_meal3.getText().toString());

                }
                database_daySelection.insertSetting("non-training", "Meal 3", Float.parseFloat(protein_edit_meal3.getText().toString()), Float.parseFloat(carbs_edit_meal3.getText().toString()), Float.parseFloat(fats_edit_meal3.getText().toString()), Float.parseFloat(calaries_edit_meal3.getText().toString()));
            }
        });

        btn_meal4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit_meal4 == 0) {
                    bit_meal4 = 1;
                    btn_meal4.setImageDrawable(getResources().getDrawable(R.drawable.save));

                    calaries_meal4.setVisibility(View.GONE);
                    protein_meal4.setVisibility(View.GONE);
                    fats_meal4.setVisibility(View.GONE);
                    carbs_meal4.setVisibility(View.GONE);


                    calaries_edit_meal4.setVisibility(View.VISIBLE);
                    protein_edit_meal4.setVisibility(View.VISIBLE);
                    fats_edit_meal4.setVisibility(View.VISIBLE);
                    carbs_edit_meal4.setVisibility(View.VISIBLE);

                    calaries_edit_meal4.setText(calaries_meal4.getText().toString());
                    protein_edit_meal4.setText(protein_meal4.getText().toString());
                    fats_edit_meal4.setText(carbs_meal4.getText().toString());
                    carbs_edit_meal4.setText(carbs_meal4.getText().toString());
                } else {
                    bit_meal4 = 0;
                    btn_meal4.setImageDrawable(getResources().getDrawable(R.drawable.edit));

                    calaries_meal4.setVisibility(View.VISIBLE);
                    protein_meal4.setVisibility(View.VISIBLE);
                    fats_meal4.setVisibility(View.VISIBLE);
                    carbs_meal4.setVisibility(View.VISIBLE);


                    calaries_edit_meal4.setVisibility(View.GONE);
                    protein_edit_meal4.setVisibility(View.GONE);
                    fats_edit_meal4.setVisibility(View.GONE);
                    carbs_edit_meal4.setVisibility(View.GONE);

                    calaries_meal4.setText(calaries_edit_meal4.getText().toString());
                    carbs_meal4.setText(carbs_edit_meal4.getText().toString());
                    fats_meal4.setText(fats_edit_meal4.getText().toString());
                    protein_meal4.setText(protein_edit_meal4.getText().toString());
                    database_daySelection.insertSetting("non-training", "Meal 4", Float.parseFloat(protein_edit_meal4.getText().toString()), Float.parseFloat(carbs_edit_meal4.getText().toString()), Float.parseFloat(fats_edit_meal4.getText().toString()), Float.parseFloat(calaries_edit_meal4.getText().toString()));
                }
            }
        });

        btn_meal5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bit_meal5 == 0) {
                    bit_meal5 = 1;
                    btn_meal5.setImageDrawable(getResources().getDrawable(R.drawable.save));

                    calaries_meal5.setVisibility(View.GONE);
                    protein_meal5.setVisibility(View.GONE);
                    fats_meal5.setVisibility(View.GONE);
                    carbs_meal5.setVisibility(View.GONE);


                    calaries_edit_meal5.setVisibility(View.VISIBLE);
                    protein_edit_meal5.setVisibility(View.VISIBLE);
                    fats_edit_meal5.setVisibility(View.VISIBLE);
                    carbs_edit_meal5.setVisibility(View.VISIBLE);

                    calaries_edit_meal5.setText(calaries_meal5.getText().toString());
                    protein_edit_meal5.setText(protein_meal5.getText().toString());
                    fats_edit_meal5.setText(carbs_meal5.getText().toString());
                    carbs_edit_meal5.setText(carbs_meal5.getText().toString());
                } else {
                    bit_meal5 = 0;
                    btn_meal5.setImageDrawable(getResources().getDrawable(R.drawable.edit));

                    calaries_meal5.setVisibility(View.VISIBLE);
                    protein_meal5.setVisibility(View.VISIBLE);
                    fats_meal5.setVisibility(View.VISIBLE);
                    carbs_meal5.setVisibility(View.VISIBLE);


                    calaries_edit_meal5.setVisibility(View.GONE);
                    protein_edit_meal5.setVisibility(View.GONE);
                    fats_edit_meal5.setVisibility(View.GONE);
                    carbs_edit_meal5.setVisibility(View.GONE);

                    calaries_meal5.setText(calaries_edit_meal5.getText().toString());
                    carbs_meal5.setText(carbs_edit_meal5.getText().toString());
                    fats_meal5.setText(fats_edit_meal5.getText().toString());
                    protein_meal5.setText(protein_edit_meal5.getText().toString());
                    database_daySelection.insertSetting("non-training", "Meal 5", Float.parseFloat(protein_meal5.getText().toString()), Float.parseFloat(carbs_edit_meal5.getText().toString()), Float.parseFloat(fats_edit_meal5.getText().toString()), Float.parseFloat(calaries_edit_meal5.getText().toString()));
                }
            }
        });
    }
}
