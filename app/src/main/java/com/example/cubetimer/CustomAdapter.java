
package com.example.cubetimer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class   CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    ArrayList<Record> recordArrayList;
    Context context;

    public CustomAdapter(ArrayList<Record> recordArrayList, Context context) {
        this.recordArrayList = recordArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.entity_record,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.timeTV.setText(recordArrayList.get(position).getTime()+"");
        holder.scrambleTV.setText(recordArrayList.get(position).getScramble()+"");
        holder.dateTV.setText(recordArrayList.get(position).getDate()+"");
        
        holder.deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
    }

    public void delete(int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Waning");
        builder.setMessage("You want to delete?");

        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                RecordRepository recordRepository = new RecordRepository(context);
                recordRepository.DeleteTask(recordArrayList.get(position));
                recordArrayList.remove(position);
                notifyDataSetChanged();
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                 dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public int getItemCount() {
        return recordArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView timeTV;
        TextView scrambleTV;
        TextView dateTV;
        ImageView deleteImg;



        public MyViewHolder(View itemview){
            super(itemview);
            this.timeTV = itemview.findViewById(R.id.timeTV);
            this.scrambleTV = itemview.findViewById(R.id.scrambleTV);
            this.dateTV = itemview.findViewById(R.id.dateTV);
            this.deleteImg = itemview.findViewById(R.id.img_delete);
        }


   }

}
