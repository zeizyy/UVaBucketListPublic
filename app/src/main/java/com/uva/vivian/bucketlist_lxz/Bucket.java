package com.uva.vivian.bucketlist_lxz;

/**
 * Created by ian_zheng on 2/4/16.
 * .
 */
public class Bucket {
    private String title;
    private int checked;
    private int id;
    private String description;

    public Bucket(String title, String description) {
        this.id = -1;
        this.checked = 0;
        this.title = title.trim();
        this.description = description.trim();
    }

    public Bucket(int id, int checked, String title, String description) {
        this.title = title.trim();
        this.checked = checked;
        this.id = id;
        this.description = description.trim();
    }

    public String getTitle() {
        return this.title;
    }

    public int getChecked() {
        return this.checked;
    }

    public boolean isChecked() {
        return this.checked != 0;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {this.title = title;}

    public void setDescription(String description) {this.description = description;}

    public void setChecked(boolean checked) {
        this.checked = checked ? 1 : 0;
    }

    public String getDescription() {
        return description;
    }
}
