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

    //методы для тренера
    public void addTrainer(Trainer trainer);
    public Trainer getTrainer(int id);
    public List<Trainer> getAllTrainers();
    public int getTrainerCount();
    public int updateTrainer(Trainer trainer);
    public void deleteTrainer(Trainer trainer);
    public void deleteAllTrainers(Trainer trainer);

    //методы для упражнений
    public void addUserTask(UserTasks userTasks);
    public List<UserTasks> getAllUserTasks();
    public void deleteAllUserTasks(UserTasks userTasks);
}
