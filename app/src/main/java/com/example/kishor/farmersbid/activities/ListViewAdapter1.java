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

public class ListViewAdapter1  extends ArrayAdapter<Product> {

    private List<Product> ProductList;
    private Context mCtx;

    public ListViewAdapter1(List<Product> list, Context applicationContext) {

        super(applicationContext, R.layout.list_item_see_con,list);
        this.mCtx=applicationContext;
        this.ProductList=list;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
     ViewHolder viewHolder =new ViewHolder();
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        convertView= inflater.inflate(R.layout.list_item_see_con, null, true);

        //getting text views
        viewHolder.productcat =  convertView.findViewById(R.id.text_pro_cat);
        viewHolder.productname =  convertView.findViewById(R.id.pro_name11);
            viewHolder.productQ =  convertView.findViewById(R.id.pro_price);
            viewHolder.productbidP =  convertView.findViewById(R.id.text_b_price);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }


        //Getting the hero for the specified position
        Product pro = ProductList.get(position);

        //setting hero values to textviews
            viewHolder.productcat.setText(pro.getPcategory());
            viewHolder.productbidP.setText(pro.getPbidprice());
            viewHolder.productQ.setText(pro.getPquntity());
            viewHolder.productname.setText(pro.getPname());


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
