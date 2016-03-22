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

        // Obtención de instancias controles
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
    public void guardarPrestamo() {

        // Obtener valores actuales de los controles
        final String cedula = etCedula.getText().toString();
        final String nombre = etNombre.getText().toString();
        final String direccion = etDireccion.getText().toString();
        final String telefono = etTelefono.getText().toString();
        final String celular = etCelular.getText().toString();
        final String otro = etOtro.getText().toString();
        final String empresa = etEmpresa.getText().toString();

        HashMap<String, String> mapCliente = new HashMap<>();// Mapeo previo

        mapCliente.put("cedula", cedula);
        mapCliente.put("nombre", nombre);
        mapCliente.put("direccion", direccion);
        mapCliente.put("telefono", telefono);
        mapCliente.put("celular", celular);
        mapCliente.put("otroTel", otro);
        mapCliente.put("empresa", empresa);

        final String v = etValor.getText().toString();
        final String in = etInteres.getText().toString();
        final String c = etCuotas.getText().toString();
        int valor = Integer.parseInt(v);
        int interes = Integer.parseInt(in);
        int cuotas = Integer.parseInt(c);

        Calendar hoy = Calendar.getInstance();
        int dia = (hoy.get(Calendar.DATE));
        int mes = hoy.get(Calendar.MONTH);
        mes = mes + 1;
        int anio = hoy.get(Calendar.YEAR);
        String fecha = anio+"-"+mes+"-"+dia;
        int hora = hoy.get(Calendar.HOUR_OF_DAY);
        hora = hora - 1;
        int minuto = hoy.get(Calendar.MINUTE);
        int segundo = hoy.get(Calendar.SECOND);

        String idCredito = String.valueOf(anio) + String.valueOf(mes) + String.valueOf(dia) + String.valueOf(hora) + String.valueOf(minuto) + String.valueOf(segundo);

        HashMap<String, String> mapCredito = new HashMap<>();// Mapeo previo

        mapCredito.put("nroPrestamo", idCredito);
        mapCredito.put("monto", v);
        mapCredito.put("nroCuotas", c);
        mapCredito.put("interes", in);
        mapCredito.put("fecha", fecha);
        mapCredito.put("cedulaCliente", cedula);

        // Crear nuevo objeto Json basado en el mapa
        JSONObject jobjectCliente = new JSONObject(mapCliente);
        JSONObject jobjectCredito = new JSONObject(mapCredito);

        // Depurando objeto Json...
        Log.d(TAG, jobjectCliente.toString());
        Log.d(TAG, jobjectCredito.toString());

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

        int inReal = (int) (valor * (interes/100.0f));
        int inTotal = inReal * (cuotas/2);
        int crTotal = valor + inTotal;
        int vc = crTotal / cuotas;
        String vrCuota = Integer.toString(vc);

        for (int i=0; i<cuotas; i++){
            String next = Integer.toString(i+1);
            String idCuota = idCredito+next;
            hoy.add(Calendar.DATE, 15);
            dia = hoy.get(Calendar.DATE);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String fechaCuota = formato.format(hoy.getTime());
            String nroCuota = next;
            String pendiente = "1";

            HashMap<String, String> mapCuota = new HashMap<>();// Mapeo previo
            mapCuota.put("idCuota", idCuota);
            mapCuota.put("valor", vrCuota);
            mapCuota.put("fecha", fechaCuota);
            mapCuota.put("numero", nroCuota);
            mapCuota.put("nroPrestamo", idCredito);
            mapCuota.put("pendiente", pendiente);

            // Crear nuevo objeto cuota Json basado en el mapa
            JSONObject jobjectCuota = new JSONObject(mapCuota);

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
                    // Terminar actividad
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
                    getActivity().finish();
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
