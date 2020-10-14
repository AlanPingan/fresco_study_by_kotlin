package com.project.alan.frescolearningbykotlin.serialize;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alan on 2018/5/21.
 */

public class StudentParcelable implements Parcelable {
    protected StudentParcelable(Parcel in) {
    }

    public static final Creator<StudentParcelable> CREATOR = new Creator<StudentParcelable>() {
        @Override
        public StudentParcelable createFromParcel(Parcel in) {
            return new StudentParcelable(in);
        }

        @Override
        public StudentParcelable[] newArray(int size) {
            return new StudentParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
