delete from Instrument;
INSERT INTO Instrument(id, type, brand, model, price, stock) VALUES
                                                                 (NEXT VALUE FOR instrument_seq,'guitar', 'Fender','Stratocaster',199.99, 3),
                                                                 (NEXT VALUE FOR instrument_seq,'guitar','Gibson','Les Paul',249.99,2),
                                                                 (NEXT VALUE FOR instrument_seq,'piano','Yamaha','CFX',239.99,5),
                                                                 (NEXT VALUE FOR instrument_seq,'drums','Minesota','Illinois',149.99,11);