package com.fitcheck.ui.elementTask;

public class ElementTask {
    private String name;
    private String type;
    private String subtype;
    private int task_id;

    public ElementTask() {}

    public ElementTask(String name, String type, String subtype, int task_id) {
        this.name = name;
        this.type = type;
        this.subtype = subtype;
        this.task_id = task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
