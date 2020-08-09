package com.amisha.generalstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String DB_NAME = "fullcart_db";
    public static final String D_TABLE="user_record";
    public static final String DB_TABLE = "prod_record";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String QUANTITY = "quantity";

    public static final String USERNAME ="username";
    public static final String PASSWORD="password";
    public static final String REGISTER_NAME="regname";
    public static final String PHONE="phone";
    public static final  String EMAIL="email";



    public MyDatabase(Context context)
    {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

            String query = "CREATE TABLE " + DB_TABLE + "(" + ID + " NUMBER PRIMARY KEY," + NAME + " TEXT," + PRICE + " NUMBER," + QUANTITY + " NUMBER);";
            String q = "CREATE TABLE "+ D_TABLE + "(" + USERNAME + " TEXT PRIMARY KEY," + PASSWORD + " TEXT," + REGISTER_NAME +" TEXT," + EMAIL + " TEXT," + PHONE + " TEXT);";
            db.execSQL(query);
            db.execSQL(q);
           // Log.d("SQL query","user");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + D_TABLE);
        onCreate(db);

    }

    //############# Insert Record ##############

    public long insertRecord(ProductRec record) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ID, record.getId());
        values.put(NAME, record.getName());
        values.put(PRICE, record.getPrice());
        values.put(QUANTITY, record.getQuantity());
        long result = db.insert(DB_TABLE, null, values);
        return result;
    }

    //################---Get Single Record---#############
    public  ProductRec getSingleRec(int id) {
        SQLiteDatabase db = getReadableDatabase();
        try {
            Cursor cur = db.query(DB_TABLE, new String[]{ID, NAME, PRICE, QUANTITY}, ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
            if (cur != null) {
                cur.moveToFirst();
            return new ProductRec(cur.getInt(0), cur.getString(1), cur.getInt(2), cur.getInt(3));

        } else {
                return new ProductRec(null,null,null,null);
            }

        }
            catch(Exception e)
            {
                return new ProductRec(null, null, null, null);
            }
    }



    //############# user data insert #####################
    public long userInsertRecord(UserRec rec) {
        SQLiteDatabase db = getWritableDatabase();//-----For writing the data....
        //Store data in object------------
        ContentValues values = new ContentValues();
        values.put(USERNAME, rec.getUsername());
        values.put(PASSWORD, rec.getPassword());

        long result = db.insert(D_TABLE, null, values);
        return result;
    }

    //############# check user data ###############3

    public  UserRec checkRec(String username)
    {
        SQLiteDatabase db=getReadableDatabase();
        try {

            Cursor cur = db.query(D_TABLE, new String[]{USERNAME, PASSWORD,REGISTER_NAME,PHONE,EMAIL}, USERNAME + "=?", new String[]{String.valueOf(username)}, null, null, null);
            if (cur != null){
                cur.moveToFirst();
            return new UserRec(cur.getString(0), cur.getString(1),cur.getString(2),cur.getString(3),cur.getString(4));
        }
            else
            {
                return  new UserRec(null,null,null,null,null);
            }

        }
        catch(Exception e)
        {
            return  new UserRec(null,null,null,null,null);
        }

    }

}
