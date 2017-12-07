package com.example.tmoraes.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private User user;
    private EditText usuario = (EditText)findViewById(R.id.edtxtUser);
    private EditText senha = (EditText)findViewById(R.id.edtxtPass);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Cadastrar(View view) {
    }

    public void Logar(View view) {
    }
}
