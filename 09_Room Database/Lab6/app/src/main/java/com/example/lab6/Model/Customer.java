package com.example.lab6.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "customers")    // change table name as "users"
public class Customer implements Parcelable {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_name")
    private String name;
    @ColumnInfo(name = "user_rating")
    private double rating;
    @ColumnInfo(name = "user_comment")
    private String comment;

    // Constructor
    public Customer(String name, double rating, String comment) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    protected Customer(Parcel in) {
        name = in.readString();
        rating = in.readDouble();
        comment = in.readString();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeDouble(rating);
        dest.writeString(comment);
    }
}
