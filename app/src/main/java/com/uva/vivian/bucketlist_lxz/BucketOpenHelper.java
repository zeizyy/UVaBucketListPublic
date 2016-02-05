package com.uva.vivian.bucketlist_lxz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by ian_zheng on 2/4/16.
 */
public class BucketOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_THING = "thing";
    public static final String KEY_FLAG = "flag";
    public static final String DATABASE_NAME = "BucketDB.db";
    private static final String BUCKET_TABLE_NAME = "bucket";
    private static final String BUCKET_TABLE_CREATE =
            "CREATE TABLE " + BUCKET_TABLE_NAME + " (" +
                    KEY_THING + " TEXT, " +
                    KEY_FLAG + " INT);";

    BucketOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BUCKET_TABLE_CREATE);
        Log.i("DBzyy", BUCKET_TABLE_CREATE);
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("thing","Steak the Lawn.");
//        contentValues.put("flag", 0);
//        db.insert("bucket", null, contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bucket");
        onCreate(db);
    }

    public ArrayList<String> getAllThings() {
        ArrayList<String> things = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from bucket", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            String thing = res.getString(res.getColumnIndex(KEY_THING));
            things.add(thing);
            res.moveToNext();
        }
        return things;
    }

    public void init(ArrayList<Bucket> buckets) {
        for(Bucket bucket:buckets){
            insertBucket(bucket);
        }
    }

    public boolean insertBucket (Bucket bucket)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_THING, bucket.getThing());
        contentValues.put(KEY_FLAG, bucket.getFlag());
        db.insert(BUCKET_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Boolean> getAllFlags() {
        ArrayList<Boolean> flags = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from bucket", null);
        res.moveToFirst();

        while(!res.isAfterLast()) {
            int flag = res.getInt(res.getColumnIndex(KEY_FLAG));
            boolean bflag = (flag == 1);
            flags.add(bflag);
            res.moveToNext();
        }
        return flags;
    }

    public ArrayList<Bucket> getAllBuckets() {
        ArrayList<Bucket> things = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+BUCKET_TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            String thing = res.getString(res.getColumnIndex(KEY_THING));
            int flag = res.getInt(res.getColumnIndex(KEY_FLAG));
            Bucket bucket = new Bucket(thing, flag);
            things.add(bucket);
            res.moveToNext();
        }
        return things;
    }
}
