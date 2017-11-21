package com.example.abyandafa.guestbook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Buat_Biodata extends AppCompatActivity {

    private EditText no, nama, jk, alamat, ket;
    private Button tambah;
    protected Cursor cursor;
    DatabaseConnector dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat__biodata);
        dbHelper = new DatabaseConnector(this);

        no = (EditText)findViewById(R.id.nomor);
        nama = (EditText)findViewById(R.id.nama);
        jk = (EditText)findViewById(R.id.jk);
        alamat = (EditText)findViewById(R.id.alamat);
        ket = (EditText)findViewById(R.id.ket);
        tambah = (Button) findViewById(R.id.tambah);


        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into tamu(no, nama, jk, alamat, keterangan) values('" +
                        no.getText().toString() + "','" +
                        nama.getText().toString() + "','" +
                        jk.getText().toString() + "','" +
                        alamat.getText().toString() + "','" +
                        ket.getText().toString() + "')");

                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.refresh();
                finish();

            }
        });

    }
}
