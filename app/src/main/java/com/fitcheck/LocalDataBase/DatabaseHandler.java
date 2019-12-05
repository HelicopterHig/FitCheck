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

    private static final String TABLE_USER = "user";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SECOND_NAME = "second_name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_PHONE_NUMBER = "phone_number";
    private static final String KEY_ACTIVE = "active";
    private static final String KEY_CARD_NUM = "card_num";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_TRAINER_ID = "trainer_id";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
            KEY_NAME + " text," + KEY_SECOND_NAME + " text," + KEY_EMAIL + " text," + KEY_PASSWORD + " text," + KEY_PHONE_NUMBER + " integer," +
            KEY_ACTIVE + " integer," + KEY_GENDER + " text," + KEY_TRAINER_ID + " integer)";


    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

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
}
