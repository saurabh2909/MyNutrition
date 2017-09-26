package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.MyNutritionApplication.mynutrition.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saubhagyam on 26/08/2017.
 */

public class Setting_setGoals_tab_layout_Fragment extends Fragment {
    public ViewPager viewPager;
    public TabLayout tabLayout;





    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    View convertView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        convertView = inflater.inflate(R.layout.setting_setgoals_tab_layout_fragment, null);

        initScreen();
        return convertView;

    }

    public void initScreen() {

        tabLayout = (TabLayout) convertView.findViewById(R.id.tabX);



        viewPager = (ViewPager) convertView.findViewById(R.id.viewpagerTabLayout);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);


        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#B2D0A4"));
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            //noinspection ConstantConditions
            TextView tv=(TextView)LayoutInflater.from(getContext()).inflate(R.layout.drawer_text,null);
            tabLayout.getTabAt(i).setCustomView(tv);
if (i==0){
    tv.setText("Training Days");
}else {
    tv.setText("Non-training Days");
}
        }

    }


    private void setupViewPager(ViewPager viewPager) {

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

        ViewPagerAdapter adapter = new ViewPagerAdapter(fragmentManager);


        adapter.addFragment(new Setting_setGoal_tab_child_Fragment(), "Training Days");
        adapter.addFragment(new Setting_setGoal_tab_child_Fragment_nontraining(), "Nontraining Days");
        viewPager.setAdapter(adapter);



    }


    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(android.support.v4.app.Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}