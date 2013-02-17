package ir.ac.tums.mail.db.entities;

/***********************************************************************
 * Module:  Unit.java
 * Author:  OEMUSER
 * Created: 2004/07/26 03:00:51 È.Ù
 * Purpose: Defines the Class Unit
 ***********************************************************************/

import java.util.*;

public class Unit
{
    public long getUnitID() {
        return unitID;
    }

    public void setUnitID(long unitID) {
        this.unitID = unitID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long unitID;
   public java.lang.String name;

}

