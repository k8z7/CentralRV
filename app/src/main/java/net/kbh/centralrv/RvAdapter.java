package net.kbh.centralrv;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.ViewHolder> {

    Context context;
    ArrayList<String> items;
    int item_layout;
    String id = "";

    public RvAdapter(Context context, ArrayList<String> items, int item_layout) {
        this.context = context;
        this.items = items;
        this.item_layout = item_layout;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        id = parent.toString();
        id = (id.contains("rv_book")) ? "rv_book" : (id.contains("rv_jang")) ? "rv_jang" : "rv_text";
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(v);
    }

    private int selected_position = 0;

    public void setSelected_position(int pos) {
        selected_position = pos;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (id == "rv_book") holder.text1.setText(MainActivity.bible_full.get(position));
        else if (id == "rv_jang") holder.text1.setText(MainActivity.jangs.get(position));
        else holder.text1.setText(MainActivity.texts.get(position));

        if(selected_position == position){
            // Here I am just highlighting the background
            holder.itemView.setBackgroundColor(Color.GREEN);
        }else{
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFEE"));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Updating old as well as new positions
                notifyItemChanged(selected_position);
                selected_position = position;
                notifyItemChanged(selected_position);

                // Do your another stuff for your onClick
                // Toast.makeText(context, id, Toast.LENGTH_LONG).show();
                if (id == "rv_book") new MainActivity().highBook(selected_position);
                else if (id == "rv_jang") new MainActivity().highJang(selected_position);
                else new MainActivity().highText(selected_position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text1;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            text1 = (TextView)itemView.findViewById(R.id.text1);
            cardView = (CardView)itemView.findViewById(R.id.cardview);
        }
    }
}