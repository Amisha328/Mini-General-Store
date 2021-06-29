package com.amisha.generalstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateRecord extends AppCompatActivity {
    MyDatabase database;
    Button search,update;
    EditText id,name,price,quantity;
    AlertDialog.Builder builder;
    int prod_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        search=findViewById(R.id.button_search);
        update=findViewById(R.id.button_update);
        id=findViewById(R.id.editText_upid);
        name=findViewById(R.id.editText_upname);
        price=findViewById(R.id.editText_upprice);
        quantity=findViewById(R.id.editText_upquantity);
        database=new MyDatabase(this);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                price.setText("");
                quantity.setText("");
                prod_id=Integer.parseInt(id.getText().toString());
                ProductRec rec=database.getSingleRec(prod_id);
                int checkid=rec.getId();
                if(checkid!=0)
                {
                    id.setText(Integer.toString(rec.getId()));
                    name.setText(rec.getName());
                    price.setText(Integer.toString(rec.getPrice()));
                    quantity.setText(Integer.toString(rec.getQuantity()));
                }
                else
                    Toast.makeText(UpdateRecord.this, "No Data Found", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder = new AlertDialog.Builder(UpdateRecord.this);
                builder.setTitle("Notification");
                if (id.getText().toString().isEmpty() || name.getText().toString().isEmpty() || price.getText().toString().isEmpty() || quantity.getText().toString().isEmpty())
                {
                    builder.setMessage("Unable to find data. Inappropriate data entered.");
                    builder.setIcon(R.drawable.ic_cancel);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            id.setText("");
                            name.setText("");
                            price.setText("");
                            quantity.setText("");
                        }
                    });
                    builder.show();
               }
                else {
                    long result = database.updateRecord(prod_id,Integer.parseInt(id.getText().toString()), name.getText().toString(), Integer.parseInt(price.getText().toString()), Integer.parseInt(quantity.getText().toString()));
                    Toast.makeText(UpdateRecord.this, "result"+result, Toast.LENGTH_SHORT).show();
                    if (result > 0){
                        builder.setMessage("Item Successfully Saved");
                        builder.setIcon(R.drawable.ic_check_circle);
                    }
                    else
                    {
                        builder.setMessage("Unable to save data");
                        builder.setIcon(R.drawable.ic_cancel);
                    }
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            id.setText("");
                            name.setText("");
                            price.setText("");
                            quantity.setText("");
                        }
                    });
                    builder.show();


                }
            }
        });
    }
}