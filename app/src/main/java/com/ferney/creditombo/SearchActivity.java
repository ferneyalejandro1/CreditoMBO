package com.ferney.creditombo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

/**
 * Actividad principal que contiene un fragmento con una lista.
 * Recuerda que la nueva librer�a de soporte reemplaz� la clase
 * {@link android.support.v7.app.ActionBarActivity} por
 * {@link AppCompatActivity} para el uso de la action bar
 * en versiones antiguas.
 */

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainFragment(), "MainFragment").commit();
        /*Intent intent = getIntent();
        String msg = intent.getStringExtra(MenuActivity.EXTRA_MESSAGE);
        TextView tv = new TextView(this);
        tv.setTextSize(40);
        tv.setText(msg);
        LinearLayout lay  = (LinearLayout)findViewById(R.id.container);
        lay.addView(tv);*/

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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() { }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_search,
                    container, false);
            return rootView;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }
}
