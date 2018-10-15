package com.example.teogomes.unipiapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class slideShowActivity extends AppCompatActivity {

    private ImageView imageslide;
    private Button backButton,forwoardButton;
    private RadioGroup radioGroup;
    private int start=0,end=3,pos;
    private TextView photoCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_show);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        pos = 0;
        imageslide = (ImageView) findViewById(R.id.imageview2);
        photoCount = (TextView) findViewById(R.id.photocount);
        backButton = (Button) findViewById(R.id.backButton);
        forwoardButton = (Button) findViewById(R.id.forwoardButton);
        radioGroup = (RadioGroup) findViewById(R.id.rgroup);

        photoCount.setText(Integer.toString(pos + 1));
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.radioButton17:
                        start = 0;
                        end = 3;
                        break;
                    case R.id.radioButton19:
                        start = 4;
                        end = 6;
                        break;
                    case R.id.radioButton18:
                        start = 7;
                        end = 8;
                        break;
                }
                pos = start;
//                imageslide.setImageResource(images[pos]);
                photoCount.setText(Integer.toString(pos - start + 1));
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos--;
                if (pos < start)
                    pos = end;
//                imageslide.setImageResource(images[pos]);
                photoCount.setText(Integer.toString(pos - start + 1));
            }
        });
        forwoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos++;
                if (pos > end)
                    pos = start;
//                imageslide.setImageResource(images[pos]);
                photoCount.setText(Integer.toString(pos - start + 1));
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
