package Model;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.taqtile.easycook.R;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

/**
 * Created by User on 5/16/2015.
 */
public class RecipesAdapter extends ParseQueryAdapter<Recipe> {
    public RecipesAdapter(Context context) {
        super(context, new ParseQueryAdapter.QueryFactory<Recipe>() {
            public ParseQuery<Recipe> create() {
                ParseQuery query = new ParseQuery("Recipe");
                query.whereExists("name");
                //query.whereContains("name", "brigadeiro");
                return query;
            }
        });
    }
    @Override
    public View getItemView(Recipe recipe, View view, ViewGroup parent){
        if(view == null)
            view = View.inflate(getContext(), R.layout.custom_view, null);

        super.getItemView(recipe, view, parent);

        TextView nameTextView = (TextView)(view.findViewById(R.id.name));
        nameTextView.setText(recipe.get("name").toString());
        TextView otherNameTextView = (TextView)(view.findViewById(R.id.otherName));
        otherNameTextView.setText("description: " + recipe.getName());

        return view;
    }
}
