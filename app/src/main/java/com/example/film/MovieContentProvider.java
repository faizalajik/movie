package com.example.film.viewmodel;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import com.example.film.database.MovieDatabase;
import com.example.film.database.MovieDb;

public class MovieContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.film.viewmodel";
    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;
    private static final UriMatcher uriMacher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMacher.addURI(AUTHORITY,"favorite", MOVIE);
        uriMacher.addURI(AUTHORITY,"favorite/#", MOVIE_ID);
    }
    @Override
    public boolean onCreate() {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        final int code = uriMacher.match(uri);
        final Context context = getContext();
        final Cursor cursor;
        MovieDatabase db = Room.databaseBuilder(getContext(), MovieDatabase.class, "favorite-database")
                .allowMainThreadQueries().build();
        if(code == MOVIE){
            cursor =  db.movieDao().getAll();
        }else{
            cursor =  db.movieDao().getMovieId((int) ContentUris.parseId(uri));
        }
        cursor.setNotificationUri(context.getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMacher.match(uri)){
            case MOVIE:
            final Context context = getContext();
            final int id = values.getAsInteger("id");
                MovieDatabase db = Room.databaseBuilder(context, MovieDatabase.class, "favorite-database")
                        .allowMainThreadQueries().build();
                db.movieDao().insertMovie(MovieDb.fromContentValue(values));

                return ContentUris.withAppendedId(uri,id);
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
    }
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
