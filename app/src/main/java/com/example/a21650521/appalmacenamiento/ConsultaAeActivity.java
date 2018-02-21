package com.example.a21650521.appalmacenamiento;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsultaAeActivity extends AppCompatActivity {
    private TextView tvTitulo, tvContenido;
    private String nombreFichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_ae);
        tvTitulo = findViewById(R.id.tvFicheroEx);
        tvContenido = findViewById(R.id.tvContenidoEx);
        recuperarNombreFichero();
        tvTitulo.setText(tvTitulo.getText().toString() + nombreFichero);
        cargarContenido();

    }

    private void recuperarNombreFichero() {
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        nombreFichero = sp.getString("FICHERO_EXT", "Anonimo");
    }

    private void cargarContenido() {
        File ruta = this.getExternalFilesDir(null);
        File f = new File(ruta.getAbsolutePath(), nombreFichero);
        BufferedReader br = null;
        String linea = null;
        String texto = "";

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            while((linea = br.readLine()) !=null){
                texto += linea + "\n";
            }
            tvContenido.setText(texto);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "El fichero no ha sido encontrado",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error de conexión con el fichero ",Toast.LENGTH_LONG).show();
        } finally {
            if(br !=null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
