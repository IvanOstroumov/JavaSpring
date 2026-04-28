delete from Reservation;
delete from Customer;
delete from Address;
alter sequence reservation_seq restart with 1;
alter sequence customer_seq restart with 1;
alter sequence address_seq restart with 1;