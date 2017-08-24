package com.example.no0ne.inventoryapp.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by no0ne on 5/14/17.
 */
public final class ProductContract {

    public static final String CONTENT_AUTHORITY = "com.example.no0ne.inventoryapp";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_INVENTORY = "inventory";

    public ProductContract() {}

    public static abstract class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_INVENTORY);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" +
                CONTENT_AUTHORITY + "/" + PATH_INVENTORY;

        public static final String TABLE_NAME = "Inventory";

        public static final String _ID = BaseColumns._ID;

        public static final String COLUMN_PRODUCT_NAME = "Name";

        public static final String COLUMN_PRODUCT_DESCRIPTION = "Description";

        public static final String COLUMN_PRODUCT_QUANTITY = "Quantity";

        public static final String COLUMN_PRODUCT_PRICE = "Price";

        public static final String COLUMN_PRODUCT_IMAGE = "Image";
    }
}
