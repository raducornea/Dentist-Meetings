/* Dentists */
insert into dentists(dentist_first_name, dentist_last_name) values('John', 'Rex');
insert into dentists(dentist_first_name, dentist_last_name) values('Mihai', 'Escu');
insert into dentists(dentist_first_name, dentist_last_name) values('Adelin', 'Motoc');
insert into dentists(dentist_first_name, dentist_last_name) values('Roxana', 'Chiuwerti');
insert into dentists(dentist_first_name, dentist_last_name) values('Andreea', 'Vandana');
insert into dentists(dentist_first_name, dentist_last_name) values('Bob', 'Rossenberg');
insert into dentists(dentist_first_name, dentist_last_name) values('Maverick', 'Bot');
insert into dentists(dentist_first_name, dentist_last_name) values('Xenia', 'Japen');
insert into dentists(dentist_first_name, dentist_last_name) values('Daniel', 'Abracadabra'); --dentistul care este si client

/* Dentists' details */
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('0258473910523', 'Curatare si Tratare', 'Stomatolog', 5, 'johnrex4you@yahoo.com', 1);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('1234567890123', 'Curatare si Tratare', 'Stomatolog', 10, 'mihaita.mihai@gmail.com', 2);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('1315141531415', 'Infrumusetare dinti', 'Stomatolog', 3, 'motocadelin@dentisti.ro', 3);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('4356437345345', 'Infrumusetare dinti', 'Asistent', 2, 'roxanaqwertyuiop@gmail.com', 4);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('5464574555578', 'Curatare', 'Asistent', 1, 'vandanaandreea@yahoo.com', 5);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('5239592938586', 'Tratare', 'Asistent', 1, 'bob_rossenberg@yahoo.com', 6);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('5464647474784', 'Chirurg', 'Stomatolog', 15, 'maverickbot@yahoo.com', 7);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('1111113114456', 'Chirurg', 'Stomatolog', 10, 'xenia.japen123@yahoo.com', 8);
insert into dentist_details(pid, dentist_specialization, dentist_qualification, dentist_years_experience, email, dentists_dentist_id) 
    values('5834234521555', 'Curatare', 'Asistent', 7, 'dany22@yahoo.com', 9); --dentistul care este si client

/* Clients */
insert into clients(client_first_name, client_last_name) values('Daniel', 'Abracadabra'); --dentistul care este si client
insert into clients(client_first_name, client_last_name) values('Blake', 'Dawg');
insert into clients(client_first_name, client_last_name) values('Fimm', 'Hooman');
insert into clients(client_first_name, client_last_name) values('Leia', 'Sava');
insert into clients(client_first_name, client_last_name) values('Amidala', 'Asdaf');
insert into clients(client_first_name, client_last_name) values('George', 'Mendeleev');
insert into clients(client_first_name, client_last_name) values('Ion', 'Honda');
insert into clients(client_first_name, client_last_name) values('Cristi', 'Gogh');

/* Clients' details */
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('5834234521555', TO_DATE('2.1.2003', 'dd.mm.yyyy'), 'Bacau', 'Romania', 'dany22@yahoo.com', 1);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('5453423112111', TO_DATE('23.10.1992', 'dd.mm.yyyy'), 'Berlin', 'Germania', 'blakedawg@yahoo.com', 2);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('8789789787777', TO_DATE('27.4.1964', 'dd.mm.yyyy'), 'Paris', 'Franta', 'fimmhooman@yahoo.com', 3);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('8080888888080', TO_DATE('22.3.1972', 'dd.mm.yyyy'), 'Cluj', 'Romania', 'leiasava@yahoo.com', 4);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('7878756855859', TO_DATE('12.11.1973', 'dd.mm.yyyy'), 'Bacau', 'Romania', 'amidalaasdaf@gmail.com', 5);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('7365376736736', TO_DATE('14.7.2002', 'dd.mm.yyyy'), 'Iasi', 'Romania', 'george24.george24@gmail.com', 6);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('8457854758458', TO_DATE('9.12.2005', 'dd.mm.yyyy'), 'Bucuresti', 'Romania', 'ionhonda@gmail.com', 7);
insert into client_details(pid, client_birth_date, client_town, client_country, email, clients_client_id) 
    values('3453463788999', TO_DATE('30.11.2007', 'dd.mm.yyyy'), 'Bacau', 'Romania', 'cristi_gogh@gmail.com', 8); --dentistul care este si client

/* Centres */
insert into centres(centre_name, centre_location, centre_phone) values('Magic Toothbrush', 'Iasi', '0746512345');
insert into centres(centre_name, centre_location, centre_phone) values('Floss, no Loss', 'Bacau', '0729999999');
insert into centres(centre_name, centre_location, centre_phone) values('Happy Tooth', 'Iasi', '0736000000');
insert into centres(centre_name, centre_location, centre_phone) values('Denty', 'Cluj', '0756499438');
insert into centres(centre_name, centre_location, centre_phone) values('Smile!', 'Bucuresti', '0730300556');

/* Agreements */
--un dentist poate fi la mai multe centre, deci sa aiba mai multe agreement-uri
insert into agreements(dentists_dentist_id, centres_centre_id) values(1, 2); --dentist, centru
insert into agreements(dentists_dentist_id, centres_centre_id) values(1, 3);
insert into agreements(dentists_dentist_id, centres_centre_id) values(2, 1);
insert into agreements(dentists_dentist_id, centres_centre_id) values(3, 1);
insert into agreements(dentists_dentist_id, centres_centre_id) values(3, 2);
insert into agreements(dentists_dentist_id, centres_centre_id) values(3, 3);
insert into agreements(dentists_dentist_id, centres_centre_id) values(4, 1);
insert into agreements(dentists_dentist_id, centres_centre_id) values(4, 2);
insert into agreements(dentists_dentist_id, centres_centre_id) values(5, 3);
insert into agreements(dentists_dentist_id, centres_centre_id) values(6, 2);
insert into agreements(dentists_dentist_id, centres_centre_id) values(7, 1);
insert into agreements(dentists_dentist_id, centres_centre_id) values(7, 3);
insert into agreements(dentists_dentist_id, centres_centre_id) values(8, 2);

/* Meetings */
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id) -- aici trebuie pus trigger-ul
    values(TO_DATE('14.7.2025 7:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('14.7.2025 8:00', 'dd.mm.yyyy HH24:MI'), 'Control Carii', 1, 13);
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('13.1.2025 13:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('13.1.2025 14:00', 'dd.mm.yyyy HH24:MI'), 'Aparat Dentar', 2, 7);
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('12.1.2025 14:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('12.1.2025 15:00', 'dd.mm.yyyy HH24:MI'), 'Control Periodic', 2, 4);
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('10.1.2025 12:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('10.1.2025 14:30', 'dd.mm.yyyy HH24:MI'), 'Operatie', 4, 4);
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('9.1.2025 12:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('9.1.2025 14:30', 'dd.mm.yyyy HH24:MI'), 'Operatie', 5, 9);
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('7.1.2025 12:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('7.1.2025 14:30', 'dd.mm.yyyy HH24:MI'), 'Operatie', 7, 12);
insert into meetings(meeting_start_time, meeting_end_time, meeting_problem, clients_client_id, agreements_agreement_id)
    values(TO_DATE('21.1.2025 15:30', 'dd.mm.yyyy HH24:MI'), TO_DATE('21.1.2025 16:30', 'dd.mm.yyyy HH24:MI'), 'Operatie', 7, 11);


    
    

