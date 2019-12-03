package com.fitcheck.LocalDataBase;

public class Trainer {
    int _id;
    String _name;
    String _surname;
    String _email;
    String _password;
    int _phone_number;
    String _gender;

    public Trainer(){}

    public Trainer(int _id, String _name, String _surname, String _email, String _password, int _phone_number, String _gender){
        this._id = _id;
        this._name = _name;
        this._surname = _surname;
        this._email = _email;
        this._password = _password;
        this._phone_number = _phone_number;
        this._gender = _gender;
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

    public String get_surname() {
        return _surname;
    }

    public void set_surname(String _surname) {
        this._surname = _surname;
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

    public String get_gender() {
        return _gender;
    }

    public void set_gender(String _gender) {
        this._gender = _gender;
    }
}
