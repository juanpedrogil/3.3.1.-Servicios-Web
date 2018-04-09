package com.example.juanpedrog.laboratorio331serviciosweb;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by juanpedrog on 8/04/18.
 */

public class GetJson extends AsyncTask<Void,Void,Void>{
    String data="";
    List<Item> datos=new ArrayList<Item>();

    @Override
    protected Void doInBackground(Void... voids) {
        try{
            URL url=new URL("http://192.168.1.67/App/obtener_alumnos.php");
            HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
            InputStream inputStream=httpURLConnection.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            String line="";
            while(line!=null){
                line=bufferedReader.readLine();
                data+=line;
            }
            JSONObject JO=new JSONObject(data);
            JSONArray arr=JO.getJSONArray("alumnos");
            for(int i=0;i<arr.length();i++){
                JSONObject aux=arr.getJSONObject(i);
                Item temp=new Item(aux.getString("idalumno"),aux.getString("nombre"),aux.getString("direccion"));
                datos.add(temp);
            }


        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.datos=datos;
        Adapter adapter=new Adapter(datos);
        MainActivity.lista.setAdapter(adapter);
        MainActivity.lista.setLayoutManager(MainActivity.layoutManager);
    }
}
