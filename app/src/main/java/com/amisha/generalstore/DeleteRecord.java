package com.amisha.generalstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteRecord extends AppCompatActivity {
    MyDatabase database;
    EditText prd_id;
    Button delete;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_record);
        database=new MyDatabase(this);
        prd_id=findViewById(R.id.editText_id);
        delete=findViewById(R.id.button_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=Integer.parseInt(prd_id.getText().toString());
                long deleted_item = database.deleteRecord(id);
                Toast.makeText(DeleteRecord.this, deleted_item+" deleted Successfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeleteRecord.this,HomeActivity.class);
                startActivity(intent);
            }
        });



    }
}