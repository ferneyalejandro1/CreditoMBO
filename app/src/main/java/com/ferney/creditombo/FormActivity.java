package com.ferney.creditombo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.xml.sax.helpers.ParserFactory;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class FormActivity extends AppCompatActivity {

    EditText etNombre, etCedula, etDireccion, etTelefono, etCelular, etOtro, etEmpresa, etValor, etInteres, etCuotas;
    Button bGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        etNombre = (EditText)findViewById(R.id.nombre);
        etCedula = (EditText)findViewById(R.id.cedula);
        etDireccion = (EditText)findViewById(R.id.direccion);
        etTelefono = (EditText)findViewById(R.id.telefono);
        etCelular = (EditText)findViewById(R.id.celular);
        etOtro = (EditText)findViewById(R.id.otro);
        etEmpresa = (EditText)findViewById(R.id.empresa);
        etValor = (EditText)findViewById(R.id.valor);
        etInteres = (EditText)findViewById(R.id.interes);
        etCuotas = (EditText)findViewById(R.id.cuotas);

        bGuardar = (Button)findViewById(R.id.guardar);
    }

    public void nuevo_credito(View view){
        String nombre = etNombre.getText().toString();
        String cedula = etCedula.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String celular = etCelular.getText().toString();
        String otro = etOtro.getText().toString();
        String empresa = etEmpresa.getText().toString();
        int valor = Integer.parseInt(etValor.getText().toString());
        int interes = Integer.parseInt(etInteres.getText().toString());
        int cuotas = Integer.parseInt(etCuotas.getText().toString());
        final Calendar hoy = Calendar.getInstance();
        int dia = (hoy.get(Calendar.DAY_OF_MONTH));
        int mes = hoy.get(Calendar.MONTH);
        mes = mes+1;
        int anio = hoy.get(Calendar.YEAR);
        String fecha = anio+"-"+mes+"-"+dia;
        int hora = hoy.get(Calendar.HOUR_OF_DAY)+hoy.get(Calendar.MINUTE)+hoy.get(Calendar.SECOND);
        int minuto = hoy.get(Calendar.MINUTE)+hoy.get(Calendar.SECOND);
        int segundo = hoy.get(Calendar.SECOND);
        String idCredito = String.valueOf(anio+mes+dia+hora+minuto+segundo);

        Cliente cliente = new Cliente(nombre, cedula, direccion, telefono, celular, otro, empresa);
        Credito cr = new Credito(valor, interes, cuotas, idCredito, fecha, cedula);

        int inReal = valor * (interes/100);
        int inTotal = inReal * (cuotas/2);
        int crTotal = valor + inTotal;
        int vrCuota = crTotal / cuotas;

        for (int i=0; i<cuotas; i++){
            String idCuota = idCredito+(i+1);
            dia += 15;
            String fechaCuota = anio+"-"+mes+"-"+dia;
            int nroCuota = i+1;
            Boolean pendiente = true;
            Cuota cuota = new Cuota(idCuota, vrCuota, nroCuota, idCredito, fechaCuota, pendiente);
        }
        finish();
        Toast.makeText(this,"El Credito ha sido grabado exitosamente", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
