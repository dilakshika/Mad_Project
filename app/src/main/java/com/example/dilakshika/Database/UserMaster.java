package com.example.dilakshika.Database;

import android.provider.BaseColumns;

public class UserMaster {
    private UserMaster(){

    }
    public static class User implements BaseColumns{
        public static final String TABLE_NAME = "ravi";
        public static final String COL_1 = "id";
        public static final String COL_2 = "username";
        public static final String COL_3 = "password";
        public static final String COL_4 = "type";
    }
}
