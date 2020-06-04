package com.example.fluperapp.Room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Product implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "regular_price")
    private int regular_price;

    @ColumnInfo(name = "sale_price")
    private int sale_price;

    public Product(String name, String description, int regular_price, int sale_price) {
        this.name = name;
        this.description = description;
        this.regular_price = regular_price;
        this.sale_price = sale_price;
    }


    /*
     * Getters and Setters
     * */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRegular_price() {
        return regular_price;
    }

    public void setRegular_price(int regular_price) {
        this.regular_price = regular_price;
    }

    public int getSale_price() {
        return sale_price;
    }

    public void setSale_price(int sale_price) {
        this.sale_price = sale_price;
    }
}

