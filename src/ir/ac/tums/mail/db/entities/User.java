package ir.ac.tums.mail.db.entities;

/***********************************************************************
 * Module:  User.java
 * Author:  OEMUSER
 * Created: 2004/07/26 03:00:51 È.Ù
 * Purpose: Defines the Class User
 ***********************************************************************/

import java.util.*;
import java.sql.*;
import java.sql.Date;

public class User
{

    public User ()
    {
        unit = new Unit();
        spec = new Speciality();
        host = new MailHost();
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public Speciality getSpec() {
        return spec;
    }

    public void setSpec(Speciality spec) {
        this.spec = spec;
    }

    public MailHost getHost() {
        return host;
    }

    public void setHost(MailHost host) {
        this.host = host;
    }

    Unit unit;
    Speciality spec;
    MailHost host;

    public long userID;

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getOldemail() {
        return oldemail;
    }

    public void setOldemail(String oldemail) {
        this.oldemail = oldemail;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public String getWorkphone() {
        return workphone;
    }

    public void setWorkphone(String workphone) {
        this.workphone = workphone;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

   public java.lang.String username;
   public java.lang.String password;
   public java.lang.String firstname;
   public java.lang.String lastname;
   public byte type;
   public java.lang.String oldemail;
   public java.lang.String homephone;
   public java.lang.String workphone;
   public java.lang.String workplace;

    public java.sql.Date getExpdate() {
        return expdate;
    }

    public void setExpdate(Date expdate) {
        this.expdate = expdate;
    }

    public java.sql.Date expdate;

    public Date getJoindate() {
        return joindate;
    }

    public void setJoindate(Date joindate) {
        this.joindate = joindate;
    }

    public java.sql.Date joindate;

}

