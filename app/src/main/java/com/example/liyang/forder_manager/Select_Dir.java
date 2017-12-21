package com.example.liyang.forder_manager;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Select_Dir extends AppCompatActivity{
    private String TAG = "Select_Dir";
    private List<String> paths;
    private ListView filelist ;
    private Button  confirm;
    private TextView cur_dir;
    private EditText Dir_Name;
    private Button create;
    private String rootPath="/";
    private String curPath ="/";
   // private int TYPE = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_dir);
        init();
    }
    public void init(){
        Intent  intent =getIntent();
        //TYPE = Integer.valueOf(intent.getStringExtra("TYPE"));
       // Log.d(TAG,"TYPE="+TYPE);
        Toast.makeText(getApplicationContext(),rootPath,Toast.LENGTH_SHORT).show();
        filelist = (ListView) findViewById(R.id.filelist);
        filelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),paths.get(i),Toast.LENGTH_SHORT).show();
                getFileDir(paths.get(i));
            }
        });
        Dir_Name = (EditText) findViewById(R.id.dir_name);
        confirm = (Button) findViewById(R.id.confirm);
        create = (Button) findViewById(R.id.createDir);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Dir_Name.getText().toString().equals(null)) {
                    createDir("index");
                }
                else{
                    createDir(Dir_Name.getText().toString());
                }
                getFileDir(curPath);
                Dir_Name.setText("");
            }
        });
        cur_dir = (TextView) findViewById(R.id.cur_dir);
        cur_dir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFileDir(new File(curPath).getParent());
            }
        });
        cur_dir.setEnabled(true);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(Select_Dir.this,MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("file", curPath);
                data.putExtras(bundle);
                setResult(RESULT_OK,data);
                finish();
            }
        });
        paths = new ArrayList<String>();
        getFileDir(rootPath);

    }
    public void getFileDir(String filePath) {
        paths.clear();
        cur_dir.setText(curPath = filePath);
        //设置向上是否可用
        if (filePath.equals(rootPath))
            cur_dir.setEnabled(false);
        else
            cur_dir.setEnabled(true);
        File f = new File(filePath);
        File[] files = f.listFiles();
        //判断当前下是否有文件夹
        if (files.length <= 0){
            Toast.makeText(this,"一个文件夹都没有",Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < files.length; i++) {
            //过滤一遍
            //1.是否为文件夹
            //2.是否可访问
            if (files[i].isDirectory() && files[i].listFiles() != null) {
                paths.add(files[i].getPath());
            }
        }
        FileAdapter adapter = new FileAdapter(this,paths);
        filelist.setAdapter(adapter);
    }
    public void createDir(String name){
        File file = null;
        try {
            file = new File(cur_dir.getText().toString()+"/"+name);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e+"");
        }
    }
}
