package com.example.a21650521.appalmacenamiento;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AexternoActivity extends AppCompatActivity {
    private EditText etTexto;
    static final String NOM_FICHERO_EXTERNO ="nombreFichero.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aexterno);
        etTexto = findViewById(R.id.etTextoExt);
    }

    public void guardarAE(View v){
        File rutaAe = this.getExternalFilesDir(null);
        File f = new File(rutaAe.getAbsolutePath(), NOM_FICHERO_EXTERNO);
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(f));
            osw.write(etTexto.getText().toString());

            guardarPreferencias();
            setResult(RESULT_OK,getIntent());
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"El fichero no ha sido guardado",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());
        } catch (IOException e) {
            Toast.makeText(this,"Error de conexión con el fichero",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());
        }finally {
            if(osw !=null){
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }finish();
        }
    }
    private void guardarPreferencias(){
        //establecer la referencia con el fichero referenciado con modo privado para que solo nosotros tengamos acceso
        SharedPreferences sp = getSharedPreferences("SPActivity", MODE_PRIVATE);

        //consultar si existe esta clave y si viene vacio en el if lo crea.
        String nombreFichExt = sp.getString("FICHERO_EXT","");

        if(nombreFichExt.equals("")){
            SharedPreferences.Editor editor = sp.edit();

            editor.putString("FICHERO_EXT",NOM_FICHERO_EXTERNO);
            editor.commit();
        }
    }
}