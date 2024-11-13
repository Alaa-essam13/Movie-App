package com.example.movieapp.data.local.Movie

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface MovieDao {

    @Upsert
    suspend fun upsertMovieList(movies: List<MovieEntity>)

    @Query("select * from MovieEntity where id = :id")
    suspend fun getMovieById(id: Int): MovieEntity

    @Query("SELECT * from MovieEntity where category = :category")
    suspend fun getMovieListByCategory(category: String): List<MovieEntity>

}