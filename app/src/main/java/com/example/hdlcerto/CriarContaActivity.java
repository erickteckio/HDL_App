package com.example.hdlcerto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class CriarContaActivity extends AppCompatActivity {
    private EditText editTextEmail;
    private EditText editTextUsuario;
    private EditText editTextSenha;
    private EditText editTextRepetirSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_conta);
        editTextUsuario = findViewById(R.id.usernameInput);
        editTextSenha = findViewById(R.id.passwordInput);
        editTextEmail = findViewById(R.id.emailInput);
        editTextRepetirSenha = findViewById(R.id.repetirSenhaInput);
    }

    @Override
    public int getChangingConfigurations() {
        return super.getChangingConfigurations();
    }

    public void voltaLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void criarConta(View view) {
        String usuario = editTextUsuario.getText().toString();
        String senha = editTextSenha.getText().toString();
        String repetirsenha = editTextRepetirSenha.getText().toString();
        String email = editTextEmail.getText().toString();

        if (!senha.equals(repetirsenha)) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(CriarContaActivity.this, "As senhas não conferem!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("usuario", usuario)
                    .add("senha", senha)
                    .add("email", email)

                    .build();

            Request request = new Request.Builder()
                    .url("https://hdlteste.000webhostapp.com/registrar.php")
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
                            Intent intent = new Intent(CriarContaActivity.this, DispActivity.class);
                            startActivity(intent);
                        }
                    }

                }
            });
        }
    }
}