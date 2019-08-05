package com.amriksinghpadam.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUserName extends AppCompatActivity {

    Button updateBtn;
    EditText newUserName;
    int userId;
    MyDatabase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_name);
        getSupportActionBar().setTitle("Update User Name");

        updateBtn = findViewById(R.id.updateBtnId);
        newUserName = findViewById(R.id.newUserNameId);
        mydb = new MyDatabase(this);
        Intent intent = getIntent();
        userId = intent.getIntExtra("id",-1);

        View.OnClickListener updateListener = new View.OnClickListener()
        {
            public void onClick(View v) {
                String uname = newUserName.getText().toString().trim();
                if (uname.isEmpty()) {
                    Toast.makeText(UpdateUserName.this, "Add User Name  ", Toast.LENGTH_SHORT).show();
                } else {
                    boolean flag = mydb.updateData(userId, uname);
                    if (flag) {
                        Toast.makeText(UpdateUserName.this, "Updated!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateUserName.this, MainActivity.class);
                        startActivity(intent);
                    } else
                        Toast.makeText(UpdateUserName.this, "Not Updated!!", Toast.LENGTH_SHORT).show();

                }
            }
        };
        updateBtn.setOnClickListener(updateListener);


    }
}
