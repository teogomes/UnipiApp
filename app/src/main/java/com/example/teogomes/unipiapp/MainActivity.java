package com.example.teogomes.unipiapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private CardView uniCard,managementCard,departmentsCard,servicesCard,loginCard,communicationCard;
    private Menu menu;
    private String itemTitle1,itemTitle2,itemTitle3,itemTitle4,itemTitle5;
    private boolean showPop = false,logged;
    private TextView helloText,loginCardText;
    private ImageView icon;
    @Override
    public boolean releaseInstance() {
        return super.releaseInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        icon = (ImageView) findViewById(R.id.loginCardIcon);
        helloText =(TextView)findViewById(R.id.logged);
        loginCardText =(TextView)findViewById(R.id.loginCardText);
        uniCard = (CardView)findViewById(R.id.unicard);
        managementCard =(CardView)findViewById(R.id.managmentCard);
        departmentsCard =(CardView)findViewById(R.id.departmentsCard);
        servicesCard = (CardView)findViewById(R.id.servicesCard);
        communicationCard =(CardView)findViewById(R.id.communicationCard);
        loginCard =(CardView)findViewById(R.id.loginCard);
        uniCard.setOnClickListener(this);
        managementCard.setOnClickListener(this);
        departmentsCard.setOnClickListener(this);
        servicesCard.setOnClickListener(this);
        communicationCard.setOnClickListener(this);
        loginCard.setOnClickListener(this);

        SharedPreferences prefs = getSharedPreferences("LOGGED", MODE_PRIVATE);
        String name = prefs.getString("NAME", "null");
         logged = prefs.getBoolean("LOGGED", false);
        if(logged){
            helloText.setVisibility(View.VISIBLE);
            helloText.setText("HELLO "+ name.toUpperCase()+" HOW ARE YOU TODAY?");
            icon.setImageDrawable(getDrawable(R.drawable.ic_highlight_off_black_24dp));
            loginCardText.setText("Αποσύνδεση");
        }else{
            loginCardText.setText("Σύνδεση");
            helloText.setVisibility(View.GONE);
            icon.setImageDrawable(getDrawable(R.drawable.ic_account_circle_black_24dp));
        }
    }

    @Override
    public void onClick(final View v) {
        itemTitle1 =null;
        itemTitle2 = null;
        itemTitle3 = null;
        itemTitle4 = null;
        itemTitle5 = null;
        switch(v.getId()){
            case R.id.unicard:
               itemTitle1 ="Ιστορία";
               itemTitle2 = "Περιήγηση";
               itemTitle3 = "Τοποθεσία και Πρόσβαση";
               itemTitle4 = "Ανακοινώσεις και Εκδηλώσεις";
                break;
            case R.id.managmentCard:
                itemTitle1 ="Πρύτανης";
                itemTitle2 = "Σύγκλητος";
                itemTitle3 = "Διοικητικές Υπηρεσίες";
                itemTitle4 = "Μέλη ΔΕΠ";
                break;
            case R.id.departmentsCard:
                itemTitle1 ="Οικονομικών και Διεθνών Σπουδών";
                itemTitle2 = "Ναυτιλίας και Βιομηχανίας";
                itemTitle3 = "Χρηματοοικονομικής και Στατιστικής";
                itemTitle4 ="Τεχνολογιών Πληροφορικής και Επικοινωνιών";
                break;
            case R.id.servicesCard:
                itemTitle1 ="Παροχές προς Φοιτητές";
                itemTitle2 = "Ηλεκτρονικές Υπηρεσίες";
                itemTitle3 = "Παροχές Φοιτητικής Μέριμνας";
                itemTitle4 = "Φοιτητική και Κοινωνική Ζωή";
                itemTitle5 = "Συμπληρωματικό Διδακτικό Προσωπικό";
                break;
            case R.id.communicationCard:
                Intent Contactintent = new Intent(this,contactActivity.class);
                startActivity(Contactintent);
                break;
            case R.id.loginCard:
                if(logged){
                    SharedPreferences.Editor editor = getSharedPreferences("LOGGED", MODE_PRIVATE).edit();
                    editor.putBoolean("LOGGED",false);
                    editor.apply();
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this,LoginActivity.class);
                    intent.putExtra("TO","LOGIN");
                    startActivity(intent);
                }
                break;

        }
        final PopupMenu popup = new PopupMenu(MainActivity.this,v);
        popup.getMenuInflater().inflate(R.menu.popup,popup.getMenu());
        // popup.getMenu().findItem(R.id.first).setTitle("test");
        updatePopTitles(popup.getMenu(),itemTitle1,itemTitle2,itemTitle3,itemTitle4,itemTitle5);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch(item.getTitle().toString()){
                    case "Οικονομικών και Διεθνών Σπουδών":
                        itemTitle1="Οικονομικής Επιστήμης";
                        itemTitle2="Οργάνωσης & Διοίκησης Επιχειρήσεων";
                        itemTitle3="Διεθνών & Ευρωπαϊκών Σπουδών";
                        itemTitle4="Τουριστικών Σπουδών";
                        showPop = true;
                        break;
                    case "Ναυτιλίας και Βιομηχανίας":
                        itemTitle1="Ναυτιλιακών Σπουδών";
                        itemTitle2="Βιομηχανικής Διοίκησης & Tεχνολογίας";
                        itemTitle3=null;
                        itemTitle4=null;
                        showPop = true;
                        break;
                    case "Χρηματοοικονομικής και Στατιστικής":
                        itemTitle1="Χρηματοοικονομικής και Τραπεζικής Διοικητικής";
                        itemTitle2="Στατιστικής και Ασφαλιστικής Επιστήμης";
                        itemTitle3=null;
                        itemTitle4=null;
                        showPop = true;
                        break;
                    case "Τεχνολογιών Πληροφορικής και Επικοινωνιών":
                        itemTitle1="Πληροφορικής";
                        itemTitle2="Ψηφιακών Συστημάτων";
                        itemTitle3=null;
                        itemTitle4=null;
                        showPop = true;
                        break;
                    case "Ιστορία":
                        Intent intent = new Intent(getApplicationContext(),textActivity.class);
                        intent.putExtra("FROM","history");
                        startActivity(intent);
                        break;
                    case "Περιήγηση":
                        startActivity(new Intent(getApplicationContext(),slideShowActivity.class));
                        break;
                    case "Τοποθεσία και Πρόσβαση":
                        Intent intent1 = new Intent(getApplicationContext(),textActivity.class);
                        intent1.putExtra("FROM","map");
                        startActivity(intent1);
                        break;
                    case "Ανακοινώσεις και Εκδηλώσεις":
                        startActivity(new Intent(getApplicationContext(),Announcements.class));
                        break;
                    case "Μέλη ΔΕΠ":
                        startActivity(new Intent(getApplicationContext(),members.class));
                        break;
                    case "Πρύτανης":
                        Intent intent2 = new Intent(getApplicationContext(),textActivity.class);
                        intent2.putExtra("FROM","pritanis");
                        startActivity(intent2);
                        break;
                    case "Σύγκλητος":
                        Intent intent3 = new Intent(getApplicationContext(),textActivity.class);
                        intent3.putExtra("FROM","sigklitos");
                        startActivity(intent3);
                        break;
                    case "Διοικητικές Υπηρεσίες":
                        Intent intent4 = new Intent(getApplicationContext(),textActivity.class);
                        intent4.putExtra("FROM","dioikitikes");
                        startActivity(intent4);
                        break;
                    case "Παροχές προς Φοιτητές":
                        Intent intent5 = new Intent(getApplicationContext(),textActivity.class);
                        intent5.putExtra("FROM","library");
                        startActivity(intent5);
                        break;
                    case "Παροχές Φοιτητικής Μέριμνας":
                        Intent intent6 = new Intent(getApplicationContext(),textActivity.class);
                        intent6.putExtra("FROM","fai");
                        startActivity(intent6);
                        break;
                    case "Φοιτητική και Κοινωνική Ζωή":
                        Intent intent7 = new Intent(getApplicationContext(),textActivity.class);
                        intent7.putExtra("FROM","socialife");
                        startActivity(intent7);
                        break;
                    case "Συμπληρωματικό Διδακτικό Προσωπικό":
                        Intent intent8 = new Intent(getApplicationContext(),textActivity.class);
                        intent8.putExtra("FROM","simpliromatiko");
                        startActivity(intent8);
                        break;
                    case "Ηλεκτρονικές Υπηρεσίες":
                        Intent intent9 = new Intent(getApplicationContext(),textActivity.class);
                        intent9.putExtra("FROM","electro");
                        startActivity(intent9);
                        break;

                }
               // startActivity(intent);

                if(showPop){
                    final PopupMenu popup2 = new PopupMenu(MainActivity.this,v);
                    popup2.getMenuInflater().inflate(R.menu.popup,popup2.getMenu());
                    popup2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Intent intent = new Intent(getApplicationContext(),textActivity.class);
                            switch (item.getTitle().toString()) {
                                case "Οικονομικής Επιστήμης":
                                    intent.putExtra("FROM","economics");
                                    break;
                                case "Οργάνωσης & Διοίκησης Επιχειρήσεων":
                                    intent.putExtra("FROM","managment");
                                    break;
                                case "Διεθνών & Ευρωπαϊκών Σπουδών":
                                    intent.putExtra("FROM","europe");
                                    break;
                                case "Τουριστικών Σπουδών":
                                    intent.putExtra("FROM","tourism");
                                    break;
                                case "Βιομηχανικής Διοίκησης & Tεχνολογίας":
                                    intent.putExtra("FROM","biomechanical");
                                    break;
                                case "Ναυτιλιακών Σπουδών":
                                    intent.putExtra("FROM","nautiliaka");
                                    break;
                                case "Χρηματοοικονομικής και Τραπεζικής Διοικητικής":
                                    intent.putExtra("FROM","xrimatooikonomiki");
                                    break;
                                case "Στατιστικής και Ασφαλιστικής Επιστήμης":
                                    intent.putExtra("FROM","statistiki");
                                    break;
                                case "Πληροφορικής":
                                    intent.putExtra("FROM","cs");
                                    break;
                                case "Ψηφιακών Συστημάτων":
                                    intent.putExtra("FROM","psifiaka");
                                    break;

                            }


                            startActivity(intent);
                            return false;
                        }
                    });
                    updatePopTitles(popup2.getMenu(),itemTitle1,itemTitle2,itemTitle3,itemTitle4,itemTitle5);
                    popup2.show();
                }
                showPop = false;
                return false;
            }
        });

        popup.show();



    }




    public void updatePopTitles(Menu menu,String title1,String title2,String title3,String title4,String title5){
        menu.clear();
        if(title1!=null) {
            MenuItem first = menu.add(Menu.NONE, 1, 0, title1);
        }
        if(title2!=null) {
            MenuItem second = menu.add(Menu.NONE, 2, 0, title2);
        }
        if(title3!=null) {
            MenuItem third = menu.add(Menu.NONE, 3, 0, title3);
        }
        if(title4!=null) {
            MenuItem fourth = menu.add(Menu.NONE, 4, 0, title4);
        }
        if(title5!=null) {
            MenuItem fifth = menu.add(Menu.NONE, 5, 0, title5);
        }
    }


}
