package com.example.no0ne.inventoryapp;

import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.no0ne.inventoryapp.Data.ProductContract.ProductEntry;

import java.io.ByteArrayOutputStream;

public class CatalogActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int PRODUCT_LOADER = 0;

    ProductCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });

        ListView productListView = (ListView) findViewById(R.id.list);
        View emptyView = findViewById(R.id.empty_view);

        productListView.setEmptyView(emptyView);

        mCursorAdapter = new ProductCursorAdapter(this, null);

        productListView.setAdapter(mCursorAdapter);

        productListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CatalogActivity.this, EditorActivity.class);
                Uri currentProductUri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI, id);
                intent.setData(currentProductUri);
                startActivity(intent);
                Log.d("***NOTICE***", ""+ProductEntry.CONTENT_URI);
            }
        });

        getLoaderManager().initLoader(PRODUCT_LOADER, null, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_insert_dummy_data:
                //insertProduct();
                return true;
            case R.id.action_delete_all_product:
                deleteAllProducts();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void insertProduct() {
        /*Uri imageUri = Uri.parse("android.resource://" + getPackageName() + "/drawable/" + R.drawable.seven);

        byte[] inputData = null;
        InputStream inputStream;

        try {
            inputStream = getContentResolver().openInputStream(imageUri);
            inputData = Utils.getBytes(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.seven);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imageUri = outputStream.toByteArray();

        Log.e("***NOTICE***", String.valueOf(imageUri));

        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_PRODUCT_NAME, "Chips");
        values.put(ProductEntry.COLUMN_PRODUCT_DESCRIPTION, "Potato");
        values.put(ProductEntry.COLUMN_PRODUCT_QUANTITY, 50);
        values.put(ProductEntry.COLUMN_PRODUCT_PRICE, 15);
        values.put(ProductEntry.COLUMN_PRODUCT_IMAGE, imageUri);

        Uri uri = getContentResolver().insert(ProductEntry.CONTENT_URI, values);
    }

    private void deleteAllProducts() {

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {ProductEntry._ID,
                ProductEntry.COLUMN_PRODUCT_NAME,
//                ProductEntry.COLUMN_PRODUCT_DESCRIPTION };
                ProductEntry.COLUMN_PRODUCT_QUANTITY,
                ProductEntry.COLUMN_PRODUCT_PRICE };

        return new CursorLoader(this, ProductEntry.CONTENT_URI, projection, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }
}
