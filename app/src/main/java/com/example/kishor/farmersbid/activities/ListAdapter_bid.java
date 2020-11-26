package com.example.kishor.farmersbid.activities;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.kishor.farmersbid.Helper.Product;

import java.util.List;

/**
 * Created by kishor on 17-02-2018.
 */

public class ListAdapter_bid extends ArrayAdapter<Product> {

    private static List<Product> BidtList;

    public ListAdapter_bid(Context context, int resource) {
        super(context, resource);
    }
}
