package com.example.teogomes.unipiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class members extends AppCompatActivity {
    private Spinner drop1,drop2;
    private EditText nameText,emailText,phoneText;
    private Button addButton,searchButton;
    private DBhelper2 database;
    private ListView listView;
    private RadioGroup radioGroup1;
    private SearchView searchView;
    private static  final String[] grade={"Καθηγητής","Αναπληρωτής Καθηγητής","Επίκουρος Καθηγητής"};
    private static final String[] office={"311/ΚΕΚΤ","304/ΔΕΛΗΓ","316/ΚΕΚΤ","318/ΚΕΚΤ","313/ΚΕΚΤ"};
    private List<String> resultList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        radioGroup1 = (RadioGroup)findViewById(R.id.radioGroup1);
        searchView =(SearchView)findViewById(R.id.searchView1);
        drop1 = (Spinner)findViewById(R.id.spinner1);
        drop2=(Spinner)findViewById(R.id.spinner2);
        nameText = (EditText)findViewById(R.id.memberName);
        emailText=(EditText)findViewById(R.id.memberEmail);
        phoneText= (EditText)findViewById(R.id.memberPhone);
        addButton = (Button)findViewById(R.id.addMemberButton);
        listView =(ListView)findViewById(R.id.listView1);
        database =new  DBhelper2(this);
        searchButton = (Button)findViewById(R.id.searchMemberButton);
        //Adapters

        ArrayAdapter<String> adapterforDrop1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, grade);
        ArrayAdapter<String> adapterforDrop2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, office);
       // listView.setOnItemClickListener(this);

       drop1.setAdapter(adapterforDrop1);
       drop2.setAdapter(adapterforDrop2);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewMember();
            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!searchView.getQuery().toString().equals(""))
                    searchMembers();

            }
        });

    }

    private void addNewMember(){
        String email=null;
        if(!nameText.getText().toString().equals("") && !emailText.getText().toString().equals("") && !phoneText.getText().toString().equals("")) {
            SharedPreferences prefs = getSharedPreferences("LOGGED", MODE_PRIVATE);
            String name = prefs.getString("NAME", "null");
            if(prefs.getBoolean("LOGGED", false)){
                if(name.equals("Admin")){
                    Cursor data = database.searchMemberEmail(emailText.getText().toString());
                    while(data.moveToNext()){
                       email =  data.getString(2);
                    }
                    if(email==null) {
                        database.addMember(nameText.getText().toString(), phoneText.getText().toString(), emailText.getText().toString(), drop1.getSelectedItem().toString(), drop2.getSelectedItem().toString());
                        Toast.makeText(getApplicationContext(), "Member Added", Toast.LENGTH_LONG).show();
                        nameText.setText("");
                        emailText.setText("");
                        phoneText.setText("");
                    }else
                        Toast.makeText(getApplicationContext(),"Email is already registered",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Only Admin Can Add Members",Toast.LENGTH_LONG).show();
                }
            }else
                Toast.makeText(getApplicationContext(),"Your are not Logged in",Toast.LENGTH_LONG).show();


        }
        else{
            if(nameText.getText().toString().equals(""))
                nameText.setError("Provide your Name");
            if(emailText.getText().toString().equals(""))
                emailText.setError("Provide your Email");
            if(phoneText.getText().toString().equals(""))
                phoneText.setError("Provide a Password");
        }
    }

    private void searchMembers(){
        Cursor data = null;
        resultList.clear();
        switch (radioGroup1.getCheckedRadioButtonId()){

            case R.id.nameRadioButton:
                data =database.searchMember("NAME",searchView.getQuery().toString());
                break;
            case  R.id.emailRadioButton:
                data =database.searchMember("EMAIL",searchView.getQuery().toString());
                break;
            case R.id.phoneRadioButton:
               data= database.searchMember("PHONE",searchView.getQuery().toString());
                break;
        }
        while(data.moveToNext()){
            resultList.add("Όνομα: "+data.getString(1)+" Email: "+data.getString(3) +" Βαμθίδα: "+data.getString(4)+" Γραφείο: "+data.getString(5));
        }
        String[] items = resultList.toArray(new String[resultList.size()]); //Converting list to array for the adapter bellow
        data.close();
        ArrayAdapter<String> aa=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(aa);
        listView.setVisibility(View.VISIBLE);
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
