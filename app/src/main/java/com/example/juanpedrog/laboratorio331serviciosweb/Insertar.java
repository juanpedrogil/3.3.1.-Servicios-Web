package com.example.juanpedrog.laboratorio331serviciosweb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Insertar extends AppCompatActivity {
    TextView nombre,direccion;
    Button insertar;
    String url="http://192.168.1.67/App/insertar_alumno.php";
    public static Insertar c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        c=this;
        nombre=findViewById(R.id.txtNombre);
        direccion=findViewById(R.id.txtDireccion);
        insertar=findViewById(R.id.insertar);
        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Sender s=new Sender(Insertar.this,url,nombre.getText().toString(),direccion.getText().toString());
                s.execute();

            }
        });
    }
    public static void close(){
        MainActivity.reset();
        c.finish();
    }
}
