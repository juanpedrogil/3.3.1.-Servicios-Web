package com.example.juanpedrog.laboratorio331serviciosweb;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by juanpedrog on 8/04/18.
 */

public class Updater extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    String nombre,direccion,id;
    ProgressDialog pd;
    @Override
    protected String doInBackground(Void... voids) {
        return this.send();
    }
    public Updater(Context c,String urlAddress,String id,String nombre,String direccion){
        this.c=c;
        this.id=id;
        this.urlAddress=urlAddress;
        this.nombre=nombre;
        this.direccion=direccion;
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Send");
        pd.setMessage("Sending... please wait");
        pd.show();
    }
    @Override
    protected void onPostExecute(String response){
        super.onPostExecute(response);
        pd.dismiss();
        if(response!=null){
            Toast.makeText(c,response,Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(c,"Unsuccessfull",Toast.LENGTH_LONG).show();
        }
    }
    private String send(){
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null){
            return null;
        }
        try{
            OutputStream os=con.getOutputStream();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            bw.write(new Item(id,nombre,direccion).packDataUpdate());
            System.out.println(new Item(id,nombre,direccion).packDataUpdate());
            bw.flush();
            bw.close();
            os.close();
            int responseCode=con.getResponseCode();
            if(responseCode==con.HTTP_OK){
                BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuffer response=new StringBuffer();
                String line;
                while((line=br.readLine())!=null){
                    response.append(line);
                }
                br.close();
                return response.toString();
            }
        } catch (IOException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
        }
        return null;
    }
}
