CREATE SEQUENCE public.account_sequence;
CREATE SEQUENCE public.entry_sequence;
CREATE SEQUENCE public.hibernate_sequence;


CREATE TABLE public.association_value_entry (
	id int8 NOT NULL,
	association_key varchar(255) NOT NULL,
	association_value varchar(255) NULL,
	saga_id varchar(255) NOT NULL,
	saga_type varchar(255) NULL,
	CONSTRAINT association_value_entry_pkey PRIMARY KEY (id)
);
CREATE INDEX ix_association_value_entry_saga_id_association_key ON public.association_value_entry (saga_id,association_key) ;


CREATE TABLE public.domain_event_entry (
	global_index int8 NOT NULL,
	event_identifier varchar(255) NOT NULL,
	meta_data bytea NULL,
	payload bytea NOT NULL,
	payload_revision varchar(255) NULL,
	payload_type varchar(255) NOT NULL,
	time_stamp varchar(255) NOT NULL,
	aggregate_identifier varchar(255) NOT NULL,
	sequence_number int8 NOT NULL,
	"type" varchar(255) NULL,
	CONSTRAINT domain_event_entry_pkey PRIMARY KEY (global_index),
	CONSTRAINT uk_domain_event_entry_aggregate_identifier_sequence_number UNIQUE (aggregate_identifier,sequence_number),
	CONSTRAINT uk_domain_event_entry_event_identifier UNIQUE (event_identifier)
);
CREATE INDEX domain_event_entry_aggregate_identifier_idx ON public.domain_event_entry (aggregate_identifier DESC,sequence_number DESC) ;
CREATE INDEX domain_event_entry_event_identifier_idx ON public.domain_event_entry (event_identifier DESC) ;


CREATE TABLE public.its_account (
	id int8 NOT NULL,
	account_number varchar(30) NOT NULL,
	balance numeric(19,2) NULL,
	created_date timestamp NOT NULL,
	CONSTRAINT its_account_pkey PRIMARY KEY (id),
	CONSTRAINT uk_its_account_account_number UNIQUE (account_number)
);
CREATE INDEX its_account_account_number_idx ON public.its_account (account_number DESC) ;
CREATE INDEX its_account_created_date_idx ON public.its_account (created_date DESC) ;


CREATE TABLE public.its_entry (
	entry_type varchar(30) NOT NULL,
	id int8 NOT NULL,
	account_id int8 NOT NULL,
	account_number varchar(30) NULL,
	amount numeric(19,2) NOT NULL,
	created_date timestamp NOT NULL,
	CONSTRAINT its_entry_pkey PRIMARY KEY (id),
	CONSTRAINT its_entry_its_account_fk FOREIGN KEY (account_id) REFERENCES public.its_account(id)
);
CREATE INDEX its_entry_account_id_idx ON public.its_entry (account_id DESC) ;
CREATE INDEX its_entry_account_number_idx ON public.its_entry (account_number DESC) ;
CREATE INDEX its_entry_created_date_idx ON public.its_entry (created_date DESC) ;


CREATE TABLE public.its_payment (
	uuid uuid NOT NULL,
	account_id int8 NULL,
	account_number varchar(30) NULL,
	cash numeric(19,2) NULL,
	cash_change numeric(19,2) NULL,
	created_date timestamp NOT NULL,
	CONSTRAINT its_payment_pkey PRIMARY KEY (uuid),
	CONSTRAINT its_payment_its_account_fk FOREIGN KEY (account_id) REFERENCES public.its_account(id)
);
CREATE INDEX its_payment_account_number_idx ON public.its_payment (account_number DESC) ;
CREATE INDEX its_payment_created_date_idx ON public.its_payment (created_date DESC) ;


CREATE TABLE public.saga_entry (
	saga_id varchar(255) NOT NULL,
	revision varchar(255) NULL,
	saga_type varchar(255) NULL,
	serialized_saga bytea NULL,
	CONSTRAINT saga_entry_pkey PRIMARY KEY (saga_id)
);


CREATE TABLE public.snapshot_event_entry (
	aggregate_identifier varchar(255) NOT NULL,
	sequence_number int8 NOT NULL,
	"type" varchar(255) NOT NULL,
	event_identifier varchar(255) NOT NULL,
	meta_data bytea NULL,
	payload bytea NOT NULL,
	payload_revision varchar(255) NULL,
	payload_type varchar(255) NOT NULL,
	time_stamp varchar(255) NOT NULL,
	CONSTRAINT snapshot_event_entry_pkey PRIMARY KEY (aggregate_identifier,sequence_number,"type"),
	CONSTRAINT uk_snapshot_event_entry_event_identifier UNIQUE (event_identifier)
);
CREATE INDEX snapshot_event_entry_aggregate_identifier_idx ON public.snapshot_event_entry (aggregate_identifier DESC) ;


CREATE TABLE public.token_entry (
	processor_name varchar(255) NOT NULL,
	segment int4 NOT NULL,
	owner varchar(255) NULL,
	"timestamp" varchar(255) NOT NULL,
	token bytea NULL,
	token_type varchar(255) NULL,
	CONSTRAINT token_entry_pkey PRIMARY KEY (processor_name,segment)
);

--GRANT USAGE ON SCHEMA public TO report;
--GRANT SELECT ON ALL TABLES IN SCHEMA public TO report;
--
--GRANT USAGE ON SCHEMA public TO extsrv;
--GRANT SELECT ON ALL TABLES IN SCHEMA public TO extsrv;
