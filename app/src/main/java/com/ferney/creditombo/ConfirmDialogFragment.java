package com.ferney.creditombo;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;


/**
 * Created by ferney on 03/14/2016.
 */
public class ConfirmDialogFragment extends DialogFragment {

    // Interfaz para la comunicación de eventos con la actividad
    ConfirmDialogListener listener;

    public ConfirmDialogFragment(){
    }

    /**
     * Cuerpo de la interfaz de comunicación.
     * Ambos métodos deben ser implementados en la actividad
     * huesped para el manejo de los botones del dialogo.
     */
    public interface ConfirmDialogListener {
        void onDialogPositiveClick();
        void onDialogNegativeClick();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Construcción del diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("GUARDAR")
                .setMessage(R.string.dialogo_guardar_msg)
                .setPositiveButton(R.string.dialogo_si, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Enviar evento de aceptar a la actividad
                        listener.onDialogPositiveClick();
                    }
                })
                .setNegativeButton(R.string.dialogo_no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Enviar evento de cancelar a la actividad
                        listener.onDialogNegativeClick();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verifica que la actividad contenedora implemente el llamado de retorno a la interfaz
        try {
            // Instancia el ConfirmDialogListener asi podremos enviar eventos al huesped
            listener = (ConfirmDialogListener) activity;
        } catch (ClassCastException e) {
            // La actividad no implementa la interfaz, arroja excepcion
            throw new ClassCastException(activity.toString()
                    + " Debes implementar ConfirmDialogListener");
        }
    }
}
