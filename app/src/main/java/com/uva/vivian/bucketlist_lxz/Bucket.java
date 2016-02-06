package com.uva.vivian.bucketlist_lxz;

/**
 * Created by ian_zheng on 2/4/16.
 * .
 */
public class Bucket {
    private String title;
    private int checked;
    private int id;

    public Bucket(int id, int checked, String title) {
        this.title = title;
        this.checked = checked;
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public int getChecked() {
        return this.checked;
    }

    public int toggleChecked() {
        this.checked = 1 - this.checked;
        return this.checked;
    }

    public int getId() {
        return id;
    }

    public void setChecked(boolean checked) {
        this.checked = checked ? 1 : 0;
    }
}
