package com.example.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn;
    private EditText edt1;
    private EditText edt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);
        this.getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        edt1=findViewById(R.id.editText);
        edt2=findViewById(R.id.editText2);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((edt1.getText().toString().equals("")) || edt2.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please Enter Player Names!", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent i=new Intent(MainActivity.this,Main.class);
                        i.putExtra("input1",edt1.getText().toString());
                        i.putExtra("input2",edt2.getText().toString());
                        startActivity(i);
                }
            }
        });


    }
}
