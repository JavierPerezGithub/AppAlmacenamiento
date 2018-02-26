package com.example.a21650521.appalmacenamiento;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SPActivity extends AppCompatActivity {
    EditText etNombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sp);
        etNombre = findViewById(R.id.etNombre);
    }

    //Si me piden guardar en SharedPreferences
    public void guardarSP(View v){
        //recupera el fichero de preferencias y si no existe lo crea con el nombre que le he indicado
        SharedPreferences sp = getSharedPreferences("SPActivity",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("NOMBRE", etNombre.getText().toString());
        editor.commit();

        setResult(RESULT_OK, getIntent());
        finish();
    }
}
