/*==============================================================*/
/* Database name:  Tums Mail System                             */
/* DBMS name:      MySQL 3.23                                   */
/* Created on:     8/16/2004 2:59:42 PM                         */
/*==============================================================*/


drop index isHost_FK on user;

drop index isIn_FK on user;

drop index isSpecializedIn_FK on user;

drop table if exists users;

drop table if exists unit;

drop table if exists speciality;

drop table if exists host;

/*==============================================================*/
/* Table: host                                                  */
/*==============================================================*/
create table if not exists host
(
   hostID                         int                            not null AUTO_INCREMENT,
   hostname                       varchar(50),
   ip                             varchar(20),
   os                             tinyint,
   mailer                         tinyint,
   primary key (hostID)
);

/*==============================================================*/
/* Table: speciality                                            */
/*==============================================================*/
create table if not exists speciality
(
   specialityID                   int                            not null AUTO_INCREMENT,
   name                           varchar(40),
   primary key (specialityID)
);

/*==============================================================*/
/* Table: unit                                                  */
/*==============================================================*/
create table if not exists unit
(
   unitID                         int                            not null AUTO_INCREMENT,
   name                           varchar(40),
   primary key (unitID)
);

/*==============================================================*/
/* Table: user                                                 */
/*==============================================================*/
create table if not exists user
(
   userID                         int                            not null AUTO_INCREMENT,
   hostID                         int                            not null,
   unitID                         int                            not null,
   specialityID                   int                            not null,
   username                       varchar(50)                    not null,
   password                     varchar(50),
   firstname                      varchar(50),
   lastname                       varchar(50),
   type                         tinyint,
   oldemail                       varchar(50),
   homephone                      varchar(40),
   workphone                      varchar(40),
   workplace                      varchar(40),
   expdate                        date,
   joindate                       date,
   primary key (userID)
);

/*==============================================================*/
/* Index: isIn_FK                                               */
/*==============================================================*/
create index isIn_FK on user
(
   unitID
);

/*==============================================================*/
/* Index: isSpecializedIn_FK                                    */
/*==============================================================*/
create index isSpecializedIn_FK on user
(
   specialityID
);

/*==============================================================*/
/* Index: isHost_FK                                             */
/*==============================================================*/
create index isHost_FK on user
(
   hostID
);

