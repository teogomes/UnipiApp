package com.example.teogomes.unipiapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Announcements extends AppCompatActivity {
    private ListView announcementView;
    private CalendarView calendarView;
    private static final String[] items={"Διακοπή ηλεκτροδότησης κτηρίων 11 Φεβρουαρίου 2018 ","14th Biennial APF Conference: Contemporary Economic, Financial, Business and Policy Issues ","ΑΝΑΚΟΙΝΩΣΗ ΠΡΟΓΡΑΜΜΑΤΟΣ ΚΑΤΑΤΑΚΤΗΡΙΩΝ ΕΞΕΤΑΣΕΩΝ ΤΜΗΜΑΤΟΣ ΝΑΥΤΙΛΙΑΚΩΝ ΣΠΟΥΔΩΝ ΑΚΑΔΗΜΑΪΚΟΥ ΕΤΟΥΣ 2017-2018",
            "ΑΝΑΚΟΙΝΩΣΗ ΣΧΕΤΙΚΑ ΜΕ ΤΗΝ ΕΚΔΗΛΩΣΗ ΔΙΕΘΝΕΙΣ ΚΟΙΝΟΒΟΥΛΕΥΤΙΚΕΣ ΥΠΟΤΡΟΦΙΕΣ"};
    private TextView eventsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        announcementView = (ListView) findViewById(R.id.listView2);
        calendarView = (CalendarView) findViewById(R.id.calendarView1);
        eventsView = (TextView) findViewById(R.id.eventText);
        ArrayAdapter<String> aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        announcementView.setAdapter(aa);
        calendarView.setDate(Calendar.getInstance().getTimeInMillis());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (year == 2018 && month == 1 && dayOfMonth == 13)
                    eventsView.setText("ΑΠΟΚΡΙΑΤΙΚΟ ΠΑΡΤΙ");
                else if (year == 2018 && month == 1 && dayOfMonth == 21)
                    eventsView.setText("ΕΞΕΤΑΣΗ ΕΡΓΑΣΙΑΣ");
                else if (year == 2018 && month == 1 && dayOfMonth == 22)
                    eventsView.setText("ΕΞΕΤΑΣΗ CMS");
                else if (year == 2018 && month == 1 && dayOfMonth == 26)
                    eventsView.setText("ΕΞΕΤΑΣΗ ΕΡΓΑΣΙΑΣ");
                else
                    eventsView.setText("TEΛΟΣ ΕΞΑΜΗΝΟΥ");
            }
        });
    }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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
