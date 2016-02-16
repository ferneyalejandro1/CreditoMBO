package com.ferney.creditombo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ferney on 02/16/2016.
 */
/**
 * Fragmento que permite al usuario insertar un nueva meta
 */
public class InsertFragment extends Fragment{
    /**
     * Etiqueta para depuración
     */
    private static final String TAG = InsertFragment.class.getSimpleName();

    /*
    Controles
    */
    EditText etNombre, etCedula, etDireccion, etTelefono, etCelular, etOtro, etEmpresa, etValor, etInteres, etCuotas;
    //Button bGuardar;

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
        View v = inflater.inflate(R.layout.fragment_form, container, false);

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

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        // Remover el action button de borrar
        //menu.removeItem(R.id.action_delete);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:// CONFIRMAR
                if (!camposVacios())
                    guardarCliente();
                else
                    Toast.makeText(
                            getActivity(),
                            "Completa los campos",
                            Toast.LENGTH_LONG).show();
                return true;

            case R.id.action_discard:// DESCARTAR
                if (!camposVacios())
                    mostrarDialogo();
                else
                    getActivity().finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Guarda los cambios de una meta editada.
     * <p>
     * Si está en modo inserción, entonces crea una nueva
     * meta en la base de datos
     */
    public void guardarCliente() {

        // Obtener valores actuales de los controles
        final String cedula = etCedula.getText().toString();
        final String nombre = etNombre.getText().toString();
        final String direccion = etDireccion.getText().toString();
        final String telefono = etTelefono.getText().toString();
        final String celular = etCelular.getText().toString();
        final String otro = etOtro.getText().toString();
        final String empresa = etEmpresa.getText().toString();

        HashMap<String, String> mapCliente = new HashMap<>();// Mapeo previo

        mapCliente.put("Cedula", cedula);
        mapCliente.put("Nombre", nombre);
        mapCliente.put("Direccion", direccion);
        mapCliente.put("Telefono", telefono);
        mapCliente.put("Celular", celular);
        mapCliente.put("OtroTel", otro);
        mapCliente.put("Empresa", empresa);

        final String v = etValor.getText().toString();
        final String in = etInteres.getText().toString();
        final String c = etCuotas.getText().toString();
        int valor = Integer.parseInt(v);
        int interes = Integer.parseInt(in);
        int cuotas = Integer.parseInt(c);

        final Calendar hoy = Calendar.getInstance();
        int dia = (hoy.get(Calendar.DAY_OF_MONTH));
        int mes = hoy.get(Calendar.MONTH);
        mes = mes+1;
        int anio = hoy.get(Calendar.YEAR);
        String fecha = anio+"-"+mes+"-"+dia;
        int hora = hoy.get(Calendar.HOUR_OF_DAY)+hoy.get(Calendar.MINUTE)+hoy.get(Calendar.SECOND);
        int minuto = hoy.get(Calendar.MINUTE)+hoy.get(Calendar.SECOND);
        int segundo = hoy.get(Calendar.SECOND);

        String idCredito = String.valueOf(anio + mes + dia + hora + minuto + segundo);

        HashMap<String, String> mapCredito = new HashMap<>();// Mapeo previo

        mapCredito.put("NroPrestamo", idCredito);
        mapCredito.put("Monto", v);
        mapCredito.put("NroCuotas", c);
        mapCredito.put("Interes", in);
        mapCredito.put("Fecha", fecha);
        mapCredito.put("CedulaCliente", cedula);

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
                        Constantes.INSERT,
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
                        Constantes.INSERT,
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

        int inReal = valor * (interes/100);
        int inTotal = inReal * (cuotas/2);
        int crTotal = valor + inTotal;
        int vc = crTotal / cuotas;
        String vrCuota = Integer.toString(vc);

        for (int i=0; i<cuotas; i++){
            String next = Integer.toString(i+1);
            String idCuota = idCredito+next;
            dia += 15;
            String fechaCuota = anio+"-"+mes+"-"+dia;
            String nroCuota = next;
            String pendiente = "1";

            //Cuota cuota = new Cuota(idCuota, vrCuota, nroCuota, idCredito, fechaCuota, pendiente);
            HashMap<String, String> mapCuota = new HashMap<>();// Mapeo previo
            mapCuota.put("IdCuota", idCuota);
            mapCuota.put("Valor", vrCuota);
            mapCuota.put("Fecha", fechaCuota);
            mapCuota.put("Numero", nroCuota);
            mapCuota.put("NroPrestamo", idCredito);
            mapCuota.put("Pendiente", pendiente);

            // Crear nuevo objeto cuota Json basado en el mapa
            JSONObject jobjectCuota = new JSONObject(mapCuota);

            // Depurando objeto Json...
            Log.d(TAG, jobjectCuota.toString());

            // Actualizar datos de la cuota en el servidor
            VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                    new JsonObjectRequest(
                            Request.Method.POST,
                            Constantes.INSERT,
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

    /**
     * Procesa la respuesta obtenida desde el sevidor
     *
     * @param response Objeto Json
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

    /**
     * Valida si los campos {@link etNombre }, {@link etCedula }, {@link etDireccion }, {@link etTelefono }, {@link etCelular },
     * {@link etOtro }, {@link etEmpresa }, {@link etValor }, {@link etInteres } y {@link etCuotas } se han rellenado
     *
     * @return true si alguno o dos de los campos están vacios, false si ambos
     * están completos
     */
    public boolean camposVacios() {
        String nombre = etNombre.getText().toString();
        String cedula = etCedula.getText().toString();
        String direccion = etDireccion.getText().toString();
        String telefono = etTelefono.getText().toString();
        String celular = etCelular.getText().toString();
        String otro = etOtro.getText().toString();
        String empresa = etEmpresa.getText().toString();
        String valor = etValor.getText().toString();
        String interes = etInteres.getText().toString();
        String cuotas = etCuotas.getText().toString();

        return (nombre.isEmpty() || cedula.isEmpty() || direccion.isEmpty() ||
                telefono.isEmpty() || celular.isEmpty() || otro.isEmpty() ||
                empresa.isEmpty() || valor.isEmpty() || interes.isEmpty() ||
                cuotas.isEmpty());
    }

    /**
     * Actualiza la fecha del campo {@link fecha_text}
     *
     * @param ano Año
     * @param mes Mes
     * @param dia Día
     */
//    public void actualizarFecha(int ano, int mes, int dia) {
//        // Setear en el textview la fecha
//        fecha_text.setText(ano + "-" + (mes + 1) + "-" + dia);
//    }

    /**
     * Muestra un diálogo de confirmación
     */
    public void mostrarDialogo() {
        DialogFragment dialogo = ConfirmDialogFragment.
                createInstance(
                        getResources().
                                getString(R.string.dialog_discard_msg));
        dialogo.show(getFragmentManager(), "ConfirmDialog");
    }
}
