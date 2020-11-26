package com.example.kishor.farmersbid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.kishor.farmersbid.R;

/**
 * Created by kishor on 09-03-2018.
 */

public class consumer_history extends AppCompatActivity  {

    ListView listView;
    ListAdapter_consumer_history history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_history);
        listView = (ListView)findViewById(R.id.product_listseed_consumer);

        history = new ListAdapter_consumer_history(BidList.list_history,getApplicationContext());
        listView.setAdapter(history);

    }
}
