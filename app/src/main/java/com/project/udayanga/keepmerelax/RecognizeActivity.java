package com.project.udayanga.keepmerelax;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.udayanga.keepmerelax.DatabaseHelp.AddUser;

public class RecognizeActivity extends AppCompatActivity {

    String name,contact_number,email,password,dob,gender;
    EditText low,peak;
    TextView statusField,roleField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recognize);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetFields();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button button = (Button)findViewById(R.id.buttonSaveRecognize);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUser();
                Intent intent = new Intent(RecognizeActivity.this, AddContactActivity.class);
                startActivity(intent);

            }
        });
    }
    private void resetFields(){
        low=(EditText)findViewById(R.id.editTextLow);
        peak=(EditText)findViewById(R.id.editTextPeak);

        low.setText("");
        peak.setText("");
    }
    private void saveUser(){
        Bundle bundle=getIntent().getExtras();

        name=bundle.getString("name");
        contact_number=bundle.getString("contact_number");
        email=bundle.getString("email");
        password=bundle.getString("password");
        dob=bundle.getString("dob");
        gender=bundle.getString("gender");
        low=(EditText)findViewById(R.id.editTextLow);
        peak=(EditText)findViewById(R.id.editTextPeak);



//        String NAME=name,PASS=password,DOB=dob,GENDER=gender;
        //int LOW= Integer.valueOf(low.getText().toString());
       // int PEAK=Integer.valueOf(peak.getText().toString());

        try{
            String LOW=low.getText().toString();
            String PEAK=peak.getText().toString();
            new AddUser(this,1).execute(contact_number,dob,email,gender,LOW,name,password,PEAK);

        }
        catch(Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
