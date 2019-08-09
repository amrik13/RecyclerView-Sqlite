package com.amriksinghpadam.sharedpref;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.widget.Toast.makeText;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<String> image = new ArrayList<>();
    private ArrayList<String> text = new ArrayList<>();
    private Context context;

    public RecyclerAdapter(Context context,ArrayList<String> image,ArrayList<String> text)
    {
        this.image= image;
        this.text= text;
        this.context= context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        Glide.with(context).asBitmap().load(image.get(position)).into(holder.img);
        holder.textt.setText(text.get(position));

        holder.relativeLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                makeText(context,"Name Of User: "+text.get(position), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return text.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView textt;
        RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageViewId);
            textt = itemView.findViewById(R.id.textViewId);
            relativeLayout = itemView.findViewById(R.id.relativeLayoutId);
        }
    }

}
