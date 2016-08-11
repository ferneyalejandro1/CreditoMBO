package com.ferney.creditombo;

/**
 * Created by ferney on 02/15/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

//Esta actividad contiene un fragmento que muestra el detalle de los clientes // de las cuotas de los prestamos.
public class DetallePrestamoActivity extends AppCompatActivity {

    //Instancia global del cliente a detallar
    private String cedula;

    /**
     * Inicia una nueva instancia de la actividad
     * @param activity Contexto desde donde se lanzará
     * @param cedula Identificador del cliente a detallar
     */
    public static void launch(Activity activity, String cedula){
        Intent intent = getLaunchIntent(activity, cedula);
        activity.startActivityForResult(intent, Constantes.CODIGO_DETALLE);
    }

    /**
     * Construye un Intent a partir del contexto y la actividad de detalle.
     * @param context Contexto donde se inicia
     * @param cedula  Identificador del cliente
     * @return Intent listo para usar
     */
    public static Intent getLaunchIntent(Context context, String cedula){
        Intent intent = new Intent(context, DetallePrestamoActivity.class);
        intent.putExtra(Constantes.EXTRA_ID, cedula);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        if (getSupportActionBar() != null){
            //deshabilita titulo de actividad
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            //Setear icono "X" como Up button
            getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_close);
        }
        //Retener instancia
        if (getIntent().getStringExtra(Constantes.EXTRA_ID) != null){
            cedula = getIntent().getStringExtra(Constantes.EXTRA_ID);
        }
        if (savedInstanceState == null) getSupportFragmentManager().beginTransaction()
                .add(R.id.container, DetalleClienteFragment.createInstance(cedula), "DetalleClienteFragment").commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case  android.R.id.home:
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
