package ir.ac.tums.mail.db.entities;

/***********************************************************************
 * Module:  Speciality.java
 * Author:  OEMUSER
 * Created: 2004/07/26 03:00:51 È.Ù
 * Purpose: Defines the Class Speciality
 ***********************************************************************/

import java.util.*;

public class Speciality
{
    public long getSpecialityID() {
        return specialityID;
    }

    public void setSpecialityID(long specialityID) {
        this.specialityID = specialityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long specialityID;
   public java.lang.String name;

}

