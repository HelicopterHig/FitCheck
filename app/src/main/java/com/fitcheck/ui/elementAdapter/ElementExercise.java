package com.fitcheck.ui.elementAdapter;

public class ElementExercise {
    private String name;
    private String ex_info;
    private String exercise_type_ex;
    private String exercise_type_work;
    private int done;

    public ElementExercise(String name, String ex_info, String exercise_type_ex, String exercise_type_work, int done){
        this.name = name;
        this.ex_info = ex_info;
        this.exercise_type_ex = exercise_type_ex;
        this.exercise_type_work = exercise_type_work;
        this.done = done;
    }

    public String getNameElement() {
        return name;
    }

    public String getEx_InfoElement() {
        return ex_info;
    }

    public String getExercise_Type_ExElement() {
        return exercise_type_ex;
    }

    public String getExercise_Type_WorkElement() {
        return exercise_type_work;
    }

    public int getDone() {
        return done;
    }

    public void setNameElement(String name, String ex_info, String exercise_type_ex, String exercise_type_work, int done) {
        this.name = name;
        this.ex_info = ex_info;
        this.exercise_type_ex = exercise_type_ex;
        this.exercise_type_work = exercise_type_work;
        this.done = done;
    }
}