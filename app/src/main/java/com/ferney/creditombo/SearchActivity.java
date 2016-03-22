package com.ferney.creditombo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Actividad principal que contiene un fragmento con una lista.
 * Recuerda que la nueva librer�a de soporte reemplaz� la clase
 * {@link android.support.v7.app.ActionBarActivity} por
 * {@link AppCompatActivity} para el uso de la action bar
 * en versiones antiguas.
 */

public class SearchActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Creacion del fragmento principal
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.container, new FragmentFindAll(), "FragmentFindAll").commit();

        /*//obtener instancia de la lista
        listView = (ListView)findViewById(R.id.listView);
        //Crear y setear adaptador
        adapter = new SearchAdapter(this);
        listView.setAdapter(adapter);*/
    }

    /**
     * A placeholder fragment containing a simple view.

    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_search,
                    container, false);
            return rootView;
        }
    }*/

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

}
