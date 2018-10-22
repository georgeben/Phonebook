package com.kurobarabenjamingeorge.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class ViewContactActivity extends AppCompatActivity {

    private TextView name_tv, phone_tv, email_tv, address_tv, bio_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        name_tv = findViewById(R.id.contact_name_tv);
        phone_tv = findViewById(R.id.contact_phone_tv);
        email_tv = findViewById(R.id.contact_email_tv);
        address_tv = findViewById(R.id.contact_address_tv);
        bio_tv = findViewById(R.id.contact_bio_tv);

        Bundle extras = getIntent().getExtras();

        if(extras.containsKey(MainActivity.NAME_EXTRA)){
            name_tv.setText(extras.getString(MainActivity.NAME_EXTRA));
        }

        if(extras.containsKey(MainActivity.PHONE_EXTRA)){
            phone_tv.setText(extras.getString(MainActivity.PHONE_EXTRA));
        }

        if(extras.containsKey(MainActivity.EMAIL_EXTRA)){
            String contact_email = extras.getString(MainActivity.EMAIL_EXTRA);
            if(!TextUtils.isEmpty(contact_email)){
                email_tv.setText(contact_email);
            }else{
                email_tv.setText("No email available");
            }

        }

        if(extras.containsKey(MainActivity.ADDRESS_EXTRA)){
            String contact_email = extras.getString(MainActivity.ADDRESS_EXTRA);
            if(!TextUtils.isEmpty(contact_email)){
                address_tv.setText(contact_email);
            }else{
                address_tv.setText("No address available");
            }
        }

        if(extras.containsKey(MainActivity.BIO_EXTRA)){
            String contact_bio = extras.getString(MainActivity.BIO_EXTRA);
            if(!TextUtils.isEmpty(contact_bio)){
                bio_tv.setText(contact_bio);
            }else{
                bio_tv.setText("No address available");
            }
        }
    }
}
