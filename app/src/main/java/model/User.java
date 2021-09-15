package model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

    private String fullname;
    private int age;
    private String address;
    private String image_path;

    public User() {
        this.fullname = "";
        this.address = "";
        this.image_path = "";
        this.age = 0;
    }

    public User(String fullname, String address, int age) {
        this.fullname = fullname;
        this.address = address;
        this.image_path = "";
        this.age = age;
    }

    public User(String fullname, String address, String image_path, int age) {
        this.fullname = fullname;
        this.address = address;
        this.image_path = image_path;
        this.age = age;
    }

    protected User(Parcel in) {
        fullname = in.readString();
        address = in.readString();
        image_path = in.readString();
        age = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullname);
        dest.writeString(address);
        dest.writeString(image_path);
        dest.writeInt(age);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getNama() {
        return fullname;
    }

    public void setNama(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}