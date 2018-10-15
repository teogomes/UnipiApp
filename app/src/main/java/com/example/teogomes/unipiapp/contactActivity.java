package com.example.teogomes.unipiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class contactActivity extends AppCompatActivity {
    private Button send;
    private TextView name , tel ,email, body;
    private RadioGroup rg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        name =(TextView) findViewById(R.id.editText2);
        tel =(TextView) findViewById(R.id.editText3);
        email =(TextView) findViewById(R.id.editText4);
        body =(TextView) findViewById(R.id.editText5);
        send=(Button)findViewById(R.id.sendButton);
        rg = (RadioGroup)findViewById(R.id.departmentRadioGroup);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                RadioButton radioButton = findViewById(rg.getCheckedRadioButtonId());
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"uniappAdmins@hotmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT, "From The Contact Form of UniApp");
                i.putExtra(Intent.EXTRA_TEXT   , "From: " + name.getText().toString()+"\nTel: "+tel.getText().toString()+"\n Email: "+email.getText().toString()+"\nQuestion: "+body.getText().toString() + "\nDepartment: " +radioButton.getText().toString() );
                try {
                    startActivity(Intent.createChooser(i, "Τhank you for contacting us"));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
