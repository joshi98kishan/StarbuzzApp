Skip to content
 
Search or jump to…

Pull requests
Issues
Marketplace
Explore
 @joshi98kishan Sign out
0
0 2 joshi98kishan/StarbuzzApp
 Code  Issues 0  Pull requests 0  Projects 0  Wiki  Insights  Settings
StarbuzzApp/DrinkCategoryActivity.java
028c3f6  5 hours ago
@joshi98kishan joshi98kishan created drinkcategoryactivity
     
57 lines (47 sloc)  1.83 KB
package com.example.android.startbuzz;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class DrinkCategoryActivity extends ListActivity {

    private SQLiteDatabase db;
    private Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listDrinks=getListView();

        try{
            SQLiteOpenHelper starbuzzDatabaseHelper=new StarbuzzDatabaseHelper(this);
            db=starbuzzDatabaseHelper.getReadableDatabase();

            cursor  =db.query("DRINK",new String[]{"_id","NAME"},null,null,null,null,null);
            CursorAdapter listAdapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,cursor,new String[]{"NAME"},new int[]{android.R.id.text1});
            listDrinks.setAdapter(listAdapter);
        }catch (SQLException e){
            Toast toast=Toast.makeText(this,"Database unavailable",Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent=new Intent(DrinkCategoryActivity.this,DrinkActivity.class);
        intent.putExtra(DrinkActivity.EXTRA_DRINKID,(int)id);
        startActivity(intent);
    }
}
© 2018 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
Pricing
API
Training
Blog
About
Press h to open a hovercard with more details.
