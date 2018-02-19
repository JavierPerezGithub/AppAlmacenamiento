package com.example.a21650521.appalmacenamiento;

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
        File rutaEs = Environment.getExternalStorageDirectory();
        File f = new File(rutaEs.getAbsolutePath(), NOM_FICHERO_EXTERNO);
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(f));
            osw.write(etTexto.getText().toString());
            setResult(RESULT_OK,getIntent());
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"El fichero no ha sido encontrado",Toast.LENGTH_LONG).show();
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
}
