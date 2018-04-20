package com.faisal.totassignment.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.faisal.totassignment.data.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "student_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Student.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Student.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertStudent(String name, String institution, String mobileNo, String emailAddress) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Student.COLUMN_NAME, name);
        values.put(Student.COLUMN_INSTITUTION, institution);
        values.put(Student.COLUMN_MOBILE_NUMBER, mobileNo);
        values.put(Student.COLUMN_EMAIL_ADDRESS, emailAddress);

        // insert row
        long id = db.insert(Student.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Student getStudent(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Student.TABLE_NAME,
                new String[]{Student.COLUMN_ID, Student.COLUMN_NAME, Student.COLUMN_INSTITUTION,
                        Student.COLUMN_MOBILE_NUMBER, Student.COLUMN_EMAIL_ADDRESS}, Student.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare student object
        Student student = new Student(
                cursor.getInt(cursor.getColumnIndex(Student.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Student.COLUMN_INSTITUTION)),
                cursor.getString(cursor.getColumnIndex(Student.COLUMN_MOBILE_NUMBER)),
                cursor.getString(cursor.getColumnIndex(Student.COLUMN_EMAIL_ADDRESS)));


        // close the db connection
        cursor.close();

        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Student.TABLE_NAME + " ORDER BY " +
                Student.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex(Student.COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME)));
                student.setInstitution(cursor.getString(cursor.getColumnIndex(Student.COLUMN_INSTITUTION)));
                student.setMobileNumber(cursor.getString(cursor.getColumnIndex(Student.COLUMN_MOBILE_NUMBER)));
                student.setEmailAddress(cursor.getString(cursor.getColumnIndex(Student.COLUMN_EMAIL_ADDRESS)));

                students.add(student);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return students list
        return students;
    }

    public int getStudentsCount() {
        String countQuery = "SELECT  * FROM " + Student.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateStudents(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Student.COLUMN_NAME, student.getName());

        // updating row
        return db.update(Student.TABLE_NAME, values, Student.COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Student.TABLE_NAME, Student.COLUMN_ID + " = ?",
                new String[]{String.valueOf(student.getId())});
        db.close();
    }
}
