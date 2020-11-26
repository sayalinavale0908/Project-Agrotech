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

public class ListViewAdapter extends ArrayAdapter<Product> {

    private static  List<Product> ProductList;

    private Context mCtx;

    public ListViewAdapter(List<Product> productList, Context applicationContext) {
        super(applicationContext,R.layout.list_item,productList);
        this.mCtx=applicationContext;
        this.ProductList=productList;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        ViewHolder viewHolder = new ViewHolder();
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);

            //creating a view with our xml layout
            convertView = inflater.inflate(R.layout.list_item, null, true);

            //getting text views
            viewHolder.productcat = convertView.findViewById(R.id.text_pro_cat);
            viewHolder.productname = convertView.findViewById(R.id.pro_name11);
            viewHolder.productQ = convertView.findViewById(R.id.pro_price);
            viewHolder.productbidP = convertView.findViewById(R.id.text_b_price);
            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        Product pro = ProductList.get(position);
        viewHolder.productcat.setText(pro.getPcategory());
        viewHolder.productname.setText(pro.getPname());
        viewHolder.productQ.setText(pro.getPquntity());
        viewHolder.productbidP.setText(pro.getPbidprice());

        //returning the listitem
        return convertView;
    }

    static class ViewHolder{
        TextView productcat;
        TextView productname;
        TextView productQ;
        TextView productbidP;
    }
}
