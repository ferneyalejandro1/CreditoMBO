package com.ferney.creditombo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.ferney.creditombo.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @TargetApi(21)
    public void activity_form(View view){
        //getWindow().setExitTransition(new Explode());
        Intent intent = new Intent(this,FormActivity.class);
        startActivity(intent);//, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @TargetApi(21)
    public void activity_search(View view){
        //getWindow().setExitTransition(new Explode());
        Intent intent = new Intent(this, SearchActivity.class);
        //EditText editText = (EditText) findViewById(R.id.ingrese_nombre);
        //String msg = "que pasa pirobo";//editText.getText().toString();
        //intent.putExtra(EXTRA_MESSAGE, msg);
        startActivity(intent);//, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
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
}
