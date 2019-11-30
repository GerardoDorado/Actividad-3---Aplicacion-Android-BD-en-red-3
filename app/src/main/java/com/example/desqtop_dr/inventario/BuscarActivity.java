package com.example.desqtop_dr.inventario;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import controlador.AnalizadorJSON;

public class BuscarActivity extends AppCompatActivity {
    ListView listView_inventario;
    ArrayAdapter<String> adaptador;

    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        listView_inventario =  findViewById(R.id.listView_Resultados);

        new  MostrarInventario().execute();
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listView_inventario.setAdapter(adaptador);
    }//onCreate

    class MostrarInventario extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... strings) {
            AnalizadorJSON json= new AnalizadorJSON();
            String url ="https://sistemadeinventario-tecjerez.000webhostapp.com/controlador_android/consulta_inventario.php";

            JSONObject jsonobject = json.peticionHTTP(url, "POST");
            try {
                JSONArray jsonArray = jsonobject.getJSONArray("inventario");

                String cadena=null;
                for (int i=0; i<jsonArray.length(); i++){
                    cadena = jsonArray.getJSONObject(i).getString("id") + " | " +
                            jsonArray.getJSONObject(i).getString("i") + " | " +
                            jsonArray.getJSONObject(i).getString("e") + " | " +
                            jsonArray.getJSONObject(i).getString("a") + " | " +
                            jsonArray.getJSONObject(i).getString("c") + " | " +
                            jsonArray.getJSONObject(i).getString("d") + " | " +
                            jsonArray.getJSONObject(i).getString("m") + " | " +
                            jsonArray.getJSONObject(i).getString("ns");

                    arrayList.add(cadena);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}//class