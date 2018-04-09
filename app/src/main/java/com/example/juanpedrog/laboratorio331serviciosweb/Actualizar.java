package com.example.juanpedrog.laboratorio331serviciosweb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Actualizar extends AppCompatActivity {
    TextView nombre,direccion;
    Button actualizar;
    String id;
    public static Actualizar c;
    String url="http://192.168.1.67/App/actualizar_alumno.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);
        c=this;
        nombre=findViewById(R.id.txtNombre);
        direccion=findViewById(R.id.txtDireccion);
        actualizar=findViewById(R.id.actualizar);
        id=getIntent().getExtras().getString("id");
        nombre.setText(getIntent().getExtras().getString("nombre"));
        direccion.setText(getIntent().getExtras().getString("direccion"));
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Updater s=new Updater(Actualizar.this,url,id,nombre.getText().toString(),direccion.getText().toString());
                s.execute();
            }
        });
    }
    public static void close(){
        MainActivity.reset();
        c.finish();
    }
}
