package com.felina.moviefianapp.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.felina.moviefianapp.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(tourism: List<MovieEntity>)

}