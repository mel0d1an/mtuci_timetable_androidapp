package com.example.mtuci_schedule_all.ui.tools;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mtuci_schedule_all.FacultyList;
import com.example.mtuci_schedule_all.R;
import com.example.mtuci_schedule_all.TimeTable;
import com.example.mtuci_schedule_all.TimetableAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.File;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class ToolsFragment extends Fragment {


    ArrayList<String> facultyList;
    ArrayList<String> facultyID;


    FirebaseDatabase database = FirebaseDatabase.getInstance();

    public void save(String text) {
        FileOutputStream fos = null;

        try {
            fos = getContext().openFileOutput("config.json", MODE_PRIVATE);
            fos.write(text.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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



        public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        final View root = inflater.inflate(R.layout.fragment_tools, container, false);


        facultyList = new ArrayList<String>();
        facultyID = new ArrayList<String>();

        DatabaseReference facultyInfo = database.getReference("faculty_list");

        facultyInfo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (@NonNull DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    FacultyList p = dataSnapshot1.getValue(FacultyList.class);
                    facultyList.add(p.getFaculty_name());
                    facultyID.add(dataSnapshot1.getKey());
                }



                Spinner spinnerFaculty = (Spinner) root.findViewById(R.id.facultySpinner);
                final Spinner spinnerGroups = (Spinner) root.findViewById(R.id.groupSpinner);


                ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,facultyList);
                LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerFaculty.setAdapter(LTRadapter);

                final ArrayList<String> group_ids;
                group_ids = new ArrayList<String>();

                spinnerFaculty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, final int position, long id) {

                        final String faculty_id = ""+position;
                        final ArrayList<String> group_list;
                        group_list = new ArrayList<String>();


                        final DatabaseReference group_info = database.getReference("faculty_list").child(faculty_id).child("group_list");

                        group_info.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                group_list.clear();
                                group_ids.clear();
                                for (@NonNull DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                    System.out.println(dataSnapshot);

                                    group_list.add(dataSnapshot1.child("name").getValue().toString());
                                    group_ids.add(dataSnapshot1.child("id").getValue().toString());

                                }

                                ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,group_list);
                                LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                                spinnerGroups.setAdapter(LTRadapter);

                                File file = new File( "config.json");

                                try {
                                    file.createNewFile();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }






                            }


                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                spinnerGroups.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        save(group_ids.get(position));
                        System.out.println(load());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });





            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return root;
    }


    public void onItemSelected(AdapterView<?> parent) {

    }
}