package com.amriksinghpadam.sharedpref;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class DeleteUser extends AppCompatActivity {

    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);
        getSupportActionBar().setTitle("All User:");

        listview = findViewById(R.id.listViewId);

        final MyDatabase myDb = new MyDatabase(this);
        final Cursor allUser = myDb.getAllUser();
        int count = allUser.getCount();
        final String[] val = new String[count];
        int i =0;
        while (allUser.moveToNext())
        {
            val[i]=allUser.getString(0)+"- "+allUser.getString(1);
            i++;
        }

        ArrayAdapter cursorAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,val);
        listview.setAdapter(cursorAdapter);

        AdapterView.OnItemClickListener listListener = new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> var1, View var2, int var3, long var4)
            {
                //Toast.makeText(DeleteUser.this,val[var3],Toast.LENGTH_SHORT).show();
                int temp=0;
                String clickedRow = val[var3];
                String[] listRow = clickedRow.split("-",0);
                for(String r : listRow)
                {
                     if(temp==0)
                    {
                        int id = Integer.parseInt(r);
                        AlertDialog.Builder alert = new AlertDialog.Builder(DeleteUser.this);
                        alert.setCancelable(true);

                        Cursor user = myDb.getAllUserData(id);
                        user.moveToNext();
                        alert.setTitle("ID Of User Is: "+user.getString(0));
                        alert.setMessage("\nName: "+user.getString(1)+"\n\nAddress: "+user.getString(4)+"\n\nUser Name: "+user.getString(3)+"\n \nPassword: "+user.getString(2));
                        alert.show();
                    }
                        temp++;
                }

            }
        };
        listview.setOnItemClickListener(listListener);

    }
}
