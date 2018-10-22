package com.kurobarabenjamingeorge.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.kurobarabenjamingeorge.phonebook.Data.Contact;
import com.kurobarabenjamingeorge.phonebook.Data.ContactOpenHelper;

public class AddContactActivity extends AppCompatActivity {
    private EditText mName_et, mPhone_et, mEmail_et, mAddress_et, mBio_et;
    private ContactOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        mDB = new ContactOpenHelper(this);

        mName_et = findViewById(R.id.name_et);
        mPhone_et = findViewById(R.id.phone_et);
        mEmail_et = findViewById(R.id.email_et);
        mAddress_et = findViewById(R.id.address_et);
        mBio_et = findViewById(R.id.bio_et);
    }

    public void saveContact(View view) {
        String contact_name = mName_et.getText().toString().trim();
        String contact_phone = mPhone_et.getText().toString().trim();
        String contact_email= mEmail_et.getText().toString().trim();
        String contact_address = mAddress_et.getText().toString().trim();
        String contact_bio = mBio_et.getText().toString().trim();

        if(TextUtils.isEmpty(contact_name)){
            Toast.makeText(this, "Contact must have a name", Toast.LENGTH_SHORT).show();
            return;
        }else if(TextUtils.isEmpty(contact_phone)){
            Toast.makeText(this, "Contact must have a phone number", Toast.LENGTH_SHORT).show();
            return;
        }else if(contact_phone.length() < 11){
            Toast.makeText(this, "Please enter a valid phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact c = new Contact();
        c.setName(contact_name);
        c.setPhone(contact_phone);
        c.setEmail(contact_email);
        c.setAddress(contact_address);
        c.setBio(contact_bio);

        mDB.insert(c);

        Intent i = new Intent(AddContactActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);

    }
}
