package com.example.abyandafa.guestbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Lihat_biodata extends AppCompatActivity {

    protected Cursor cursor;
    private DatabaseConnector dbHelper;
    private Button kembali;
    private TextView no1, nama1, jk1, alamat1, keterangan1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_biodata);

        dbHelper = new DatabaseConnector(this);
        no1 = (TextView) findViewById(R.id.nomor1);
        nama1 =(TextView) findViewById(R.id.nama1);
        jk1 = (TextView) findViewById(R.id.jk1);
        alamat1 = (TextView) findViewById(R.id.alamat1);
        keterangan1 = (TextView) findViewById(R.id.ket1);
        kembali = (Button) findViewById(R.id.kembali);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM tamu WHERE nama = '" +
                getIntent().getStringExtra("name") + "'",null);

        cursor.moveToFirst();

        if(cursor.getCount()>0)
        {

            cursor.moveToPosition(0);
            no1.setText(cursor.getString(0).toString());
            nama1.setText(cursor.getString(1).toString());
            jk1.setText(cursor.getString(2).toString());
            alamat1.setText(cursor.getString(3).toString());
            keterangan1.setText(cursor.getString(4).toString());
        }
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
