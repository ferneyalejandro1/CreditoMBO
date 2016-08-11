package com.ferney.creditombo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;

import org.json.JSONException;

public class FormActivity extends AppCompatActivity implements ConfirmDialogFragment.ConfirmDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

      //Creacion de fragmento de insercion
        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new InsertFragment(), "InsertFragment").commit();
        }
    }

    @Override
    public void onDialogPositiveClick() {
        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            if (!insertFragment.camposVacios()){

                try {
                    insertFragment.guardarPrestamo(); // Guardando datos
                    finish();//Finalizar actividad
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }else
                Toast.makeText(this,"Completa los campos",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDialogNegativeClick() {
        InsertFragment insertFragment = (InsertFragment)
                getSupportFragmentManager().findFragmentByTag("InsertFragment");
        if (insertFragment != null) {
            // Nada por el momento
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

}
