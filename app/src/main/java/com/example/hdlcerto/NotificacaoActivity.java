package com.example.hdlcerto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NotificacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificacao);



    }
    public void telaGaleria(View view) {
        Intent intent = new Intent(this, GaleriaActivity.class);
        startActivity(intent);
    }
    public void telaCamera(View view) {
        Intent intent = new Intent(this, DispActivity.class);
        startActivity(intent);
    }
}