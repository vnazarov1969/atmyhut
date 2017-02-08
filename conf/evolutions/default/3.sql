# --- Sample dataset

# --- !Ups

insert into product (id,name,description,introduced,discontinued) values (  1,'Хлеб ржаной','Хлеб ржаной Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  2,'Хлеб пшеничный','Хлеб пшеничный Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  3,'Багет пшеничный','Багет пшеничный Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  4,'Хлеб бородинский','Хлеб бородинский Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  5,'Чиабата','Чиабата Гост:394424',null,null);

insert into product (id,name,description,introduced,discontinued) values (  6,'Круассан классический','Круассан классический Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  7,'Круассан с малиной','Круассан с малиной Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  8,'Круассан с шокаладом','Круассан с шокаладом Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  9,'Рулет с маком','Рулет с маком Гост:394424',null,null);
insert into product (id,name,description,introduced,discontinued) values (  10,'Рулет с корицей','Рулет с корицей Гост:394424',null,null);


insert into price (id,product_id, price,company_id,introduced,discontinued) values (  1, 1,45,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  2, 2,50,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  3, 3,30,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  4, 4,55,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  5, 5,65,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  6, 6,40,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  7, 7,50,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  8, 8,50,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  9, 9,33,1,null,null);
insert into price (id,product_id, price,company_id,introduced,discontinued) values (  10, 10,27,1,null,null);


# --- !Downs

delete from price;
delete from product;
