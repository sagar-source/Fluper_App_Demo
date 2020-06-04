package com.example.fluperapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.fluperapp.Adapter.ProductAdapter;
import com.example.fluperapp.Room.Product;
import com.example.fluperapp.ViewModel.ProductViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ProductViewModel mProductViewModel;

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private ProductAdapter adapter;
    private int index = 0;
    private String[] nameArray, descArray;
    private int[] regArray, saleArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameArray = getResources().getStringArray(R.array.name_array);
        descArray = getResources().getStringArray(R.array.desc_array);
        regArray = getResources().getIntArray(R.array.reg_price_array);
        saleArray = getResources().getIntArray(R.array.sale_price_array);

        floatingActionButton = findViewById(R.id.floating_button_add);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton.setOnClickListener(this);

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

    @Override
    public void onClick(View v) {

        mProductViewModel.insert(new Product(nameArray[index],descArray[index],regArray[index],saleArray[index]));

        index = index + 1;

        if(index >= nameArray.length) {
            index = 0;
        }
    }
}
