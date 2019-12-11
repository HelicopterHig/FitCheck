package com.fitcheck.LocalDataBase;

public class Form {
    int _id;
    int _form_id;
    int _client_id;
    int _weight;
    int _height;
    int _pressure;
    String _activity_lvl;
    String _goal;
    String _goal_other;
    String _sport_experience;
    String _negative_effects;
    String _date;

    public Form() {}

    public Form(int _id, int _form_id, int _client_id, int _weight, int _height, int _pressure, String _activity_lvl, String _goal, String _goal_other, String _sport_experience, String _negative_effects, String _date) {
        this._id = _id;
        this._form_id = _form_id;
        this._client_id = _client_id;
        this._weight = _weight;
        this._height = _height;
        this._pressure = _pressure;
        this._activity_lvl = _activity_lvl;
        this._goal = _goal;
        this._goal_other = _goal_other;
        this._sport_experience = _sport_experience;
        this._negative_effects = _negative_effects;
        this._date = _date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_form_id() {
        return _form_id;
    }

    public void set_form_id(int _form_id) {
        this._form_id = _form_id;
    }

    public int get_client_id() {
        return _client_id;
    }

    public void set_client_id(int _client_id) {
        this._client_id = _client_id;
    }

    public int get_weight() {
        return _weight;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }

    public int get_height() {
        return _height;
    }

    public void set_height(int _height) {
        this._height = _height;
    }

    public int get_pressure() {
        return _pressure;
    }

    public void set_pressure(int _pressure) {
        this._pressure = _pressure;
    }

    public String get_activity_lvl() {
        return _activity_lvl;
    }

    public void set_activity_lvl(String _activity_lvl) {
        this._activity_lvl = _activity_lvl;
    }

    public String get_goal() {
        return _goal;
    }

    public void set_goal(String _goal) {
        this._goal = _goal;
    }

    public String get_goal_other() {
        return _goal_other;
    }

    public void set_goal_other(String _goal_other) {
        this._goal_other = _goal_other;
    }

    public String get_sport_experience() {
        return _sport_experience;
    }

    public void set_sport_experience(String _sport_experience) {
        this._sport_experience = _sport_experience;
    }

    public String get_negative_effects() {
        return _negative_effects;
    }

    public void set_negative_effects(String _negative_effects) {
        this._negative_effects = _negative_effects;
    }

    public String get_date() {
        return _date;
    }

    public void set_date(String _date) {
        this._date = _date;
    }
}
