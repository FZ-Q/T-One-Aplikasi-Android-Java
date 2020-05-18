package com.f_a.project_android_unknown.service;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.f_a.project_android_unknown.model.ModelAccount;
import com.f_a.project_android_unknown.model.ModelLogin;

import java.util.List;

@Dao
public interface DAOAccount {
    @Query("SELECT * FROM tb_account")
    public List<ModelAccount> FindAll();

    @Query("SELECT * FROM tb_account WHERE Id = :Id")
    public ModelAccount FindOneById(Integer Id);

    @Query("SELECT * FROM tb_account WHERE User = :IUser")
    public ModelAccount FindOneByUser(String IUser);

    @Query("SELECT Id, COUNT(User) AS CUser, COUNT(Pass) AS CPass FROM tb_account WHERE User = :IUser AND Pass = :IPass")
    public ModelLogin Login(String IUser, String IPass);

    @Insert
    public void save(ModelAccount modelAccount);

    @Update
    public void update(ModelAccount modelAccount);

    @Delete
    public void delete(ModelAccount modelAccount);
}
