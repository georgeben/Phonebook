package com.kurobarabenjamingeorge.phonebook.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by George Benjamin on 10/21/2018.
 */

public class ContactOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "PHONEBOOK";
    private static final int DATABASE_VERSION = 1;

    private static final String CONTACT_TABLE = "Contacts";
    private static final String ID_KEY = "_id";
    private static final String NAME_KEY= "name";
    private static final String PHONE_KEY = "phone";
    private static final String EMAIL_KEY = "email";
    private static final String ADDRESS_KEY = "address";
    private static final String BIO_KEY = "bio";
    private static final String[] CONTACTS_COLUMN_LIST = new String[]{ID_KEY,
    NAME_KEY, PHONE_KEY, EMAIL_KEY, ADDRESS_KEY, BIO_KEY};

    private SQLiteDatabase mReadableDatabase;
    private  SQLiteDatabase mWritableDatabase;

    //CREATE TABLE Contacts (_id INTEGER PRIMARY KEY, name TEXT, phone TEXT, email TEXT, address TEXT, bio TEXT);
    private static final String CONTACT_TABLE_CREATE_SQL = "CREATE TABLE "+ CONTACT_TABLE+
            " ( "+ ID_KEY + " INTEGER PRIMARY KEY, " + NAME_KEY + " TEXT, " + PHONE_KEY + " TEXT, "+
            EMAIL_KEY + " TEXT, " + ADDRESS_KEY + " TEXT , " + BIO_KEY  + " TEXT);";

    public ContactOpenHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CONTACT_TABLE_CREATE_SQL);
        fillDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.w(ContactOpenHelper.class.getName(),
                "Upgrading database from version " + i + " to "
                        + i1 + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
        onCreate(db);
    }

    private void fillDatabase(SQLiteDatabase db){
        ContentValues values = new ContentValues();
        for (int i = 0; i < 4; i++){
            values.put(NAME_KEY, "George");
            values.put(PHONE_KEY, "1234");
            values.put(EMAIL_KEY, "g@gmail");
            values.put(ADDRESS_KEY, "Brass rd");
            values.put(BIO_KEY, "Cool guy");

            db.insert(CONTACT_TABLE, null, values);
        }
        /*ArrayList<Contact>  dummyData = new ArrayList<>();
        dummyData.add(new Contact("George", "1234", "george@gmail.com", "Brass road", "Cool guy"));
        dummyData.add(new Contact("Ben", "1234", "george@gmail.com", "Brass road", "Cool guy"));
        dummyData.add(new Contact("Emeka", "1234", "george@gmail.com", "Brass road", "Cool guy"));
        dummyData.add(new Contact("Phil", "1234", "george@gmail.com", "Brass road", "Cool guy"));*/

        /*for(int i = 0; i < dummyData.size(); i++){
            Contact c = dummyData.get(i);
            values.put(NAME_KEY, c.getName());
            values.put(PHONE_KEY, c.getPhone());
            values.put(EMAIL_KEY, c.getEmail());
            values.put(ADDRESS_KEY, c.getAddress());
            values.put(BIO_KEY, c.getBio());

            db.insert(CONTACT_TABLE, null, values);
        }*/
    }

    public Contact query(int position){
        //SELECT * FROM Contacts ORDER BY name ASC LIMIT 1,1;
        String queryString = "SELECT * FROM " + CONTACT_TABLE + " ORDER BY " +
                NAME_KEY + " ASC LIMIT " + position + " , 1;";
        Contact contact = new Contact();
        Cursor c = null;
        try{
            if(mReadableDatabase == null){
                mReadableDatabase = getReadableDatabase();
            }
            c = mReadableDatabase.rawQuery(queryString, null);
            c.moveToFirst();
            int idIndex = c.getColumnIndex(ID_KEY);
            int nameIndex = c.getColumnIndex(NAME_KEY);
            int phoneIndex = c.getColumnIndex(PHONE_KEY);
            int emailIndex = c.getColumnIndex(EMAIL_KEY);
            int addressIndex = c.getColumnIndex(ADDRESS_KEY);
            int bioIndex = c.getColumnIndex(BIO_KEY);

            contact.setId(c.getInt(idIndex));
            contact.setName(c.getString(nameIndex));
            contact.setPhone(c.getString(phoneIndex));
            contact.setEmail(c.getString(emailIndex));
            contact.setAddress(c.getString(addressIndex));
            contact.setBio(c.getString(bioIndex));

            Log.i("Contact name", c.getString(nameIndex));

            //c.close();

        }catch (Exception e){
            Log.i("Failed to query", e.getMessage());
        }finally {
            c.close();
            return contact;
        }
        //return  contact;
    }

    public long count(){
        if(mReadableDatabase == null){
            mReadableDatabase = getReadableDatabase();
        }
        return DatabaseUtils.queryNumEntries(mReadableDatabase, CONTACT_TABLE);
    }

    public long insert(Contact contact){
        long newId = 0;
        ContentValues values = new ContentValues();
        values.put(NAME_KEY ,contact.getName());
        values.put(PHONE_KEY, contact.getPhone());
        values.put(EMAIL_KEY, contact.getEmail());
        values.put(ADDRESS_KEY, contact.getAddress());
        values.put(BIO_KEY, contact.getBio());
        try{
            if(mWritableDatabase == null){
                mWritableDatabase = getWritableDatabase();
            }
            newId = mWritableDatabase.insert(CONTACT_TABLE, null, values);

        }catch (Exception e){
            Log.i("Failed to insert", e.getMessage());
        }

        return newId;
    }
}
