package com.example.geolocationbasis;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.LinearLayout;
        import android.widget.ListView;
        import android.widget.SimpleAdapter;
        import android.widget.Toast;

        import java.util.HashMap;
        import java.util.LinkedList;


public class Tasks extends AppCompatActivity {
    ListView listView;
    LinearLayout linearLayout;
    Intent intent;
    int i = 0;
    int userId = 9999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks);

        listView = findViewById(R.id.storis);
        linearLayout = findViewById(R.id.my_linear);

        final LinkedList<DBMS> books = new LinkedList<>();
        books.add(new DBMS(1," Лёгкая прогулка ",">"));
        books.add(new DBMS(2," Остров Юность ",">" ));
        books.add(new DBMS(3, " Заброшенный бункер ",">"));

        LinkedList<HashMap<String, Object>> listBooks = new LinkedList<>();
        for (int j = 0; j < books.size(); j++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("namber", books.get(j).namber);
            map.put("name", books.get(j).name);
            map.put("s", books.get(j).s);
            listBooks.add(map);
        }

        String [] keys = {"namber", "name", "s"};
        int [] toID = {R.id.namber, R.id.name,R.id.s};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listBooks, R.layout.stories,
                keys, toID);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                intent = new Intent(Tasks.this, MapsActivity.class);
                startActivity(intent);
            }
        });


    }
}