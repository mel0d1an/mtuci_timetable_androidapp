package com.example.mtuci_schedule_all;


public class FacultyList {
    String faculty_name;
    String key;


    public FacultyList(){

    }

    public FacultyList(String faculty_name, String key) {
        this.faculty_name = faculty_name;
        this.key = key;

    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
