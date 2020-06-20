package com.example.thefactorfactor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText enter = (EditText) findViewById(R.id.number);
                int number = Integer.parseInt(enter.getText().toString());
                Log.d(TAG, "onClick: Number Given");
                Intent activity2 = new Intent(MainActivity.this, MainActivity2.class);
                activity2.putExtra("key", number);
                startActivity(activity2);

            }
        });
    }
}