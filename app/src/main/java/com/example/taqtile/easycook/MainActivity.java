package com.example.taqtile.easycook;

import android.content.Intent;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQueryAdapter;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Model.Ingredient;
import Model.PreparationStep;
import Model.PrepareMode;
import Model.Recipe;
import Model.RecipesAdapter;




public class MainActivity extends ActionBarActivity {

    private Uri outputFileUri;

    public static String getUniqueImageFilename() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        Date now = new Date();
        String fileName = formatter.format(now);
        return fileName;
    }

    private void openImageIntent() {

// Determine Uri of camera image to save.
        final File root = new File(Environment.getExternalStorageDirectory() + File.separator + "MyDir" + File.separator);
        root.mkdirs();
        final String fname = getUniqueImageFilename();
        final File sdImageMainDirectory = new File(root, fname);
        outputFileUri = Uri.fromFile(sdImageMainDirectory);

        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for(ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));

        startActivityForResult(chooserIntent, 1);
    }

    //PreparationStep pS1;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                final boolean isCamera;
                if (data == null) {
                    isCamera = true;
                } else {
                    final String action = data.getAction();
                    if (action == null) {
                        isCamera = false;
                    } else {
                        isCamera = action.equals(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    }
                }

                Uri selectedImageUri;
                if (isCamera) {
                    selectedImageUri = outputFileUri;
                } else {
                    selectedImageUri = data == null ? null : data.getData();
                }
                try{
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                    bitmap = bitmap.createScaledBitmap(bitmap, 400, 400*bitmap.getHeight()/bitmap.getWidth(), false);
                    if(isCamera){
                        Matrix matrix = new Matrix();
                        matrix.postRotate(-90);
                        bitmap = bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                    }
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    byte[] imageData = bos.toByteArray();
                    final ParseFile photoFile = new ParseFile("default.jpg", imageData);
                    photoFile.saveInBackground(new SaveCallback(){
                        public void done(ParseException e){
                            if(e != null){
                                Toast.makeText(getApplicationContext(), "Error saving: " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }else{
                            //    pS1.setPhoto(photoFile);
                            //    pS1.saveInBackground();
                            }
                        }
                    });
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "L9briZquymAiUgbweETWvgERUJsAy7d6Ws4oGqED", "CU2rg1a03wuGyF7wTtyh73RCN12pacNsRMkei5It");
        ParseObject.registerSubclass(Recipe.class);
        ParseObject.registerSubclass(PrepareMode.class);
        ParseObject.registerSubclass(PreparationStep.class);

        /*openImageIntent();

        ArrayList<PreparationStep> s = new ArrayList<PreparationStep>();
        pS1 = new PreparationStep();
        pS1.setTime(0);
        pS1.setName("receita do kiki");
        pS1.setDescription("receita nova");
        Recipe testRecipe = new Recipe();
        testRecipe.setDifficulty(1.0f);
        ArrayList<String> equipments = new ArrayList<String>();
        equipments.add("frigideira");
        equipments.add("fogao");
        testRecipe.setEquipments(equipments);
        ArrayList<Ingredient> il = new ArrayList<Ingredient>();
        Ingredient ovo = new Ingredient();
        ovo.setName("ovo caipira");
        ovo.setQuantity(2);
        ovo.setUnit("unidade(s)");
        Ingredient azeite = new Ingredient();
        azeite.setName("azeite");
        azeite.setQuantity(30);
        azeite.setUnit("ml");
        il.add(ovo);
        il.add(azeite);
        testRecipe.setIngredientsList(il);
        testRecipe.setName("ovos fritos");
        testRecipe.setPortions(1);
        testRecipe.setTime(5);
        PrepareMode pm = new PrepareMode();
        ArrayList<PreparationStep> sArray = new ArrayList<PreparationStep>();
        PreparationStep pS1 = new PreparationStep();
        pS1.setTime(0);
        pS1.setName("quebrar");
        pS1.setDescription("quebre os ovos num copo e ponha sal a gosto");
        pS1.saveInBackground();
        PreparationStep pS2 = new PreparationStep();
        pS2.setTime(0);
        pS2.setName("fritar");
        pS2.setDescription("coloque os ovos na frigideira aquecida com azeite");
        pS2.saveInBackground();
        sArray.add(pS1);
        sArray.add(pS2);
        pm.setSteps(s);
        pm.saveInBackground();
        testRecipe.setPrepareMode(pm);

        testRecipe.saveInBackground();*/

        /*PreparationStep pS1 = new PreparationStep();
        pS1.setTime(0);
        pS1.setName("quebrar");
        pS1.saveInBackground();*/

        /*RecipesAdapter mainAdapter = new RecipesAdapter(this);
        ListView listView = (ListView) findViewById(R.id.listView);
        mainAdapter.setTextKey("name");
        listView.setAdapter(mainAdapter);*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    public void clique_search (View view){
        Intent search = new Intent(this, SearchActivity.class);
        MainActivity.this.startActivity(search);
    }

    public void clique_time (View view){
        Intent search = new Intent(this, SearchActivity.class);
        MainActivity.this.startActivity(search);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
