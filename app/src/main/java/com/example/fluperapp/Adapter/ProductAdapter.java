package com.example.fluperapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fluperapp.R;
import com.example.fluperapp.Room.Product;
import com.example.fluperapp.UI.UpdateProductActivity;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    Context mCtx;
    List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.product_row, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
        holder.textView.setText(productList.get(position).getName());
        holder.textView_desc.setText(productList.get(position).getDescription());
        Bitmap bitmap = BitmapFactory.decodeByteArray(productList.get(position).getProduct_photo(), 0, productList.get(position).getProduct_photo().length);
        holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textView;
        TextView textView_desc;
        ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.text_view_title);
            textView_desc = itemView.findViewById(R.id.text_view_description);
            imageView = itemView.findViewById(R.id.product_img);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Product product = productList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateProductActivity.class);
            intent.putExtra("product", product);

            mCtx.startActivity(intent);
        }
    }
}


