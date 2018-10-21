package com.kurobarabenjamingeorge.phonebook.Data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kurobarabenjamingeorge.phonebook.R;

import java.util.ArrayList;

/**
 * Created by George Benjamin on 10/21/2018.
 */

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter<ContactRecyclerViewAdapter.ViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<Contact> contacts;
    private ContactItemClickListener mListener;

    public interface ContactItemClickListener{
        void onItemClick(int position);
    }

    public ContactRecyclerViewAdapter(Context ctx, ArrayList<Contact> contacts, ContactItemClickListener listener){
        mContext = ctx;
        this.contacts = contacts;
        mInflater = LayoutInflater.from(mContext);
        mListener = listener;
    }
    @Override
    public ContactRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.contact_item, parent, false);
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(ContactRecyclerViewAdapter.ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.contact_name.setText(contact.getName());
        holder.contact_phone.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

     class ViewHolder extends  RecyclerView.ViewHolder{
        public ImageView contact_image;
        public TextView contact_name, contact_phone;
        private ContactRecyclerViewAdapter adapter;
        public ViewHolder(View itemView, ContactRecyclerViewAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            contact_image = itemView.findViewById(R.id.contact_image);
            contact_name = itemView.findViewById(R.id.contact_name);
            contact_phone = itemView.findViewById(R.id.contact_phone);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }

     }
}
