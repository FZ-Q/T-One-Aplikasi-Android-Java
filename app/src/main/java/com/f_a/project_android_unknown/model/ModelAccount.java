package com.f_a.project_android_unknown.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_account",
        indices = {@Index("Id")}
)
public class ModelAccount {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "Id")
    private Integer Id;

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    private byte[] Photo;

    @ColumnInfo(name = "User")
    private String User;

    @ColumnInfo(name = "Email")
    private String Email;

    @ColumnInfo(name = "No_Pon")
    private String No_Pon;

    @ColumnInfo(name = "TL")
    private String TL;

    @ColumnInfo(name = "JK")
    private String JK;

    @ColumnInfo(name = "Pass")
    private String Pass;

    public ModelAccount() {}

    @NonNull
    public Integer getId() {
        return Id;
    }

    public void setId(@NonNull Integer id) {
        Id = id;
    }

    public byte[] getPhoto() {
        return Photo;
    }

    public void setPhoto(byte[] photo) {
        Photo = photo;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNo_Pon() {
        return No_Pon;
    }

    public void setNo_Pon(String no_Pon) {
        No_Pon = no_Pon;
    }

    public String getTL() {
        return TL;
    }

    public void setTL(String TL) {
        this.TL = TL;
    }

    public String getJK() {
        return JK;
    }

    public void setJK(String JK) {
        this.JK = JK;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
