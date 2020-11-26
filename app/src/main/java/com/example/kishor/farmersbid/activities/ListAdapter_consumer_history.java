package com.example.kishor.farmersbid.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kishor.farmersbid.Helper.Product;
import com.example.kishor.farmersbid.R;

import java.util.List;

/**
 * Created by kishor on 10-03-2018.
 */

public class ListAdapter_consumer_history extends ArrayAdapter<Product> {

    List<Product> productList;
    private Context mCtx;

    public ListAdapter_consumer_history(List<Product> list, Context applicationContext) {
        super(applicationContext, R.layout.consumer_history_item,list);
        this.mCtx=applicationContext;
        this.productList=list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mCtx);

            convertView = inflater.inflate(R.layout.consumer_history_item,null,true);

            viewHolder.bidid =convertView.findViewById(R.id.text_win_p_id_h);
            viewHolder.date=convertView.findViewById(R.id.bid_date);
            viewHolder.consumerprice=convertView.findViewById(R.id.consumer_bid_p_a);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Product product = productList.get(position);

        viewHolder.bidid.setText(product.getBid());
        viewHolder.date.setText(product.getBidDate());
        viewHolder.consumerprice.setText(""+product.getNew_bid_price());

        return  convertView;
    }


    static class ViewHolder {
        TextView bidid;
        TextView date;
        TextView consumerprice;
    }
}
