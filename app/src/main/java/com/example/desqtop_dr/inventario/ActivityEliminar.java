package com.example.desqtop_dr.inventario;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import controlador.AnalizadorJSON;

public class ActivityEliminar extends AppCompatActivity {
    EditText editText_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        editText_Id = findViewById(R.id.editText_Id);
    }

    public void eliminarRegistro(View v) {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();

        Log.w("MSJ wifi", ni.isConnected() + "");

        if (ni != null && ni.isConnected()) {

            String nc = editText_Id.getText().toString();

            if (nc.equals("")) {
                Toast.makeText(getApplicationContext(), "Inserta el número de control en la caja de texto", Toast.LENGTH_LONG).show();
            } else {
                new EliminarAlumno().execute(nc);
                editText_Id.setText("");
            }
        }
    }

    class EliminarAlumno extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... datos) {
            Map<String, String> registros = new HashMap<>();
            registros.put("id", datos[0]);

            Log.i("MSJ datos", Arrays.toString(datos));
            AnalizadorJSON aJSON = new AnalizadorJSON();

            String url="http://localhost/HTTP_Android/eliminarAlumno.php";
            String metodoEnvio = "POST";

            final JSONObject jsonObject = aJSON.peticionHTTP(url, metodoEnvio, "eliminar", registros);

            try {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), jsonObject.toString(), Toast.LENGTH_LONG).show();
                    }
                });
                int exito = jsonObject.getInt("Exito");
                if (exito == 1) {
                    Log.i("MSJ", "REGISTRO ELIMINADO");
                } else {
                    Log.i("MSJ", "ERROR EN ELIMINACION");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}