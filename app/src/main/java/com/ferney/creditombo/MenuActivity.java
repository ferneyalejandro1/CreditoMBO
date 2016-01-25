package com.ferney.creditombo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class MenuActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.ferney.creditombo.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //enable transition
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_menu);
    }

    @TargetApi(21)
    public void activity_form(View view){
        getWindow().setExitTransition(new Explode());
        Intent intent = new Intent(this,FormActivity.class);
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
    }

    @TargetApi(21)
    public void consulta(View view){
        getWindow().setExitTransition(new Explode());
        Intent intent = new Intent(this, ListActivity.class);
        EditText editText = (EditText) findViewById(R.id.ingrese_nombre);
        String msg = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, msg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }
}
