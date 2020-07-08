package com.fes.ui_exercise1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fes.ui_exercise1.model.Info;

import java.util.List;

@Dao
public interface InfoDao {
    @Query("SELECT * FROM INFORMATION ORDER BY ID")
    List<Info> loadAllInfo();

    @Insert
    void insertInfo(Info info);

    @Update
    void updateInfo(Info info);

    @Delete
    void deleteInfo(Info info);

    @Query("SELECT * FROM INFORMATION WHERE id = :id")
    Info loadInfoById(int id);

}
