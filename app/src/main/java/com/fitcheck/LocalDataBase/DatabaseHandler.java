package com.fitcheck.LocalDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper implements IDatabaseHandler {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "fit_check";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_TRAINER_TABLE);
        db.execSQL(CREATE_USER_TASKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRAINER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_TASKS);

        onCreate(db);
    }

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
        values.put(KEY_PASSWORD, user.get_password());
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
    public void deleteAllUser(User user) {
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
        values.put(KEY_TR_PASSWORD, trainer.get_password());
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
    public void deleteAllTrainers(Trainer trainer) {
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
    public List<UserTasks> getAllUserTasks() {
        List<UserTasks> userTasksList = new ArrayList<UserTasks>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

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
    public void deleteAllUserTasks(UserTasks userTasks) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER_TASKS, null, null);
        db.close();
    }
}
