package com.example.mtuci_schedule_all;

public class Faculty {

    String faculty_name;
    String group_list;

    public Faculty(){

    }

    public Faculty(String faculty_name, String group_list) {
        this.faculty_name = faculty_name;
        this.group_list = group_list;
    }

    public String getFaculty_name() {
        return faculty_name;
    }

    public void setFaculty_name(String faculty_name) {
        this.faculty_name = faculty_name;
    }

    public String getGroup_list() {
        return group_list;
    }

    public void setGroup_list(String group_list) {
        this.group_list = group_list;
    }
}
