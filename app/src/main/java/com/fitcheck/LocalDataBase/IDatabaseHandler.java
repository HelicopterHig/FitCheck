package com.fitcheck.LocalDataBase;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

interface IDatabaseHandler {
    void onCreate(SQLiteDatabase db);

    //методы для пользователя
    public void addUser(User user);
    public User getUser(int id);
    public List<User> getAllUser();
    public int getUserCount();
    public int updateUser(User user);
    public void deleteUser(User user);
    public void deleteAllUser(User user);
}
