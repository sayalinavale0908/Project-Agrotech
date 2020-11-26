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
 * Created by kishor on 17-02-2018.
 */

public class ListAdapter_Win extends ArrayAdapter<Product> {

    List<Product> productList;
    private Context mCtx;

    public ListAdapter_Win(List<Product> list, Context applicationContext) {
        super(applicationContext, R.layout.list_one_product,list);
        this.mCtx=applicationContext;
        this.productList=list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ListAdapter_Win.ViewHolder viewHolder = new ListAdapter_Win.ViewHolder();

        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(mCtx);

            convertView = inflater.inflate(R.layout.list_one_product,null,true);

            viewHolder.no =convertView.findViewById(R.id.no);
            viewHolder.consumername=convertView.findViewById(R.id.consumer_name_l);
            viewHolder.bidprice=convertView.findViewById(R.id.consumer_bid_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ListAdapter_Win.ViewHolder)convertView.getTag();
        }

        Product product = productList.get(position);

        viewHolder.no.setText(product.getCid());
        viewHolder.consumername.setText(product.getConsumer_Name());
        viewHolder.bidprice.setText("                "+product.getNew_bid_price());

        return  convertView;
    }


    static class ViewHolder {
        TextView no;
        TextView consumername;
        TextView bidprice;
    }

}
