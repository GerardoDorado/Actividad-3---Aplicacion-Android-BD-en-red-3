package com.example.desqtop_dr.inventario;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class inicio_crud extends AppCompatActivity {
    Button btn_agregar, btn_modificar, btn_consultar, btn_elimnar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_crud);

        Intent i;

        btn_agregar = findViewById(R.id.btn_agregar);
        btn_consultar = findViewById(R.id.btn_consultar);
        btn_elimnar = findViewById(R.id.btn_Eliminar);
        btn_modificar = findViewById(R.id.btn_modificar);

        btn_agregar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Actvity_Altas.class);
                startActivity(i);
            }
        });

        btn_modificar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ActivityEliminar.class);
                startActivity(i);
            }
        });

        btn_consultar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), BuscarActivity.class);
                startActivity(i);
            }
        });

        btn_elimnar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ActivityModificar.class);
                startActivity(i);
            }
        });
    }
}
