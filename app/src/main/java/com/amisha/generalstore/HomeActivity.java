package com.amisha.generalstore;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {

   Intent intent;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String message;
    int flag = 0;
    ImageButton btn1,btn2,btn3,btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btn1=findViewById(R.id.imageButton);
        btn2=findViewById(R.id.imageButton2);
        btn3=findViewById(R.id.imageButton3);
        btn4=findViewById(R.id.imageButton4);
        pref = getSharedPreferences("name", Context.MODE_PRIVATE);
        editor = pref.edit();
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               intent=new Intent(HomeActivity.this,AddProductActivity.class);
               startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(HomeActivity.this,ShowActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.item) {
            message = null;
            editor.putString("logout_key", message);
            editor.commit();
            flag = 1;


           Intent i = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(i);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(flag!=1)
        {
            message = "not pressed";
            editor.putString("logout_key", message);
            editor.commit();

        }
        super.onBackPressed();
    }
}

