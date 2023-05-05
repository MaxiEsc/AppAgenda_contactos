package com.maxescobar.agenda_contactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etNombreContacto, etDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombreContacto =  (EditText) findViewById(R.id.etNombre);
        etDatos =  (EditText) findViewById(R.id.etMDatos);
    }

    //Metodo para Guardar
    public void btnGuardar(View vista){
        //Extraemos las cadenas de texto de los editTexts
        String nombre = etNombreContacto.getText().toString();
        String datos = etDatos.getText().toString();

        //Utilizamos shared preferences para crear un punto de restauracion
        SharedPreferences sp = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEditor = sp.edit(); //Ahora podemos editarlo
        //En este caso no extraemos de una llave si no de un nombre que se haya guardado previamente
        spEditor.putString(nombre, datos);
        spEditor.commit(); //Guardamos
        Toast.makeText(this,"Los datos ingresados se han guardado",Toast.LENGTH_SHORT).show();
    }

    //Metodo para Buscar
    public void btnBuscar(View vista){
        //Comenzamos como el boton guardar extraer el texto de nombre de contacto
        String nombre = etNombreContacto.getText().toString();
        //En este caso cargamos el ultimo punto de shared preference creado anteriormente osea
        SharedPreferences sp = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        //En este caso las comillas hacen referencia a los datos que pueden cargar
        String datos = sp.getString(nombre,"");

        if (datos.isEmpty()){
            Toast.makeText(this,"No hay datos cargados en el sistema",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Datos cargados",Toast.LENGTH_SHORT).show();
            etDatos.setText(datos);
        }
    }
}