package com.amisha.generalstore;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText e3, e4, e5, e6, e7;
    Button b2;
    MyDatabase database;
    TextView t;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = new MyDatabase(this);
        e3 = findViewById(R.id.editText3);
        e4 = findViewById(R.id.editText4);
        e5 = findViewById(R.id.editText5);
        e6 = findViewById(R.id.editText6);
        e7 = findViewById(R.id.editText7);
        b2 = findViewById(R.id.button2);
        t = findViewById(R.id.textView10);


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* if ((e3.getText().toString()).isEmpty())
                    e3.setError("Required");
                if ((e4.getText().toString()).isEmpty())
                    e4.setError("Required");
                if ((e5.getText().toString()).isEmpty())
                    e5.setError("Required");
                if ((e6.getText().toString()).isEmpty() )
                   e6.setError("Required");
                if ((e7.getText().toString()).isEmpty())
                    e7.setError("Required");
                if(!e6.getText().toString().contains("@"))
                    e6.setError("Invalid");
                else {*/

                String username = e4.getText().toString();
                String password = e7.getText().toString();
                String regname = e3.getText().toString();
                String phone = e5.getText().toString();
                String email = e6.getText().toString();

                String arr[] = new String[]{regname, phone, email, username, password};
                for (int i = 0; i < 5; i++) {
                    String item = arr[i];
                    int check = checkEmpty(item, i);

                    switch (check) {
                        case 0:
                            e3.setError("Required");
                            break;
                        case 1:
                            e4.setError("Required");
                            break;
                        case 2:
                            e5.setError("Required");
                            break;
                        case 3:
                            e6.setError("Required");
                            break;
                        case 4:
                            e7.setError("Required");
                            break;
                        case 5:
                            flag = 1;
                            break;
                    }
                }
                if (flag == 1) {
                    if(!e6.getText().toString().contains("@"))
                        e6.setError("Invalid");
                    else {

                        UserRec record = new UserRec(username, password, regname, phone, email);
                        long result = database.userInsertRecord(record);
                        //Toast.makeText(RegisterActivity.this,"result "+result,Toast.LENGTH_LONG).show();
                        if (result > 0) {
                            // Toast.makeText(RegisterActivity.this,"if block",Toast.LENGTH_SHORT).show();

                            final Dialog dialog = new Dialog(RegisterActivity.this);
                            dialog.setContentView(R.layout.custom_layout);
                            ImageView img = dialog.findViewById(R.id.imageView);
                            TextView t = dialog.findViewById(R.id.success);
                            new CountDownTimer(4000, 1000) {

                                @Override
                                public void onTick(long millisUntilFinished) {
                                }

                                @Override
                                public void onFinish() {
                                    dialog.dismiss();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }.start();

                            dialog.show();
                            // Toast.makeText(RegisterActivity.this, "end if", Toast.LENGTH_LONG).show();
                        } else {
                            t.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }
        });
    }

    int checkEmpty(String item, int i) {
        return (item.isEmpty())? i:5;
    }
}



