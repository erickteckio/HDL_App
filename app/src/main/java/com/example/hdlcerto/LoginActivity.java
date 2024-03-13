package com.example.hdlcerto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.*;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUsuario;
    private EditText editTextSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Inicialize os campos de email e senha
        editTextUsuario = findViewById(R.id.usuarioEditText);
        editTextSenha = findViewById(R.id.senhaEditText);
    }

    public void onLoginButtonClick(View view) {
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenha.getText().toString();

        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("usuario", usuario)
                .add("senha", senha)
                .build();

        Request request = new Request.Builder()
                .url("https://hdlteste.000webhostapp.com/login.php")
                .post(formBody)
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
                    if (serverResponse.equals("Sucesso")) {
                        Intent intent = new Intent(LoginActivity.this, DispActivity.class);
                        startActivity(intent);
                    } else {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }
    public void onCreateAccount(View view){
        Intent intent = new Intent(this, CriarContaActivity.class);
        startActivity(intent);
    }
}
