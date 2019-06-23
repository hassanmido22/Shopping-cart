package com.example.hassan.shopping_project.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String dataBase_Name = "shopping_shopp";

    private static final String Person_Table = "person";
    private static final int dataBase_Version = 2;
    private static final String PID_pk = "pid_pk";
    private static final String Uname = "username";
    private static final String Pass = "password";
    private static final String Email = "email";
    private static final String Age = "age";

    private static final String Categories_Table = "category";
    private static final String Category = "cat";
    private static final String CID_PK = "cid_pk";
    private static final String C_Name = "c_name";

    private static final String Orders_Table = "order_tb";
    private static final String OID = "oid_pk";
    private static final String CID_FK = "cid_fk";
    private static final String PID_FK = "pid_fk";
    private static final String Num_Item = "num_item";
    private static final String State = "state";

    private static final String Login_Table = "login";
    private static final String login_ID = "login_id" ;
    private static final String P_User_name = "login_username";

    private Context context ;

    public DBHelper(Context context) {
        super(context, dataBase_Name, null, dataBase_Version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String Person = "create table "+Person_Table+" ( "+
                PID_pk+" integer PRIMARY KEY AUTOINCREMENT not null , "+
                Uname+" varchar(255) not null , "+
                Pass+" varchar(255) not null , "+
                Email+" varchar(255) not null , "+
                Age+" integer not null ) ";

        String Categories = "create table "+Categories_Table+" ( "+
                CID_PK+" integer PRIMARY KEY AUTOINCREMENT not null , "+
                Category+" varchar(255) not null , "+
                C_Name+" varchar(255) not null ) ";



        String login = "create table "+Login_Table+" ( "+
                login_ID+" integer PRIMARY KEY AUTOINCREMENT not null ,"
                +P_User_name+" varchar(255) not null ); ";

        db.execSQL(Person);
        db.execSQL(Categories);
        db.execSQL(login);
        Toast.makeText(context, "DataBase set !!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String Orders = "create table "+Orders_Table+" ( "+
                OID+" integer(3) not null , "+
                PID_FK+" integer(3) not null , "+
                Num_Item+" integer not null , "+
                CID_FK+" integer(3) not null , "+
                State+" varchar(255) not null , " +
                "PRIMARY KEY ("+PID_FK+","+OID+","+CID_FK+") ) ";

        db.execSQL(Orders);

    }

    public void PersonInsert(person p)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Uname,p.getUsername());
        cv.put(Pass,p.getPass());
        cv.put(Email,p.getEmail());
        cv.put(Age,p.getAge());
        db.insert(Person_Table,null,cv);
    }

    public void PersonUpdate(person p)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Uname,p.getUsername());
        cv.put(Pass,p.getPass());
        cv.put(Email,p.getEmail());
        cv.put(Age,p.getAge());
        db.update(Person_Table,cv,PID_pk+" = "+login_ID(),null);
    }

    public void order_insert(orders order)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OID,order.getO_id());
        cv.put(PID_FK,order.getP_id());
        cv.put(CID_FK,order.getC_id());
        cv.put(State,order.getState());
        cv.put(Num_Item,order.getNum_of_item());
        db.insert(Orders_Table,null,cv);

    }

    public void order_update_chick(orders order)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Num_Item,order.getNum_of_item());
        db.update(Orders_Table,cv,State+" = 0 and "+PID_FK+" = "+login_ID()+" and "+CID_FK+
                " = "+get_CID("Chicken") ,null);

    }

    public void order_update_beef(orders order)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Num_Item,order.getNum_of_item());
        db.update(Orders_Table,cv,State+" = 0 and "+PID_FK+" = "+login_ID()+" and "+CID_FK+
                " = "+get_CID("Beef") ,null);

    }

    public void order_update_rice(orders order)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Num_Item,order.getNum_of_item());
        db.update(Orders_Table,cv,State+" = 0 and "+PID_FK+" = "+login_ID()+" and "+CID_FK+
                " = "+get_CID("rice") ,null);

    }

    public int get_CID(String cname)
    {
        SQLiteDatabase db = getWritableDatabase();
        String[] arr = {CID_PK,Category};
        Cursor c = db.query(Categories_Table,arr,null,null,null,null,null);
        int id = 0;
        String name = null;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            name = c.getString(c.getColumnIndex(Category));
            if(name.equals(cname))
            {
                id = c.getInt(c.getColumnIndex(CID_PK));
                break;
            }
        }
        return id;
    }

    public void Category_insert_chick()
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_Name,"Food");
        cv.put(Category,"Chicken");
        db.insert(Categories_Table,null,cv);
    }

    public void Category_insert_rice()
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_Name,"Food");
        cv.put(Category,"rice");
        db.insert(Categories_Table,null,cv);
    }

    public void Category_insert_beef()
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(C_Name,"Food");
        cv.put(Category,"Beef");
        db.insert(Categories_Table,null,cv);
    }

    public void Login_insert(String unname)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(P_User_name,unname);
        db.insert(Login_Table,null,cv);
    }


    public String s_login(String uname)
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select "+Uname+" , "+Pass+" from "+Person_Table+" ";
        Cursor c = db.rawQuery(Query,null);
        String username , password = "not found";

        if(c.moveToFirst())
        {
            do{
                username = c.getString(0);
                if(username.equals(uname)) {
                    password = c.getString(1);
                    break;
                }
            }while(c.moveToNext());

        }
        return password;
    }

    public int Login_search() {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select " + login_ID + " from " + Login_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        int size = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            size++;
        }
        return size;

    }

    public int login_ID()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select " + PID_pk + " from " + Person_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        int last;
        c.moveToLast();
        last = c.getInt(c.getColumnIndex(PID_pk));

        return last;
    }


    public String categories()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Categories_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        StringBuffer sb = new StringBuffer();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            String c_name = c.getString(c.getColumnIndex(C_Name));
            String cat = c.getString(c.getColumnIndex(Category));
            int id = c.getInt(c.getColumnIndex(CID_PK));
            sb.append(id +" " + c_name + " " + cat +"\n");
        }

        return sb.toString();
    }


    public int categories_count()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Categories_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        int idd = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            idd = c.getInt(c.getColumnIndex(CID_PK));
        }

        return idd;
    }

    public void delete_login()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" delete from " + Login_Table);
    }

    public void delete_person()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" delete from " + Person_Table);
    }

    public void delete_orders()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" delete from " + Orders_Table);
    }

    public void delete_category()
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" delete from " + Categories_Table);
    }

    public ArrayList<categories> retrieve_data ()
    {
        categories cat ;
        ArrayList<categories> cats = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Categories_Table + " ";
        Cursor c = db.rawQuery(Query, null);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            String c_name = c.getString(c.getColumnIndex(C_Name));
            String catego = c.getString(c.getColumnIndex(Category));
            cat = new categories(catego,c_name);
            cats.add(cat);
        }
        return cats;
    }


    public ArrayList<String> retrieve_orders_cart()
    {
        ArrayList<String> all = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select "+Num_Item+" from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" and "+State+" = 0 ";
        Cursor c = db.rawQuery(Query, null);
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()) {
            String num = c.getString(c.getColumnIndex(Num_Item));
            all.add(num);
        }
        return all;
    }

    public boolean is_inserted_cart()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" and "+State+" = 0 ";
        Cursor c = db.rawQuery(Query, null);
        int size = 0 ;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            size++;
        }
        if(size>0)
            return true;
        else
            return false;
    }


    public boolean is_inserted_history()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" and "+State+" = 1 ";
        Cursor c = db.rawQuery(Query, null);
        int size = 0 ;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            size++;
        }
        if(size>0)
            return true;
        else
            return false;
    }


    public String orders()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Orders_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        StringBuffer sb = new StringBuffer();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            String state = c.getString(c.getColumnIndex(State));
            int id = c.getInt(c.getColumnIndex(OID));
            int num = c.getInt(c.getColumnIndex(Num_Item));
            int pid = c.getInt(c.getColumnIndex(PID_FK));
            int cid = c.getInt(c.getColumnIndex(CID_FK));
            sb.append(id +" " + num + " " + pid + " " + cid + " " + state + "\n");
        }

        return sb.toString();
    }


    public String orders_cart()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" ";
        Cursor c = db.rawQuery(Query, null);

        StringBuffer sb = new StringBuffer();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            String state = c.getString(c.getColumnIndex(State));
            int id = c.getInt(c.getColumnIndex(OID));
            int num = c.getInt(c.getColumnIndex(Num_Item));
            int pid = c.getInt(c.getColumnIndex(PID_FK));
            int cid = c.getInt(c.getColumnIndex(CID_FK));
            sb.append(id +" " + num + " " + pid + " " + cid + " " + state + "\n");
        }

        return sb.toString();
    }

    public void insert_order_history(orders order)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(State,order.getState());
        db.update(Orders_Table,cv,State+" = 0 and "+PID_FK+" = "+login_ID(),null);
    }


    public String max()
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select "+OID+" from " + Orders_Table + " ";
        Cursor c = db.rawQuery(Query, null);
        c.moveToLast();

        return c.getString(c.getColumnIndex(OID));
    }

    public int num_of_cats()
    {

        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Categories_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        int num = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            num ++;
        }

        return num;
    }

    public int num_of_orders()
    {

        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Orders_Table + " ";
        Cursor c = db.rawQuery(Query, null);

        int num = 0;
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            num ++;
        }

        return num;
    }

    public ArrayList<Integer> retrieve_history_data()
    {
        ArrayList<Integer> all = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        // retrieve chicken num of items
        String Query = "select * from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" and "
                +State+" = 1 and "+CID_FK+" = "+get_CID("Chicken")+" ";
        Cursor c = db.rawQuery(Query, null);
        int sum = 0;

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            sum = sum + c.getInt(c.getColumnIndex(Num_Item));
        }
        all.add(sum);

        // retrieve rice num of items
        Query = "select * from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" and "
                +State+" = 1 and "+CID_FK+" = "+get_CID("Beef")+" ";
        c = db.rawQuery(Query, null);
        sum = 0;

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            sum = sum + c.getInt(c.getColumnIndex(Num_Item));
        }
        all.add(sum);

        // retrieve rice num of items

        Query = "select * from " + Orders_Table + " where "+PID_FK+" = "+login_ID()+" and "
                +State+" = 1 and "+CID_FK+" = "+get_CID("rice")+" ";
        c = db.rawQuery(Query, null);
        sum = 0;

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            sum = sum + c.getInt(c.getColumnIndex(Num_Item));
        }
        all.add(sum);

        return all;
    }



    public person retrieve_person_data(person p)
    {
        SQLiteDatabase db = getWritableDatabase();
        String Query = "select * from " + Person_Table + " where "+PID_pk+" = "+login_ID()+" ";
        Cursor c = db.rawQuery(Query, null);
        StringBuffer str = new StringBuffer();
        c.moveToFirst();

        String username = c.getString(c.getColumnIndex(Uname));
        String pass = c.getString(c.getColumnIndex(Pass));
        String email = c.getString(c.getColumnIndex(Email));
        int age = c.getInt(c.getColumnIndex(Age));

        p.setUsername(username);
        p.setPass(pass);
        p.setEmail(email);
        p.setAge(age);

        return p;
    }

}
