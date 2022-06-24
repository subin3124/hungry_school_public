package com.subin.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Setting extends AppCompatActivity {

    TextView openSearchSchoolView;
    EditText inputSeachSchool;
    Button btnSearchSchool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        openSearchSchoolView = findViewById(R.id.openSearchSchoolView);
        Dialog searchSchoolDialog = new Dialog(this);
        openSearchSchoolView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSchoolDialog.setContentView(R.layout.find_school_dialog_layout);
                searchSchoolDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                inputSeachSchool = searchSchoolDialog.findViewById(R.id.inputSchoolName);
                btnSearchSchool = searchSchoolDialog.findViewById(R.id.btnSearchSchool);
                searchSchoolDialog.show();
                btnSearchSchool.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Callback searchSchoolCallback = new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                ResponseBody body = response.body();
                                SchoolInfo info = new SchoolInfo();
                                List<SchoolInfo> infos = info.getSchoolLists(body.string());

                            }
                        };
                    }
                });
            }
        });
    }
}