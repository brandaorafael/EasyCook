package com.example.taqtile.easycook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.SeekBar.*;


public class SearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }


    public void clique_nome (View view){
        final EditText getName = (EditText) findViewById(R.id.getNome);
        int value;
        if(getName.getVisibility() == View.VISIBLE){
            value = View.GONE;
        }else{
            value = View.VISIBLE;
        }
        getName.setVisibility(value);
    }

    public void clique_ingredients (View view){
        final EditText getIngredients = (EditText) findViewById(R.id.getIngredients);
        int value;
        if(getIngredients.getVisibility() == View.VISIBLE){
            value = View.GONE;
        }else{
            value = View.VISIBLE;
        }
        getIngredients.setVisibility(value);
    }

    public void clique_time (View view){
        final SeekBar getTime = (SeekBar) findViewById(R.id.seekBar);
        int value;
        if(getTime.getVisibility() == View.VISIBLE){
            value = View.GONE;
        }else{
            value = View.VISIBLE;
        }
        getTime.setVisibility(value);
        findViewById(R.id.textView3).setVisibility(value);
    }

    public void submitSearch (View view){
        Intent searchList = new Intent(this.getApplicationContext(), SearchActivityList.class);
        startActivity(searchList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_search, container, false);
            final SeekBar sb =  ((SeekBar)rootView.findViewById(R.id.seekBar));
            sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    int total = i * 180;
                    int horas = total / 3600;
                    int minutos = (total - horas * 3600) / 60;
                    ((TextView) rootView.findViewById(R.id.textView3)).setText("" + horas + "h" + minutos + "min");
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
            return rootView;
        }

    }
}
