package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
private TextView txtcolorL , txtcolorF;
private Button btnGrabar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtcolorF = findViewById(R.id.txtColorF);
        txtcolorL = findViewById(R.id.txtColorL);
        SharedPreferences preferences = getSharedPreferences("settings2", Context.MODE_PRIVATE);
        txtcolorL.setText(preferences.getString("colorL", ""));
        txtcolorF.setText(preferences.getString("colorF", ""));

        btnGrabar = findViewById(R.id.btnGrabar);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guardar();
            }
        });
    }

    public void Guardar(){
        SharedPreferences preferencias = getSharedPreferences("settings2", Context.MODE_PRIVATE);
        SharedPreferences.Editor Obj_editor = preferencias.edit();
        Obj_editor.putString("colorL",txtcolorL.getText().toString());
        Obj_editor.putString("colorF",txtcolorF.getText().toString());
        Obj_editor.commit();
        finish();
    }
}