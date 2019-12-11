package com.fitcheck.LocalDataBase;

public class Diet {
    int _id;
    int _diet_id;
    String _name;
    int _grams;
    int _calories;
    String _day_week;
    String _time;

    public Diet() {}

    public Diet(int _id, int _diet_id, String _name, int _grams, int _calories, String _day_week, String _time) {
        this._id = _id;
        this._diet_id = _diet_id;
        this._name = _name;
        this._grams = _grams;
        this._calories = _calories;
        this._day_week = _day_week;
        this._time = _time;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_diet_id() {
        return _diet_id;
    }

    public void set_diet_id(int _diet_id) {
        this._diet_id = _diet_id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public int get_grams() {
        return _grams;
    }

    public void set_grams(int _grams) {
        this._grams = _grams;
    }

    public int get_calories() {
        return _calories;
    }

    public void set_calories(int _calories) {
        this._calories = _calories;
    }

    public String get_day_week() {
        return _day_week;
    }

    public void set_day_week(String _day_week) {
        this._day_week = _day_week;
    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }
}
