package sg.edu.rp.c346.id20025312.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btn5star;
    ListView lv;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        DBHelper dbh = new DBHelper(MainActivity2.this);

        btn5star = findViewById(R.id.btn5star);
        lv = findViewById(R.id.lvSongs);

        Intent i = getIntent();
        data = (Song) i .getSerializableExtra("data");

        al = new ArrayList<Song>();
        al.addAll(dbh.getAllSongs());
        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = al.get(position);

                Intent i = new Intent(MainActivity2.this, MainActivity3.class);
                i.putExtra("data", song);
                startActivity(i);
            }
        });

        btn5star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity2.this);
                al.clear();
                al.addAll(dbh.getAllSongs("5"));
                aa.notifyDataSetChanged();

            }
        });

    }

    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(MainActivity2.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
        dbh.close();

    }
}