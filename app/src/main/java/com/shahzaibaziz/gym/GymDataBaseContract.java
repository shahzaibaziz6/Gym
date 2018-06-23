package com.shahzaibaziz.gym;

import android.provider.BaseColumns;

public final class GymDataBaseContract {
    private  GymDataBaseContract(){}


    public static class GymModel implements BaseColumns{
        public static final String TABLE_NAME="Customer";
        public static final String ID="ID";
        public static final String FIRST_NAME="FirstName";
        public static final String LAST_NAME="secondName";
        public static final String PHONE_NUMBER="PhoneNumber";
        public static final String ADDRESS="Address";
        public static final String STARTING_DATE="Date";
        public static final String USER_PIC="Picture";
    }
}
