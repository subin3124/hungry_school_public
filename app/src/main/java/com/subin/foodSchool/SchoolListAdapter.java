package com.subin.foodSchool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.subin.testapplication.R;

import java.util.List;

public class SchoolListAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<SchoolInfo> schoolInfo;

    public SchoolListAdapter(Context context, List<SchoolInfo> schoolInfo) {
        this.context = context;
        this.schoolInfo = schoolInfo;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return schoolInfo.size();
    }

    @Override
    public SchoolInfo getItem(int i) {
        return schoolInfo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = inflater.inflate(R.layout.school_list_layout,null);
        TextView Schoolid = view2.findViewById(R.id.schoolLstId);
        TextView SchoolName = view2.findViewById(R.id.schoolLstName);
        TextView SchoolRegion = view2.findViewById(R.id.schoolLstRegion);
        TextView SchoolHomePage = view2.findViewById(R.id.schoolLstHomePage);
        Schoolid.setText(schoolInfo.get(i).getSchoolId());
        SchoolName.setText(schoolInfo.get(i).getSchoolName());
        SchoolRegion.setText(schoolInfo.get(i).getRegion());
        SchoolHomePage.setText(schoolInfo.get(i).getHomePage());
        return view2;
    }
}
