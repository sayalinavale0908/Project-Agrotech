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
 * Created by kishor on 05-02-2018.
 */

public class ListViewAdapter2_His extends ArrayAdapter<Product> {

    List<Product> products1;
    int i=1;
    private Context mCtx;

    public ListViewAdapter2_His(List<Product> products, Context applicationContext) {
        super(applicationContext, R.layout.list_one_product,products);
        this.mCtx=applicationContext;
        this.products1=products;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        ListViewAdapter2_His.ViewHolder viewHolder1 = new ListViewAdapter2_His.ViewHolder();
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);

            //creating a view with our xml layout
            convertView = inflater.inflate(R.layout.list_one_product, null, true);

            viewHolder1.no =convertView.findViewById(R.id.no);
            viewHolder1.consumername=convertView.findViewById(R.id.consumer_name_l);
            viewHolder1.bidprice=convertView.findViewById(R.id.consumer_bid_price);
            convertView.setTag(viewHolder1);
        }else{
            viewHolder1 = (ListViewAdapter2_His.ViewHolder)convertView.getTag();
        }
        Product pro = products1.get(position);
        i=position;
        viewHolder1.no.setText(String.valueOf(i+1));
        viewHolder1.consumername.setText(pro.getConsumer_Name());
        viewHolder1.bidprice.setText(pro.getNew_bid_price());


     return  convertView;
    }
    static class ViewHolder {
        TextView no;
        TextView consumername;
        TextView bidprice;
    }

}
