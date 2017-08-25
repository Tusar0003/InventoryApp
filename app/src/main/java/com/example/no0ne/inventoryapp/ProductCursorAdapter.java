package com.example.no0ne.inventoryapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.no0ne.inventoryapp.Data.ProductContract.ProductEntry;
import com.example.no0ne.inventoryapp.Data.Utils;

/**
 * Created by no0ne on 5/22/17.
 */
public class ProductCursorAdapter extends CursorAdapter {

    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = (TextView) view.findViewById(R.id.name);
        TextView textViewQuantity = (TextView) view.findViewById(R.id.quantity);
        TextView textViewPrice = (TextView) view.findViewById(R.id.price);
        ImageView imageViewIcon = (ImageView) view.findViewById(R.id.list_item_icon);

        int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
        int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_QUANTITY);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int imageColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_IMAGE);

        String productName = cursor.getString(nameColumnIndex);
        String productQuantity = Integer.toString(cursor.getInt(quantityColumnIndex));
        String productPrice = Integer.toString(cursor.getInt(priceColumnIndex));
        byte[] productImage = cursor.getBlob(imageColumnIndex);

        textViewName.setText(productName);
        textViewQuantity.setText(productQuantity);
        textViewPrice.setText(productPrice);

        if (productImage != null) {
            Bitmap bitmap = Utils.getImage(productImage);
            imageViewIcon.setImageBitmap(bitmap);
        }
    }
}
