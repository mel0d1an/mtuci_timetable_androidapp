package com.example.mtuci_schedule_all.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mtuci_schedule_all.R;
import com.example.mtuci_schedule_all.TimeTable;
import com.example.mtuci_schedule_all.TimetableAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    ArrayList<TimeTable> list;

    FirebaseDatabase database = FirebaseDatabase.getInstance();


    Calendar calendar = Calendar.getInstance();
    int dayNum = (calendar.get(Calendar.DAY_OF_WEEK));
    String day = "" + (dayNum - 2);


    DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child(day);


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView recyclerviewTimetable = (RecyclerView) root.findViewById(R.id.recyclerviewTimetable);
        list = new ArrayList<TimeTable>();

        Button buttonMonday = root.findViewById(R.id.buttonMonday);
        Button buttonTuesday = root.findViewById(R.id.buttonTuesday);
        Button buttonWednesday = root.findViewById(R.id.buttonWednesday);
        Button buttonThursday = root.findViewById(R.id.buttonThursday);
        Button buttonFriday = root.findViewById(R.id.buttonFriday);

        buttonMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child("0");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            TimeTable p = dataSnapshot1.getValue(TimeTable.class);
                            list.add(p);
                            String strtext=getArguments().getString("message");
                            System.out.println(strtext);
                        }

                        recyclerviewTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerviewTimetable.setLayoutManager(layoutManager);
                        recyclerviewTimetable.setAdapter(new TimetableAdapter(list));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });


        buttonTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child("1");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            TimeTable p = dataSnapshot1.getValue(TimeTable.class);
                            list.add(p);


                        }
                        recyclerviewTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerviewTimetable.setLayoutManager(layoutManager);
                        recyclerviewTimetable.setAdapter(new TimetableAdapter(list));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        buttonWednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child("2");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            TimeTable p = dataSnapshot1.getValue(TimeTable.class);
                            list.add(p);


                        }
                        recyclerviewTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerviewTimetable.setLayoutManager(layoutManager);
                        recyclerviewTimetable.setAdapter(new TimetableAdapter(list));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        buttonThursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child("3");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            TimeTable p = dataSnapshot1.getValue(TimeTable.class);
                            list.add(p);


                        }
                        recyclerviewTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerviewTimetable.setLayoutManager(layoutManager);
                        recyclerviewTimetable.setAdapter(new TimetableAdapter(list));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        buttonFriday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child("4");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            TimeTable p = dataSnapshot1.getValue(TimeTable.class);
                            list.add(p);


                        }
                        recyclerviewTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        recyclerviewTimetable.setLayoutManager(layoutManager);
                        recyclerviewTimetable.setAdapter(new TimetableAdapter(list));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    TimeTable p = dataSnapshot1.getValue(TimeTable.class);
                    list.add(p);


                }
                recyclerviewTimetable.setLayoutManager(new LinearLayoutManager(getContext()));
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                recyclerviewTimetable.setLayoutManager(layoutManager);
                recyclerviewTimetable.setAdapter(new TimetableAdapter(list));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return root;
    }

}
