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
   addressId            bigint(20) not null comment '地址Id',
   user_id              bigint(20) not null comment '用户Id',
   receiver_name        varchar(50) not null comment '收货人姓名',
   receiver_phone       varchar(18) not null comment '收货人手机号',
   email                varchar(30) not null comment '邮箱',
   receiver_address     varchar(150) comment '详细地址',
   province             varchar(18) comment '省',
   city                 varchar(18) comment '市',
   area                 varchar(18) comment '区',
   create_time          datetime,
   update_time          datetime,
   delete_flag          int(1) comment '删除Flag',
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
   catalog_id           bigint(20) not null comment '分类Id',
   parent_catalogId     bigint(20) not null,
   catalog_level        bigint(20) not null,
   catalog_name         varchar(150) not null,
   create_time          datetime,
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

alter table shop_catalog comment '类别表';

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
   order_id             bigint(20) not null comment '订单Id（第三方平台订单号）',
   product_id           bigint(20) not null comment '商品ID',
   user_id              bigint(20) not null comment '用户Id',
   order_type           int(2) not null default 2 comment '订单状态 0已付款 1失败 2 待付款 3 已取消 4 已发货 5 退款中 6 已退款，默认为2',
   order_info           varchar(255) not null comment '订单详情',
   trade_no             varchar(50) not null comment '支付平台订单号',
   failure_reason       varchar(50),
   count                int(3) not null comment '产品个数',
   price                decimal(8,2) not null,
   address_id           bigint(20) not null,
   pay_type             int(2) not null,
   create_time          datetime,
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

alter table shop_order comment '订单表';

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
   product_id           bigint(20) not null comment '商品ID',
   product_name         varchar(60) not null comment '商品名称',
   merchant_id          bigint(20) not null comment '商户Id',
   catalog_id           bigint(20) not null comment '分类ID',
   price                decimal(8,2) not null comment '单价',
   inventory            int(10) not null comment '库存',
   selled_num           int(10) not null comment '已售数量',
   info                 text comment '商品描述',
   onsell_start_time    datetime not null comment '商品上架时间',
   onsell_end_time      datetime not null comment '商品下架时间',
   create_time          datetime comment '创建时间',
   update_time          datetime comment '更新时间',
   delete_flag          int(1) default 0 comment '删除Flag
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
   id                   bigint(20) not null comment '自增ID',
   user_id              bigint(20) not null comment '用户ID',
   user_code            varchar(10) not null comment '用户名',
   password             varchar(10) not null comment '密码',
   create_time          datetime comment '创建时间',
   update_time          datetime,
   delete_flag          int(1),
   primary key (id)
);

alter table shop_user comment '用户表';

/*==============================================================*/
/* Index: idx_user                                              */
/*==============================================================*/
create index idx_user on shop_user
(
   user_id
);

