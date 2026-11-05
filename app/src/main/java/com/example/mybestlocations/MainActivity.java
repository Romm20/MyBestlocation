package com.example.mybestlocations;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import com.example.mybestlocations.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(MainActivity.this));
        setContentView(binding.getRoot());

        binding.bottomNavView.setSelectedItemId(R.id.nav_home);

        binding.bottomNavView.setOnItemReselectedListener(new NavigationBarView.OnItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId()==R.id.nav_home)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.host,new HomeFragment()).commit();
                }
                if(menuItem.getItemId()==R.id.nav_add){
                    getSupportFragmentManager().beginTransaction().replace(R.id.host,new AddFragment()).commit();
                }

            }
        });
    }
}/*
        binding.btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Telechargement t=new Telechargement();
                t.execute();







            }
        });

        binding.btnSendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number=binding.edphone.getText().toString();
                //envoie sms
                SmsManager smsManager=SmsManager.getDefault(); //sim par defaut
                smsManager.sendTextMessage(number,null,Utilities.REQUEST_SMS,null,null);

            }
            //DEMANDER PERMISSION



        });
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.SEND_SMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.READ_SMS,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.POST_NOTIFICATIONS,
                },1);




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);
        if(requestCode==1)
        {
            if(grantResults.length==0)
        finish();
        else
        {
            if (grantResults[0]== PackageManager.PERMISSION_DENIED || grantResults[3]== PackageManager.PERMISSION_DENIED ) {


                finish();
            }
        }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    class Telechargement extends AsyncTask {
        ArrayList<Position> data=new ArrayList<>();
        AlertDialog alert;
        JSONObject result;

        @Override
        protected void onPreExecute() {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("telechargement");
            dialog.setMessage("veuillez patientez");


            alert = dialog.create();
            alert.show();
        }


        @Override


        protected Object doInBackground(Object[] objects) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            JSONParser parser = new JSONParser();
            result = parser.makeRequest(Utilities.URL_GETALLPOSITION);
            try {
                int success = result.getInt("success");
                if (success == 0) {
                    String msg = result.getString("message");
                } else
                {
                    JSONArray tableau = result.getJSONArray("positions");
                    for (int i = 0; i < tableau.length(); i++) {
                        JSONObject ligne=tableau.getJSONObject(i);
                        int idposition=ligne.getInt("idposition");
                        String numero=ligne.getString("numero");
                        String pseudo=ligne.getString("pseudo");
                        String longitude=ligne.getString("longitude");
                        String latitude=ligne.getString("latitude");


                        Position p = new Position(idposition,numero,pseudo,longitude,latitude);
                        data.add(p);


                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }


            return null;
        }


        @Override
        protected void onPostExecute(Object o) {
            Toast.makeText(MainActivity.this, "reponse" + result.toString(), Toast.LENGTH_SHORT).show();
            alert.dismiss();
            binding.lv_Position.setAdapter(new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,data));



        }
    }

}*/