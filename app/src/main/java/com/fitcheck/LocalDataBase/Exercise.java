package com.fitcheck.LocalDataBase;

public class Exercise {
    private int _id;
    private int _client_id;
    private int _ut_id;
    private int _done;
    private String _name;
    private String _ex_info;
    private String _exercise_type_ex;
    private String _exercise_type_work;
    private String _date;


    public Exercise() {}

    public Exercise(int _id, int _client_id, int _ut_id, int _done, String _name, String _ex_info, String _exercise_type_ex, String _exercise_type_work, String _date) {
        this._id = _id;
        this._client_id = _client_id;
        this._ut_id = _ut_id;
        this._done = _done;
        this._name = _name;
        this._ex_info = _ex_info;
        this._exercise_type_ex = _exercise_type_ex;
        this._exercise_type_work = _exercise_type_work;
        this._date = _date;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public int getClient_id() {
        return _client_id;
    }

    public void setClient_id(int client_id) {
        this._client_id = client_id;
    }

    public int getUt_id() {
        return _ut_id;
    }

    public void setUt_id(int ut_id) {
        this._ut_id = ut_id;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_ex_info() {
        return _ex_info;
    }

    public void set_ex_info(String _ex_info) {
        this._ex_info = _ex_info;
    }

    public String get_exercise_type_ex() {
        return _exercise_type_ex;
    }

    public void set_exercise_type_ex(String _exercise_type_ex) {
        this._exercise_type_ex = _exercise_type_ex;
    }

    public String get_exercise_type_work() {
        return _exercise_type_work;
    }

    public void set_exercise_type_work(String _exercise_type_work) {
        this._exercise_type_work = _exercise_type_work;
    }

    public int get_done() {
        return _done;
    }

    public void set_done(int _done) {
        this._done = _done;
    }
}
