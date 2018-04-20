package com.faisal.totassignment.data;

public class Student {
    public static final String TABLE_NAME = "student";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_INSTITUTION = "institution";
    public static final String COLUMN_MOBILE_NUMBER = "mobile_number";
    public static final String COLUMN_EMAIL_ADDRESS = "email_address";


    private int id;
    private String name;
    private String institution;
    private String mobileNumber;
    private String emailAddress;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_INSTITUTION + " TEXT,"
                    + COLUMN_MOBILE_NUMBER + " TEXT,"
                    + COLUMN_EMAIL_ADDRESS + " TEXT"
                    + ")";

    public Student() {

    }

    public Student(int id, String name, String institution, String mobileNumber, String emailAddress) {
        this.id = id;
        this.name = name;
        this.institution = institution;
        this.mobileNumber = mobileNumber;
        this.emailAddress = emailAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}

