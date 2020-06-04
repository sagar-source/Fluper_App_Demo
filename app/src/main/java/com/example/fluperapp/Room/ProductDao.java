package com.example.fluperapp.Room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();

    @Insert
    void insert(Product task);

    @Delete
    void delete(Product task);

    @Update
    void update(Product task);
}
