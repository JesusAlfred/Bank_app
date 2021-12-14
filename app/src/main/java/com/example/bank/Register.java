package com.example.bank;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {
    // Declaracion de elementos de la vista
    Button btnRegister;
    EditText txtName, txtLastname, txtUsername, txtPassword1, txtPassword2, txtCellphone;
    CheckBox btnAcceptCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button)findViewById(R.id.newRegiser);

        txtName = (EditText) findViewById(R.id.name);
        txtLastname = (EditText) findViewById(R.id.lastname);
        txtUsername = (EditText) findViewById(R.id.newuser);
        txtPassword1 = (EditText) findViewById(R.id.password1);
        txtPassword2 = (EditText) findViewById(R.id.password2);
        txtCellphone = (EditText) findViewById(R.id.cellphone);

        btnAcceptCheckBox = (CheckBox) findViewById(R.id.acceptCheckBox);

        final DB_Controller control = new DB_Controller(this);
        final SQLiteDatabase db = control.getReadableDatabase();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtName.getText().toString();
                String lastname = txtLastname.getText().toString();
                String username = txtUsername.getText().toString();
                String password1 = txtPassword1.getText().toString();
                String password2 = txtPassword2.getText().toString();
                String cellphone = txtCellphone.getText().toString();

                if (
                        name != null && !name.equals("") &&
                        lastname != null && !lastname.equals("") &&
                        username != null && !username.equals("") &&
                        password1 != null && !password1.equals("") &&
                        password2 != null && !password2.equals("") &&
                        cellphone != null && !cellphone.equals("")){
                    if (password1.equals(password2)){
                        if (btnAcceptCheckBox.isChecked()){
                            Cliente C = new Cliente(username, password1, name, lastname, cellphone, 10000, 0, 0);
                            control.insertNewClient(db, C);
                            finish();
                            Toast.makeText(getApplicationContext(),"User created",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Check Terms and Conditions",Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Passwords Don't Match. Try again.",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
