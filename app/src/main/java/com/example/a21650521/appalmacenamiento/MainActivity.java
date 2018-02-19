package com.example.a21650521.appalmacenamiento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String nombre;
    private TextView tvBienvenida;
    static final int SP_CODE = 1;
    static final int AI_CODE = 2;
    static final int AE_CODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvBienvenida = findViewById(R.id.tvBienvenida);
        nombreBienvenida();
    }

    //almacenar en Shared Preferences
    public void almacenarSP(View v){
        startActivityForResult(new Intent(this,SPActivity.class),SP_CODE);
    }
    public void almacenarAI(View v){
        startActivityForResult(new Intent(this,AInternoActivity.class),AI_CODE);
    }

    public void consultarAI(View v){
        startActivity(new Intent(this,ConsultaAiActivity.class));
    }
    public void almacenarAE(View v){
        startActivityForResult(new Intent(this,AexternoActivity.class),AE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SP_CODE){
            if(resultCode == RESULT_OK){
                nombreBienvenida();
            }
        }else if(requestCode == AI_CODE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,"Se ha realizado el almacenamiento interno con éxito",
                        Toast.LENGTH_LONG).show();
            }
        }else if(requestCode == AE_CODE){
            if(resultCode == RESULT_OK){
                Toast.makeText(this,"Se ha realizado el almacenamiento externo con éxito",
                        Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(this,"no se ha podido realizar el almacenamiento interno",
                    Toast.LENGTH_LONG).show();
        }
    }
    private void nombreBienvenida() {
        SharedPreferences sp = getSharedPreferences("SPActivity", MODE_PRIVATE);
        //primer parametro: la clave y segundo parametro valor por defecto
        nombre = sp.getString("NOMBRE","Anonimo");
        tvBienvenida.setText("Bienvenido "+ nombre);
    }
}
