package com.amisha.generalstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddProductActivity extends AppCompatActivity {
    MyDatabase database;
    EditText e8, e9, e10, e11;
    Button b3;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        database=new MyDatabase(this);
        e8 = findViewById(R.id.editText8);
        e9 = findViewById(R.id.editText9);
        e10 = findViewById(R.id.editText10);
        e11 = findViewById(R.id.editText11);
        b3 = findViewById(R.id.button3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(AddProductActivity.this);
                builder.setTitle("Notification");
                if (e8.getText().toString().isEmpty() || e9.getText().toString().isEmpty() || e10.getText().toString().isEmpty() || e11.getText().toString().isEmpty()) {
                    builder.setMessage("Unable to save data. Inappropriate data entered.");
                    builder.setIcon(R.drawable.ic_cancel);
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            e8.setText("");
                            e9.setText("");
                            e10.setText("");
                            e11.setText("");


                        }
                    });
                    builder.show();

                } else {
                    int id = Integer.parseInt(e8.getText().toString());
                    String name = e9.getText().toString();
                    int price = Integer.parseInt(e10.getText().toString());
                    int quantity = Integer.parseInt(e11.getText().toString());

                    ProductRec rec = new ProductRec(id, name, price, quantity);
                    long result = database.insertRecord(rec);


                    if (result > 0) {

                        builder.setMessage("Item Successfully Saved");
                        builder.setIcon(R.drawable.ic_check_circle);
                    } else {
                        // Toast.makeText(AddProductActivity.this, "not added", Toast.LENGTH_LONG).show();
                        builder.setMessage("Unable to save data");
                        builder.setIcon(R.drawable.ic_cancel);
                    }
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            e8.setText("");
                            e9.setText("");
                            e10.setText("");
                            e11.setText("");
                        }
                    });
                    builder.show();

                }
            }




        });
    }
}
