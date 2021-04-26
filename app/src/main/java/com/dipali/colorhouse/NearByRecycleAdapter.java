package com.dipali.colorhouse;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dipali.colorhouse.models.NearByHelper;

import java.util.ArrayList;

public class NearByRecycleAdapter extends RecyclerView.Adapter<NearByRecycleAdapter.FeaturedViewHolder> {

    private Context context;
    ArrayList<NearByHelper> featuredLocations;

    public NearByRecycleAdapter(Context context,ArrayList<NearByHelper> featuredLocations) {
        this.context = context;
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.near_card_layout,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view);
        context = parent.getContext();
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {

        NearByHelper featuredHelper = featuredLocations.get(position);
        holder.phone.setText(featuredHelper.getPhone());
        holder.title.setText(featuredHelper.getTitle());
        holder.des.setText(featuredHelper.getDes());
        holder.email.setText(featuredHelper.getDes());
        holder.user_email.setText(featuredHelper.getUseremail());
//        holder.b_book.setOnClickListener((View.OnClickListener) this);
    }
    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder{

        TextView title, des, phone, email, user_email;
        Button b_book;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener((View.OnClickListener) this);
            //Hooks
            phone = itemView.findViewById(R.id.shop_phone1);
            title = itemView.findViewById(R.id.shop_name1);
            des = itemView.findViewById(R.id.shop_add1);
            email = itemView.findViewById(R.id.shop_email);
            user_email = itemView.findViewById(R.id.user_email);
            b_book = itemView.findViewById(R.id.shop_book);

            b_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("goon","clicked");
                    Toast.makeText(itemView.getContext(),"clicked " + title.getText().toString(),Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(itemView.getContext(), OrdersDashboard.class);
                    intent.putExtra("phone",phone.getText().toString());
                    intent.putExtra("session_email",user_email.getText().toString());
                    itemView.getContext().startActivity(intent);
                }
            });

        }



    }
}
