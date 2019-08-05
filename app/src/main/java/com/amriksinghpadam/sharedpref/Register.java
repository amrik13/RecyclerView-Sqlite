package com.amriksinghpadam.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText eName,eUserName,ePassword,eAddress;
    Button registerBtn;
    String name,userName,password,address;
    MyDatabase myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("REGISTRATION");

        eName = findViewById(R.id.nameId);
        eUserName = findViewById(R.id.userNameId);
        ePassword = findViewById(R.id.passwordId);
        eAddress = findViewById(R.id.addressId);
        registerBtn = findViewById(R.id.registerBtn);

// Inserting data Button Functionality
        View.OnClickListener regListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                name = eName.getText().toString().trim();
                userName = eUserName.getText().toString().trim();
                password = ePassword. getText().toString().trim();
                address = eAddress.getText().toString().trim();
                if(name.isEmpty() || userName.isEmpty() || password.isEmpty() || address.isEmpty())
                {
                    Toast.makeText(Register.this,"Fill All Section!!",Toast.LENGTH_SHORT).show();
                }else
                {
                    myDb = new MyDatabase(Register.this);

                    boolean isUserExist = myDb.checkUserExist(userName);
                    if(isUserExist==true)
                    {
                        Toast.makeText(Register.this,"User Name Already Exist!!",Toast.LENGTH_SHORT).show();
                    }else {
                        boolean flag = myDb.insertData(name, password, userName, address);

                        if (flag == true) {
                            Toast.makeText(Register.this, "Data Inserted Successfully!!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Register.this, "Unable to Insert Data!!", Toast.LENGTH_SHORT).show();
                        }
                    }

                }

            }
        };
        registerBtn.setOnClickListener(regListener);




    }
}
