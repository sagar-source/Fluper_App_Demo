package com.example.fluperapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.fluperapp.Adapter.ProductAdapter;
import com.example.fluperapp.Room.Product;
import com.example.fluperapp.ViewModel.ProductViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductViewModel mProductViewModel;

    private RecyclerView recyclerView;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        mProductViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> productList) {
                adapter = new ProductAdapter(MainActivity.this, productList);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);

            }
        });
    }
}
