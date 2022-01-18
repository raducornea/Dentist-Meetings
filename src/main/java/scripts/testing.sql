-- toate meeting-urile detaliate ale unui dentist
select * from dentists d, dentist_details dd, agreements a, meetings m
where d.dentist_id = 3 and dd.dentists_dentist_id = 3 and a.dentists_dentist_id = 3 and m.agreements_agreement_id = a.agreement_id;

-- se incearca inserarea aceluiasi user
insert into dentists(dentist_first_name, dentist_last_name) values('Xenia', 'Japen');

-- redenumeste dentistul
update dentists
set dentist_last_name = 'Vandanna'
WHERE dentist_last_name = 'Vandana';

-- selecteaza specializare si calificare dentist
select dentist_specialization, dentist_qualification from dentist_details
where dentists_dentist_id = 1;

-- actualizare specializare dentist
update dentist_details
set dentist_specialization = 'STOMATOLOG PROFESIONIST'
WHERE dentists_dentist_id = 1;

-- actualizare nume persoana
update clients
set client_last_name = 'MENDELEEV'
WHERE client_last_name = 'Mendeleev';

-- actualizare PID unei persoane atunci cand isi greseste CNP-ul
update client_details
set pid = '4958485757571'
WHERE clients_client_id = 2;

-- redenumire centru
update centres
set centre_name = '...:::Magic Toothbrush:::...'
WHERE centre_id = 1;








-- se cauta dentistii care sunt si clienti -> ei ar trebui sa nu aiba dreptul de a fi repartizati la ei insisi
select client_id, dentist_id from clients, dentists, client_details cd, dentist_details dd
where client_first_name = dentist_first_name and client_last_name = dentist_last_name and cd.pid = dd.pid;

-- pentru un client, se cauta toti dentistii care nu sunt el (adica toti dentistii valabili unui pacient):
select client_id, dentist_id from clients, dentists, client_details cd, dentist_details dd
where not(client_first_name = dentist_first_name and client_last_name = dentist_last_name) 
and cd.clients_client_id = client_id and dd.dentists_dentist_id = dentist_id
and client_id = 1;

-- modifica contractul cu un dentist
update agreements
set dentists_dentist_id = 8
WHERE agreement_id = 12;




-- transforma un meeting intr-un format respectat
select TO_CHAR(meeting_start_time, 'dd.mm.yyyy HH24:MI') from meetings;

-- se incearca inserarea unei date invalide mai mici decat data curenta
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('13.1.2021 13:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('13.1.2021 14:00', 'dd.mm.yyyy HH24:MI'), 'Aparat Dentar', 3, 5);
-- se incearca inserarea unei date de start mai mari decat data de sfarsit meeting  
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('13.1.2023 13:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('12.1.2023 14:00', 'dd.mm.yyyy HH24:MI'), 'Aparat Dentar', 3, 5);
-- se incearca inserarea aceluiasi client la acelasi contract
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('13.1.2022 13:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('13.1.2022 14:00', 'dd.mm.yyyy HH24:MI'), 'Aparat Dentar', 2, 7);
    
    -- incerci sa inserezi in intervale orare similare (trebuie verificat 1. daca clientul poate, 2. daca pe contract (cu dentistul) se poate)
    -- se poate doar in 2 cazuri: (start & end) < TOATE meeting-urile SAU (start & end) > TOATE meeting-urile -> atat pentru client, cat si contract
    
            select m.meeting_start_time, m.meeting_end_time, m.clients_client_id, m.agreements_agreement_id
                from meetings m 
                
                where (m.clients_client_id = 2 
                      and not((TO_DATE('13.1.2022 13:30', 'dd.mm.yyyy HH24:MI') < m.meeting_start_time AND TO_DATE('13.1.2022 14:00', 'dd.mm.yyyy HH24:MI') < m.meeting_end_time) or
                      (TO_DATE('13.1.2022 13:30', 'dd.mm.yyyy HH24:MI') > m.meeting_start_time AND TO_DATE('13.1.2022 14:00', 'dd.mm.yyyy HH24:MI') > m.meeting_end_time))
                )
                
                or (m.agreements_agreement_id = 7 
                    and not((TO_DATE('13.1.2022 13:30', 'dd.mm.yyyy HH24:MI') < m.meeting_start_time AND TO_DATE('13.1.2022 14:00', 'dd.mm.yyyy HH24:MI') < m.meeting_end_time) or
                    (TO_DATE('13.1.2022 13:30', 'dd.mm.yyyy HH24:MI') > m.meeting_start_time AND TO_DATE('13.1.2022 14:00', 'dd.mm.yyyy HH24:MI') > m.meeting_end_time))
                );
            -- de pus in documentatie cu cele 2 unique-uri ca un pacient sa nu aiba in aceeasi programare SI dentistul sa nu aiba in aceeasi programare
            -- pot insera daca trec de asta
            -- de pus in documentatie
            -- de sters chestia aia bruh
            
    -- inserezi in meeting daca e null
    -- mai multe meeting-uri pe client - dar sa nu fie in acelasi interval orar in oricare dintre celelalte programari
    insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
        values(TO_DATE('13.1.2022 13:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('13.1.2022 14:00', 'dd.mm.yyyy HH24:MI'), 'Aparat Dentar', 2, 7);
        

    
        -- daca vrei actualizare meeting, trebuie actualizata intalnirea la acel contract   
        update meetings
        set meeting_start_time = TO_DATE('13.2.2022 13:30', 'dd.mm.yyyy HH24:MI'),
        meeting_end_time = TO_DATE('13.2.2022 14:00', 'dd.mm.yyyy HH24:MI')
        WHERE clients_client_id = 2
        and agreements_agreement_id = 7;

select * from meetings;


-- updateaza operatiune efectuiata de dentist
update meetings
set meeting_problem = 'Tooth Implant'
WHERE meeting_problem = 'Operatie';

select client_id from clients where
client_first_name = 'first_name' and client_last_name = 'last_name'

select a.agreement_id, d.dentist_first_name, d.dentist_last_name, c.centre_name, c.centre_phone
from agreements a, dentists d, centres c
where a.dentists_dentist_id = d.dentist_id and a.centres_centre_id = c.centre_id;

with my_clients as (select client_first_name, client_last_name, client_id from clients),
my_agreements as (select * from agreements)
select cl.client_first_name, cl.client_last_name, cntr.centre_name, cntr.centre_location, dents.dentist_first_name, dents. dentist_last_name, (TO_CHAR(m.meeting_start_time, 'dd.mm.yyyy HH24:MI')), (TO_CHAR(m.meeting_end_time, 'dd.mm.yyyy HH24:MI')), m.meeting_problem
from meetings m, my_clients cl, my_agreements agr, (select centre_id, centre_name, centre_location from centres) cntr, (select dentist_id, dentist_first_name, dentist_last_name from dentists) dents
where cl.client_id = m.clients_client_id
and agr.agreement_id = m.agreements_agreement_id
and cntr.centre_id = agr.centres_centre_id
and dents.dentist_id = agr.dentists_dentist_id;