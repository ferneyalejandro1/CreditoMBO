package com.ferney.creditombo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ferney.creditombo.Modelos.Cliente;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by win8.1 on 02/24/2016.
 */
public class SearchAdapter extends ArrayAdapter {

    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private String URL_BASE = "";
    private static final String TAG = "SearchAdapter";
    List<Cliente> items;

    public SearchAdapter(Context context) {
        super(context, 0);

        //Crear nueva cola de peticiones
        requestQueue = Volley.newRequestQueue(context);

        //Nueva peticion JSONObject
        jsArrayRequest = new JsonObjectRequest(Request.Method.GET, URL_BASE, null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                    }
                });

        //añadir la peticion a la cola de peticiones
        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        //referencia del view segun la fila
        View listItemView;

        //conprueba si existe o no
        listItemView = null == convertView ?
            //si no existe entonces lo infla
            listItemView = layoutInflater.inflate(R.layout.item_list, parent, false) : convertView;

        //obtener el item actual
        Cliente item = items.get(position);

        //obtener views
        TextView nombre = (TextView)listItemView.findViewById(R.id.nombre);
        TextView empresa = (TextView)listItemView.findViewById(R.id.empresa);
        TextView celular = (TextView)listItemView.findViewById(R.id.celular);

        //actualiza los views
        nombre.setText(item.getNombre());
        empresa.setText(item.getEmpresa());
        celular.setText(item.getCelular());

        return listItemView;
    }

    public List<Cliente> parseJson(JSONObject jsonObject){
        List<Cliente> clientes = new ArrayList<>();
        JSONArray jsonArray = null;

        try {
            //obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("items");

            for (int i=0; i<jsonArray.length(); i++){
                try {
                    JSONObject objeto = jsonArray.getJSONObject(i);
                    Cliente cliente = new Cliente(null,
                            objeto.getString("nombre"), null, null,
                            objeto.getString("celular"), null,
                            objeto.getString("empresa"));
                    clientes.add(cliente);
                }catch (JSONException e){
                    Log.e(TAG, "Error de parsing: " + e.getMessage());
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
