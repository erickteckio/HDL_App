package com.example.hdlcerto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GaleriaActivity extends AppCompatActivity {
    private LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        container = findViewById(R.id.notificacoes); // Substitua pelo ID do seu LinearLayout
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://hdlteste.000webhostapp.com/buscanotificacoes.php")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {

                    String serverResponse = response.body().string();
                    Log.d("Server Response", serverResponse); // Imprime a resposta do servidor no log
                    try {
                        JSONArray jsonArray = new JSONArray(serverResponse);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject notificacao = jsonArray.getJSONObject(i);
                            String nome = notificacao.getString("nome");
                            String id = notificacao.getString("id");
                            String data = notificacao.getString("data");
                            String caminho = notificacao.getString("caminho");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    View view = LayoutInflater.from(GaleriaActivity.this).inflate(R.layout.activity_galeria, null); // Substitua pelo layout da sua notificação
                                    TextView nomeDisp = view.findViewById(R.id.NomeDisp);
                                    nomeDisp.setText(nome);
                                    TextView Data = view.findViewById(R.id.Data);
                                    Data.setText(data);
                                    container.addView(view);
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    public void telaNot(View view) {
        Intent intent = new Intent(this, NotificacaoActivity.class);
        startActivity(intent);
    }
    public void telaCamera(View view) {
        Intent intent = new Intent(this, DispActivity.class);
        startActivity(intent);
    }
}