package com.example.appgrocerylist.ui;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgrocerylist.R;
import com.example.appgrocerylist.activities.DetailsActivity;
import com.example.appgrocerylist.data.DatabaseHandler;
import com.example.appgrocerylist.models.Grocery;

import org.w3c.dom.Text;

import java.util.List;

public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.MyViewHolder> {

    private Context mContext;
    private List<Grocery> groceries;
    private DatabaseHandler db;
    private AlertDialog dialog;
    private AlertDialog.Builder dialogBuilder;

    public GroceryAdapter(Context mContext, List<Grocery> groceries) {
        this.mContext = mContext;
        this.groceries = groceries;
        this.db = new DatabaseHandler(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.grocery_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Grocery grocery = groceries.get(i);

        myViewHolder.name.setText(grocery.getName());
        myViewHolder.date.setText("Added on: " + grocery.getDateAdded());
        myViewHolder.qty.setText("Qty: " + grocery.getQty());

    }

    @Override
    public int getItemCount() {
        return this.groceries.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name, date, qty;
        Button editButton, deleteButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.groceryNameId);
            date = itemView.findViewById(R.id.groceryDateId);
            qty = itemView.findViewById(R.id.groceryQtyId);
            editButton = itemView.findViewById(R.id.editButtonId);
            deleteButton = itemView.findViewById(R.id.deleteButtonId);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent detailsIntent = new Intent(mContext, DetailsActivity.class);
                    detailsIntent.putExtra("grocery", groceries.get(position));
                    mContext.startActivity(detailsIntent);
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Grocery grocery = groceries.get(position);
            switch (v.getId()) {
                case R.id.editButtonId: {
                    editGrocery(grocery);
                }
                break;
                case R.id.deleteButtonId: {
                    deleteGrocery(grocery);
                }
                break;
                default:
                    break;
            }
        }

        private void deleteGrocery(final Grocery grocery) {
//            Creating a dialog to ask the user if they truly wanna do it!
            dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setTitle("Delete " + grocery.getName() + " from database?")
                    .setIcon(android.R.drawable.ic_delete)
                    .setMessage("Are you sure you wanna delete this item?")
                    .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.deleteGrocery(grocery.getId());
                            Toast.makeText(mContext, grocery.getName() + " deleted!", Toast.LENGTH_SHORT).show();
                            groceries.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                        }
                    })
                    .setNegativeButton("Nah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(mContext, "Okay!", Toast.LENGTH_SHORT).show();
                        }
                    });

            dialog = dialogBuilder.create();
            dialog.show();
        }

        private void editGrocery(final Grocery grocery) {
            dialogBuilder = new AlertDialog.Builder(mContext);

            View v = LayoutInflater.from(mContext).inflate(R.layout.add_item_dialog, null);
            final EditText name = v.findViewById(R.id.itemNameId);
            final EditText qty = v.findViewById(R.id.itemQtyId);
            final TextView title = v.findViewById(R.id.dialogTitleId);
            Button saveButton = v.findViewById(R.id.saveButtonId);

            dialogBuilder.setTitle("Update " + grocery.getName())
                    .setIcon(android.R.drawable.ic_menu_edit)
                    .setView(v);

            dialog = dialogBuilder.create();

            name.setText(grocery.getName());
            qty.setText(grocery.getQty());
            title.setText("Edit " + grocery.getName());
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!(TextUtils.isEmpty(name.getText().toString()) && TextUtils.isEmpty(qty.getText().toString()))) {
                        if (!name.getText().toString().equals(grocery.getName()))
                            grocery.setName(name.getText().toString());
                        if (!qty.getText().toString().equals(grocery.getQty()))
                            grocery.setQty(qty.getText().toString());

                        dialog.dismiss();
                        db.updateGrocery(grocery);
                        int position = getAdapterPosition();
                        groceries.remove(position);
                        groceries.add(position, grocery);
                        notifyItemChanged(position);

                    } else {
                        Toast.makeText(mContext, "Empty fields!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            dialog.show();

        }
    }


}
