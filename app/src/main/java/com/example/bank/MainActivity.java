package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Declaración de elementos de la vista
    Button btnLogin, btnRegister;
    EditText txtUsername, txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignación de elementos respecto a su Id
        btnLogin = (Button) findViewById(R.id.login);
        btnRegister = (Button) findViewById(R.id.register);

        btnLogin.setBackgroundColor(Color.RED);
        btnLogin.setTextColor(Color.WHITE);
        btnRegister.setBackgroundColor(Color.RED);
        btnRegister.setTextColor(Color.WHITE);

        txtUsername = (EditText)findViewById(R.id.newuser);
        txtPassword = (EditText)findViewById(R.id.password);

        // Se crea la base de datos
        final DB_Controller control = new DB_Controller(this);
        final SQLiteDatabase db = control.getReadableDatabase();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Checar con la base de datos las credenciales
                String user = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                if(user != null && !user.equals("") && password != null && !password.equals("")){
                    // Check in the database for the username and password
                    if (db != null){
                        Cliente cliente = control.getClientByUsername(db, user, password);
                        if (cliente != null) {
                            Toast.makeText(getApplicationContext(), "Welcome " + cliente.getName() + " " + cliente.getLastname(), Toast.LENGTH_SHORT).show();
                            Intent miIntent = new Intent(MainActivity.this, DashBoard.class);
                            miIntent.putExtra("client_id", cliente.getId());
                            startActivity(miIntent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Error, no match found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(),"Can't access the data base",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Fill all the fields",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent miIntent = new Intent(MainActivity.this,Register.class);
                startActivity(miIntent);
            }
        });
    }
}