package Model;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taqtile.easycook.R;
import com.example.taqtile.easycook.SearchActivityList;
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

                ParseQuery<Recipe> query = Recipe.getQuery();

                if (!name.isEmpty()) {
                    query.whereContains("name", name);
                }

                if (total_time > 0) {
                    query.whereLessThanOrEqualTo("time",total_time);
                }

                if(ingredients.length > 0) {
                    ArrayList<String> a = new ArrayList<String>();
                    for (int i = 0; i < ingredients.length; i++) {
                        if(ingredients[i].length() > 0)
                            a.add(ingredients[i]);
                    }
                    if(a.size() > 0){
                        query.whereContainedIn("ingredientsNamesList", a);
                    }

                }


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

        TextView ingredients = (TextView) view.findViewById(R.id.ingredients);
        ingredients.setText("Ingredients");
        /*String ingredientsList = "";
        ArrayList<Ingredient> ingredientes = recipe.getIngredientsList();
        for(int i = 0; i < ingredientes.size(); i++){
            Ingredient ingredient = ingredientes.get(i);
            if(i != 0)
                ingredientsList = ingredientsList + ", ";
            ingredientsList = ingredientsList + ingredient.getName().toString();
        }
        String hey_there = ingredientsList;
        ingredients.setText(ingredientsList);*/

        return view;
    }
}
