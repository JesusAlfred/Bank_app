package com.example.bank;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashBoard extends AppCompatActivity implements View.OnClickListener{

    TextView txtDeb1, txtDeb2, txtCre1, txtTitleDash;

    ImageButton btnTransfer, btnHistory, btnServices, btnMap;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        id = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id = extras.getInt("client_id");
        } else {
            finish();
        }
        txtDeb1 = (TextView) findViewById(R.id.deb1);
        txtDeb2 = (TextView) findViewById(R.id.deb2);
        txtCre1 = (TextView) findViewById(R.id.cre1);
        txtTitleDash = (TextView) findViewById(R.id.DashTitle);

        btnTransfer = (ImageButton) findViewById(R.id.transfer);
        btnHistory = (ImageButton) findViewById(R.id.history);
        btnServices = (ImageButton) findViewById(R.id.service);
        btnMap = (ImageButton) findViewById(R.id.map);

        btnTransfer.setOnClickListener(this);
        btnHistory.setOnClickListener(this);
        btnServices.setOnClickListener(this);
        btnMap.setOnClickListener(this);

        updateValance();

    }
    @Override
    public void onClick(View v) {
        Intent miIntent;
        switch (v.getId()) {
            case R.id.transfer:
                miIntent = new Intent(DashBoard.this, Transfer.class);
                break;
            case R.id.history:
                miIntent = new Intent(DashBoard.this, History.class);
                break;
            case R.id.service:
                miIntent = new Intent(DashBoard.this, Service.class);
                break;
            case R.id.map:
                miIntent = new Intent(DashBoard.this, Map.class);
                break;
            default:
                return;
        }
        miIntent.putExtra("client_id", id);
        startActivityForResult(miIntent, 1);

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateValance();
    }
    private void updateValance(){
        final DB_Controller control = new DB_Controller(this);
        final SQLiteDatabase db = control.getReadableDatabase();
        if (db != null) {
            Cliente cliente = control.getClientById(db, id);
            txtTitleDash.setText("Welcome " + cliente.getName());
            txtDeb1.setText("$" + String.format("%,.2f",cliente.getBalanceDeb1()));
            txtDeb2.setText("$" + String.format("%,.2f",cliente.getBalanceDeb2()));
            txtCre1.setText("$" + String.format("%,.2f",cliente.getBalanceCre1()));
        }
    }
}
