package com.ferney.creditombo;

/**
 * Created by ferney on 02/15/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ferney.creditombo.Modelos.Cliente;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * A placeholder fragment containing a simple view.
 */

public class DetalleClienteFragment extends Fragment {

    /*
    Etiqueta de valor extra
     */
    private static final String EXTRA_ID = "CEDULA";

    /**
     * Etiqueta de depuración
     */
    private static final String TAG = DetalleClienteFragment.class.getSimpleName();

    /**
     * Etiqueta de depuración
     */
    private ImageView cabecera;
    private TextView nombre, celular;//titulo

    private ImageButton pagarButton;
    private String extra;
    private Gson gson = new Gson();
    private Bundle arguments;
    private String activity;

    public DetalleClienteFragment(){
    }

    public static DetalleClienteFragment createInstance(String cedula){
        DetalleClienteFragment detalleClienteFragment = new DetalleClienteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_ID, cedula);
        detalleClienteFragment.setArguments(bundle);
        return detalleClienteFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        //obtencion de Views
        cabecera = (ImageView)v.findViewById(R.id.cabecera);
        nombre = (TextView)v.findViewById(R.id.nombre);
        celular = (TextView)v.findViewById(R.id.celular);

        //setear escuchas para el fab
        pagarButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //Iniciar actividad de actualizacion
                        Intent i = new Intent(getActivity(), UpdateActivity.class);
                        i.putExtra(EXTRA_ID, extra);
                        getActivity().startActivityForResult(i, Constantes.CODIGO_ACTUALIZACION);
                    }
                }
        );

        //obtener Extra del intent de envìo
        extra = getArguments().getString(EXTRA_ID);

        //cargar datos desde el webservice
        cargarDatos();

        return v;
    }

    /**
     * Obtiene los datos desde el servidor
     */
    private void cargarDatos() {
        // Añadir parámetro a la URL del web service
        String newURL = Constantes.GET_BY_ID + "?cedula=" + extra;

        // Realizar la peticion
        VolleySingleton.getInstance(getActivity()).addToRequestQueue(
                new JsonObjectRequest(Request.Method.GET,
                        newURL,
                        null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                //Procesar respuesta del Json
                                procesarRespuesta(response);
                            }
                        }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Volley: " + error.getMessage());
                    }
                })
        );
    }

    /**
     * Procesa cada uno de los estados posibles de la
     * respuesta enviada desde el servidor
     *
     * @param response Objeto Json
     */
    private void procesarRespuesta(JSONObject response) {
        try {
            //obtener atributo mensaje
            String mensaje = response.getString("estado");

            switch (mensaje){
                case "1":
                    //obtener objeto cliente
                    JSONObject object = response.getJSONObject("cliente");

                    //Parsear el objeto
                    Cliente cliente = gson.fromJson(object.toString(), Cliente.class);

                    //Asignar color del fondo
                    /*switch (cliente.vencido){
                        case "una cuota":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.alertaNaranja));
                            break;
                        case "dos cuotas":
                            cabecera.setBackgroundColor(getResources().getColor(R.color.alertaRoja));
                            break;
                    }*/
                    //Seteando valores en los views
                    nombre.setText(cliente.getNombre());
                    celular.setText(cliente.getCelular());

                    break;

                case "2":
                    String msg2 = response.getString("mensaje");
                    Toast.makeText(getActivity(), msg2, Toast.LENGTH_LONG).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
