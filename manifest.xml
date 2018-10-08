package com.example.android.startbuzz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DrinkActivity extends Activity {

    public static final String EXTRA_DRINKID = "drinkNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINKID);

     try{
            SQLiteOpenHelper startbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = startbuzzDatabaseHelper.getWritableDatabase();
            Cursor cursor = db.query("DRINK", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID","FAVORITE"}, "_id=?", new String[]{Integer.toString(drinkId)}, null, null, null);

            if (cursor.moveToFirst()) {
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite=(cursor.getInt(3)==1);

                CheckBox favorite=(CheckBox)findViewById(R.id.favorite);
                favorite.setChecked(isFavorite);

                //Populate the drink name
                TextView name = (TextView) findViewById(R.id.name);
                name.setText(nameText);

                //Populate the drink description
                TextView description = (TextView) findViewById(R.id.description);
                description.setText(descriptionText);

                //Populate the drink image
                ImageView photo = (ImageView) findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
                cursor.close();
                db.close();
     }catch (SQLException e){
                Toast toast=Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
                toast.show();
            }
    }

    public void onFavoriteClicked(View view) {
        int drinkNo=(Integer) getIntent().getExtras().get("drinkNo");
        CheckBox favorite=(CheckBox)findViewById(R.id.favorite);
        ContentValues drinkValues=new ContentValues();
        drinkValues.put("FAVORITE",favorite.isChecked());
        SQLiteOpenHelper starbuzzDatabaseHelper=new StarbuzzDatabaseHelper(DrinkActivity.this);
        try{
            SQLiteDatabase db=starbuzzDatabaseHelper.getWritableDatabase();
            db.update("DRINK",drinkValues,"_id=?",new String[]{Integer.toString(drinkNo)});
            db.close();
        }catch (SQLException e){
            Toast toast=Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
