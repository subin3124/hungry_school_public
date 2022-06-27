package com.subin.testapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Entity;
import androidx.room.Room;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
    ListView findSchoolListView;
    SchoolListAdapter adapter;
    Button settingsFinished;
    SettingsEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        openSearchSchoolView = findViewById(R.id.openSearchSchoolView);
        settingsFinished = findViewById(R.id.SettingsComplete);
        Dialog searchSchoolDialog = new Dialog(this);
        openSearchSchoolView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchSchoolDialog.setContentView(R.layout.find_school_dialog_layout);

                inputSeachSchool = searchSchoolDialog.findViewById(R.id.inputSchoolName);
                btnSearchSchool = searchSchoolDialog.findViewById(R.id.btnSearchSchool);
                findSchoolListView = searchSchoolDialog.findViewById(R.id.findSchoolDialogListView);
                searchSchoolDialog.show();
         //       searchSchoolDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                btnSearchSchool.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Neis neis = new Neis();
                        neis.setSchoolName(inputSeachSchool.getText().toString());

                        Callback searchSchoolCallback = new Callback() {
                            @Override
                            public void onFailure(@NonNull Call call, @NonNull IOException e) {

                            }

                            @Override
                            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                                    ResponseBody body = response.body();
                                    SchoolInfo info = new SchoolInfo();
                                    List<SchoolInfo> infos = info.getSchoolLists(body.string());
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            adapter = new SchoolListAdapter(getApplicationContext(),infos);
                                            findSchoolListView.setAdapter(adapter);
                                        }
                                    });


                            }
                        };
                        neis.searchSchoolId(searchSchoolCallback);
                        findSchoolListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                openSearchSchoolView.setText(adapter.getItem(i).getSchoolId());
                                entity.schoolCode = adapter.getItem(i).getSchoolId();
                                entity.educationStateCode = adapter.getItem(i).getRegionCode();
                                searchSchoolDialog.dismiss();

                            }
                        });
                    }

                });
            }
        });
        //a
        settingsFinished.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsDB db = Room.databaseBuilder(getApplicationContext(),SettingsDB.class,"configuration").build();
                SettingsDAO dao = db.dao();
                entity.schoolCode = openSearchSchoolView.getText().toString();
                if(dao.getSettings()!=null){
                    dao.UpdateSettings(entity);
                }else{
                    dao.InitializeSettings(entity);
                }
            }
        });
    }
}