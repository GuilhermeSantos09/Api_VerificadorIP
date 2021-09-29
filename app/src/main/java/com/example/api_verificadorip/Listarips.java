package com.example.api_verificadorip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Listarips extends AppCompatActivity {

    ListView ipsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarips);

        ipsView = findViewById(R.id.ips);

        new Thread(() -> {
            Http_Util util = new Http_Util();

            List<String> ips = util.buscarIPs();

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    android.R.id.text1,
                    ips
            );

            runOnUiThread(() -> {
                ipsView.setAdapter(adapter);
            });
        }).start();

    }
}