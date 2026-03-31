delete from Customer;
insert into Customer (id, name,surname,age,city,ccnumber,ccexpiration,cccvv)
values (NEXT VALUE FOR customer_seq, 'Klein', 'Moretti', 19, 'Tingon', 7091591294056401, '01/27', 191),
       (NEXT VALUE FOR customer_seq,'Sherlock', 'Moriarti', 19, 'Backland' ,8482947278941982, '09/29', 512),
       (NEXT VALUE FOR customer_seq,'Gehrman', 'Sparrow', 23, 'Sea' ,0174827499174572, '12/28', 112);