package com.example.juanpedrog.laboratorio331serviciosweb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Item> datos;
    public static RecyclerView lista;
    public static RecyclerView.LayoutManager layoutManager;
    Button nuevo,buscar;
    public static TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=findViewById(R.id.lista);
        id=findViewById(R.id.id);
        nuevo=findViewById(R.id.agregar);
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Insertar.class);
                startActivity(intent);
            }
        });
        buscar=findViewById(R.id.buscar);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id.getText().toString().equals("")){
                    GetJson process=new GetJson();
                    process.execute();
                }else {
                    Searcher s = new Searcher(id.getText().toString());
                    s.execute();
                }
            }
        });
        layoutManager=new LinearLayoutManager(this);
        GetJson process=new GetJson();
        process.execute();
        //datos.add(new Item("1","JUan","agl"));

    }
    public static void reset(){
        GetJson process=new GetJson();
        process.execute();
    }
}
