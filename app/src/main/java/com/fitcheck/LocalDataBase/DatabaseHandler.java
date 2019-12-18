package com.fitcheck.LocalDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "fit_check2";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TRAINER_TABLE);
        db.execSQL(CREATE_USER_TASKS_TABLE);
        db.execSQL(CREATE_TASKS_TABLE);
        db.execSQL(CREATE_STATS_TABLE);
        db.execSQL(CREATE_FORM_TABLE);
        db.execSQL(CREATE_DIET_TABLE);
        db.execSQL(CREATE_EXER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIET);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXER);

        onCreate(db);
    }
    //******************************************USER************************************************

    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SECOND_NAME = "second_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_TRAINER_ID = "trainer_id";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_NAME + " text," + KEY_SECOND_NAME + " text," + KEY_EMAIL + " text," + KEY_PASSWORD + " text," + KEY_PHONE_NUMBER + " integer," +
            KEY_ACTIVE + " integer," + KEY_GENDER + " text," + KEY_TRAINER_ID + " integer)";



    @Override
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, user.get_id());
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_SECOND_NAME, user.get_second_name());
        values.put(KEY_EMAIL, user.get_email());
        values.put(KEY_PASSWORD, user.get_password());
        values.put(KEY_PHONE_NUMBER, user.get_phone_number());
        values.put(KEY_ACTIVE, user.get_active());
        values.put(KEY_GENDER, user.get_gender());
        values.put(KEY_TRAINER_ID, user.getTrainer_id());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    @Override
    public User getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER, new String[] {KEY_ID, KEY_NAME, KEY_SECOND_NAME, KEY_EMAIL, KEY_PASSWORD, KEY_PHONE_NUMBER, KEY_ACTIVE, KEY_GENDER, KEY_TRAINER_ID }, KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        User user = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)), cursor.getString(7),
                Integer.parseInt(cursor.getString(8)));

        return user;
    }

    @Override
    public List<User> getAllUser() {
        List<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                User user = new User();

                user.set_id(Integer.parseInt(cursor.getString(0)));
                user.set_name(cursor.getString(1));
                user.set_second_name(cursor.getString(2));
                user.set_email(cursor.getString(3));
                user.set_password(cursor.getString(4));
                user.set_phone_number(Integer.parseInt(cursor.getString(5)));
                user.set_active(Integer.parseInt(cursor.getString(6)));
                user.set_gender(cursor.getString(7));
                user.setTrainer_id(Integer.parseInt(cursor.getString(8)));

                userList.add(user);
            }while (cursor.moveToNext());
        }

        return userList;
    }

    @Override
    public int getUserCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.get_name());
        values.put(KEY_SECOND_NAME, user.get_second_name());
        values.put(KEY_EMAIL, user.get_email());
        values.put(KEY_PASSWORD, user.get_password());
        values.put(KEY_PHONE_NUMBER, user.get_phone_number());
        values.put(KEY_ACTIVE, user.get_active());
        values.put(KEY_GENDER, user.get_gender());
        values.put(KEY_TRAINER_ID, user.getTrainer_id());

        return db.update(TABLE_USER, values, KEY_ID + " = ?", new String[] { String.valueOf(user.get_id())});
    }

    @Override
    public void deleteUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER, KEY_ID + " = ?", new String[] {String.valueOf(user.get_id())});
        db.close();
    }

    @Override
    public void deleteAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
    }

    //******************************************Trainer*********************************************

    private static final String TABLE_TRAINER = "trainer";
    private static final String KEY_TR_ID = "id";
    private static final String KEY_TR_NAME = "name";
    private static final String KEY_TR_SURNAME = "surname";
    private static final String KEY_TR_EMAIL = "email";
    private static final String KEY_TR_PASSWORD = "password";
    private static final String KEY_TR_PHONE_NUMBER = "phone_number";
    private static final String KEY_TR_GENDER = "gender";

    private static final String CREATE_TRAINER_TABLE = "CREATE TABLE " + TABLE_TRAINER + "(" + KEY_TR_ID + " INTEGER PRIMARY KEY," +
            KEY_TR_NAME + " text," + KEY_TR_SURNAME + " text," + KEY_TR_EMAIL + " text," + KEY_TR_PASSWORD + " text," + KEY_TR_PHONE_NUMBER + " integer," + KEY_TR_GENDER + " text)";

    @Override
    public void addTrainer(Trainer trainer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TR_ID, trainer.get_id());
        values.put(KEY_TR_NAME, trainer.get_name());
        values.put(KEY_TR_SURNAME, trainer.get_surname());
        values.put(KEY_TR_EMAIL, trainer.get_email());
        values.put(KEY_TR_PASSWORD, trainer.get_password());
        values.put(KEY_TR_PHONE_NUMBER, trainer.get_phone_number());
        values.put(KEY_TR_GENDER, trainer.get_gender());

        db.insert(TABLE_TRAINER, null, values);
        db.close();
    }

    @Override
    public Trainer getTrainer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TRAINER, new String[] {KEY_TR_ID, KEY_TR_NAME, KEY_TR_SURNAME, KEY_TR_EMAIL, KEY_TR_PASSWORD, KEY_TR_PHONE_NUMBER, KEY_TR_GENDER}, KEY_TR_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Trainer trainer = new Trainer(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), Integer.parseInt(cursor.getString(5)),
                cursor.getString(6));

        return trainer;
    }

    @Override
    public List<Trainer> getAllTrainers() {
        List<Trainer> trainerList = new ArrayList<Trainer>();
        String selectQuery = "SELECT * FROM " + TABLE_TRAINER;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Trainer trainer = new Trainer();

                trainer.set_id(Integer.parseInt(cursor.getString(0)));
                trainer.set_name(cursor.getString(1));
                trainer.set_surname(cursor.getString(2));
                trainer.set_email(cursor.getString(3));
                trainer.set_password(cursor.getString(4));
                trainer.set_phone_number(Integer.parseInt(cursor.getString(5)));
                trainer.set_gender(cursor.getString(6));

                trainerList.add(trainer);
            }while (cursor.moveToNext());
        }

        return trainerList;
    }

    @Override
    public int getTrainerCount() {
        String countQuery = "SELECT * FROM " + TABLE_TRAINER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateTrainer(Trainer trainer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TR_NAME, trainer.get_name());
        values.put(KEY_TR_SURNAME, trainer.get_surname());
        values.put(KEY_TR_EMAIL, trainer.get_email());
        values.put(KEY_TR_PASSWORD, trainer.get_password());
        values.put(KEY_TR_PHONE_NUMBER, trainer.get_phone_number());
        values.put(KEY_TR_GENDER, trainer.get_gender());

        return db.update(TABLE_TRAINER, values, KEY_TR_ID + " = ?", new String[] { String.valueOf(trainer.get_id())});
    }

    @Override
    public void deleteTrainer(Trainer trainer) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TRAINER, KEY_TR_ID + " = ?", new String[] {String.valueOf(trainer.get_id())});
        db.close();
    }

    @Override
    public void deleteAllTrainers() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRAINER, null, null);
        db.close();
    }

    //**************************************USER_TASKS**********************************************

    private static final String TABLE_USER_TASKS = "user_tasks";
    private static final String KEY_UT_ID = "id";
    private static final String KEY_UT_TASK_ID = "task_id";
    private static final String KEY_UT_DATE = "date";
    private static final String KEY_UT_DONE = "done";
    private static final String KEY_UT_CLIENT_ID = "client_id";
    private static final String KEY_UT_TIMES = "times";
    private static final String KEY_UT_SETS = "sets";
    private static final String KEY_UT_WEIGHT = "weight";
    private static final String KEY_UT_TIME = "time";
    private static final String KEY_UT_METERS = "meters";

    private static final String CREATE_USER_TASKS_TABLE = "CREATE TABLE " + TABLE_USER_TASKS + "(" + KEY_UT_ID + " INTEGER PRIMARY KEY," +
            KEY_UT_TASK_ID + " integer," + KEY_UT_DATE + " text," + KEY_UT_DONE + " integer," + KEY_UT_CLIENT_ID + " integer," + KEY_UT_TIMES + " integer," +
            KEY_UT_SETS + " integer," + KEY_UT_WEIGHT + " integer," + KEY_UT_TIME + " integer," + KEY_UT_METERS + " integer)";

    @Override
    public void addUserTask(UserTasks userTasks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UT_ID, userTasks.get_id());
        values.put(KEY_UT_TASK_ID, userTasks.get_task_id());
        values.put(KEY_UT_DATE, userTasks.get_date());
        values.put(KEY_UT_DONE, userTasks.get_done());
        values.put(KEY_UT_CLIENT_ID, userTasks.get_client_id());
        values.put(KEY_UT_TIMES, userTasks.get_times());
        values.put(KEY_UT_SETS, userTasks.get_sets());
        values.put(KEY_UT_WEIGHT, userTasks.get_weight());
        values.put(KEY_UT_TIME, userTasks.get_time());
        values.put(KEY_UT_METERS, userTasks.get_meters());

        db.insert(TABLE_USER_TASKS, null, values);
        db.close();
    }

    @Override
    public UserTasks getUserTasks(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_TASKS, new String[] {KEY_UT_ID, KEY_UT_TASK_ID, KEY_UT_DATE, KEY_UT_DONE, KEY_UT_CLIENT_ID, KEY_UT_TIMES, KEY_UT_SETS, KEY_UT_WEIGHT, KEY_UT_TIME, KEY_UT_METERS}, KEY_UT_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        UserTasks userTasks = new UserTasks(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2),Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),Integer.parseInt(cursor.getString(5)),Integer.parseInt(cursor.getString(6)),Integer.parseInt(cursor.getString(7)),
                (cursor.getString(8)),Integer.parseInt(cursor.getString(9)));

        return userTasks;
    }

    @Override
    public List<UserTasks> getAllUserTasks() {
        List<UserTasks> userTasksList = new ArrayList<UserTasks>();
        String selectQuery = "SELECT * FROM " + TABLE_USER_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                UserTasks userTasks = new UserTasks();

                userTasks.set_id(Integer.parseInt(cursor.getString(0)));
                userTasks.set_task_id(Integer.parseInt(cursor.getString(1)));
                userTasks.set_date(cursor.getString(2));
                userTasks.set_done(Integer.parseInt(cursor.getString(3)));
                userTasks.set_client_id(Integer.parseInt(cursor.getString(4)));
                userTasks.set_times(Integer.parseInt(cursor.getString(5)));
                userTasks.set_sets(Integer.parseInt(cursor.getString(6)));
                userTasks.set_weight(Integer.parseInt(cursor.getString(7)));
                userTasks.set_time(cursor.getString(8));
                userTasks.set_meters(Integer.parseInt(cursor.getString(9)));

                userTasksList.add(userTasks);
            }while (cursor.moveToNext());
        }
        return userTasksList;
    }

    @Override
    public int getUserTasksCount() {
        String countQuery = "SELECT * FROM " + TABLE_USER_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateUserTasks(UserTasks userTasks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_UT_TASK_ID, userTasks.get_task_id());
        values.put(KEY_UT_DATE, userTasks.get_date());
        values.put(KEY_UT_DONE, userTasks.get_done());
        values.put(KEY_UT_CLIENT_ID, userTasks.get_client_id());
        values.put(KEY_UT_TIMES, userTasks.get_times());
        values.put(KEY_UT_SETS, userTasks.get_sets());
        values.put(KEY_UT_WEIGHT, userTasks.get_weight());
        values.put(KEY_UT_TIME, userTasks.get_time());
        values.put(KEY_UT_METERS, userTasks.get_meters());

        return db.update(TABLE_USER_TASKS, values, KEY_UT_ID + " = ?", new String[] { String.valueOf(userTasks.get_id())});
    }

    @Override
    public void deleteUserTasks(UserTasks userTasks) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_USER_TASKS, KEY_UT_ID + " = ?", new String[] {String.valueOf(userTasks.get_id())});
        db.close();
    }


    @Override
    public void deleteAllUserTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_TASKS, null, null);
        db.close();
    }

    //******************************************TASKS************************************************
    private static final String TABLE_TASKS = "task";
    private static final String KEY_T_ID = "id";
    private static final String KEY_T_TASK_ID = "ID_tasks";
    private static final String KEY_T_NAME = "name";
    private static final String KEY_T_TYPE = "type";
    private static final String KEY_T_SUBTYPE= "subtupe";


    private static final String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "(" + KEY_T_ID + " INTEGER PRIMARY KEY," +
            KEY_T_TASK_ID + " integer," + KEY_T_NAME + " text," + KEY_T_TYPE + " text,"  + KEY_T_SUBTYPE + " text)";

    @Override
    public void addTask(Tasks tasks) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_T_ID, tasks.get_id());
        values.put(KEY_T_TASK_ID, tasks.get_ID_task());
        values.put(KEY_T_NAME, tasks.get_name());
        values.put(KEY_T_TYPE, tasks.get_type());
        values.put(KEY_T_SUBTYPE, tasks.get_subtupe());

        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    @Override
    public Tasks getTasks(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[] {KEY_T_ID, KEY_T_TASK_ID, KEY_T_NAME, KEY_T_TYPE, KEY_T_SUBTYPE}, KEY_T_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Tasks tasks = new Tasks(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return tasks;
    }

    @Override
    public List<Tasks> getAllTasks() {
        List<Tasks> TasksList = new ArrayList<Tasks>();
        String selectQuery = "SELECT * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Tasks tasks = new Tasks();

                tasks.set_id(Integer.parseInt(cursor.getString(0)));
                tasks.set_ID_task(Integer.parseInt(cursor.getString(1)));
                tasks.set_name(cursor.getString(2));
                tasks.set_type(cursor.getString(3));
                tasks.set_subtupe(cursor.getString(4));


                TasksList.add(tasks);
            }while (cursor.moveToNext());
        }
        return TasksList;
    }

    @Override
    public int getTasksCount() {
        String countQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateTasks(Tasks tasks) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_T_TASK_ID, tasks.get_ID_task());
        values.put(KEY_T_NAME, tasks.get_name());
        values.put(KEY_T_TYPE, tasks.get_type());
        values.put(KEY_T_SUBTYPE, tasks.get_subtupe());


        return db.update(TABLE_TASKS, values, KEY_T_ID + " = ?", new String[] { String.valueOf(tasks.get_id())});
    }

    @Override
    public void deleteTasks(Tasks tasks) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TASKS, KEY_T_ID + " = ?", new String[] {String.valueOf(tasks.get_id())});
        db.close();
    }


    @Override
    public void deleteAllTasks() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, null, null);
        db.close();
    }

    //******************************************Stats************************************************
    private static final String TABLE_STATS = "stats";
    private static final String KEY_ST_ID = "id";
    private static final String KEY_ST_STAT_ID = "stat_id";
    private static final String KEY_ST_CLID = "client_id";
    private static final String KEY_ST_HEART = "heartbeat";
    private static final String KEY_ST_DISTANCE= "distance";
    private static final String KEY_ST_STEPS= "steps";
    private static final String KEY_ST_CALOR= "calories";
    private static final String KEY_ST_SQUIR= "squirrels";
    private static final String KEY_ST_FATS= "fats";
    private static final String KEY_ST_CARBO= "carbohydrates";
    private static final String KEY_ST_IMT= "body_mass_index";
    private static final String KEY_ST_WEIGHT= "weight";


    private static final String CREATE_STATS_TABLE = "CREATE TABLE " + TABLE_STATS + "(" + KEY_ST_ID + " INTEGER PRIMARY KEY," +
            KEY_ST_STAT_ID + " integer," + KEY_ST_CLID + " integer," + KEY_ST_HEART + " integer,"  + KEY_ST_DISTANCE + " integer," +
            KEY_ST_STEPS + " integer," + KEY_ST_CALOR + " integer," + KEY_ST_SQUIR + " integer,"  + KEY_ST_FATS + " integer," +
            KEY_ST_CARBO + " integer," + KEY_ST_IMT + " integer," + KEY_ST_WEIGHT + " integer)";

    @Override
    public void addStats(Stats stats) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ST_ID, stats.get_id());
        values.put(KEY_ST_STAT_ID, stats.get_stat_id());
        values.put(KEY_ST_CLID, stats.get_client_id());
        values.put(KEY_ST_HEART, stats.get_heartbeat());
        values.put(KEY_ST_DISTANCE, stats.get_distance());
        values.put(KEY_ST_STEPS, stats.get_steps());
        values.put(KEY_ST_CALOR, stats.get_calories());
        values.put(KEY_ST_SQUIR, stats.get_squirrels());
        values.put(KEY_ST_FATS, stats.get_fats());
        values.put(KEY_ST_CARBO, stats.get_carbohydrates());
        values.put(KEY_ST_IMT, stats.get_body_mass_index());
        values.put(KEY_ST_WEIGHT, stats.get_weight());


        db.insert(TABLE_STATS, null, values);
        db.close();
    }

    @Override
    public Stats getStats(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_STATS, new String[] {KEY_ST_ID, KEY_ST_STAT_ID, KEY_ST_CLID, KEY_ST_HEART, KEY_ST_DISTANCE, KEY_ST_STEPS, KEY_ST_CALOR, KEY_ST_SQUIR, KEY_ST_FATS, KEY_ST_CARBO, KEY_ST_IMT, KEY_ST_WEIGHT}, KEY_ST_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Stats stats = new Stats(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)), Integer.parseInt(cursor.getString(7)), Integer.parseInt(cursor.getString(8)), Integer.parseInt(cursor.getString(9)), Integer.parseInt(cursor.getString(10)), Integer.parseInt(cursor.getString(11)));

        return stats;
    }

    @Override
    public List<Stats> getAllStats() {
        List<Stats> StatsList = new ArrayList<Stats>();
        String selectQuery = "SELECT * FROM " + TABLE_STATS;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Stats stats = new Stats();

                stats.set_id(Integer.parseInt(cursor.getString(0)));
                stats.set_stat_id(Integer.parseInt(cursor.getString(1)));
                stats.set_client_id(Integer.parseInt(cursor.getString(2)));
                stats.set_heartbeat(Integer.parseInt(cursor.getString(3)));
                stats.set_distance(Integer.parseInt(cursor.getString(4)));
                stats.set_steps(Integer.parseInt(cursor.getString(5)));
                stats.set_calories(Integer.parseInt(cursor.getString(6)));
                stats.set_squirrels(Integer.parseInt(cursor.getString(7)));
                stats.set_fats(Integer.parseInt(cursor.getString(8)));
                stats.set_carbohydrates(Integer.parseInt(cursor.getString(9)));
                stats.set_body_mass_index(Integer.parseInt(cursor.getString(10)));
                stats.set_weight(Integer.parseInt(cursor.getString(11)));


                StatsList.add(stats);
            }while (cursor.moveToNext());
        }
        return StatsList;
    }

    @Override
    public int getStatsCount() {
        String countQuery = "SELECT * FROM " + TABLE_STATS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateStats(Stats stats) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ST_STAT_ID, stats.get_stat_id());
        values.put(KEY_ST_CLID, stats.get_client_id());
        values.put(KEY_ST_HEART, stats.get_heartbeat());
        values.put(KEY_ST_DISTANCE, stats.get_distance());
        values.put(KEY_ST_STEPS, stats.get_steps());
        values.put(KEY_ST_CALOR, stats.get_calories());
        values.put(KEY_ST_SQUIR, stats.get_squirrels());
        values.put(KEY_ST_FATS, stats.get_fats());
        values.put(KEY_ST_CARBO, stats.get_carbohydrates());
        values.put(KEY_ST_IMT, stats.get_body_mass_index());
        values.put(KEY_ST_WEIGHT, stats.get_weight());


        return db.update(TABLE_STATS, values, KEY_ST_ID + " = ?", new String[] { String.valueOf(stats.get_id())});
    }

    @Override
    public void deleteStats(Stats stats) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_STATS, KEY_ST_ID + " = ?", new String[] {String.valueOf(stats.get_id())});
        db.close();
    }


    @Override
    public void deleteAllStats() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STATS, null, null);
        db.close();
    }

    //******************************************Form************************************************
    private static final String TABLE_FORM = "form";
    private static final String KEY_F_ID = "id";
    private static final String KEY_F_STAT_ID = "form_id";
    private static final String KEY_F_CLID = "client_id";
    private static final String KEY_F_WEIGHT = "weight";
    private static final String KEY_F_HEIGHT= "height";
    private static final String KEY_F_PRESSURE= "pressure";
    private static final String KEY_F_ACTLVL= "activity_lvl";
    private static final String KEY_F_GOAL= "goal";
    private static final String KEY_F_GOALOTHER= "goal_other";
    private static final String KEY_F_SPRTEX= "sport_experience";
    private static final String KEY_F_NEGATIVE= "negative_effects";
    private static final String KEY_F_DATE= "date";


    private static final String CREATE_FORM_TABLE = "CREATE TABLE " + TABLE_FORM + "(" + KEY_F_ID + " INTEGER PRIMARY KEY," +
            KEY_F_STAT_ID + " integer," + KEY_F_CLID + " integer," + KEY_F_WEIGHT + " integer,"  + KEY_F_HEIGHT + " integer," +
            KEY_F_PRESSURE + " integer," + KEY_F_ACTLVL + " text," + KEY_F_GOAL + " text,"  + KEY_F_GOALOTHER + " text," +
            KEY_F_SPRTEX + " text," + KEY_F_NEGATIVE + " text," + KEY_F_DATE + " text)";

    @Override
    public void addForm(Form form) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_F_ID, form.get_id());
        values.put(KEY_F_STAT_ID, form.get_form_id());
        values.put(KEY_F_CLID, form.get_client_id());
        values.put(KEY_F_WEIGHT, form.get_weight());
        values.put(KEY_F_HEIGHT, form.get_height());
        values.put(KEY_F_PRESSURE, form.get_pressure());
        values.put(KEY_F_ACTLVL, form.get_activity_lvl());
        values.put(KEY_F_GOAL, form.get_goal());
        values.put(KEY_F_GOALOTHER, form.get_goal_other());
        values.put(KEY_F_SPRTEX, form.get_sport_experience());
        values.put(KEY_F_NEGATIVE, form.get_negative_effects());
        values.put(KEY_F_DATE, form.get_date());


        db.insert(TABLE_FORM, null, values);
        db.close();
    }

    @Override
    public Form getForm(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FORM, new String[] {KEY_F_ID, KEY_F_STAT_ID, KEY_F_CLID, KEY_F_WEIGHT, KEY_F_HEIGHT, KEY_F_PRESSURE, KEY_F_ACTLVL, KEY_F_GOAL, KEY_F_GOALOTHER, KEY_F_SPRTEX, KEY_F_NEGATIVE, KEY_F_DATE}, KEY_F_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Form form = new Form(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), Integer.parseInt(cursor.getString(5)),
                cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));

        return form;
    }

    @Override
    public List<Form> getAllForm() {
        List<Form> FormList = new ArrayList<Form>();
        String selectQuery = "SELECT * FROM " + TABLE_FORM;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Form form = new Form();

                form.set_id(Integer.parseInt(cursor.getString(0)));
                form.set_form_id(Integer.parseInt(cursor.getString(1)));
                form.set_client_id(Integer.parseInt(cursor.getString(2)));
                form.set_weight(Integer.parseInt(cursor.getString(3)));
                form.set_height(Integer.parseInt(cursor.getString(4)));
                form.set_pressure(Integer.parseInt(cursor.getString(5)));
                form.set_activity_lvl(cursor.getString(6));
                form.set_goal(cursor.getString(7));
                form.set_goal_other(cursor.getString(8));
                form.set_sport_experience(cursor.getString(9));
                form.set_negative_effects(cursor.getString(10));
                form.set_date(cursor.getString(11));

                FormList.add(form);
            }while (cursor.moveToNext());
        }
        return FormList;
    }

    @Override
    public int getFormCount() {
        String countQuery = "SELECT * FROM " + TABLE_FORM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateForm(Form form) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_F_STAT_ID, form.get_form_id());
        values.put(KEY_F_CLID, form.get_client_id());
        values.put(KEY_F_WEIGHT, form.get_weight());
        values.put(KEY_F_HEIGHT, form.get_height());
        values.put(KEY_F_PRESSURE, form.get_pressure());
        values.put(KEY_F_ACTLVL, form.get_activity_lvl());
        values.put(KEY_F_GOAL, form.get_goal());
        values.put(KEY_F_GOALOTHER, form.get_goal_other());
        values.put(KEY_F_SPRTEX, form.get_sport_experience());
        values.put(KEY_F_NEGATIVE, form.get_negative_effects());
        values.put(KEY_F_DATE, form.get_date());


        return db.update(TABLE_FORM, values, KEY_F_ID + " = ?", new String[] { String.valueOf(form.get_id())});
    }

    @Override
    public void deleteForm(Form form) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_FORM, KEY_F_ID + " = ?", new String[] {String.valueOf(form.get_id())});
        db.close();
    }


    @Override
    public void deleteAllForm() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FORM, null, null);
        db.close();
    }

    //******************************************DIET************************************************
    private static final String TABLE_DIET = "diet";
    private static final String KEY_D_ID = "id";
    private static final String KEY_D_DIET_ID = "diet_id";
    private static final String KEY_D_NAME = "name";
    private static final String KEY_D_GRAMS = "grams";
    private static final String KEY_D_CALORIES= "calories";
    private static final String KEY_D_DAY= "day_week";
    private static final String KEY_D_TIME= "time";
;


    private static final String CREATE_DIET_TABLE = "CREATE TABLE " + TABLE_DIET + "(" + KEY_D_ID + " INTEGER PRIMARY KEY," +
            KEY_D_DIET_ID + " integer," + KEY_D_NAME + " text," + KEY_D_GRAMS + " integer,"  + KEY_D_CALORIES + " integer," +
            KEY_D_DAY + " text," + KEY_D_TIME + " text)";

    @Override
    public void addDiet(Diet diet) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_D_ID, diet.get_id());
        values.put(KEY_D_DIET_ID, diet.get_diet_id());
        values.put(KEY_D_NAME, diet.get_name());
        values.put(KEY_D_GRAMS, diet.get_grams());
        values.put(KEY_D_CALORIES, diet.get_calories());
        values.put(KEY_D_DAY, diet.get_day_week());
        values.put(KEY_D_TIME, diet.get_time());



        db.insert(TABLE_DIET, null, values);
        db.close();
    }

    @Override
    public Diet getDiet(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_DIET, new String[] {KEY_D_ID, KEY_D_DIET_ID, KEY_D_NAME, KEY_D_GRAMS, KEY_D_CALORIES, KEY_D_DAY, KEY_D_TIME}, KEY_D_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Diet diet = new Diet(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                cursor.getString(2), Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5),
                cursor.getString(6));

        return diet;
    }

    @Override
    public List<Diet> getAllDiet() {
        List<Diet> DietList = new ArrayList<Diet>();
        String selectQuery = "SELECT * FROM " + TABLE_DIET;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Diet diet = new Diet();

                diet.set_id(Integer.parseInt(cursor.getString(0)));
                diet.set_diet_id(Integer.parseInt(cursor.getString(1)));
                diet.set_name(cursor.getString(2));
                diet.set_grams(Integer.parseInt(cursor.getString(3)));
                diet.set_calories(Integer.parseInt(cursor.getString(4)));
                diet.set_day_week(cursor.getString(5));
                diet.set_time(cursor.getString(6));


                DietList.add(diet);
            }while (cursor.moveToNext());
        }
        return DietList;
    }

    @Override
    public int getDietCount() {
        String countQuery = "SELECT * FROM " + TABLE_DIET;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateDiet(Diet diet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_D_DIET_ID, diet.get_diet_id());
        values.put(KEY_D_NAME, diet.get_name());
        values.put(KEY_D_GRAMS, diet.get_grams());
        values.put(KEY_D_CALORIES, diet.get_calories());
        values.put(KEY_D_DAY, diet.get_day_week());
        values.put(KEY_D_TIME, diet.get_time());


        return db.update(TABLE_DIET, values, KEY_D_ID + " = ?", new String[] { String.valueOf(diet.get_id())});
    }

    @Override
    public void deleteDiet(Diet diet) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_DIET, KEY_D_ID + " = ?", new String[] {String.valueOf(diet.get_id())});
        db.close();
    }


    @Override
    public void deleteAllDiet() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIET, null, null);
        db.close();
    }

    //******************************************Exercise************************************************
    private static final String TABLE_EXER = "exer";
    private static final String KEY_E_ID = "id";
    private static final String KEY_E_CLIENT_ID = "client_id";
    private static final String KEY_E_UT_ID = "ut_id";
    private static final String KEY_E_DONE = "done";
    private static final String KEY_E_NAME= "name";
    private static final String KEY_E_EX_INFO= "ex_info";
    private static final String KEY_E_TYPE_EX= "exercise_type_ex";
    private static final String KEY_E_TYPE_WRK= "exercise_type_work";
    private static final String KEY_E_TYPE_DATE= "date";


    private static final String CREATE_EXER_TABLE = "CREATE TABLE " + TABLE_EXER + "(" + KEY_E_ID + " INTEGER PRIMARY KEY," +
            KEY_E_CLIENT_ID + " integer," + KEY_E_UT_ID + " integer," + KEY_E_DONE + " integer,"  + KEY_E_NAME + " text," +
            KEY_E_EX_INFO + " text," + KEY_E_TYPE_EX + " text," + KEY_E_TYPE_WRK + " text," + KEY_E_TYPE_DATE + " text)";


    @Override
    public void addExercise(Exercise exer) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_E_ID, exer.get_id());
        values.put(KEY_E_CLIENT_ID, exer.getClient_id());
        values.put(KEY_E_UT_ID, exer.getUt_id());
        values.put(KEY_E_DONE, exer.get_done());
        values.put(KEY_E_NAME, exer.get_name());
        values.put(KEY_E_EX_INFO, exer.get_ex_info());
        values.put(KEY_E_TYPE_EX, exer.get_exercise_type_ex());
        values.put(KEY_E_TYPE_WRK, exer.get_exercise_type_work());
        values.put(KEY_E_TYPE_DATE, exer.get_date());



        db.insert(TABLE_EXER, null, values);
        db.close();
    }

    @Override
    public Exercise getExercise(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_EXER, new String[] {KEY_E_ID, KEY_E_CLIENT_ID, KEY_E_UT_ID, KEY_E_DONE, KEY_E_NAME, KEY_E_EX_INFO, KEY_E_TYPE_EX, KEY_E_TYPE_WRK, KEY_E_TYPE_DATE}, KEY_E_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Exercise exer = new Exercise(Integer.parseInt(cursor.getString(0)), Integer.parseInt(cursor.getString(1)),
                Integer.parseInt(cursor.getString(2)), Integer.parseInt(cursor.getString(3)), cursor.getString(4), cursor.getString(5),
                cursor.getString(6), cursor.getString(7), cursor.getString(8));

        return exer;
    }

    @Override
    public List<Exercise> getAllExercise() {
        List<Exercise> ExerciseList = new ArrayList<Exercise>();
        String selectQuery = "SELECT * FROM " + TABLE_EXER;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do{
                Exercise exer = new Exercise();

                exer.set_id(Integer.parseInt(cursor.getString(0)));
                exer.setClient_id(Integer.parseInt(cursor.getString(1)));
                exer.setUt_id(Integer.parseInt(cursor.getString(2)));
                exer.set_done(Integer.parseInt(cursor.getString(3)));
                exer.set_name(cursor.getString(4));
                exer.set_ex_info(cursor.getString(5));
                exer.set_exercise_type_ex(cursor.getString(6));
                exer.set_exercise_type_work(cursor.getString(7));
                exer.set_date(cursor.getString(8));


                ExerciseList.add(exer);
            }while (cursor.moveToNext());
        }
        return ExerciseList;
    }

    @Override
    public int getExerciseCount() {
        String countQuery = "SELECT * FROM " + TABLE_EXER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    @Override
    public int updateExercise(Exercise exer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_E_ID, exer.get_id());
        values.put(KEY_E_CLIENT_ID, exer.getClient_id());
        values.put(KEY_E_UT_ID, exer.getUt_id());
        values.put(KEY_E_DONE, exer.get_done());
        values.put(KEY_E_NAME, exer.get_name());
        values.put(KEY_E_EX_INFO, exer.get_ex_info());
        values.put(KEY_E_TYPE_EX, exer.get_exercise_type_ex());
        values.put(KEY_E_TYPE_WRK, exer.get_exercise_type_work());
        values.put(KEY_E_TYPE_DATE, exer.get_date());



        return db.update(TABLE_EXER, values, KEY_E_ID + " = ?", new String[] { String.valueOf(exer.get_id())});
    }

    @Override
    public void deleteExercise(Exercise exer) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_EXER, KEY_E_ID + " = ?", new String[] {String.valueOf(exer.get_id())});
        db.close();
    }


    @Override
    public void deleteAllExercise() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EXER, null, null);
        db.close();
    }
}
