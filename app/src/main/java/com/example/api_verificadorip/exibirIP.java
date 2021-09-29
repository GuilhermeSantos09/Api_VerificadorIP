package com.example.api_verificadorip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class exibirIP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir);

        EditText txt = findViewById(R.id.txt_ip);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");

        new Thread(()->{

            String ip;

            Http_Util http = new Http_Util();

            if(type.equals("ipv4")){
                ip =  http.buscarIPV4();
            }
            else {
                ip = http.buscarIPV6();
            }

            http.enviarIP(ip);

            runOnUiThread(()->{

                if (null != ip) {
                    txt.setText(ip);
                }
                else {
                    txt.setText("IP não disponível");
                }

            });
        }).start();
    }
}