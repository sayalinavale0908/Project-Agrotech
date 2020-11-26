package com.example.kishor.farmersbid.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.kishor.farmersbid.R;

/**
 * Created by kishor on 09-03-2018.
 */

public class Consumer_win_list extends AppCompatActivity {

    ListView listView;
    ListAdapter_consumer_win adapter_consumer_win_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_product);
        listView = (ListView)findViewById(R.id.product_listseed_consumer_winer);

        adapter_consumer_win_p= new ListAdapter_consumer_win(BidList.list_win,getApplicationContext());
        listView.setAdapter(adapter_consumer_win_p);

    }
}
