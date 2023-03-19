package com.example.solestock;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import android.view.View;
public class SneakerViewHolder extends RecyclerView.ViewHolder {
    private TextView categoryTextView;
    private TextView priceTextView;
    private TextView shoeNameTextView;

    public SneakerViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryTextView = itemView.findViewById(R.id.tv_size);
        priceTextView = itemView.findViewById(R.id.tv_price);
        shoeNameTextView = itemView.findViewById(R.id.tv_name);
    }

    public void bind(Sneakers sneaker) {
        categoryTextView.setText(sneaker.getcategory());
        priceTextView.setText(sneaker.getprice());
        shoeNameTextView.setText(sneaker.getShoeName());
    }
}