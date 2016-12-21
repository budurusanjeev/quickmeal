package com.yitsol.quickmeal.utilities;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yitsol.quickmeal.domain.AddressDO;
import com.yitsol.quickmeal.domain.OrderNowDO;

import java.util.ArrayList;

public class CartDatabase {

    public static final boolean DEBUG = true;

    /********************
     * Logcat TAG
     ************/
    public static final String LOG_TAG = "CARTDATABASE";

    /***************
     * Address Table Fields
     ******************/
    public static final String Column_custId = "custId";
    public static final String Column_addressId = "addressId";
    public static final String Column_city = "city";
    public static final String Column_landmark = "landmark";
    public static final String Column_zipcode = "zipcode";
    public static final String Column_houseNo = "houseNo";
    public static final String Column_address = "address";
    public static final String Column_addressType = "addressType";


    /*******************
     * OrderList Table
     *************************/

    public static final String Column_itemid = "itemId";
    public static final String Column_Ordertype = "Ordertype";
    public static final String Column_MealTime = "MealTime";
    public static final String Column_MealType = "MealType";
    public static final String Column_PackageType_SC = "PackageType_SC";
    public static final String Column_PackageTypeID = "PackageTypeID";
    public static final String Column_PackageName_SGD = "PackageName_SGD";
    public static final String Column_Quantity = "Quantity";
    public static final String Column_PackagePrice = "PackagePrice";
    public static final String Column_PackageDesc = "PackageDesc";
    public static final String Column_PackageName = "PackageName";


    /*********************
     * Installation Check
     *******************/
    public static final String Column_id = "id";
    public static final String Column_state = "state";

    /********************
     * Database Name
     ************/
    public static final String DATABASE_NAME = "spicebox1";

    /********************
     * Database Version (Increase one if want to also upgrade your database)
     ************/
    public static final int DATABASE_VERSION = 1;// started at 1

    /**
     * Table names
     */
    public static final String Table_Cartlist = "cartlist";
    public static final String Table_UserAddress = "userAddress";
    public static final String Table_InstallFlag = "install_Flag_Table";


    /********************
     * Set all table with comma seperated like USER_TABLE,ABC_TABLE
     ************/
    private static final String[] ALL_TABLES = {Table_Cartlist, Table_UserAddress};


    /**
     * Create table syntax
     */
    private static final String USER_CREATECARTLIST = "create table cartlist(itemId INTEGER, Ordertype TEXT,MealTime TEXT, MealType TEXT, PackageType_SC TEXT, PackageTypeID TEXT, PackageName_SGD TEXT, Quantity INTEGER, PackagePrice TEXT, PackageDesc TEXT, PackageName TEXT);";

    private static final String USER_Address = "create table userAddress(custId INTEGER, addressId TEXT, houseNo TEXT, address TEXT, landmark TEXT, city TEXT, zipcode TEXT, addressType TEXT);";

    private static final String APP_INSTALL = "create table install_Flag_Table(id INTEGER, state INTEGER);";

    /********************
     * Used to open database in syncronized way
     ************/
    private static DataBaseHelper DBHelper = null;

    protected CartDatabase() {
    }

    /*******************
     * Initialize database
     *************/
    public static void init(Context context) {
        if (DBHelper == null) {
            if (DEBUG)
                Log.i("DBAdapter", context.toString());
            DBHelper = new DataBaseHelper(context);
        }
    }

    /**********************
     * Open database for insert,update,delete in syncronized manner
     ********************/
    private static synchronized SQLiteDatabase open() throws SQLException {
        return DBHelper.getWritableDatabase();
    }

    /***********************
     * Escape string for single quotes (Insert,Update)
     ************/
    private static String sqlEscapeString(String aString) {
        String aReturn = "";

        if (null != aString) {
            //aReturn = aString.replace("'", "''");
            aReturn = DatabaseUtils.sqlEscapeString(aString);
            // Remove the enclosing single quotes ...
            aReturn = aReturn.substring(1, aReturn.length() - 1);
        }

        return aReturn;
    }

    /***********************
     * UnEscape string for single quotes (show data)
     ************/
    private static String sqlUnEscapeString(String aString) {

        String aReturn = "";

        if (null != aString) {
            aReturn = aString.replace("''", "'");
        }

        return aReturn;
    }

    /**
     * All Operations (Create, Read, Update, Delete)
     */
    public static void addCartData(OrderNowDO orderNowDO) {
        final SQLiteDatabase db = open();
        /*---------------Columns of table-------------------------------
        itemId Ordertype MealTime PackageType_SC PackageTypeID PackageName_SGD Quantity PackagePrice PackageDesc PackageName*/
        String itemId = orderNowDO.itemId;
        String Ordertype = orderNowDO.orderType;
        String MealTime = orderNowDO.mealTime;
        String MealType = orderNowDO.mealType;
        String PackageType_SC = orderNowDO.pkgTpye_S_C;
        String PackageTypeID = orderNowDO.pkgId;
        String PackageName_SGD = orderNowDO.pkgTpye_G_S_D;
        int Quantity = orderNowDO.quantity;
        double PackagePrice = orderNowDO.pkg_Price;
        String PackageDesc = orderNowDO.pkg_Desc;
        String PackageName = orderNowDO.pkg_Name;

//========================================================================================================================
        ContentValues cv = new ContentValues();
        cv.put(Column_itemid, itemId);
        cv.put(Column_Ordertype, Ordertype);
        cv.put(Column_MealTime, MealTime);
        cv.put(Column_MealType, MealType);
        cv.put(Column_PackageType_SC, PackageType_SC);
        cv.put(Column_PackageTypeID, PackageTypeID);
        cv.put(Column_PackageName_SGD, PackageName_SGD);
        cv.put(Column_Quantity, Quantity);
        cv.put(Column_PackagePrice, PackagePrice);
        cv.put(Column_PackageDesc, PackageDesc);
        cv.put(Column_PackageName, PackageName);
        db.insert(Table_Cartlist, null, cv);
        db.close(); // Closing database connection
    }

    //*****************************************************************************
    public static void SaveInstallState(int id, int state) {
        final SQLiteDatabase db = open();

        ContentValues value = new ContentValues();
        value.put(Column_id, id);
        value.put(Column_state, state);
        db.insert(Table_InstallFlag, null, value);
        db.close(); // Closing database connection

    }

    //*************update Address

/*
    public static int updateAddress(AddressDO addressDO)
    {
        final SQLiteDatabase db = open();

        int custId = addressDO.custId;
        //int addressId = addressDO.addressId;
        int cityId = addressDO.cityId;
        String cityName = sqlEscapeString(addressDO.cityName);
        String pincode = sqlEscapeString(addressDO.pincode);
        String houseNo = sqlEscapeString(addressDO.houseNo);
        String address = sqlEscapeString(addressDO.address);

        ContentValues cv = new ContentValues();
        cv.put(Column_custId, custId);
        cv.put(Column_cityId, cityId);
       // cv.put(Column_addressId, addressId);
        cv.put(Column_cityName, cityName);
        cv.put(Column_pincode, pincode);
        cv.put(Column_houseNo, houseNo);
        cv.put(Column_address, address);

        // updating row
        return db.update(Table_UserAddress, cv, Column_addressId + " = ?",new String[] { String.valueOf(addressDO.addressId)});
      //  db.close(); // Closing database connection
    }
*/

//************************* Install Flag **************************************

    public static int getInstallstatecount() {
        SQLiteDatabase db = open();
        Cursor cursor;
        cursor = db.rawQuery("SELECT count(*) FROM " + Table_InstallFlag, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        cursor.close();
        return icount;
    }

    public static int getInstallstate(int id) {
        int notificationstate = -1;

        final SQLiteDatabase db = open();
        Cursor cursor = db.query(Table_InstallFlag, new String[]{Column_id, Column_state}, Column_id + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

                notificationstate = cursor.getInt(1);
            } while (cursor.moveToNext());
        }

        return notificationstate;
    }

    public static int updateInstallourstate(int id, int state) {
        final SQLiteDatabase db = open();

        ContentValues values = new ContentValues();
        values.put(Column_state, state);

        // updating row
        return db.update(Table_InstallFlag, values, Column_id + " = ?", new String[]{String.valueOf(id)});
    }

    //**************************Address***********************************
    public static void addUserAddress(AddressDO addressDO) {
        final SQLiteDatabase db = open();


        String addressId = sqlEscapeString(addressDO.addressId);
        String houseNo = sqlEscapeString(addressDO.houseNo);
        String address = sqlEscapeString(addressDO.address);
        String landmark = sqlEscapeString(addressDO.landmark);
        String customerId = sqlEscapeString(addressDO.customerId);
        String city = sqlEscapeString(addressDO.city);
        String zipcode = sqlEscapeString(addressDO.zipcode);
        String addressType = sqlEscapeString(addressDO.addressType);


        ContentValues cv = new ContentValues();
        cv.put(Column_custId, customerId);
        cv.put(Column_addressId, addressId);
        cv.put(Column_houseNo, houseNo);
        cv.put(Column_address, address);
        cv.put(Column_landmark, landmark);
        cv.put(Column_city, city);
        cv.put(Column_zipcode, zipcode);
        cv.put(Column_addressType, addressType);

        db.insert(Table_UserAddress, null, cv);
        db.close(); // Closing database connection

    }

    //***********************************************************************************

    public static int getAddressListCount() {
        SQLiteDatabase db = open();
        Cursor cursor;
        cursor = db.rawQuery("SELECT count(*) FROM " + Table_UserAddress, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        cursor.close();
        return icount;
    }

    // Getting All Cartlist
    public static ArrayList<OrderNowDO> getAllCartList() {
        ArrayList<OrderNowDO> cartList = new ArrayList<OrderNowDO>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + Table_Cartlist;

        final SQLiteDatabase db = open();
        Cursor cursor = db.rawQuery(selectQuery, null);
/*itemId Ordertype MealTime PackageType_SC PackageTypeID PackageName_SGD Quantity PackagePrice PackageDesc PackageName*/
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                OrderNowDO data = new OrderNowDO();
                data.itemId = cursor.getString(0);
                data.orderType = cursor.getString(1);
                data.mealTime = cursor.getString(2);
                data.mealType = cursor.getString(3);
                data.pkgTpye_S_C = cursor.getString(4);
                data.pkgId = cursor.getString(5);
                data.pkgTpye_G_S_D = cursor.getString(6);
                data.quantity = cursor.getInt(7);
                data.pkg_Price = cursor.getDouble(8);
                data.pkg_Desc = cursor.getString(9);
                data.pkg_Name = cursor.getString(10);
                // Adding contact to list
                cartList.add(data);
            } while (cursor.moveToNext());
        }
        // return cartlist list
        return cartList;
    }

    public static ArrayList<AddressDO> getAddressById(String addressType) {
        ArrayList<AddressDO> addressList = new ArrayList<>();
        final SQLiteDatabase db = open();
        String query = "select * from userAddress where addressType=" + "'" + addressType + "'";
        Cursor cursor = db.rawQuery(query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddressDO data = new AddressDO();
                data.customerId = cursor.getString(0);
                data.addressId = cursor.getString(1);
                data.houseNo = cursor.getString(2);
                data.address = cursor.getString(3);
                data.landmark = cursor.getString(4);
                data.city = cursor.getString(5);
                data.zipcode = cursor.getString(6);
                data.addressType = cursor.getString(7);
                addressList.add(data);
            } while (cursor.moveToNext());
        }
        return addressList;
    }

    public static ArrayList<AddressDO> getAllAddressById(Integer addressId) {
        ArrayList<AddressDO> addressList = new ArrayList<AddressDO>();
        final SQLiteDatabase db = open();
        String selectQuery = "SELECT  * FROM " + Table_UserAddress;
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddressDO data = new AddressDO();
                data.customerId = cursor.getString(0);
                data.addressId = cursor.getString(1);
                data.houseNo = cursor.getString(2);
                data.address = cursor.getString(3);
                data.landmark = cursor.getString(4);
                data.city = cursor.getString(5);
                data.zipcode = cursor.getString(6);
                data.addressType = cursor.getString(7);
                addressList.add(data);
            } while (cursor.moveToNext());
        }
        return addressList;
    }

    /*
        public static ArrayList<String> getAllColorlist(String ItemId) {
            ArrayList<String> colorList = new ArrayList<String>();
            final SQLiteDatabase db = open();
            String query="select colorname from colorlist where itemid="+"'"+ItemId+"'";
            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    String data = cursor.getString(0);
                    colorList.add(data);
                } while (cursor.moveToNext());
            }

            // return cartlist list
            return colorList;
    */
    // Updating single item in cartlist
  /*      public static int updateCartlist(OrderNowDO data) {
            final SQLiteDatabase db = open();

            ContentValues values = new ContentValues();
            values.put(Column_mealTimeId, data.mealTimeId);
            values.put(Column_mealTypeId, data.mealTypeId);
            values.put(Column_foodTypeId, data.foodTypeId);
            values.put(Column_weekDurId, data.weekDurId);
            values.put(Column_packageDurId, data.packageDurId);
            values.put(Column_addressType, data.addressType);
            values.put(Column_quantity, data.quantity);
            // updating row
            return db.update(Table_Cartlist, values, Column_itemid + " = ?",new String[] { data.ItemId });
        }

       public static int updateQuantityInCartlist(ItemCartsDO data,int quantity) {
            final SQLiteDatabase db = open();

            ContentValues values = new ContentValues();
            values.put(Column_itemqty, quantity);

            // updating row
            return db.update(Table_Cartlist, values, Column_itemid + " = ?",new String[] { data.ItemId });
        }
*/
    // Deleting single item in cartlist
    public static void deletecartlist(OrderNowDO data) {
        final SQLiteDatabase db = open();
        //   db.delete(Table_Cartlist, Column_itemid + " = ?", new String[]{String.valueOf(data.itemId)});

        String delete_blauky = "DELETE  from cartlist where itemId=" + data.itemId;
        db.execSQL(delete_blauky);

        db.close();
    }

    public static void clearDatabase() {
        SQLiteDatabase db = open();
        db.execSQL("delete from " + Table_Cartlist);
    }

    public static void clearAddresses() {
        SQLiteDatabase db = open();
        db.execSQL("delete from " + Table_UserAddress);
    }

    public static ArrayList<AddressDO> checkAddressById(String addressId) {
        final SQLiteDatabase db = open();
        //   db.delete(Table_Cartlist, Column_itemid + " = ?", new String[]{String.valueOf(data.itemId)});
        ArrayList<AddressDO> addressDOList = new ArrayList<>();

        String query = "select address from userAddress where addressId=" + "'" + addressId + "'";
        Cursor cursor = db.rawQuery(query, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddressDO addressDO = new AddressDO();
                String data = cursor.getString(0);
                addressDO.address = data;
                addressDOList.add(addressDO);
            } while (cursor.moveToNext());
        }
        return addressDOList;
    }

    // Getting cartlist Count
    public static int getCartlistCount() {
        SQLiteDatabase db = open();
        Cursor cursor;
        cursor = db.rawQuery("SELECT count(*) FROM " + Table_Cartlist, null);
        cursor.moveToFirst();
        int icount = cursor.getInt(0);
        cursor.close();
        return icount;
    }

    /**********************
     * Main Database creation INNER class
     ********************/
    private static class DataBaseHelper extends SQLiteOpenHelper {
        public DataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            if (DEBUG)
                Log.i(LOG_TAG, "new create");
            try {
                db.execSQL(USER_CREATECARTLIST);
                db.execSQL(USER_Address);
                db.execSQL(APP_INSTALL);

            } catch (Exception exception) {
                if (DEBUG)
                    Log.i(LOG_TAG, "Exception onCreate() exception");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (DEBUG)
                Log.w(LOG_TAG, "Upgrading database from version" + oldVersion
                        + "to" + newVersion + "...");

            for (String table : ALL_TABLES) {
                db.execSQL("DROP TABLE IF EXISTS " + table);
            }
            onCreate(db);
        }

    } // Inner class closed

}



