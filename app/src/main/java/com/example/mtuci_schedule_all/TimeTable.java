package com.example.mtuci_schedule_all;

public class TimeTable {


    String lesson;
    String lesson_type;
    String room;
    String time;

    public TimeTable() {

    }

    public TimeTable(String lesson, String lesson_type, String room, String time) {
        this.lesson = lesson;
        this.lesson_type = lesson_type;
        this.room = room;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getLesson_type() {
        return lesson_type;
    }

    public void setLesson_type(String lesson_type) {
        this.lesson_type = lesson_type;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}


