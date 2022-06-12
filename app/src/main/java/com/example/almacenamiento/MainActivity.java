package com.example.almacenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private TextView txtNombre, txtTel, txtMail;
    Button siguiente;
    private Button btnGrabar, btnRecuperar;
    private static final String FILE_NAME = "contacto.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setUpView();


        siguiente = (Button) findViewById(R.id.btnPreferencias);
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(i);
            }
        });
    }

    //texto
    private void setUpView() {
        txtNombre = findViewById(R.id.txtNombre);
        txtTel = findViewById(R.id.txtTel);
        txtMail = findViewById(R.id.txtEmail);
        btnGrabar = findViewById(R.id.btnGrabar);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveFile();
            }
        });
        btnRecuperar = findViewById(R.id.btnRecuperar);
        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFile();
            }
        });
    }
    private void saveFile(){
    //String texto = "Nombre:"+txtNombre.getText().toString()+"Tel: "+txtTel.getText().toString()+"Mail: "+ txtMail.getText().toString()+"\n\n";
        String n = "Nombre:"+txtNombre.getText().toString()+"\n";
        String t = "Tel: "+txtTel.getText().toString()+"\n";
        String m = "Mail: "+ txtMail.getText().toString()+"\n";
        FileOutputStream fileOutputStream =null;
        try {
            fileOutputStream = openFileOutput(FILE_NAME,MODE_PRIVATE);
          //  fileOutputStream.write(texto.getBytes());
            fileOutputStream.write(n.getBytes());
            fileOutputStream.write(t.getBytes());
            fileOutputStream.write(m.getBytes());
            Log.d("TAG1","FICHERO SALVADO EN : "+ getFilesDir()+"/"+FILE_NAME);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileOutputStream !=null){
                try {
                    fileOutputStream.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        txtNombre.setText("");
        txtTel.setText("");
        txtMail.setText("");
    }
    private  void readFile(){
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String lineaTexto, lineaTexto2, lineaTexto3;
            StringBuilder stringBuilder = new StringBuilder();
            lineaTexto= bufferedReader.readLine();
            lineaTexto2= bufferedReader.readLine();
            lineaTexto3= bufferedReader.readLine();

            if(lineaTexto!=null){
                txtNombre.setText(lineaTexto);
                txtTel.setText(lineaTexto2);
                txtMail.setText(lineaTexto3);
            }
             /* String lineaTexto;
            StringBuilder stringBuilder = new StringBuilder();
          while ((lineaTexto= bufferedReader.readLine())!=null){
                txtNombre.setText(stringBuilder);
                stringBuilder.append(lineaTexto).append("\n");
                txtTel.setText(stringBuilder);
                stringBuilder.append(lineaTexto).append("\n");
                txtMail.setText(stringBuilder);

            }

           // txtNombre.setText(stringBuilder);

*/

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fileInputStream != null){
                try {
                    fileInputStream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
