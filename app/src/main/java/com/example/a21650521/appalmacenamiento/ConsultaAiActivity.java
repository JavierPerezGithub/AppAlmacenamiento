package com.example.a21650521.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsultaAiActivity extends AppCompatActivity {
    TextView tvTitulo, tvContenido;
    String nombreFichero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ai);
        tvTitulo = findViewById(R.id.tvFichero);
        tvContenido = findViewById(R.id.tvContenido);
        recuperarNombreFichero();
        tvTitulo.setText(tvTitulo.getText().toString() + nombreFichero);
        cargarContenido();
    }

    private void recuperarNombreFichero() {
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        nombreFichero = sp.getString("FICHERO_INT", "Anonimo");
    }

    private void cargarContenido() {
        InputStreamReader isr = null;
        FileInputStream fis= null;
        BufferedReader br = null;
        String texto = "";
        String linea = null;
        try {
            fis = openFileInput(nombreFichero);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);

            while((linea = br.readLine()) !=null){
                texto += linea + "\n";
            }
            tvContenido.setText(texto);
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"El fichero no ha sido encontrado",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this,"Error de conexión con el fichero",Toast.LENGTH_LONG).show();
        }finally {
            try {
                if(br !=null)
                    br.close();
                if(isr !=null)
                isr.close();
                if(fis !=null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
