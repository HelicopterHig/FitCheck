package com.fitcheck.LocalDataBase;

public class UserTasks {
    int _id;
    int _task_id;
    String _date;
    int _done;
    int _client_id;
    int _times;
    int _sets;
    int _weight;
    String _time;
    int _meters;

    public UserTasks(){}

    public UserTasks(int _id, int _task_id, String _date, int _done, int _client_id, int _times, int _sets, int _weight, String _time, int _meters){
        this._id = _id;
        this._task_id = _task_id;
        this._date = _date;
        this._done = _done;
        this._client_id = _client_id;
        this._times = _times;
        this._sets = _sets;
        this._weight = _weight;
        this._time = _time;
        this._meters = _meters;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_task_id() {
        return _task_id;
    }

    public void set_task_id(int _task_id) {
        this._task_id = _task_id;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }

    public int get_done() {
        return _done;
    }

    public void set_done(int _done) {
        this._done = _done;
    }

    public int get_client_id() {
        return _client_id;
    }

    public void set_client_id(int _client_id) {
        this._client_id = _client_id;
    }

    public int get_times() {
        return _times;
    }

    public void set_times(int _times) {
        this._times = _times;
    }

    public int get_sets() {
        return _sets;
    }

    public void set_sets(int _sets) {
        this._sets = _sets;
    }

    public int get_weight() {
        return _weight;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public int get_meters() {
        return _meters;
    }

    public void set_meters(int _meters) {
        this._meters = _meters;
    }
}
