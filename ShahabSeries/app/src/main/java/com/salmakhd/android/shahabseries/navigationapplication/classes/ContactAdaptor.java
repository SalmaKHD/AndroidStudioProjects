package com.salmakhd.android.shahabseries.navigationapplication.classes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salmakhd.android.shahabseries.R;

import java.util.ArrayList;

public class ContactAdaptor extends RecyclerView.Adapter<ContactAdaptor.ViewHolder> {
    private ArrayList<ContactModel> list;

    public ContactAdaptor(ArrayList<ContactModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ContactAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContactModel contactModel = list.get(position);
        holder.name.setText(contactModel.getName());
        holder.phone.setText(contactModel.getPhone());
        holder.shape.setText(contactModel.getName().charAt(0));
        holder.id = list.get(position).getId();
        holder.positionInList = position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView phone;
        public TextView shape;
        private AlertDialog.Builder builder;
        public int id;
        public int positionInList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            builder = new AlertDialog.Builder(itemView.getContext());
            builder.setTitle("Delete Contact");
            builder.setMessage("Are you fucking sure?");
            builder.setCancelable(false);
            builder.setPositiveButton(R.string.positive_dialog_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // delete contact from database
                    Database db = new Database(itemView.getContext());
                    db.deleteCContact(id);
                    list.remove(positionInList);
                        notifyDataSetChanged();
                    Toast.makeText(itemView.getContext(), "OK button clicked!", Toast.LENGTH_LONG).show();
                    // close dialog
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton(R.string.negative_dialog_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(itemView.getContext(), "Cancel button clicked!", Toast.LENGTH_LONG).show();
                }
            });
            name = itemView.findViewById(R.id.row_name);
            phone = itemView.findViewById(R.id.row_phone);
            shape = itemView.findViewById(R.id.shape);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), name + " Clicked!", Toast.LENGTH_LONG).show();
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    builder.show();
                    return true;
                }
            });
        }
    }
}