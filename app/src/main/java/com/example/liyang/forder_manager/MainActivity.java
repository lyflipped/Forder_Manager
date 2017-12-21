package com.example.liyang.forder_manager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String DATA_PATH;
    private String INDEX_PATH;
    private EditText data_dir;
    private EditText index_dir;
    private Button dir_select;
    private Button index_select;
    private String TAG = "ROOTPATH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data_dir = (EditText) findViewById(R.id.data_dir);
        index_dir = (EditText)findViewById(R.id.index_dir);
        dir_select = (Button) findViewById(R.id.dir_select);
        dir_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Select_Dir.class);
                intent.putExtra("TYPE",1);
                startActivityForResult(intent,1);
            }
        });
        index_select =(Button) findViewById(R.id.index_select);
        index_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Select_Dir.class);
                intent.putExtra("TYPE",2);
                startActivityForResult(intent,2);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String cur_path = bundle.getString("file");
                    DATA_PATH =cur_path;
                    data_dir.setText(cur_path);
                }
                break;
            case 2:
                if(resultCode == RESULT_OK){
                    Bundle bundle = data.getExtras();
                    String cur_path = bundle.getString("file");
                    INDEX_PATH = cur_path;
                    index_dir.setText(cur_path);
                }
                break;
            default:
                break;
        }
    }
}
