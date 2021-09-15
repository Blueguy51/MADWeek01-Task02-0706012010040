package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Data implements Parcelable {

    public static ArrayList<User> saveList = new ArrayList<>();

    protected Data(Parcel in) {

    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {

        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
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