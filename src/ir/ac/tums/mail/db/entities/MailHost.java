package ir.ac.tums.mail.db.entities;

/***********************************************************************
 * Module:  MailHost.java
 * Author:  OEMUSER
 * Created: 2004/07/26 03:00:51 È.Ù
 * Purpose: Defines the Class MailHost
 ***********************************************************************/

import java.util.*;

public class MailHost
{
   public long hostID;
   public java.lang.String hostname;

    public long getHostID() {
        return hostID;
    }

    public void setHostID(long hostID) {
        this.hostID = hostID;
    }

    public byte getMailer() {
        return mailer;
    }

    public void setMailer(byte mailer) {
        this.mailer = mailer;
    }

    public byte getOs() {
        return os;
    }

    public void setOs(byte os) {
        this.os = os;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public java.lang.String ip;
   public byte os;
   public byte mailer;

}

