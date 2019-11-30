package com.example.desqtop_dr.inventario;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class Actvity_Altas extends AppCompatActivity {
    EditText editText_identificador, editText_equipo, editText_aula, editText_categoria, editText_Marca, editText_descripcion;
    Button btn_limpiar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actvity__altas);

        editText_identificador=(EditText)findViewById(R.id.editText_identificador);
        editText_aula=(EditText)findViewById(R.id.editText_aula);
        editText_equipo=(EditText)findViewById(R.id.editText_equipo);
        editText_categoria=(EditText)findViewById(R.id.editText_categoria);
        editText_Marca=(EditText)findViewById(R.id.editText_Marca);
        editText_descripcion=(EditText)findViewById(R.id.editText_descripcion);
        btn_limpiar=(Button) findViewById(R.id.btn_limpiar);

        btn_limpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                editText_identificador.setText("");
                editText_aula.setText("");
                editText_equipo.setText("");
                editText_categoria.setText("");
                editText_Marca.setText("");
                editText_descripcion.setText("");
            }
        });
    }

    public void limpiar() {
        editText_identificador.setText("");
        editText_aula.setText("");
        editText_equipo.setText("");
        editText_categoria.setText("");
        editText_Marca.setText("");
        editText_descripcion.setText("");
    }

    public void agreagarRegistros(View v)
    {
        ConnectivityManager cm =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni =cm.getActiveNetworkInfo();

        Log.w("MSJ WIFI", ni.isConnected()+"");

        if (ni !=null && ni.isConnected()){

            String i = editText_identificador.getText().toString();
            String a = editText_aula.getText().toString();
            String e = editText_equipo.getText().toString();
            String c = editText_categoria.getText().toString();
            String m =editText_Marca.getText().toString();
            String d =editText_descripcion.getText().toString();

            new AgregarAlumno().execute(i,a,e,c,m,d);
            limpiar();
        }//if
    }

    class AgregarAlumno extends AsyncTask<String,String,String> {
        @Override
        protected String doInBackground(String... datos) {
            Map<String,String> registros=new HashMap<String, String>();

            registros.put("i",datos[0]);
            registros.put("a",datos[1]);
            registros.put("e",datos[2]);
            registros.put("c",datos[3]);
            registros.put("m",datos[4]);
            registros.put("d",datos[5]);

            Log.i("MSJ:", Arrays.toString(datos));

            AnalizadorJSON aJSON = new AnalizadorJSON();

            String url="http://localhost/HTTP_Android/agregarAlumno.php";
            String metodoEnvio="POST";

            final JSONObject jsonObject= aJSON.peticionHTTP(url, metodoEnvio, registros);

            Log.i("MSJ JSON:",jsonObject.toString());

            try{
                Log.i("MSJ JSON:",jsonObject.toString());

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();

                    }
                });

                int exito=jsonObject.getInt( "exito");

                if(exito==1){
                    Log.i("MSJ", "Registro agregado");
                }else{
                    Log.i("MSJ",  "Error en la agregación");
                }
            }catch(JSONException e){
                e.printStackTrace();
            }

            return null;
        }
    }
}
