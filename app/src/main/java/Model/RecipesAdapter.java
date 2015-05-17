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
    public RecipesAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Recipe>() {
            public ParseQuery<Recipe> create() {
                ParseQuery query = new ParseQuery("Recipe");
                ArrayList<String> a = new ArrayList<String>();
                a.add("azeite");
                /*ArrayList<JSONObject> jsonArray = new ArrayList<JSONObject>();
                JSONObject jsonObject = new JSONObject();
                try{
                    jsonObject.put("name", "azeite");
                    jsonObject.putOpt("quantity", 0);
                }catch(Exception e){
                    e.printStackTrace();
                }
                jsonArray.add(jsonObject);*/
                query.whereContains("ingredientsList", "azeite");
                //query.whereContainedIn("ingredientsList", a);//.whereEqualTo("portions", 1);
                //query.whereContains("ingredients", "azeite");
                //query.whereContains("ingredientsList", "azeite");
                //query.whereContains("name", "brigadeiro");
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
