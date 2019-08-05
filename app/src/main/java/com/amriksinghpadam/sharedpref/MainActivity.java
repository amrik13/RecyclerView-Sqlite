package com.amriksinghpadam.sharedpref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button register,login;
    private EditText userName,password;
    private MyDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("LOGIN");

        login = findViewById(R.id.loginBtn);
        register = findViewById(R.id.registerBtn);
        userName = findViewById(R.id.uname);
        password = findViewById(R.id.pwd);

//Registration Button
        View.OnClickListener registerBtnListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        };
        register.setOnClickListener(registerBtnListener);

// Login Button
        View.OnClickListener loginListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String myUserName,myPassword;
                myUserName = userName.getText().toString().trim();
                myPassword = password.getText().toString().trim();
                if(myUserName.isEmpty() || myPassword.isEmpty())
                    Toast.makeText(MainActivity.this,"Fill All Section Please!!", Toast.LENGTH_SHORT).show();
                else
                {
                    Map<String,String> map = new HashMap<>();
                    map.put("username",myUserName);
                    map.put("password",myPassword);
                    //Toast.makeText(MainActivity.this,myUserName+"--"+myPassword, Toast.LENGTH_SHORT).show();
                    myDb = new MyDatabase(MainActivity.this);
                    int flag = myDb.getData(map);

                    if(flag>=0)
                    {
                        Toast.makeText(MainActivity.this,"Successfully Login!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Home.class);
                        Bundle extras = new Bundle();
                        extras.putString("index", flag+"");
                        intent.putExtras(extras);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(MainActivity.this,"Invalid User!!!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        };
        login.setOnClickListener(loginListener);

    }
}
