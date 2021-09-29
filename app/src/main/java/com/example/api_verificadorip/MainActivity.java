package com.example.api_verificadorip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buscarIPV4(View view){
        Intent intent = new Intent(this, exibirIP.class);
        intent.putExtra("type", "ipv4");
        startActivity(intent);
    }

    public void buscarIPV6(View view) {
        Intent intent = new Intent(this, exibirIP.class);
        intent.putExtra("type", "ipv6");
        startActivity(intent);
    }

    public void listar(View view) {
        Intent intent = new Intent(this, Listarips.class);
        startActivity(intent);
    }
}