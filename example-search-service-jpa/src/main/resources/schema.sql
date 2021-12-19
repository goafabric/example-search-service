drop table if exists address;
drop table if exists skill;
drop table if exists person;

create table person
(
	id varchar(36) not null
		constraint pk_person
			primary key,
	first_name varchar(255) NOT NULL,
	last_name varchar(255) NOT NULL,
	birthday timestamp,

	address_id varchar(36) NOT NULL,
	version bigint default 0
);

create table address
(
	id varchar(36) not null
		constraint pk_address
			primary key,
	street varchar(255) NULL,
	city varchar(255) NULL,
	version bigint default 0
);

create table skill
(
	id varchar(36) not null
		constraint pk_skill
			primary key,
	name varchar(255) NULL,
	description varchar(255) NULL,
	person_id varchar(36) NULL,
	version bigint default 0
);
