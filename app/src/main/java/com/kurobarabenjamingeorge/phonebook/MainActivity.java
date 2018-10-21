package com.kurobarabenjamingeorge.phonebook;

import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kurobarabenjamingeorge.phonebook.Data.Contact;
import com.kurobarabenjamingeorge.phonebook.Data.ContactOpenHelper;
import com.kurobarabenjamingeorge.phonebook.Data.ContactRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ContactRecyclerViewAdapter.ContactItemClickListener{
    private RecyclerView mContactRecyclerView;
    private ContactRecyclerViewAdapter mAdapter;
    private FloatingActionButton fab;
    //private ArrayList<Contact>  mContacts;
    private ContactOpenHelper mDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new ContactOpenHelper(this);


        /*mContacts = new ArrayList<>();
        mContacts.add(new Contact("George", "1234", "george@gmail.com", "Brass road", "Cool guy"));
        mContacts.add(new Contact("Ben", "1234", "george@gmail.com", "Brass road", "Cool guy"));
        mContacts.add(new Contact("Emeka", "1234", "george@gmail.com", "Brass road", "Cool guy"));
        mContacts.add(new Contact("Phil", "1234", "george@gmail.com", "Brass road", "Cool guy"));*/

        mContactRecyclerView = (RecyclerView) findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setHasFixedSize(true);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ContactRecyclerViewAdapter(this,  this, mDB);
        mContactRecyclerView.setAdapter(mAdapter);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // mDB.fillDatabase(mDB.getWritableDatabase());
                long numItems = mDB.count();
                Log.i("Num of items", String.valueOf(numItems));
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Toast.makeText(this, String.valueOf(position) , Toast.LENGTH_SHORT).show();
    }
}
