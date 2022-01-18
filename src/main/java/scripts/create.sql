--drop table dentist_details;
--drop table client_details;
--drop table meetings;
--drop table clients;
--drop table agreements;
--drop table centres;
--drop table dentists;
--drop sequence agreement_id_sequence;
--drop sequence centre_id_sequence;
--drop sequence client_id_sequence;
--drop sequence dentist_id_sequence;


CREATE TABLE agreements (
    agreement_id        NUMBER NOT NULL,
    dentists_dentist_id NUMBER NOT NULL,
    centres_centre_id   NUMBER NOT NULL
)
LOGGING;

ALTER TABLE agreements ADD CONSTRAINT agreements_pk PRIMARY KEY ( agreement_id );


ALTER TABLE agreements ADD CONSTRAINT agr_dentist_id_centre_id_un UNIQUE ( dentists_dentist_id,
                                                                           centres_centre_id );

CREATE TABLE centres (
    centre_id       NUMBER NOT NULL,
    centre_name     VARCHAR2(50) NOT NULL,
    centre_location VARCHAR2(50) NOT NULL,
    centre_phone    VARCHAR2(10) NOT NULL
)
LOGGING;

ALTER TABLE centres
    ADD CONSTRAINT x_name_ck CHECK ( length(centre_name) > 1 );

ALTER TABLE centres
    ADD CONSTRAINT x_location_ck CHECK ( length(centre_location) > 1 );

ALTER TABLE centres
    ADD CONSTRAINT x_phone_ck CHECK ( length(centre_phone) = 10 );

ALTER TABLE centres ADD CONSTRAINT centres_pk PRIMARY KEY ( centre_id );

ALTER TABLE centres ADD CONSTRAINT centres_centre_phone_un UNIQUE ( centre_phone );

ALTER TABLE centres ADD CONSTRAINT centres_centre_name_un UNIQUE ( centre_name );

CREATE TABLE client_details (
    pid               VARCHAR2(13) NOT NULL,
    client_birth_date DATE NOT NULL,
    client_town       VARCHAR2(50) NOT NULL,
    client_country    VARCHAR2(50) NOT NULL,
    email             VARCHAR2(75) NOT NULL,
    clients_client_id NUMBER NOT NULL
)
LOGGING;

ALTER TABLE client_details
    ADD CONSTRAINT x_client_pid_ck CHECK ( length(pid) = 13 );

ALTER TABLE client_details
    ADD CONSTRAINT x_bday_ck CHECK ( client_birth_date BETWEEN DATE '1900-01-01' AND DATE '2021-11-18' );

ALTER TABLE client_details
    ADD CONSTRAINT x_town_ck CHECK ( length(client_town) > 1 );

ALTER TABLE client_details
    ADD CONSTRAINT x_country_ck CHECK ( length(client_country) > 1 );

ALTER TABLE client_details
    ADD CONSTRAINT x_client_email_ck CHECK ( REGEXP_LIKE ( email,
                                                           '[a-z0-9._%-]+@[a-z0-9._%-]+\.[a-z]{2,4}' ) );

ALTER TABLE client_details ADD CONSTRAINT client_details_pk PRIMARY KEY ( clients_client_id );

ALTER TABLE client_details ADD CONSTRAINT client_details_email_un UNIQUE ( email );

ALTER TABLE client_details ADD CONSTRAINT client_details_pid_un UNIQUE ( pid );

CREATE TABLE clients (
    client_id         NUMBER NOT NULL,
    client_first_name VARCHAR2(50) NOT NULL,
    client_last_name  VARCHAR2(50) NOT NULL
)
LOGGING;

ALTER TABLE clients
    ADD CONSTRAINT x_client_first_name_ck CHECK ( length(client_first_name) > 1 );

ALTER TABLE clients
    ADD CONSTRAINT x_client_last_name_ck CHECK ( length(client_last_name) > 1 );

ALTER TABLE clients ADD CONSTRAINT clients_pk PRIMARY KEY ( client_id );

ALTER TABLE clients ADD CONSTRAINT clients_full_name_un UNIQUE ( client_first_name,
                                                                 client_last_name );

CREATE TABLE dentist_details (
    pid                      VARCHAR2(13) NOT NULL,
    dentist_specialization   VARCHAR2(30) NOT NULL,
    dentist_qualification    VARCHAR2(30) NOT NULL,
    dentist_years_experience NUMBER NOT NULL,
    email                    VARCHAR2(75) NOT NULL,
    dentists_dentist_id      NUMBER NOT NULL
)
LOGGING;

ALTER TABLE dentist_details
    ADD CONSTRAINT x_dentist_pid_ck CHECK ( length(pid) = 13 );

ALTER TABLE dentist_details
    ADD CONSTRAINT x_specialization_ck CHECK ( length(dentist_specialization) > 1 );

ALTER TABLE dentist_details
    ADD CONSTRAINT x_qualification_ck CHECK ( length(dentist_qualification) > 1 );

ALTER TABLE dentist_details
    ADD CONSTRAINT x_dentist_email_ck CHECK ( REGEXP_LIKE ( email,
                                                            '[a-z0-9._%-]+@[a-z0-9._%-]+\.[a-z]{2,4}' ) );

ALTER TABLE dentist_details ADD CONSTRAINT dentist_details_pk PRIMARY KEY ( dentists_dentist_id );

ALTER TABLE dentist_details ADD CONSTRAINT dentist_details_email_un UNIQUE ( email );

ALTER TABLE dentist_details ADD CONSTRAINT dentist_details_pid_un UNIQUE ( pid );

CREATE TABLE dentists (
    dentist_id         NUMBER NOT NULL,
    dentist_first_name VARCHAR2(50) NOT NULL,
    dentist_last_name  VARCHAR2(50) NOT NULL
)
LOGGING;

ALTER TABLE dentists
    ADD CONSTRAINT x_dentist_first_name_ck CHECK ( length(dentist_first_name) > 1 );

ALTER TABLE dentists
    ADD CONSTRAINT x_dentist_last_name_ck CHECK ( length(dentist_last_name) > 1 );

ALTER TABLE dentists ADD CONSTRAINT dentists_pk PRIMARY KEY ( dentist_id );


ALTER TABLE dentists ADD CONSTRAINT dentists_full_name_un UNIQUE ( dentist_first_name,
                                                                   dentist_last_name );

CREATE TABLE meetings (
    meeting_start_time      DATE NOT NULL,
    meeting_end_time        DATE NOT NULL,
    meeting_problem         VARCHAR2(100) NOT NULL,
    clients_client_id       NUMBER NOT NULL,
    agreements_agreement_id NUMBER NOT NULL
)
LOGGING;

ALTER TABLE meetings ADD CONSTRAINT x_start_time_ck CHECK ( meeting_start_time < meeting_end_time );

ALTER TABLE meetings ADD CONSTRAINT x_end_time_ck CHECK ( meeting_end_time > meeting_start_time );

/*ALTER TABLE meetings ADD CONSTRAINT meetings_client_agr_id_un UNIQUE ( clients_client_id,
                                                                       agreements_agreement_id );
*/

ALTER TABLE agreements
    ADD CONSTRAINT agreements_centres_fk FOREIGN KEY ( centres_centre_id )
        REFERENCES centres ( centre_id )
        ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE agreements
    ADD CONSTRAINT agreements_dentists_fk FOREIGN KEY ( dentists_dentist_id )
        REFERENCES dentists ( dentist_id )
        ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE client_details
    ADD CONSTRAINT client_details_clients_fk FOREIGN KEY ( clients_client_id )
        REFERENCES clients ( client_id )
        ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE dentist_details
    ADD CONSTRAINT dentist_details_dentists_fk FOREIGN KEY ( dentists_dentist_id )
        REFERENCES dentists ( dentist_id )
        ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE meetings
    ADD CONSTRAINT meetings_agreements_fk FOREIGN KEY ( agreements_agreement_id )
        REFERENCES agreements ( agreement_id )
        ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE meetings
    ADD CONSTRAINT meetings_clients_fk FOREIGN KEY ( clients_client_id )
        REFERENCES clients ( client_id )
        ON DELETE CASCADE
    NOT DEFERRABLE;

CREATE OR REPLACE TRIGGER trg_meetings_BRIU 
    BEFORE INSERT OR UPDATE ON meetings 
    FOR EACH ROW 
BEGIN
	IF( :new.meeting_start_time <= SYSDATE )
	THEN
		RAISE_APPLICATION_ERROR( -20001, 'Data invalida: ' || TO_CHAR( :new.meeting_start_time, 'DD.MM.YYYY HH24:MI:SS' ) || ' trebuie sa fie mai mare decat data curenta.' );
	END IF;
END; 
/

CREATE SEQUENCE agreement_id_sequence START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER agreements_agreement_id_trg BEFORE
    INSERT ON agreements
    FOR EACH ROW
    WHEN ( new.agreement_id IS NULL )
BEGIN
    :new.agreement_id := agreement_id_sequence.nextval;
END;
/

CREATE SEQUENCE centre_id_sequence START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER centres_centre_id_trg BEFORE
    INSERT ON centres
    FOR EACH ROW
    WHEN ( new.centre_id IS NULL )
BEGIN
    :new.centre_id := centre_id_sequence.nextval;
END;
/

CREATE SEQUENCE client_id_sequence START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER clients_client_id_trg BEFORE
    INSERT ON clients
    FOR EACH ROW
    WHEN ( new.client_id IS NULL )
BEGIN
    :new.client_id := client_id_sequence.nextval;
END;
/

CREATE SEQUENCE dentist_id_sequence START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER dentists_dentist_id_trg BEFORE
    INSERT ON dentists
    FOR EACH ROW
    WHEN ( new.dentist_id IS NULL )
BEGIN
    :new.dentist_id := dentist_id_sequence.nextval;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             7
-- CREATE INDEX                             0
-- ALTER TABLE                             38
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           4
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          4
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
