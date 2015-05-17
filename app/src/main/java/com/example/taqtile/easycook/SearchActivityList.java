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
import android.widget.LinearLayout;
import android.widget.ListView;

import Model.RecipesAdapter;


public class SearchActivityList extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_activity_list);
        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }*/
        String name = getIntent().getStringExtra("name");
        int time = getIntent().getIntExtra("total_time",0);
        String ingredients[] = getIntent().getStringArrayExtra("ingredients");
        System.out.println(name);
        RecipesAdapter mainAdapter = new RecipesAdapter(this, name, time, ingredients);
        ListView listView = (ListView) findViewById(R.id.recipesListView);
        listView.setAdapter(mainAdapter);

    }

    public void clique_start (View view){
        Intent start = new Intent(this.getApplicationContext(), PreparationActivity.class);
        
        startActivity(start);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_activity_list, menu);
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
    /*public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_search_activity_list, container, false);


            LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.list_container);

            for (int i = 0; i <=10; i++) {
                View view = View.inflate(getActivity(), R.layout.search_cell, null);

                linearLayout.addView(view);
            }

            return rootView;
        }
    }*/
}
