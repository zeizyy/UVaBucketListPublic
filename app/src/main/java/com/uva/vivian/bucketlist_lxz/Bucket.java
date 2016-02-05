package com.uva.vivian.bucketlist_lxz;

/**
 * Created by ian_zheng on 2/4/16.
 */
public class Bucket {
    private String thing;
    private int flag;

    public Bucket(String thing, int flag) {
        this.thing = thing;
        this.flag = flag;
    }

    public String getThing() {
        return this.thing;
    }

    public int getFlag() {
        return this.flag;
    }
}
