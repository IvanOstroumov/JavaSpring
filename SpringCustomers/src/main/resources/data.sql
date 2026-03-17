delete from Customer;
insert into Customer (id, name,surname,age,city,ccnumber,ccexpiration,cccvv)
values (NEXT VALUE FOR customer_seq, 'Quan', 'Tran', 19, 'Arbedo-Castione', 4870527279016688, '04/30', 222),
       (NEXT VALUE FOR customer_seq,'Yasser', 'Oudabashi', 200, 'Caslano' , 4304777294056978, '05/29', 111),
       (NEXT VALUE FOR customer_seq,'Massimo', 'Fuxia', 23, 'Fust-silli' , 4910799127094926, '07/49', 991);