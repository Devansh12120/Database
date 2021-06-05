package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        EditText name ,cntact, email;
        DBHelper DB;
        String id = Long.toString(generateID());
        Button insert, update, delete, view;
        name = findViewById(R.id.name);
        cntact = findViewById(R.id.contact);
        email = findViewById(R.id.email);
        insert = findViewById(R.id.insertbutton);
        update = findViewById(R.id.updatebutton);
        delete = findViewById(R.id.deletebutton);
        view = findViewById(R.id.viewbutton);
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idtxt= id;
                String nametxt = name.getText().toString();
                String contacttxt = cntact.getText().toString();
                String emailtxt = email.getText().toString();
                Boolean checkinsertdata = DB.insertuserdata(idtxt,nametxt,contacttxt,emailtxt);
                if (checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Entry inserted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Entry not inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = name.getText().toString();
                String contacttxt = cntact.getText().toString();
                String emailtxt = email.getText().toString();
                Boolean checkupdatedata = DB.updateuserdata(nametxt,contacttxt,emailtxt);
                if (checkupdatedata==true){
                    Toast.makeText(MainActivity.this, "Entry updated",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Entry not updated",Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nametxt = name.getText().toString();
                Boolean checkdeletedata = DB.deleteuserdata(nametxt);
                if (checkdeletedata==true){
                    Toast.makeText(MainActivity.this, "Entry deleted",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Entry not deleted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "no Entry  found",Toast.LENGTH_SHORT).show();
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    while (res.moveToNext()){
                        buffer.append("Id - "+res.getString(0)+"\n");
                        buffer.append("Name - "+res.getString(1)+"\n");
                        buffer.append("Contact - "+res.getString(2)+"\n");
                        buffer.append("Email - "+res.getString(3)+"\n\n\n");
                    }
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setCancelable(true);
                    builder.setTitle("User Entries");
                    builder.setMessage(buffer.toString());
                    builder.show();
                }
            }
        });
    }
    public long generateID() {
        Random rnd = new Random();
        char [] digits = new char[10];
        digits[0] = (char) (rnd.nextInt(9) + '1');
        for(int i=1; i<digits.length; i++) {
            digits[i] = (char) (rnd.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

}