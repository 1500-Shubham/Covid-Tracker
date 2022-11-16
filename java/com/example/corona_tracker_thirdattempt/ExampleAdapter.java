package com.example.corona_tracker_thirdattempt;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    private ArrayList<JobCandidates> mexampleItems;

    public ExampleAdapter(ArrayList<JobCandidates> example) {
        mexampleItems = example;

    } // saara value constructor call pe mil jayega

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView mimageView;
        public TextView mTextView1, mTextView2, newconfirm, recovered;

        public ExampleViewHolder(@NonNull View itemView) {//yeh itemView layout hai uske elements combine
            super(itemView); //View type hai
            mimageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            newconfirm = itemView.findViewById(R.id.newconfirm);
            recovered = itemView.findViewById(R.id.recovered);
        }
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_list, parent, false);
        //call view holder and pass the view
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override //yeh upar ka evh mila niche and v gaya upar ab dono ko link
    //position 0 1 2 type hota by default toh array mein use kar sakte
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        JobCandidates current = mexampleItems.get(position);
        holder.mimageView.setText(current.getName());

       holder.mTextView2.setText("TOTAL CASES:- \n" + String.valueOf(current.getAge()));
        holder.mTextView1.setText("DEATH:- \n" + current.getGender());
        holder.newconfirm.setText("NEW CASES:- \n" + current.getNewcases());
        holder.recovered.setText("RECOVERED:- \n " + current.getRecovered());
    }

    @Override
    public int getItemCount() {
        return mexampleItems.size();
    }


}
