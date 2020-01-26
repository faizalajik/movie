package com.example.film.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM favorite")
    List<MovieDb> getAllData();

    @Insert
    void insertMovie(MovieDb movieDb);

    @Delete
    void deleteMovie (MovieDb movieDb);

    @Query("SELECT * FROM favorite WHERE id = :id")
    List<MovieDb> getMovie(int id);

    @Query("SELECT * FROM favorite WHERE category = :category")
    List<MovieDb> getMovie(String category);
}
