package com.example.mtuci_schedule_all.ui.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    private static String getDayNum() {
        Calendar calendar = Calendar.getInstance();
        int dayNum = (calendar.get(Calendar.DAY_OF_WEEK));
        if (dayNum>1 && dayNum < 7) {
            return "" + (dayNum-2);
        }
        return "0";
    }

    public String load() {
        FileInputStream fis = null;

        try {
            fis = getContext().openFileInput("config.json");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null) {
                sb.append(text).append("\n");
            }

            return sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();


        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "0";
    }


    private ArrayList<TimeTable> list;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("groups").child("0").child("timetable").child("0").child("0");







    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        System.out.println(load());
        DatabaseReference myRef = database.getReference("groups").child(load()).child("timetable").child("0").child(getDayNum());


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView recyclerviewTimetable = root.findViewById(R.id.recyclerviewTimetable);
        list = new ArrayList<>();


        final HorizontalScrollView buttonsScrollView = root.findViewById(R.id.horizontalScrollView);
        final Button buttonMonday = root.findViewById(R.id.buttonMonday);
        final Button buttonTuesday = root.findViewById(R.id.buttonTuesday);
        final Button buttonWednesday = root.findViewById(R.id.buttonWednesday);
        final Button buttonThursday = root.findViewById(R.id.buttonThursday);
        final Button buttonFriday = root.findViewById(R.id.buttonFriday);

        buttonMonday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(load());
                list.clear();
                buttonMonday.setTypeface(Typeface.DEFAULT_BOLD);
                buttonTuesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonThursday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonWednesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonFriday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonsScrollView.scrollTo(0, 0);
                DatabaseReference myRef = database.getReference("groups").child(load()).child("timetable").child("0").child("0");
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


        buttonTuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                buttonMonday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonTuesday.setTypeface(Typeface.DEFAULT_BOLD);
                buttonThursday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonWednesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonFriday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonsScrollView.scrollTo(buttonMonday.getWidth() - 20, 0);
                DatabaseReference myRef = database.getReference("groups").child(load()).child("timetable").child("0").child("1");
                String group_id = load();
                System.out.println(group_id);
                System.out.println(load());
                System.out.println(getContext().getFilesDir());
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
                buttonMonday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonTuesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonThursday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonWednesday.setTypeface(Typeface.DEFAULT_BOLD);
                buttonFriday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonsScrollView.scrollTo(buttonThursday.getWidth() + buttonMonday.getWidth() + 50, 0);
                DatabaseReference myRef = database.getReference("groups").child(load()).child("timetable").child("0").child("2");
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
                buttonMonday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonTuesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonWednesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonThursday.setTypeface(Typeface.DEFAULT_BOLD);
                buttonFriday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonsScrollView.scrollTo(buttonThursday.getWidth() + buttonMonday.getWidth() + buttonWednesday.getWidth() + 50, 0);
                DatabaseReference myRef = database.getReference("groups").child(load()).child("timetable").child("0").child("3");
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
                buttonMonday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonTuesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonThursday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonWednesday.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
                buttonFriday.setTypeface(Typeface.DEFAULT_BOLD);
                buttonsScrollView.scrollTo(buttonThursday.getWidth() + buttonMonday.getWidth() + buttonWednesday.getWidth() + buttonThursday.getWidth() + 50, 0);
                DatabaseReference myRef = database.getReference("groups").child(load()).child("timetable").child("0").child("4");
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
