package com.ferney.creditombo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ferney.creditombo.Modelos.Cliente;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by ferney on 02/13/2016.
 */
/**
 * Fragmento principal que contiene la lista de todos los clientes
 */
public class FragmentFindAll extends Fragment {
     /*
    Etiqueta de depuracion
     */
    private static final String TAG = FragmentFindAll.class.getSimpleName();

    /*
    Adaptador del recycler view
     */
    private ClienteAdapter adapter;

    /*
    Instancia global del recycler view
     */
    private RecyclerView lista;

    /*
    instancia global del administrador
     */
    private RecyclerView.LayoutManager lManager;

    /*
    Instancia global del FAB
     */
    //private FloatingActionButton fab;

    //private Button button;
    private Gson gson = new Gson();


    public FragmentFindAll(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        lista = (RecyclerView)v.findViewById(R.id.recycler);
        lista.setHasFixedSize(true);

        //Usar un administrador para LinearLayout
        lManager = new LinearLayoutManager(getActivity());
        lista.setLayoutManager(lManager);

        //cargar datos en el adaptador
        cargarAdaptador();

        //obtener instancia del boton
        //fab = (FloatingActionButton)view.findViewById(R.id.fab);
        //button = (Button)view.findViewById(R.id.nuevo_cliente);

        /*/asignar escucha al boton
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iniciar actividad de insercion
                getActivity().startActivityForResult(new Intent(getActivity(), FormActivity.class), 3);
            }
        });*/
        return v;
    }

    /**
     * Carga el adaptador con las metas obtenidas
     * en la respuesta
     */
    public void cargarAdaptador(){
        //peticion GET
        VolleySingleton.
                getInstance(getActivity()).
                addToRequestQueue(
                        new JsonObjectRequest(
                                Request.Method.GET,
                                Constantes.GET_CLIENTES,
                                null,
                                new Response.Listener<JSONObject>() {
                                    @Override
                                        public void onResponse(JSONObject response){
                                        //procesar la respuesta json
                                        procesarRespuesta(response);
                                        }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, "Error Volley: " + error.getMessage());
                                    }
                                }
                        )
                );
    }

    /**
     * Interpreta los resultados de la respuesta y así
     * realizar las operaciones correspondientes
     *
     * @param response Objeto Json con la respuesta
     */
    private void procesarRespuesta(JSONObject response){
        try {
            //obtener atributo estado
            String estado = response.getString("estado");

            switch (estado){
                case "1": //Exito
                    //obtener array de clientes Json
                    JSONArray mensaje = response.getJSONArray("clientes");
                    //parsear con gson
                    Cliente[] clientes = gson.fromJson(mensaje.toString(), Cliente[].class);
                    //inicializar un adaptador
                    adapter = new ClienteAdapter(Arrays.asList(clientes), getActivity());
                    //setear adaptador a la lista
                    lista.setAdapter(adapter);
                    break;

                case "2": //Fallido
                    String mensaje2 = response.getString("mensaje");
                    Toast.makeText(getActivity(), mensaje2, Toast.LENGTH_LONG).show();
                    break;
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
}
