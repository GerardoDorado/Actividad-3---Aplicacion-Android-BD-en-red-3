package controlador;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class AnalizadorJSON {
    InputStream is =  null;
    JSONObject jsonObject = null;
    String json = null;
    OutputStream os=null;

    public JSONObject peticionHTTP(String url, String metodo){
        HttpURLConnection conexion = null;
        URL mUrl=null;

        //magia
        //{"nc":"1"}
        try{
            mUrl=new URL(url);
            conexion = (HttpURLConnection) mUrl.openConnection();
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);

            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            os=new BufferedOutputStream(conexion.getOutputStream());
            os.flush();
            os.close();

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            is=new BufferedInputStream(conexion.getInputStream());
            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            StringBuilder cadena = new StringBuilder();
            String linea;

            while((linea=br.readLine()) !=null){
                cadena.append(linea+"\n");
            }

            is.close();
            json=cadena.toString();
            Log.i("Mensaje 1 >>>>", "RESPUESTA JSON:" + json);

            jsonObject = new JSONObject(json);

        }catch(IOException e){
            e.printStackTrace();
        }catch(JSONException e){
            e.printStackTrace();
        }

        return jsonObject;

    }//metodo peticionHTTP

    public JSONObject peticionHTTP(String url, String metodo, Map datos){

        HttpURLConnection conexion = null;
        URL mUrl = null;
        String cadenaJSON = null;
        try {
             cadenaJSON = "{\"i\":\"" + URLEncoder.encode(String.valueOf(datos.get("i")), "UTF-8")+
                    "\", \"a\":\"" + URLEncoder.encode(String.valueOf(datos.get("a")), "UTF-8")+
                    "\", \"e\":\"" + URLEncoder.encode(String.valueOf(datos.get("pe")), "UTF-8")+
                    "\", \"c\":\"" + URLEncoder.encode(String.valueOf(datos.get("c")), "UTF-8")+
                    "\", \"m\":\"" + URLEncoder.encode(String.valueOf(datos.get("m")), "UTF-8")+
                    "\", \"d\":\"" + URLEncoder.encode(String.valueOf(datos.get("d")), "UTF-8")+ "\"}";

            Log.e("Ms cadenaJSON", cadenaJSON);

            mUrl = new URL(url);
            conexion =(HttpURLConnection) mUrl.openConnection();

            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            OutputStream os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is =  new BufferedInputStream(conexion.getInputStream());
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));
            StringBuilder cad = new StringBuilder();

            String fila;
            while ((fila = br.readLine()) != null){
                cad.append(fila+"\n");
            }
            is.close();
            json = cad.toString();

            Log.i( "MSJ 1", "Cadena JSON: " + cad);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Log.e("MSJ2","error en buferreader"+e.toString());
        }catch (IOException e){
            e.printStackTrace();
            Log.e("MSJ3","error en el flujo de comunicacion "+e.toString());
        }

        try {
            jsonObject = new JSONObject(json);
        }catch (JSONException e){
            e.printStackTrace();
            Log.e("MSJ","error al convertit la cadena JSON");
        }

        return jsonObject;
    }

    public JSONObject peticionHTTP(String url) {

        HttpURLConnection conexion = null;
        URL mUrl=null;

        try{
            mUrl = new URL(url);
            conexion =(HttpURLConnection) mUrl.openConnection();
            conexion.setDoOutput(true);
            conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            OutputStream os = new BufferedOutputStream(conexion.getOutputStream());
            os.flush();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is =  new BufferedInputStream(conexion.getInputStream());
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila;
            while ((fila = br.readLine()) != null){
                cad.append(fila+"\n");
            }
            is.close();
            json = cad.toString();

            Log.i( "MSJ 1:","Respuesta JSON: " + cad);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Log.e("MSJ 2","error en buferreader"+e.toString());
        }catch (IOException e){
            e.printStackTrace();
            Log.e("MSJ 3","error en el flujo de comunicacion"+e.toString());
        }

        try {
            jsonObject = new JSONObject(json);
        }catch (JSONException e){
            e.printStackTrace();
            Log.e("MSJ 4","error al convertit la cadena JSON");
        }

        return jsonObject;
    }

    public JSONObject peticionHTTP(String url, String metodo, String nc,Map datos){

        HttpURLConnection conexion = null;
        URL mUrl = null;

        try {
            String cadenaJSON = "{\"id\":\""+ URLEncoder.encode(String.valueOf(datos.get("id")),"UTF-8")+"\"}";

            Log.e("Ms cadenaJSON", cadenaJSON);

            mUrl = new URL(url);
            conexion =(HttpURLConnection) mUrl.openConnection();

            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);
            conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            OutputStream os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            is =  new BufferedInputStream(conexion.getInputStream());
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila;
            while ((fila = br.readLine()) != null){
                cad.append(fila+"\n");
            }
            is.close();
            json = cad.toString();

            Log.i( "MSJ 1", "Cadena JSON: " + cad);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Log.e("MSJ 2","error en buferreader"+e.toString());
        }catch (IOException e){
            e.printStackTrace();
            Log.e("MSJ 3","error en el flujo de comunicacion"+e.toString());
        }

        try {
            jsonObject = new JSONObject(json);
        }catch (JSONException e){
            e.printStackTrace();
            Log.e("MSJ","error al convertit la cadena JSON");
        }

        return jsonObject;
    }

    public JSONObject peticionHTTP(String url, String metodo, String dato){

        HttpURLConnection conexion = null;
        URL mUrl = null;


        try {

            //'{"a":1,"b":2,"c":3,"d":4,"e":5}'
            //{"nc":"01"
            String cadenaJSON = "{\"nc\":\""+ URLEncoder.encode(dato,"UTF-8")+"\"}"; //Terminar
            //String cadenaJSON = "{\"c\":\""+ URLEncoder.encode(dato,"UTF-8")+"\"}";

            Log.e("Ms cadenaJSON", cadenaJSON);

            mUrl = new URL(url);
            conexion =(HttpURLConnection) mUrl.openConnection();

            //Activamos el envio de datos atraves del post
            conexion.setDoOutput(true);
            conexion.setRequestMethod(metodo);

            //tamaño previamiente establecido
            conexion.setFixedLengthStreamingMode(cadenaJSON.getBytes().length);

            //establecer formato de codificacion estandar para los datos enviados
            conexion.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            OutputStream os = new BufferedOutputStream(conexion.getOutputStream());

            os.write(cadenaJSON.getBytes());
            os.flush();
            os.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //recibe respuesta HTTP desde PHP
        try {
            is =  new BufferedInputStream(conexion.getInputStream());
            BufferedReader br =  new BufferedReader(new InputStreamReader(is));

            StringBuilder cad = new StringBuilder();

            String fila;
            while ((fila = br.readLine()) != null){
                cad.append(fila+"\n");
            }
            is.close();
            json = cad.toString();

            Log.i( "MSJ 1", "Cadena JSON: " + cad);

        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
            Log.e("MSJ 2","error en buferreader"+e.toString());
        }catch (IOException e){
            e.printStackTrace();
            Log.e("MSJ 3","error en el flujo de comunicacion"+e.toString());

            //ActivityAgregarAlumno.mensaje="Error en flujo de comunicacion.";


        }

        try {
            jsonObject = new JSONObject(json);
        }catch (JSONException e){
            e.printStackTrace();
            Log.e("MSJ","error al convertit la cadena JSON");
        }

        return jsonObject;
    }
}