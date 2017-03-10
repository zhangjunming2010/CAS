/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/6 11:30:38                            */
/*==============================================================*/


drop table if exists cas_category;

drop table if exists cas_evaluation;

drop table if exists cas_evaluation_assess;

drop table if exists cas_evaluation_history;

drop table if exists cas_option;

drop table if exists cas_question;

drop table if exists cas_type;

drop table if exists cas_user;

drop table if exists evaluation_question;

/*==============================================================*/
/* Table: cas_category                                          */
/*==============================================================*/
create table cas_category
(
   cc_id                varchar(50) not null,
   cc_name              varchar(50),
   primary key (cc_id)
);

alter table cas_category comment '题目类别表';

/*==============================================================*/
/* Table: cas_evaluation                                        */
/*==============================================================*/
create table cas_evaluation
(
   ce_id                varchar(50) not null,
   ce_title             varchar(255),
   ce_status            bool,
   ce_des               varchar(255),
   primary key (ce_id)
);


alter table cas_evaluation comment '测评记录表';

/*==============================================================*/
/* Table: cas_evaluation_assess                                 */
/*==============================================================*/
create table cas_evaluation_assess
(
   cea_id               varchar(50) not null,
   ce_id                varchar(50),
   cea_min              int,
   cea_max              int,
   cea_desc             varchar(255),
   primary key (cea_id)
);

alter table cas_evaluation_assess comment '测评对应的评价划分说明表';

/*==============================================================*/
/* Table: cas_evaluation_history                                */
/*==============================================================*/
create table cas_evaluation_history
(
   ceh_id               varchar(50) not null,
   cu_id                varchar(50),
   ceh_title            varchar(50),
   ceh_status           bool,
   ceh_date             varchar(50),
   ceh_options          longtext,
   ceh_score            int,
   ceh_desc             varchar(255),
   primary key (ceh_id)
);


alter table cas_evaluation_history comment '测评记录表';

/*==============================================================*/
/* Table: cas_option                                            */
/*==============================================================*/
create table cas_option
(
   co_id                varchar(50) not null,
   cq_id                varchar(50) not null,
   co_title             varchar(255),
   co_score             int,
   primary key (co_id)
);


alter table cas_option comment '题目对应选项表';

/*==============================================================*/
/* Table: cas_question                                          */
/*==============================================================*/
create table cas_question
(
   cq_id                varchar(50) not null,
   ct_id                varchar(50) not null,
   cc_id                varchar(50) not null,
   cq_title             varchar(255),
   primary key (cq_id)
);


alter table cas_question comment '题目表';

/*==============================================================*/
/* Table: cas_type                                              */
/*==============================================================*/
create table cas_type
(
   ct_id                varchar(50) not null,
   ct_name              varchar(50),
   primary key (ct_id)
);


alter table cas_type comment '题目类型表';

/*==============================================================*/
/* Table: cas_user                                              */
/*==============================================================*/
create table cas_user
(
   cu_id                varchar(50) not null,
   cu_account           varchar(50),
   cu_password          varchar(255),
   cu_email             varchar(50),
   cu_type              varchar(3),
   cu_status            bool,
   cu_name              varchar(10),
   cu_role              int,
   primary key (cu_id)
);

alter table cas_user comment '用户表';

/*==============================================================*/
/* Table: evaluation_question                                   */
/*==============================================================*/
create table evaluation_question
(
   ce_id                varchar(50) not null,
   cq_id                varchar(50) not null,
   primary key (ce_id, cq_id)
);


alter table evaluation_question comment '测评记录同题目关系表';

alter table cas_evaluation_assess add constraint FK_Relationship_7 foreign key (ce_id)
      references cas_evaluation (ce_id) on delete restrict on update restrict;

alter table cas_evaluation_history add constraint FK_Relationship_5 foreign key (cu_id)
      references cas_user (cu_id) on delete restrict on update restrict;

alter table cas_option add constraint FK_Relationship_3 foreign key (cq_id)
      references cas_question (cq_id) on delete restrict on update restrict;

alter table cas_question add constraint FK_Relationship_1 foreign key (cc_id)
      references cas_category (cc_id) on delete restrict on update restrict;

alter table cas_question add constraint FK_Relationship_2 foreign key (ct_id)
      references cas_type (ct_id) on delete restrict on update restrict;

alter table evaluation_question add constraint FK_evaluation_question foreign key (ce_id)
      references cas_evaluation (ce_id) on delete restrict on update restrict;

alter table evaluation_question add constraint FK_evaluation_question2 foreign key (cq_id)
      references cas_question (cq_id) on delete restrict on update restrict;

