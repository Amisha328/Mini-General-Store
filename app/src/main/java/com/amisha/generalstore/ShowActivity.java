package com.amisha.generalstore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {
   MyDatabase database;
   Button button;
   ListView l;
   EditText e12;
   ArrayList<String> detail;
   ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        database=new MyDatabase(this);
        button=findViewById(R.id.button4);
        l=findViewById(R.id.listview);
        e12=findViewById(R.id.editText12);
        detail=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,detail);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id=Integer.parseInt(e12.getText().toString());
                ProductRec rec=database.getSingleRec(id);
                int checkid=rec.getId();
                if(checkid!=0)
                {
                    detail.clear();
                    detail.add("Product id: "+rec.getId()+"\n\n\n"+"Product Name: "+rec.getName()+"\n\n\n"+"Product Price: "+rec.getPrice()+" per pic \n\n\nProduct Quantity: "+rec.getQuantity());

                }
                else
                {
                    detail.clear();
                    detail.add("No Result Found");

                }

                l.setAdapter(arrayAdapter);

                arrayAdapter.notifyDataSetChanged();


                }
        });


    }
}
