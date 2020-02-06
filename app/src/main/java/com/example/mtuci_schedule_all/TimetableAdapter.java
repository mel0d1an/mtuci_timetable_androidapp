package com.example.mtuci_schedule_all;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;


import com.example.mtuci_schedule_all.ui.home.HomeFragment;

import java.util.ArrayList;


public class TimetableAdapter extends RecyclerView.Adapter<TimetableAdapter.MyViewHolder> {

    //Context context;


    ArrayList<TimeTable> TimeTable;

    public TimetableAdapter(/*Context c,*/ ArrayList<TimeTable> p) {
        //context = c;
        TimeTable = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_timetable, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.textViewLessonName.setText(TimeTable.get(i).getLesson());
        myViewHolder.textViewLessonType.setText(TimeTable.get(i).getLesson_type());
        myViewHolder.textViewNumberOfRoom.setText(TimeTable.get(i).getRoom());
        myViewHolder.textViewTime.setText(TimeTable.get(i).getTime());



    }

    @Override
    public int getItemCount() {
        return TimeTable.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewLessonName, textViewLessonType, textViewNumberOfRoom, textViewTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewLessonName = (TextView) itemView.findViewById(R.id.textViewLessonName);
            textViewLessonType = (TextView) itemView.findViewById(R.id.textViewLessonType);
            textViewNumberOfRoom = (TextView) itemView.findViewById(R.id.textViewNumberOfRoom);
            textViewTime = (TextView) itemView.findViewById(R.id.textViewTime);
        }
    }

}
