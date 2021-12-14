package com.example.bank;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class DB_Controller extends SQLiteOpenHelper {
    private static final String USER_TABLE_CREATE = "CREATE TABLE users(_id INTEGER PRIMARY KEY AUTOINCREMENT, user TEXT, password TEXT, name TEXT, lastname TEXT, cellphone TEXT,balanceDeb1 REAL, balanceDeb2 REAL, balanceCre1 REAL)";
    private static final String MOVEMENTS_TABLE_CREATE = "CREATE TABLE movements(_Userid INTEGER, date TEXT, type TEXT, mount REAL, comment TEXT, FOREIGN KEY(_Userid) REFERENCES users(_id))";
    private static final String DB_NAME = "usersInfo.sqlite";
    private static final int DB_VERSION = 1;

    public DB_Controller(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(MOVEMENTS_TABLE_CREATE);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS movements");
        db.execSQL(USER_TABLE_CREATE);
        db.execSQL(MOVEMENTS_TABLE_CREATE);
    }



    /*
    * Queries personalizadas
    * */

    public Cliente getClientByUsername(SQLiteDatabase db,String username, String incomepassword){

        String query = "select * from users " + "where user = '" + username + "' and password = '" + incomepassword + "'";
        //String query = "select * from users";
        Cursor C = db.rawQuery(query, null);
        C.moveToNext();
        try {
            System.out.println(C.getString(C.getColumnIndexOrThrow("_id")));
        } catch (Exception e){
            System.out.println("Error ---------------------");
            System.out.println(e);
        }
        int cnt = C.getCount();
        if(cnt <= 0){
            return null;
        }

        int id = C.getInt(C.getColumnIndexOrThrow("_id"));
        String user = C.getString(C.getColumnIndexOrThrow("user"));
        String name = C.getString(C.getColumnIndexOrThrow("name"));
        String lastname = C.getString(C.getColumnIndexOrThrow("lastname"));
        String password = C.getString(C.getColumnIndexOrThrow("password"));
        String cellphone = C.getString(C.getColumnIndexOrThrow("cellphone"));
        float BalanceDeb1 = C.getFloat(C.getColumnIndexOrThrow("balanceDeb1"));
        float BalanceDeb2 = C.getFloat(C.getColumnIndexOrThrow("balanceDeb2"));
        float BalanceCre1 = C.getFloat(C.getColumnIndexOrThrow("balanceCre1"));

        return new Cliente(id, user, password, name, lastname, cellphone, BalanceDeb1, BalanceDeb2, BalanceCre1);
    }

    public void insertNewClient(SQLiteDatabase db, Cliente C){
        db.execSQL("INSERT INTO users (user, password, name, lastname, cellphone, balanceDeb1, balanceDeb2, balanceCre1) VALUES ("
                + "'" + C.getUser() + "',"
                + "'" + C.getPassword() + "',"
                + "'" + C.getName() + "',"
                + "'" + C.getLastname() + "',"
                + "'" + C.getCellphone() + "',"
                + C.getBalanceDeb1() + ","
                + C.getBalanceDeb2() + ","
                + C.getBalanceCre1()
                + ")");
    }

    public Cliente getClientById (SQLiteDatabase db, int id){
        String query = "select * from users where _id = " + id;
        Cursor C = db.rawQuery(query, null);
        C.moveToNext();
        try {
            System.out.println(C.getString(C.getColumnIndexOrThrow("_id")));
        } catch (Exception e){
            System.out.println("Error ---------------------");
            System.out.println(e);
        }
        int cnt = C.getCount();
        if(cnt <= 0){
            return null;
        }
        String user = C.getString(C.getColumnIndexOrThrow("user"));
        String name = C.getString(C.getColumnIndexOrThrow("name"));
        String lastname = C.getString(C.getColumnIndexOrThrow("lastname"));
        String password = C.getString(C.getColumnIndexOrThrow("password"));
        String cellphone = C.getString(C.getColumnIndexOrThrow("cellphone"));
        float BalanceDeb1 = C.getFloat(C.getColumnIndexOrThrow("balanceDeb1"));
        float BalanceDeb2 = C.getFloat(C.getColumnIndexOrThrow("balanceDeb2"));
        float BalanceCre1 = C.getFloat(C.getColumnIndexOrThrow("balanceCre1"));

        return new Cliente(id, user, password, name, lastname, cellphone, BalanceDeb1, BalanceDeb2, BalanceCre1);
    }

    public void upDateMounts(SQLiteDatabase db, Cliente C){
        String query = "UPDATE users " +
                "SET balanceDeb1 = " + C.getBalanceDeb1() + ", " +
                "balanceDeb2 = " + C.getBalanceDeb2() + ", " +
                "balanceCre1 = " + C.getBalanceCre1()
                + " WHERE _id = " + C.getId();
        db.execSQL(query);
    }

    public void addMovement(SQLiteDatabase db, Cliente C, String type, float mount, String com){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = dtf.format(now);

        db.execSQL("INSERT INTO movements (_Userid, date, type, mount, comment) VALUES ("
                + C.getId() + ","
                + "'" + date + "',"
                + "'" + type + "',"
                + mount + ","
                + "'" + com + "'"
                + ")");
    }

    public List<TransactionElement> getTransactionById(SQLiteDatabase db, int id){
        String query = "select * from movements where _Userid = " + id;
        Cursor C = db.rawQuery(query, null);
        try {
            //System.out.println(C.getString(C.getColumnIndexOrThrow("_Userid")));
        } catch (Exception e){
            System.out.println("Error ---------------------");
            System.out.println(e);
        }
        int cnt = C.getCount();
        if(cnt <= 0){
            return null;
        }else {
            int i = 0;
            List<TransactionElement> ret = new ArrayList<TransactionElement>();
            do {
                C.moveToNext();
                ret.add(new TransactionElement(
                        id,
                        C.getString(C.getColumnIndexOrThrow("date")),
                        C.getString(C.getColumnIndexOrThrow("type")),
                        C.getFloat(C.getColumnIndexOrThrow("mount")),
                        C.getString(C.getColumnIndexOrThrow("comment"))
                ));
                i++;
            } while (i < cnt);
            return ret;
        }
    }
}
