package com.project.MyNutritionApplication.mynutrition.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.MyNutritionApplication.mynutrition.R;

/**
 * Created by Saubhagyam on 09/09/2017.
 */

public class Contact_Fragment extends Fragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.contact_fragment,container,false);

        TextView contact_site= (TextView) view.findViewById(R.id.contact_site);
        TextView contact_mailid= (TextView) view.findViewById(R.id.contact_mailid);


        contact_site.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.mynutrition123.com"));
                startActivity(intent);
            }
        });
        contact_mailid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_SEND);
                String[] recipients={"mynutrition123@gmail.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.setType("text/html");
                startActivity(Intent.createChooser(intent, "Send Mail"));
            }
        });

        return view;
    }
}
