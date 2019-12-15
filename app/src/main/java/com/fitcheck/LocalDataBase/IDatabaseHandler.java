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
    public void deleteAllUser();

    //методы для тренера
    public void addTrainer(Trainer trainer);
    public Trainer getTrainer(int id);
    public List<Trainer> getAllTrainers();
    public int getTrainerCount();
    public int updateTrainer(Trainer trainer);
    public void deleteTrainer(Trainer trainer);
    public void deleteAllTrainers();

    //методы для упражнений
    public void addUserTask(UserTasks userTasks);
    public UserTasks getUserTasks(int id);
    public List<UserTasks> getAllUserTasks();
    public int getUserTasksCount();
    public int updateUserTasks(UserTasks userTasks);
    public void deleteUserTasks(UserTasks userTasks);
    public void deleteAllUserTasks();

    //Методы для заданий
    public void addTask(Tasks tasks);
    public Tasks getTasks(int id);
    public List<Tasks> getAllTasks();
    public int getTasksCount();
    public int updateTasks(Tasks tasks);
    public void deleteTasks(Tasks tasks);
    public void deleteAllTasks();

    //Методы для статистики
    public void addStats(Stats stats);
    public Stats getStats(int id);
    public List<Stats> getAllStats();
    public int getStatsCount();
    public int updateStats(Stats stats);
    public void deleteStats(Stats stats);
    public void deleteAllStats();

    //Методы для формы
    public void addForm(Form form);
    public Form getForm(int id);
    public List<Form> getAllForm();
    public int getFormCount();
    public int updateForm(Form form);
    public void deleteForm(Form form);
    public void deleteAllForm();

    //Методы диеты
    public void addDiet(Diet diet);
    public Diet getDiet(int id);
    public List<Diet> getAllDiet();
    public int getDietCount();
    public int updateDiet(Diet diet);
    public void deleteDiet(Diet diet);
    public void deleteAllDiet();

    //Методы для упражнений
    public void addExercise(Exercise exer);
    public Exercise getExercise(int id);
    public List<Exercise> getAllExercise();
    public int getExerciseCount();
    public int updateExercise(Exercise exer);
    public void deleteExercise(Exercise exer);
    public void deleteAllExercise();
}
