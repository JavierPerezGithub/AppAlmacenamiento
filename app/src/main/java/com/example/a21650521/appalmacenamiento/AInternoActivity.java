package com.example.a21650521.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AInternoActivity extends AppCompatActivity {
    EditText etNombre;
    static final String NOM_FICHERO = "miFichero.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ainterno);
        etNombre = findViewById(R.id.etNombreAI);
    }
    public void guardarAI(View v){
        String string = etNombre.getText().toString();
        FileOutputStream fos = null;

        try {
            fos = openFileOutput(NOM_FICHERO,MODE_APPEND);
            fos.write((string).getBytes());

            almacenarSP();
            setResult(RESULT_OK,getIntent());
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"El fichero no ha sido encontrado",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());
        } catch (IOException e) {
            Toast.makeText(this,"Error de conexión con el fichero",Toast.LENGTH_LONG).show();
            setResult(RESULT_CANCELED,getIntent());
        }finally {
            try {
                if(fos !=null){
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            finish();
        }
    }

    private void almacenarSP() {
        SharedPreferences sp = getSharedPreferences("SPActivity", MODE_PRIVATE);
        //primer parametro: la clave y segundo parametro valor por defecto
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("FICHERO_INT",NOM_FICHERO);
        editor.commit();
    }
}
