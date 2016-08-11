package com.ferney.creditombo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ferney.creditombo.Modelos.Cliente;
import com.ferney.creditombo.Modelos.Credito;
import com.ferney.creditombo.Modelos.Cuota;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ferney on 02/16/2016.
 */
/**
 * Fragmento que permite al usuario insertar un nuevo credito
 */
public class InsertFragment extends Fragment {

    /**
     * Etiqueta para depuración */
    private static final String TAG = InsertFragment.class.getSimpleName();

    /* Controles */
    private EditText etCedula, etNombre, etDireccion, etTelefono, etCelular, etOtro, etEmpresa, etValor, etInteres, etCuotas;
    private Button bGuardar;

    public InsertFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Habilitar al fragmento para contribuir en la action bar
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflando layout del fragmento
        View v = inflater.inflate(R.layout.activity_form, container, false);

        // Obtención de instancias de los controles
        etNombre = (EditText)v.findViewById(R.id.nombre);
        etCedula = (EditText)v.findViewById(R.id.cedula);
        etDireccion = (EditText)v.findViewById(R.id.direccion);
        etTelefono = (EditText)v.findViewById(R.id.telefono);
        etCelular = (EditText)v.findViewById(R.id.celular);
        etOtro = (EditText)v.findViewById(R.id.otro);
        etEmpresa = (EditText)v.findViewById(R.id.empresa);
        etValor = (EditText)v.findViewById(R.id.valor);
        etInteres = (EditText)v.findViewById(R.id.interes);
        etCuotas = (EditText)v.findViewById(R.id.cuotas);
        bGuardar = (Button)v.findViewById(R.id.guardar);
        bGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ConfirmDialogFragment().show(getFragmentManager(), "ConfirmDialogFragment");
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    /* Si está en modo inserción, entonces crea un nuevo credito en la base de datos */
    public void guardarPrestamo() throws JSONException {
        int inReal, inTotal, crTotal, vrCuota, dia, mes, anio, hora, minuto, segundo;
        String fecha, idCredito ;

        // Obtener valores actuales de los controles
        final String cedulaCliente = etCedula.getText().toString();
        final String nombre = etNombre.getText().toString();
        final String direccion = etDireccion.getText().toString();
        final String telefono = etTelefono.getText().toString();
        final String celular = etCelular.getText().toString();
        final String otro = etOtro.getText().toString();
        final String empresa = etEmpresa.getText().toString();

        Cliente cliente = new Cliente(cedulaCliente, nombre, direccion, telefono, celular, otro, empresa);
        Gson gson = new Gson();
        String json = gson.toJson(cliente);
        JSONObject jobjectCliente = new JSONObject(json);

        // Actualizar datos del cliente en el servidor
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constantes.INSERT_CLIENTE,
                        jobjectCliente,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );

        final int valor = Integer.parseInt(etValor.getText().toString());
        final int interes = Integer.parseInt(etInteres.getText().toString());
        final int nroCuotas = Integer.parseInt(etCuotas.getText().toString());

        //obteniendo fehca y hora del sistema para generar el idCredito y el campo fecha del crédito
        Calendar hoy = Calendar.getInstance();
        dia = (hoy.get(Calendar.DATE));
        mes = hoy.get(Calendar.MONTH);
        mes = mes + 1;
        anio = hoy.get(Calendar.YEAR);
        if(mes < 10) {
            fecha = anio+"-0"+mes+"-"+dia;
        }else {
            fecha = anio+"-"+mes+"-"+dia;
        }
        hora = hoy.get(Calendar.HOUR_OF_DAY);
        hora = hora - 1;
        minuto = hoy.get(Calendar.MINUTE);
        segundo = hoy.get(Calendar.SECOND);

        idCredito = String.valueOf(anio) + String.valueOf(mes) + String.valueOf(dia) + String.valueOf(hora) + String.valueOf(minuto) + String.valueOf(segundo);

        Credito prestamo = new Credito(valor, interes, nroCuotas, idCredito, fecha, cedulaCliente);

        gson = new Gson();
        json = gson.toJson(prestamo);

        // Crear nuevo objeto Json basado en el json
        JSONObject jobjectCredito = new JSONObject(json);
        // Depurando objeto Json...
        Log.d(TAG, jobjectCredito.toString());

        // Actualizar datos del credito en el servidor
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.POST,
                        Constantes.INSERT_CREDITO,
                        jobjectCredito,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Procesar la respuesta del servidor
                                procesarRespuesta(response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d(TAG, "Error Volley: " + error.getMessage());
                            }
                        }
                ) {
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<String, String>();
                        headers.put("Content-Type", "application/json; charset=utf-8");
                        headers.put("Accept", "application/json");
                        return headers;
                    }

                    @Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8" + getParamsEncoding();
                    }
                }
        );

        inReal = (int) (valor * (interes/100.0f));
        inTotal = (int) (inReal * (nroCuotas/2.0f));
        crTotal = valor + inTotal;
        vrCuota = crTotal / nroCuotas;

        for (int i=0; i<nroCuotas; i++){
            String numero = Integer.toString(i+1);
            String idCuota = idCredito+numero;
            hoy.add(Calendar.DATE, 15);
            dia = hoy.get(Calendar.DATE);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaCuota = formato.format(hoy.getTime());
            int pendiente = 1;

            Cuota cuota = new Cuota(idCuota, numero, fechaCuota, vrCuota, idCredito, pendiente);
            json = gson.toJson(cuota);

           // Crear nuevo objeto cuota Json basado en el mapa
            JSONObject jobjectCuota = new JSONObject(json);

            // Depurando objeto Json...
            Log.d(TAG, jobjectCuota.toString());

            // Actualizar datos de la cuota en el servidor
            VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                    new JsonObjectRequest(
                            Request.Method.POST,
                            Constantes.INSERT_CUOTA,
                            jobjectCuota,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    // Procesar la respuesta del servidor
                                    procesarRespuesta(response);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Log.d(TAG, "Error Volley: " + error.getMessage());
                                }
                            }
                    ) {
                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> headers = new HashMap<String, String>();
                            headers.put("Content-Type", "application/json; charset=utf-8");
                            headers.put("Accept", "application/json");
                            return headers;
                        }

                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8" + getParamsEncoding();
                        }
                    }
            );
        }

    }


    /* Procesa la respuesta obtenida desde el sevidor
    *  @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {

        try {
            // Obtener estado
            String estado = response.getString("estado");
            // Obtener mensaje
            String mensaje = response.getString("mensaje");

            switch (estado) {
                case "1":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de éxito
                    getActivity().setResult(Activity.RESULT_OK);
                    // Terminar actividad // enviar proximamente a detalle................................................................
                    getActivity().finish();
                    break;

                case "2":
                    // Mostrar mensaje
                    Toast.makeText(
                            getActivity(),
                            mensaje,
                            Toast.LENGTH_LONG).show();
                    // Enviar código de falla
                    getActivity().setResult(Activity.RESULT_CANCELED);
                    // Terminar actividad
                    //getActivity().finish();
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    /** Valida si los campos {@link etNombre }, {@link etCedula }, {@link etDireccion }, {@link etTelefono }, {@link etCelular },
     * {@link etOtro }, {@link etEmpresa }, {@link etValor }, {@link etInteres } y {@link etCuotas } se han rellenado
     *
     * @return true si alguno o dos de los campos están vacios, false si ambos están completos
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public boolean camposVacios() {
        String nombre = etNombre.getText().toString();
        String cedula = etCedula.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String celular = etCelular.getText().toString();
        String empresa = etEmpresa.getText().toString();
        String valor = etValor.getText().toString();
        String interes = etInteres.getText().toString();
        String cuotas = etCuotas.getText().toString();

        return (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() ||
                telefono.isEmpty() || celular.isEmpty() || empresa.isEmpty() ||
                valor.isEmpty() || interes.isEmpty() || cuotas.isEmpty());
    }
}
