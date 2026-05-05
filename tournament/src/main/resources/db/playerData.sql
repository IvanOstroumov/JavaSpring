insert into Player (id, name, surname, age, gender, race, city, team_id, gameProfile_id, isdeleted)
values (
            NEXT VALUE FOR player_seq, 'Mundus', 'Kundus', 23, 'Male', 'Dirty Indian', 'Mumbai', select distinct id from Team where name = 'NaVi', select distinct id from game_profile where nickname = 'Tw1nKLess_', false
       ),
       (
           NEXT VALUE FOR player_seq, 'Matadora', 'Tiki', 19, 'Male', 'Pure White', 'Barcelona', select distinct id from Team where name = 'SIGMA', select distinct id from game_profile where nickname = 'NeMo_Ivan', false
       ),
       (
           NEXT VALUE FOR player_seq, 'Ken', 'Tukki', 81, 'Male', 'Red American', 'Tampa', select distinct id from Team where name = 'Virtus PRO', select distinct id from game_profile where nickname = 'NeMo_Krab1K', false
       );

