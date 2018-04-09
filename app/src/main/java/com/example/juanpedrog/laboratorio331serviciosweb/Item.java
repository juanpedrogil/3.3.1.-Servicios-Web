package com.example.juanpedrog.laboratorio331serviciosweb;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

/**
 * Created by juanpedrog on 8/04/18.
 */

public class Item {
    private String id,nombre,direccion;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Item(String id, String nombre, String direccion) {

        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public Item(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String packData(){
        JSONObject jo=new JSONObject();
        StringBuffer packedData=new StringBuffer();
        try{
            jo.put("nombre",nombre);
            jo.put("direccion",direccion);
            boolean firstValue=true;
            Iterator it=jo.keys();
            do{
                String key=it.next().toString();
                String value=jo.get(key).toString();
                if(firstValue){
                    firstValue=false;
                }else{
                    packedData.append("&");
                }
                packedData.append(URLEncoder.encode(key,"UTF-8"));
                packedData.append("=");
                packedData.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return jo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
    public String packDataUpdate(){
        JSONObject jo=new JSONObject();
        StringBuffer packedData=new StringBuffer();
        try{
            jo.put("idalumno",id);
            jo.put("nombre",nombre);
            jo.put("direccion",direccion);
            boolean firstValue=true;
            Iterator it=jo.keys();
            do{
                String key=it.next().toString();
                String value=jo.get(key).toString();
                if(firstValue){
                    firstValue=false;
                }else{
                    packedData.append("&");
                }
                packedData.append(URLEncoder.encode(key,"UTF-8"));
                packedData.append("=");
                packedData.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return jo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Item(String id) {
        this.id = id;
    }

    public String packDataDelete(){
        JSONObject jo=new JSONObject();
        StringBuffer packedData=new StringBuffer();
        try{
            jo.put("idalumno",id);

            boolean firstValue=true;
            Iterator it=jo.keys();
            do{
                String key=it.next().toString();
                String value=jo.get(key).toString();
                if(firstValue){
                    firstValue=false;
                }else{
                    packedData.append("&");
                }
                packedData.append(URLEncoder.encode(key,"UTF-8"));
                packedData.append("=");
                packedData.append(URLEncoder.encode(value,"UTF-8"));
            }while (it.hasNext());
            return jo.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
