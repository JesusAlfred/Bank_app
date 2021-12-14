package com.example.bank;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {
    ListView lista;
    List<String> labels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        int id = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            id = extras.getInt("client_id");
        } else {
            finish();
        }

        final DB_Controller control = new DB_Controller(this);
        final SQLiteDatabase db = control.getReadableDatabase();

        lista = (ListView) findViewById(R.id.list);

        labels.clear();
        List<TransactionElement> Transaction = control.getTransactionById(db, id);

        for(TransactionElement T:Transaction){
            labels.add("Date: " + T.getDate() + ". type: " + T.getType() + ". mount: $" + T.getMount() + ". comment: " + T.getComment());
        }
        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, labels);
        lista.setAdapter(adaptador);
    }
}
