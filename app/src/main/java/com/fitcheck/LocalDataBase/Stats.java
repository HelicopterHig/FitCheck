package com.fitcheck.LocalDataBase;

public class Stats {
    int _id;
    int _stat_id;
    int _client_id;
    int _heartbeat;
    int _distance;
    int _steps;
    int _calories;
    int _squirrels;
    int _fats;
    int _carbohydrates;
    int _body_mass_index;
    int _weight;

    public Stats() {}

    public Stats(int _id, int _stat_id, int _client_id, int _heartbeat, int _distance, int _steps, int _calories, int _squirrels, int _fats, int _carbohydrates, int _body_mass_index, int _weight) {
        this._id = _id;
        this._stat_id = _stat_id;
        this._client_id = _client_id;
        this._heartbeat = _heartbeat;
        this._distance = _distance;
        this._steps = _steps;
        this._calories = _calories;
        this._squirrels = _squirrels;
        this._fats = _fats;
        this._carbohydrates = _carbohydrates;
        this._body_mass_index = _body_mass_index;
        this._weight = _weight;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_stat_id() {
        return _stat_id;
    }

    public void set_stat_id(int _stat_id) {
        this._stat_id = _stat_id;
    }

    public int get_client_id() {
        return _client_id;
    }

    public void set_client_id(int _client_id) {
        this._client_id = _client_id;
    }

    public int get_heartbeat() {
        return _heartbeat;
    }

    public void set_heartbeat(int _heartbeat) {
        this._heartbeat = _heartbeat;
    }

    public int get_distance() {
        return _distance;
    }

    public void set_distance(int _distance) {
        this._distance = _distance;
    }

    public int get_steps() {
        return _steps;
    }

    public void set_steps(int _steps) {
        this._steps = _steps;
    }

    public int get_calories() {
        return _calories;
    }

    public void set_calories(int _calories) {
        this._calories = _calories;
    }

    public int get_squirrels() {
        return _squirrels;
    }

    public void set_squirrels(int _squirrels) {
        this._squirrels = _squirrels;
    }

    public int get_fats() {
        return _fats;
    }

    public void set_fats(int _fats) {
        this._fats = _fats;
    }

    public int get_carbohydrates() {
        return _carbohydrates;
    }

    public void set_carbohydrates(int _carbohydrates) {
        this._carbohydrates = _carbohydrates;
    }

    public int get_body_mass_index() {
        return _body_mass_index;
    }

    public void set_body_mass_index(int _body_mass_index) {
        this._body_mass_index = _body_mass_index;
    }

    public int get_weight() {
        return _weight;
    }

    public void set_weight(int _weight) {
        this._weight = _weight;
    }
}
