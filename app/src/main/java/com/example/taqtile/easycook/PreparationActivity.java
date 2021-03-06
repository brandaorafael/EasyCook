package com.example.taqtile.easycook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


public class PreparationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preparation);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_preparation, menu);
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

        int cont = 0;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_preparation, container, false);
            //rootView.setLayoutParams(new ViewGroup.LayoutParams();

            LinearLayout stepLayout = (LinearLayout) rootView.findViewById(R.id.step_cell_container);

            stepLayout.addView(View.inflate(getActivity(), R.layout.step_cell, null));


            Button button = (Button) rootView.findViewById(R.id.button);
            final TextView textView = (TextView)rootView.findViewById(R.id.textView2);
            final TextView textView2 = (TextView) rootView.findViewById(R.id.textView);
            textView2.setText("Step: 1");
            button.setText("Next");
            textView.setText("Do stuff");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cont++;
                    textView.setText("Step: " + String.valueOf(cont));

//                    stepLayout.addView(View.inflate(getActivity(), R.layout.step_cell, null));
                }
            });

            return rootView;
        }
    }
}
