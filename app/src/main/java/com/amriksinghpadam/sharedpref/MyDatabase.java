package com.amriksinghpadam.sharedpref;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.Map;

import static android.widget.Toast.LENGTH_SHORT;

public class MyDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "myfirstdatabase.db";
    private static final String TABLE_NAME = "MY_TABLE";
    private static final int VERSION = 1;
     static final String COL0="id";
     static final String COL1="name";
     static final String COL2="password";
     static final String COL3="username";
     static final String COL4="address";

    MyDatabase(Context context)
    {
        super(context,DB_NAME,null,VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+COL0+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL1+" TEXT, "+COL2+" TEXT, "+COL3+" INTEGER, "+COL4+" TEXT) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String password,String username,String address)
    {
        SQLiteDatabase sqlDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL1,name);
        contentValues.put(COL2,password);
        contentValues.put(COL3,username);
        contentValues.put(COL4,address);

        long result = sqlDb.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public int getData(Map<String,String> map)
    {
        String userName = map.get("username");
        String password = map.get("password");
        //System.out.println(userName+password+" showing from map result!!");
        SQLiteDatabase sqlDb = getReadableDatabase();
        Cursor row = sqlDb.rawQuery("SELECT * FROM "+TABLE_NAME,null);

        int count=0;
        int index=0;
        while(row.moveToNext())
        {
            String uname = row.getString(3);
            String pwd = row.getString(2);

            if(userName.equals(uname) && password.equals(pwd))
            {
                   count++;
                index = Integer.parseInt(row.getString(0));
            }
        }
        if(count==0)
        {
            return -1;
        }else
        {
            return index;
        }
    }
    public Cursor getAllUserData(int id)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor row = db.rawQuery("SELECT * FROM "+TABLE_NAME+" WHERE "+COL0+" = "+id,null);//+" WHERE "+COL0+" = "+id
        return row;
    }
    public boolean updateData(int id, String newUserName)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COL3,newUserName);
        String idd = String.valueOf(id);
        int row = db.update(TABLE_NAME,cv,COL0+" = ?", new String[]{idd});
        if(row>0)
            return true;
        else
            return false;
    }
    public boolean checkUserExist(String userName)
    {
        SQLiteDatabase sqlDb = getReadableDatabase();
        Cursor row = sqlDb.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        int temp = 0;
        while(row.moveToNext())
        {
            if(row.getString(3).equals(userName))
            {
                temp++;
            }
        }
        if(temp>0)
            return true;
        else
            return false;
    }
    public Cursor getAllUser()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor row = db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return row;
    }

}
