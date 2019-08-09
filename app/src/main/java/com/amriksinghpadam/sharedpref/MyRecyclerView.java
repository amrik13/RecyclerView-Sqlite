package com.amriksinghpadam.sharedpref;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MyRecyclerView extends AppCompatActivity {

    private ArrayList<String> img = new ArrayList<>();
    private ArrayList<String> txt = new ArrayList<>();
    RecyclerView recyclerView;
    RelativeLayout relLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recycler_view);
        getSupportActionBar().setTitle("Recycler View");

//        img.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
//        txt.add("image 1");
//
//        img.add("https://image.shutterstock.com/image-photo/hand-scientist-holding-flask-lab-260nw-1425336056.jpg");
//        txt.add("image 2");
//
//        img.add("https://image.shutterstock.com/image-photo/transparent-testtubes-blue-water-flasks-260nw-242404399.jpg");
//        txt.add("image 3");
//
//        img.add("https://image.shutterstock.com/image-photo/empty-test-tubes-laboratory-closeup-260nw-1057760678.jpg");
//        txt.add("image 4");
//
//        img.add("https://image.shutterstock.com/image-photo/scientist-working-soil-laboratory-600w-1212005038.jpg");
//        txt.add("image 5");
//
//        img.add("https://image.shutterstock.com/image-photo/hand-scientist-holding-flask-lab-260nw-1425336056.jpg");
//        txt.add("image 6");
//
//        img.add("https://image.shutterstock.com/image-photo/hand-scientist-holding-flask-lab-260nw-1425336056.jpg");
//        txt.add("image 7");
//
//        img.add("https://image.shutterstock.com/image-photo/hand-scientist-holding-flask-lab-260nw-1425336056.jpg");
//        txt.add("image 8");
//
//        img.add("https://image.shutterstock.com/image-photo/hand-scientist-holding-flask-lab-260nw-1425336056.jpg");
//        txt.add("image 9");
//
//        img.add("https://homepages.cae.wisc.edu/~ece533/images/airplane.png");
//        txt.add("image 10");
//
//        img.add("https://image.shutterstock.com/image-photo/hand-scientist-holding-flask-lab-260nw-1425336056.jpg");
//        txt.add("image 11");
//
//        img.add("https://image.shutterstock.com/image-photo/empty-test-tubes-laboratory-closeup-260nw-1057760678.jpg");
//        txt.add("image 12");

        final MyDatabase myDb = new MyDatabase(this);
        final Cursor allUser = myDb.getAllUser();
        int count = allUser.getCount();

        while(allUser.moveToNext())
        {
            img.add("https://upload.wikimedia.org/wikipedia/commons/thumb/1/12/User_icon_2.svg/1024px-User_icon_2.svg.png");
            txt.add(allUser.getString(1));
        }

        int i = getIntent().getIntExtra("id",0);

        recyclerView = findViewById(R.id.recyclerViewId);
        relLayout = findViewById(R.id.relativeLayoutId);
        RecyclerAdapter adapter = new RecyclerAdapter(this,img,txt);
        recyclerView.setAdapter(adapter);

        if(i==1)
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        }else if(i==2)
        {
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        }else
        {
            recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        }



    }
}
