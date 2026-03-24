delete from Item;
insert into Item (id,code,type,name,price,itemcount)
values (NEXT VALUE FOR item_seq, 'faq-67','Loshara', 'Quan',1.99, 1233),
       (NEXT VALUE FOR item_seq,'dal-12','Legenda','Ivan',109.12, 1),
       (NEXT VALUE FOR item_seq,'pop-91','Ourara','Messie',9999.99, 23);