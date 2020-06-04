package com.example.fluperapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fluperapp.Room.Product;
import com.example.fluperapp.ViewModel.ProductViewModel;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText editTextName, editTextDesc, editTextRegPrice, editTextSalePrice;
    private ProductViewModel mProductViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        editTextName = findViewById(R.id.edt_name);
        editTextDesc = findViewById(R.id.edt_desc);
        editTextRegPrice = findViewById(R.id.edt_reg_price);
        editTextSalePrice = findViewById(R.id.edt_sale_price);

        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        final Product product = (Product) getIntent().getSerializableExtra("product");

        loadProduct(product);

        findViewById(R.id.button_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProduct(product);
            }
        });

        findViewById(R.id.button_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProductActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mProductViewModel.delete(product);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void loadProduct(Product product) {
        editTextName.setText(product.getName());
        editTextDesc.setText(product.getDescription());
        editTextRegPrice.setText(String.valueOf(product.getRegular_price()));
        editTextSalePrice.setText(String.valueOf(product.getSale_price()));
    }

    private void updateProduct(final Product product) {
        product.setName(editTextName.getText().toString().trim());
        product.setDescription(editTextDesc.getText().toString().trim());
        product.setRegular_price(Integer.parseInt(editTextRegPrice.getText().toString().trim()));
        product.setSale_price(Integer.parseInt(editTextSalePrice.getText().toString().trim()));


        if (editTextName.getText().toString().trim().isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (editTextDesc.getText().toString().trim().isEmpty()) {
            editTextDesc.setError("Description required");
            editTextDesc.requestFocus();
            return;
        }

        if (editTextRegPrice.getText().toString().trim().isEmpty()) {
            editTextRegPrice.setError("Regular Price required");
            editTextRegPrice.requestFocus();
            return;
        }

        if (editTextSalePrice.getText().toString().trim().isEmpty()) {
            editTextSalePrice.setError("Sale Price required");
            editTextSalePrice.requestFocus();
            return;
        }

        mProductViewModel.update(product);
        finish();
    }

}
