package com.example.fluperapp.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.fluperapp.Repository.ProductRepository;
import com.example.fluperapp.Room.Product;

import java.util.List;

public class ProductViewModel extends AndroidViewModel {

    private ProductRepository mRepository;

    private LiveData<List<Product>> mAllProducts;

    public ProductViewModel (Application application) {
        super(application);
        mRepository = new ProductRepository(application);
        mAllProducts = mRepository.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() { return mAllProducts; }

    public void insert(Product word) { mRepository.insert(word); }
}
