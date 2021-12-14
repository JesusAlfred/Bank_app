package com.example.bank;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Service  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        final DB_Controller control = new DB_Controller(this);
        final SQLiteDatabase db = control.getReadableDatabase();

        int id = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getInt("client_id");
        } else {
            finish();
        }
        Button pay = findViewById(R.id.pay);
        Spinner fromToPay = findViewById(R.id.fromToPay);
        Spinner service = findViewById(R.id.listOfServices);
        TextView txtMount = findViewById(R.id.payMount);

        String[] items = new String[]{"Deb1", "Deb2", "Cre1"};
        String[] items2 = new String[]{"Electricity", "Water", "Internet", "Tv", "Taxes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        fromToPay.setAdapter(adapter);
        service.setAdapter(adapter2);
        int finalId = id;
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float mount;
                try {
                    mount = Float.parseFloat(txtMount.getText().toString());
                }catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error in the input", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mount > 0){
                    float pullFrom = 0, destTo = 0;
                    Cliente C = control.getClientById(db, finalId);
                    //Check if the sourse have enough founds
                    if (fromToPay.getSelectedItemPosition() == 0){ pullFrom = C.getBalanceDeb1(); }
                    if (fromToPay.getSelectedItemPosition() == 1){ pullFrom = C.getBalanceDeb2(); }
                    if (fromToPay.getSelectedItemPosition() == 2){ pullFrom = C.getBalanceCre1(); }

                    if(pullFrom < mount){
                        Toast.makeText(getApplicationContext(),"No enough founds in" + items[fromToPay.getSelectedItemPosition()],Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (fromToPay.getSelectedItemPosition() == 0){ C.setBalanceDeb1(C.getBalanceDeb1() - mount); }
                    if (fromToPay.getSelectedItemPosition() == 1){ C.setBalanceDeb2(C.getBalanceDeb2() - mount); }
                    if (fromToPay.getSelectedItemPosition() == 2){ C.setBalanceCre1(C.getBalanceCre1() - mount); }

                    //Update the fields in the data base
                    control.upDateMounts(db, C);
                    //Save to the history
                    control.addMovement(db, C, "Service", mount, "Payment of the service " + items2[service.getSelectedItemPosition()]);
                    finish();

                } else {
                    Toast.makeText(getApplicationContext(),"Enter more than 0 or positive values",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
