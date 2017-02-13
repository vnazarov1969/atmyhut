# --- First database schema

# --- !Ups


create table company (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table product (
  id                        bigint not null,
  category_id 			        bigint not null default 1,
  introduced                timestamp,
  discontinued              timestamp,
  name                      varchar(255),
  description								varchar(1024),
  photo											bytea,
  constraint pk_product primary key (id))
;

create table price (
  id                        bigint not null,
  product_id                bigint,
  company_id                bigint,
  introduced                timestamp,
  discontinued              timestamp,
  price											money,
  constraint pk_price primary key (id))
;

create table computer (
  id                        bigint not null,
  name                      varchar(255),
  introduced                timestamp,
  discontinued              timestamp,
  company_id                bigint,
  constraint pk_computer primary key (id))
;

create sequence company_seq start with 1000;

create sequence computer_seq start with 1000;

create sequence product_seq start with 1000;

create sequence price_seq start with 1000;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);

alter table price add constraint fk_price_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_price_company_1 on price (company_id);

alter table price add constraint fk_price_product_1 foreign key (product_id) references product (id) on delete restrict on update restrict;
create index ix_price_product_1 on price (product_id);


# --- !Downs

 -- SET REFERENTIAL_INTEGRITY FALSE;



drop table if exists company cascade;

drop table if exists computer cascade;

drop table if exists product cascade;

drop table if exists price cascade;

 -- SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists company_seq;

drop sequence if exists computer_seq;

drop sequence if exists product_seq;

drop sequence if exists price_seq;
