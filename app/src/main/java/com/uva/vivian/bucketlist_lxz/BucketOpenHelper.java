package com.uva.vivian.bucketlist_lxz;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by ian_zheng on 2/4/16.
 * .
 */
public class BucketOpenHelper extends SQLiteOpenHelper {
    private final Context fContext;
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_THING = "thing";
    public static final String KEY_FLAG = "flag";
    public static final String KEY_DES = "description";
    public static final String DATABASE_NAME = "BucketDB.db";
    private static final String BUCKET_TABLE_NAME = "bucket";
    private static final String BUCKET_TABLE_CREATE =
            "CREATE TABLE " + BUCKET_TABLE_NAME + " (id INTEGER PRIMARY KEY, " +
                    KEY_THING + " TEXT, " +
                    KEY_FLAG + " INT, " + KEY_DES + " TEXT);";

    BucketOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        fContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BUCKET_TABLE_CREATE);
        init(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS bucket");
        onCreate(db);
    }

    public void init(SQLiteDatabase db) {
        Resources res = fContext.getResources();
        // read csv file to create an ArrayList<Bucket>. Bucket (String thing, int flag)
        InputStream inputStream = res.openRawResource(R.raw.uva111things);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        String[] split;
        String thing;
        String des;
        int flag;
        ContentValues contentValues = new ContentValues();
        try {
            while ((line = reader.readLine()) != null) {
                split = line.split("\t");
                thing = split[0];
                flag = Integer.parseInt(split[1]);
                des = split[2];
                contentValues.put(KEY_THING, thing);
                contentValues.put(KEY_FLAG, flag);
                contentValues.put(KEY_DES, des);
                db.insert(BUCKET_TABLE_NAME, null, contentValues);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean insertBucket(Bucket bucket) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_THING, bucket.getTitle());
        contentValues.put(KEY_FLAG, bucket.getChecked());
        contentValues.put(KEY_DES, bucket.getDes());
        db.insert(BUCKET_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean setFlag(int id, boolean isChecked) {
        SQLiteDatabase db_w = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        int flag = (isChecked) ? 1 : 0;
        contentValues.put(KEY_FLAG, flag);
        int num = db_w.update(BUCKET_TABLE_NAME, contentValues, "id = ? ", new String[]{Integer.toString(id)});
        Log.i("DB setFlag()", "Flag of " + id + " set to " + flag);
        return num > 0;
    }

    public boolean removeBucket(int id) {
        SQLiteDatabase db_w = this.getWritableDatabase();
        int num = db_w.delete(BUCKET_TABLE_NAME, "id = ? ", new String[]{Integer.toString(id)});
        Log.i("DB removeBucket()", "id:" + id);
        return num > 0;
    }

    public Bucket getBucket(int id) {
        SQLiteDatabase db_r = this.getReadableDatabase();
        Cursor res = db_r.rawQuery("select * from " + BUCKET_TABLE_NAME + " where id = ? ", new String[]{id + ""});
        res.moveToFirst();
        int checked = res.getInt(res.getColumnIndex(KEY_FLAG));
        String title = res.getString(res.getColumnIndex(KEY_THING));
        String des = res.getString(res.getColumnIndex(KEY_DES));
        res.close();
        return new Bucket(id, checked, title, des);
    }


    public ArrayList<Bucket> getAllBuckets() {
        ArrayList<Bucket> things = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + BUCKET_TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            String title = res.getString(res.getColumnIndex(KEY_THING));
            int checked = res.getInt(res.getColumnIndex(KEY_FLAG));
            int id = res.getInt(res.getColumnIndex("id"));
            String des = res.getString(res.getColumnIndex(KEY_DES));
            Bucket bucket = new Bucket(id, checked, title, des);
            things.add(bucket);
            res.moveToNext();
        }
        res.close();
        return things;
    }


}
