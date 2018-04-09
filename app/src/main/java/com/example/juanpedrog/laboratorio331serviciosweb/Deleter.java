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

public class Deleter extends AsyncTask<Void,Void,String> {
    Context c;
    String urlAddress;
    String id;
    ProgressDialog pd;
    @Override
    protected String doInBackground(Void... voids) {
        return this.send();
    }
    public Deleter(Context c,String urlAddress,String id){
        this.c=c;
        this.id=id;
        this.urlAddress=urlAddress;
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
            MainActivity.reset();
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
            bw.write(new Item(id).packDataDelete());
            System.out.println(new Item(id).packDataDelete());
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
