package com.ferney.creditombo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ferney.creditombo.Modelos.Cliente;

import java.util.List;

/**
 * Created by ferney on 02/11/2016.
 */
public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> implements ItemClickListener{
    /**
     * Lista de objetos {@link Cliente} que representan la fuente de datos
     * de inflado
     */
    private List<Cliente> items;
    /*
    Contexto donde actua el recycler view
     */
    private Context context;
    public ClienteAdapter(List<Cliente> items, Context context){
        this.context = context;
        this.items = items;
    }

    @Override
    public int getItemCount(){
        return items.size();
}
    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup vg, int i){
        View v = LayoutInflater.from(vg.getContext()).inflate(R.layout.item_list, vg, false);
        return new ClienteViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder viewHolder, int i){
        viewHolder.nombre.setText(items.get(i).getNombre());
        viewHolder.celular.setText(items.get(i).getCelular());
    }

    /**
     * Sobrescritura del método de la interfaz {@link ItemClickListener}
     *
     * @param view     item actual
     * @param position posición del item actual
     */
    @Override
    public void onItemClick(View view, int position){
        DetallePrestamoActivity.launch((Activity)context, items.get(position).getCedula());
    }

    public static class ClienteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Campos respectivos de un item
        public TextView nombre;
        public TextView celular;
        public ItemClickListener listener;

        public ClienteViewHolder(View view, ItemClickListener listener){
            super(view);
            nombre = (TextView) view.findViewById(R.id.nombre);
            celular = (TextView) view.findViewById(R.id.celular);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getPosition());  //OnItemClick(v, getAdapterPosition());
        }
    }

}

interface ItemClickListener{
    int getItemCount();

    ClienteAdapter.ClienteViewHolder onCreateViewHolder(ViewGroup vg, int i);

    void onBindViewHolder(ClienteAdapter.ClienteViewHolder viewHolder, int i);

    void onItemClick(View view, int position);
}
