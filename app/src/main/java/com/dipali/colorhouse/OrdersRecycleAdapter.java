package com.dipali.colorhouse;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dipali.colorhouse.models.NearByHelper;
import com.dipali.colorhouse.models.OrdersHelper;

import java.util.ArrayList;

public class OrdersRecycleAdapter extends RecyclerView.Adapter<OrdersRecycleAdapter.FeaturedViewHolder> {

    private Context context;
    ArrayList<OrdersHelper> featuredLocations;

    public OrdersRecycleAdapter(Context context, ArrayList<OrdersHelper> featuredLocations) {
        this.context = context;
        this.featuredLocations = featuredLocations;
    }
    @NonNull
    @Override
    public OrdersRecycleAdapter.FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_layout,parent,false);
        OrdersRecycleAdapter.FeaturedViewHolder featuredViewHolder = new OrdersRecycleAdapter.FeaturedViewHolder(view);
        context = parent.getContext();
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRecycleAdapter.FeaturedViewHolder holder, int position) {

        OrdersHelper featuredHelper = featuredLocations.get(position);
        holder.phone.setText(featuredHelper.getPhone());
        holder.title.setText(featuredHelper.getTitle());
        holder.email.setText(featuredHelper.getEmail());
    }
    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {

        TextView title,phone, email;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener((View.OnClickListener) this);
            //Hooks
            email = itemView.findViewById(R.id.shop_phone1);
            title = itemView.findViewById(R.id.shop_name1);
            phone = itemView.findViewById(R.id.shop_add1);

        }

    }
}
