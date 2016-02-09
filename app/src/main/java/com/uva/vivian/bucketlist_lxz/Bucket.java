package com.uva.vivian.bucketlist_lxz;

/**
 * Created by ian_zheng on 2/4/16.
 * .
 */
public class Bucket {
    private String title;
    private int checked;
    private int id;
    private String des;

    public Bucket(String title, String des) {
        this.title = title;
        this.des = des;
    }

    public Bucket(int id, int checked, String title, String des) {
        this.title = title;
        this.checked = checked;
        this.id = id;
        this.des = des;
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

    public String getDes() { return des; }
}
