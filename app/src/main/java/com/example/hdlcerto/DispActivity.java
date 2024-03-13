package com.example.hdlcerto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DispActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disp);
    }
    public void telaNot(View view) {
        Intent intent = new Intent(this, NotificacaoActivity.class);
        startActivity(intent);
    }
    public void telaGaleria(View view) {
        Intent intent = new Intent(this, GaleriaActivity.class);
        startActivity(intent);
    }
}