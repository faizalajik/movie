package com.example.film.database;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class MovieDb implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "average")
    private double average;
    @ColumnInfo(name = "year")
    private String year;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "poster_path")
    private String posterPath;
    @ColumnInfo(name = "category")
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public static Creator<MovieDb> getCREATOR() {
        return CREATOR;
    }

    public MovieDb(Parcel in) {
        id = in.readInt();
        title = in.readString();
        average = in.readDouble();
        overview = in.readString();
        posterPath = in.readString();
        category = in.readString();
        year = in.readString();
    }

    public static final Creator<MovieDb> CREATOR = new Creator<MovieDb>() {
        @Override
        public MovieDb createFromParcel(Parcel in) {
            return new MovieDb(in);
        }

        @Override
        public MovieDb[] newArray(int size) {
            return new MovieDb[size];
        }
    };

    public MovieDb() {

    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeDouble(average);
        dest.writeString(overview);
        dest.writeString(posterPath);
        dest.writeString(category);
        dest.writeString(year);
    }
    public static MovieDb fromContentValue(ContentValues values){
        final MovieDb movieDb =  new MovieDb();
        if(values.containsKey("id")){
            movieDb.id = values.getAsInteger("id");
        }
        if (values.containsKey("title")){
            movieDb.title = values.getAsString("title");
        }
        if (values.containsKey("overview")){
            movieDb.overview = values.getAsString("overview");
        }
        if (values.containsKey("poster_path")){
            movieDb.posterPath = values.getAsString("poster_path");
        }
        if (values.containsKey("year")){
            movieDb.year = values.getAsString("year");
        }
        return movieDb;
    }
}
