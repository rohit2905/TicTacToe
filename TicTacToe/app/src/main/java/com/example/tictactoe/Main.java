package com.example.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean player1=true;
    private int count;
    private int p1;
    private int p2;
    private TextView tvp1;
    private TextView tvp2;
    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("\t\t\t\t\t\t\t\t\t\t\tTIC TAC TOE");
        setContentView(R.layout.activity_main);
        tvp1=findViewById(R.id.tv1);
        tvp2=findViewById(R.id.tv2);

        Intent intent=getIntent();
         s1=intent.getStringExtra("input1");
         s2=intent.getStringExtra("input2");
        tvp1.setText(s1 + "\t\t:\t0");
        tvp2.setText(s2 + "\t\t:\t0");
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                String ButtonID="b"+i+j;
                int resID=getResources().getIdentifier(ButtonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button btnr=findViewById(R.id.br);
        btnr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetgame();
            }
        });

    }



    @Override
    public void onClick(View v) {

        if(!((Button) v).getText().toString().equals(""))
        {
            return ;
        }
        if(player1){
            ((Button) v).setText("X");
        }
        else
        {
            ((Button) v).setText("O");
        }
        count++;
        if(checkforwin()){
            if(player1){
                player1wins();
            }
            else {
                player2wins();
            }
        }
        else if(count==9){
            draw();
        }
        else{
            player1=!player1;
        }
    }
    private boolean checkforwin()
    {
        String[][] field=new String[3][3];
        for (int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].equals((""))){
                return true;
            }
        }
        for(int i=0;i<3;i++)
        {
            if(field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].equals((""))){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].equals((""))){
            return true;
        }
        if(field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].equals((""))){
            return true;
        }
        return false;
    }
    private void player1wins(){
        p1++;
        Toast.makeText(this, s1+" Wins!!", Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
    }
    private void player2wins(){
        p2++;
        Toast.makeText(this, s2+" Wins!!", Toast.LENGTH_SHORT).show();
        updatepointstext();
        resetboard();
    }
    private void draw(){
        Toast.makeText(this, "Draw!!", Toast.LENGTH_SHORT).show();
        resetboard();
    }
    private void updatepointstext(){
        tvp1.setText(s1+"\t\t:\t"+p1);
        tvp2.setText(s2+"\t\t:\t"+p2);
    }
    private void resetboard(){
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        count=0;
        player1=true;
    }
    private void resetgame(){
        p1=0;
        p2=0;
        updatepointstext();
        resetboard();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        count=savedInstanceState.getInt("count",count);
        p1=savedInstanceState.getInt("p1",p1);
        p2=savedInstanceState.getInt("p2",p2);
        player1=savedInstanceState.getBoolean("player1",player1);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count",count);
        outState.putInt("p1",p1);
        outState.putInt("p2",p2);
        outState.putBoolean("player1",player1);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();

        final Intent intent=new Intent(Main.this,MainActivity.class);

        AlertDialog.Builder builder=new AlertDialog.Builder(Main.this);
        builder.setTitle("Leave the game?");
        builder.setIcon(R.drawable.logout);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent1=new Intent(Main.this,MainActivity.class);
                startActivity(intent);

            }
}
        );
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }}
