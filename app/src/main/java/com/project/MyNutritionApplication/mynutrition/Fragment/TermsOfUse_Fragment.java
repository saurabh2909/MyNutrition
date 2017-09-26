package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.MyNutritionApplication.mynutrition.R;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

/**
 * Created by Saubhagyam on 09/09/2017.
 */

public class TermsOfUse_Fragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.trems_of_use_fragment,container,false);

        return view;
    }
}
