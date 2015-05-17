package Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taqtile.easycook.R;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by User on 5/16/2015.
 */
public class RecipesAdapter extends ParseQueryAdapter<Recipe> {
    public RecipesAdapter(Context context, final String name, final int total_time, final String[] ingredients) {

        super(context, new ParseQueryAdapter.QueryFactory<Recipe>() {
            public ParseQuery<Recipe> create() {
                ParseQuery query = new ParseQuery("Recipe");

                if (!name.isEmpty()) {
                    query.whereContains("name",name);
                }

                if (total_time > 0) {
                    query.whereLessThanOrEqualTo("time",total_time);
                }


                ArrayList<String> a = new ArrayList<String>();
                for (int i=0;i<ingredients.length;i++)
                    a.add(ingredients[i]);
                query.whereContainedIn("ingredientsNamesList", a);

                return query;
            }
        });
    }
    @Override
    public View getItemView(Recipe recipe, View view, ViewGroup parent){
        if(view == null)
            view = View.inflate(getContext(), R.layout.search_cell, null);

        super.getItemView(recipe, view, parent);

        TextView nameTextView = (TextView)(view.findViewById(R.id.title));
        nameTextView.setText(recipe.getName());
        /*TextView otherNameTextView = (TextView)(view.findViewById(R.id.otherName));
        otherNameTextView.setText("description: " + recipe.getName());*/

        return view;
    }
}
