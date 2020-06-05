package com.example.fluperapp.UI;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.fluperapp.R;
import com.example.fluperapp.Room.Product;
import com.example.fluperapp.ViewModel.ProductViewModel;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText editTextName, editTextDesc, editTextRegPrice, editTextSalePrice;
    private ImageView imageView;
    private ProductViewModel mProductViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        editTextName = findViewById(R.id.edt_name);
        editTextDesc = findViewById(R.id.edt_desc);
        editTextRegPrice = findViewById(R.id.edt_reg_price);
        editTextSalePrice = findViewById(R.id.edt_sale_price);

        imageView = findViewById(R.id.product_image);

        mProductViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        final Product product = (Product) getIntent().getSerializableExtra("product");

        loadProduct(product);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(UpdateProductActivity.this,android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.zoomimage_layout);

                ImageView ivPreview = dialog.findViewById(R.id.iv_preview_image);
                Bitmap bitmap = BitmapFactory.decodeByteArray(product.getProduct_photo(), 0, product.getProduct_photo().length);
                ivPreview.setImageBitmap(bitmap);

                dialog.show();
            }
        });

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
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getProduct_photo(), 0, product.getProduct_photo().length);
        imageView.setImageBitmap(bitmap);
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
