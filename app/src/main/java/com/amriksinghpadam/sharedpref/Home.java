package com.amriksinghpadam.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private int userId;
    TextView welcomeText,nameText,addressText;
    MyDatabase db;
    Button forgetUserNameBtn, dltUserBtn;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().setTitle("Home");

        welcomeText = findViewById(R.id.welcomId);
        nameText = findViewById(R.id.homeNameId);
        addressText = findViewById(R.id.homeAdressId);
        forgetUserNameBtn = findViewById(R.id.forgetUserNameId);
        dltUserBtn = findViewById(R.id.deleteUserId);

        try
        {
            welcomeText.setText("Welcome");

            Intent intent = getIntent();
            userId = Integer.parseInt(intent.getStringExtra("index"));

            db = new MyDatabase(Home.this);
            Cursor userDetail = db.getAllUserData(userId);

            userDetail.moveToNext();

            int ind = userDetail.getColumnIndex("name");
            String name = userDetail.getString(ind);
            nameText.append(name);

            int ind1 = userDetail.getColumnIndex("address");
            String address = userDetail.getString(ind1);
            addressText.append(address);

            View.OnClickListener forgetUserNameListener = new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent intent = new Intent(Home.this,UpdateUserName.class);
                    intent.putExtra("id",userId);
                    startActivity(intent);
                }
            };
            forgetUserNameBtn.setOnClickListener(forgetUserNameListener);

            View.OnClickListener dltUserListener = new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent intent = new Intent(Home.this,DeleteUser.class);
                    startActivity(intent);
                }
            };
            dltUserBtn.setOnClickListener(dltUserListener);


        }
        catch (Exception e)
        {
            Toast.makeText(Home.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
