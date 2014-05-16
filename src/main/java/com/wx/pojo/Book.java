package com.wx.pojo;

import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Shawn on 2014/5/16.
 */
public class Book {
    @Field
    private String id;
    @Field
    private String name;
    @Field("description")
    private String desc;
    @Field
    private String author;

    public Book() {
    }

    public Book(String id, String name, String desc, String author) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
