package com.example.desqtop_dr.inventario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class login extends AppCompatActivity {
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login=findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Actvity_Altas.class);
                startActivity(i);
            }
        });
    }
}
