package com.example.solestock;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import androidx.annotation.NonNull;
import android.view.ViewGroup;
import android.view.View;
import android.view.LayoutInflater;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import android.widget.TextView;
public class SneakerAdapter extends FirebaseRecyclerAdapter<Sneakers, SneakerViewHolder> {

    private TextView totalPriceTextView;
    private  TextView totalItemsTextview;
    private int itemCount;
    public SneakerAdapter(@NonNull FirebaseRecyclerOptions<Sneakers> options, TextView totalPriceTextView,TextView totalItemsTextview) {
        super(options);
        this.totalPriceTextView = totalPriceTextView;
        this.totalItemsTextview = totalItemsTextview;
    }

    @Override
    protected void onBindViewHolder(@NonNull SneakerViewHolder holder, int position, @NonNull Sneakers model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public SneakerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new SneakerViewHolder(view);
    }

    @Override
    public void onDataChanged() {
        super.onDataChanged();
        // Calculate total price
        double totalPrice = 0.0;
        for (int i = 0; i < getItemCount(); i++) {
            Sneakers sneakers = getItem(i);
            if (sneakers != null && sneakers.getprice() != null) {
                totalPrice += Double.parseDouble(sneakers.getprice());
            }
            itemCount ++;
        }

        totalPriceTextView.setText(String.format(" $%.2f", totalPrice));
        totalItemsTextview.setText(String.format(" %d", itemCount));
    }

}