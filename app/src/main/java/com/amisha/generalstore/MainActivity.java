package com.amisha.generalstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText e1,e2;
    Button b1;
    TextView t1,t2;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String name,password;
    String key="";
    MyDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=new MyDatabase(this);
        e1=findViewById(R.id.editText);
        e2=findViewById(R.id.editText2);
        b1=findViewById(R.id.button);
        t1=findViewById(R.id.textView9);
        t2=findViewById(R.id.textView);
        pref = getSharedPreferences("name", Context.MODE_PRIVATE);
        editor = pref.edit();
        key=pref.getString("logout_key",null);
        if(key==null) {

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = e1.getText().toString();
                    password = e2.getText().toString();
                    if (name.isEmpty())
                        e1.setError("Required");
                    if( password.isEmpty())
                        e2.setError("Required");
                    else {
                        UserRec rec = database.checkRec(name);
                        String checkuser = rec.getUsername();
                        String checkpass = rec.getPassword();
                        if (checkuser != null) {
                            if (checkpass.equals(password)) {
                                editor.putString("name_key", name);

                                editor.putString("password_key", password);
                                editor.commit();
                                Intent i = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(i);
                                finish();
                            } else {
                                e2.setError("Wrong Password");
                            }
                        } else {
                            t1.setVisibility(View.VISIBLE);
                        }
                    }

                }
            });

            t2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }
        else {
            Intent i2=new Intent(MainActivity.this,HomeActivity.class);
            startActivity(i2);
            finish();

        }
    }
}
