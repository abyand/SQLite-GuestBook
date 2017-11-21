package com.example.abyandafa.guestbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update_tamu extends AppCompatActivity {

    private EditText nama2, jk2, alamat2, ket2;
    private TextView no2;
    private Button tambah2, kembali2;
    protected Cursor cursor;
    DatabaseConnector dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tamu);

        dbHelper = new DatabaseConnector(this);

        nama2 = (EditText) findViewById(R.id.nama2);
        jk2 = (EditText) findViewById(R.id.jk2);
        alamat2 = (EditText) findViewById(R.id.alamat2);
        ket2 = (EditText) findViewById(R.id.ket2);
        no2 = (TextView) findViewById(R.id.nomor2);

        SQLiteDatabase db =dbHelper.getWritableDatabase();

        cursor = db.rawQuery("SELECT * FROM tamu WHERE nama = '" +
                getIntent().getStringExtra("name") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            no2.setText(cursor.getString(0).toString());
            nama2.setText(cursor.getString(1).toString());
            jk2.setText(cursor.getString(2).toString());
            alamat2.setText(cursor.getString(3).toString());
            ket2.setText(cursor.getString(4).toString());
        }

        tambah2 = (Button) findViewById(R.id.tambah2);
        kembali2 = (Button)findViewById(R.id.balik2);

        tambah2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update tamu set nama='"+
                        nama2.getText().toString() +"', jk='" +
                        jk2.getText().toString()+"', alamat='"+
                        alamat2.getText().toString() +"', keterangan='" +
                        ket2.getText().toString() + "' where no='" +
                        no2.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.refresh();
                finish();
            }
        });

        kembali2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }
}
