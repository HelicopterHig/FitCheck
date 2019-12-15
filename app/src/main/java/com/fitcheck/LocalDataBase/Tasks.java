package com.fitcheck.LocalDataBase;

public class Tasks {
    int _id;
    int _ID_task;
    String _name;
    String _type;
    String _subtupe;

    public Tasks() {}




    public Tasks(int _id, int _ID_task, String _name, String _type, String _subtupe) {
        this._id = _id;
        this._ID_task = _ID_task;
        this._name = _name;
        this._type = _type;
        this._subtupe = _subtupe;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_ID_task() {
        return _ID_task;
    }

    public void set_ID_task(int _ID_task) {
        this._ID_task = _ID_task;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_type() {
        return _type;
    }

    public void set_type(String _type) {
        this._type = _type;
    }

    public String get_subtupe() {
        return _subtupe;
    }

    public void set_subtupe(String _subtupe) {
        this._subtupe = _subtupe;
    }
}


