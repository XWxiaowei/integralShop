/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/26 16:08:12                           */
/*==============================================================*/


drop index idx_addressid on shop_address;

drop table if exists shop_address;

drop index idx_catalogId on shop_catalog;

drop table if exists shop_catalog;

drop index idx_merchantId on shop_merchant;

drop table if exists shop_merchant;

drop index idx_orderId on shop_order;

drop table if exists shop_order;

drop index idx_productId on shop_product;

drop table if exists shop_product;

drop index idx_user on shop_user;

drop table if exists shop_user;

/*==============================================================*/
/* Table: shop_address                                          */
/*==============================================================*/
create table shop_address
(
   id                   bigint(20) not null,
   addressId            bigint(20) not null comment '��ַId',
   user_id              bigint(20) not null comment '�û�Id',
   receiver_name        varchar(50) not null comment '�ջ�������',
   receiver_phone       varchar(18) not null comment '�ջ����ֻ���',
   email                varchar(30) not null comment '����',
   receiver_address     varchar(150) comment '��ϸ��ַ',
   province             varchar(18) comment 'ʡ',
   city                 varchar(18) comment '��',
   area                 varchar(18) comment '��',
   create_time          datetime,
   update_time          datetime,
   delete_flag          int(1) comment 'ɾ��Flag',
   primary key (id)
);

/*==============================================================*/
/* Index: idx_addressid                                         */
/*==============================================================*/
create unique index idx_addressid on shop_address
(
   addressId
);

/*==============================================================*/
/* Table: shop_catalog                                          */
/*==============================================================*/
create table shop_catalog
(
   id                   bigint(20) not null,
   catalog_id           bigint(20) not null comment '����Id',
   parent_catalogId     bigint(20) not null,
   catalog_level        bigint(20) not null,
   catalog_name         varchar(150) not null,
   create_time          datetime,
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

alter table shop_catalog comment '����';

/*==============================================================*/
/* Index: idx_catalogId                                         */
/*==============================================================*/
create index idx_catalogId on shop_catalog
(
   catalog_id
);

/*==============================================================*/
/* Table: shop_merchant                                         */
/*==============================================================*/
create table shop_merchant
(
   id                   bigint(20) not null,
   merchant_id          bigint(20) not null,
   merchant_name        varchar(150) not null,
   crecat_time          datetime,
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

/*==============================================================*/
/* Index: idx_merchantId                                        */
/*==============================================================*/
create unique index idx_merchantId on shop_merchant
(
   merchant_id
);

/*==============================================================*/
/* Table: shop_order                                            */
/*==============================================================*/
create table shop_order
(
   id                   bigint(20) not null,
   order_id             bigint(20) not null comment '����Id��������ƽ̨�����ţ�',
   product_id           bigint(20) not null comment '��ƷID',
   user_id              bigint(20) not null comment '�û�Id',
   order_type           int(2) not null default 2 comment '����״̬ 0�Ѹ��� 1ʧ�� 2 ������ 3 ��ȡ�� 4 �ѷ��� 5 �˿��� 6 ���˿Ĭ��Ϊ2',
   order_info           varchar(255) not null comment '��������',
   trade_no             varchar(50) not null comment '֧��ƽ̨������',
   failure_reason       varchar(50),
   count                int(3) not null comment '��Ʒ����',
   price                decimal(8,2) not null,
   address_id           bigint(20) not null,
   pay_type             int(2) not null,
   create_time          datetime,
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

alter table shop_order comment '������';

/*==============================================================*/
/* Index: idx_orderId                                           */
/*==============================================================*/
create unique index idx_orderId on shop_order
(
   order_id
);

/*==============================================================*/
/* Table: shop_product                                          */
/*==============================================================*/
create table shop_product
(
   id                   bigint(20) not null,
   product_id           bigint(20) not null comment '��ƷID',
   product_name         varchar(60) not null comment '��Ʒ����',
   merchant_id          bigint(20) not null comment '�̻�Id',
   catalog_id           bigint(20) not null comment '����ID',
   price                decimal(8,2) not null comment '����',
   inventory            int(10) not null comment '���',
   selled_num           int(10) not null comment '��������',
   info                 text comment '��Ʒ����',
   onsell_start_time    datetime not null comment '��Ʒ�ϼ�ʱ��',
   onsell_end_time      datetime not null comment '��Ʒ�¼�ʱ��',
   create_time          datetime comment '����ʱ��',
   update_time          datetime comment '����ʱ��',
   delete_flag          int(1) default 0 comment 'ɾ��Flag
            ',
   primary key (id)
);

/*==============================================================*/
/* Index: idx_productId                                         */
/*==============================================================*/
create index idx_productId on shop_product
(
   product_id
);

/*==============================================================*/
/* Table: shop_user                                             */
/*==============================================================*/
create table shop_user
(
   id                   bigint(20) not null comment '����ID',
   user_id              bigint(20) not null comment '�û�ID',
   user_code            varchar(10) not null comment '�û���',
   password             varchar(10) not null comment '����',
   create_time          datetime comment '����ʱ��',
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

alter table shop_user comment '�û���';

/*==============================================================*/
/* Index: idx_user                                              */
/*==============================================================*/
create index idx_user on shop_user
(
   user_id
);

