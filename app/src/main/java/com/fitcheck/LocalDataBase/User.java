package com.fitcheck.LocalDataBase;

public class User {
    int _id;
    String _name;
    String _second_name;
    String _email;
    String _password;
    int _phone_number;
    int _active;
    int _card_num;
    String _gender;
    int trainer_id;

    public User(){

    }

    public User(int _id, String _name, String _second_name, String _email, String _password, int _phone_number, int _active, String _gender, int trainer_id){
        this._id = _id;
        this._name = _name;
        this._second_name = _second_name;
        this._email = _email;
        this._password = _password;
        this._phone_number = _phone_number;
        this._active = _active;
        this._gender = _gender;
        this.trainer_id = trainer_id;
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

    public String get_second_name() {
        return _second_name;
    }

    public void set_second_name(String _second_name) {
        this._second_name = _second_name;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public int get_phone_number() {
        return _phone_number;
    }

    public void set_phone_number(int _phone_number) {
        this._phone_number = _phone_number;
    }

    public int get_active() {
        return _active;
    }

    public void set_active(int _active) {
        this._active = _active;
    }

    public int get_card_num() {
        return _card_num;
    }

    public void set_card_num(int _card_num) {
        this._card_num = _card_num;
    }

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }

    public int getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(int trainer_id) {
        this.trainer_id = trainer_id;
    }
}
