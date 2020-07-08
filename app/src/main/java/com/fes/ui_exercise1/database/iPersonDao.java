package com.fes.ui_exercise1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.fes.ui_exercise1.model.Person;

import java.util.List;

@Dao
public interface iPersonDao {
    //CRUD
    @Insert
    void insertPerson(Person person);

    @Query("SELECT * FROM PERSON ORDER BY ID")
    List<Person> loadAllPersons();

    @Update
    void updatePerson(Person person);

    @Delete
    void deletePerson(Person person);
}
