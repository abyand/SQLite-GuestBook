package com.example.abyandafa.guestbook;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

public class MainActivity extends AppCompatActivity {

    String[] daftar;
    ListView list;
    private Button add;

    protected Cursor cursor;
    DatabaseConnector dbcenter;
    public static MainActivity ma;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add = (Button) findViewById(R.id.add1);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(MainActivity.this, Buat_Biodata.class);
                startActivity(inten);
            }
        });

        ma = this;
        dbcenter = new DatabaseConnector(this);
        refresh();



    }

    public void refresh() {
        SQLiteDatabase db =dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * from tamu", null);

        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();

        for(int cc = 0; cc<cursor.getCount(); cc++)
        {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }

        list = (ListView) findViewById(R.id.daftar1);

        list.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));

        list.setSelected(true);
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogItem = {"Lihat tamu", "Update tamu", "Hapus tamu"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which)
                        {
                            case 0:
                                Intent go = new Intent(getApplicationContext(), Lihat_biodata.class);
                                go.putExtra("name", selection);
                                startActivity(go);
                                break;
                            case 1:
                                Intent up = new Intent(getApplicationContext(), update_tamu.class);
                                up.putExtra("name", selection);
                                startActivity(up);
                                break;
                            case 2:
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from tamu where nama = '"+selection+"'");
                                refresh();
                                break;

                        }
                    }
                });
                builder.create().show();

            }
        });
        ((ArrayAdapter)list.getAdapter()).notifyDataSetInvalidated();


    }

}
