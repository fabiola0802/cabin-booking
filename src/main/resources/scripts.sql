/* users table creation */
CREATE TABLE public.users(id serial  primary key not null, 
							 name varchar(25) not null, 
							 surname varchar(25) not null, 
							 username varchar (25) UNIQUE, 
							 email varchar(25) not null, 
							 password varchar(25) not null,
							 role varchar(25) not null);

/* site table creation */
CREATE TABLE public.site (id serial primary key not null, code integer unique not null, description varchar(255), location varchar(30));

/* cabin table creation */
CREATE TABLE public.cabin(id serial primary key not null, 
							 cabin_number integer unique not null,  
							 number_of_floors integer not null, 
							 number_of_kitchens integer not null, 
							 number_of_bathrooms integer not null,
							 number_of_bedrooms integer not null,
							 price double precision not null,
							 max_capacity integer not null,
							 site_id integer not null);

 /*update of cabin table, set site_id as a foreign key */
ALTER TABLE ONLY public.cabin
    ADD CONSTRAINT "site_id(FK)" FOREIGN KEY (site_id) REFERENCES public.site(id); 
/* attribute table creation */					 
CREATE TABLE public.attribute (id serial primary key not null, 
						 name varchar(25) not null,
						 type varchar(25));
/* site_attribute table creation */
CREATE TABLE public.site_attribute(site_id integer not null,
								   attribute_id integer not null);	
/*update of site_attribute table, set site_id  and attribute_id as foreign keys */							   
ALTER TABLE ONLY public.site_attribute
    ADD CONSTRAINT "site_id(FK)" FOREIGN KEY (site_id) REFERENCES public.site(id); 
	
ALTER TABLE ONLY public.site_attribute
    ADD CONSTRAINT "attribute_id(FK)" FOREIGN KEY (attribute_id) REFERENCES public.attribute(id); 
	
/* cabin_attribute table creation */	
CREATE TABLE public.cabin_attribute(cabin_id integer not null,
						   attribute_id integer not null);	
									 
/*update of cabin_attribute table, set cabin_id  and attribute_id as foreign keys */									 
 ALTER TABLE ONLY public.cabin_attribute
    ADD CONSTRAINT "cabin_id(FK)" FOREIGN KEY (cabin_id) REFERENCES public.cabin(id); 
	
ALTER TABLE ONLY public.cabin_attribute
    ADD CONSTRAINT "attribute_id(FK)" FOREIGN KEY (attribute_id) REFERENCES public.attribute(id); 
/* booking table creation */									 
CREATE TABLE public.booking(id serial primary key not null,
							 booking_date date not null,
							 check_in_date date not null,
							 check_out_date date not null,
							 number_of_people integer not null,
							 user_id integer not null,
							 cabin_id integer not null);
							 
/*update of booking table, set user_id  and cabin_id as foreign keys */							 						 
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT "user_id(FK)" FOREIGN KEY (user_id) REFERENCES public.users(id); 
	
ALTER TABLE ONLY public.booking
    ADD CONSTRAINT "cabin_id(FK)" FOREIGN KEY (cabin_id) REFERENCES public.cabin(id); 
/*inserting data in tables */	 
INSERT INTO users(name, surname, username, email, password, role) VALUES ('fabiola', 'alterziu','fabi','fabi@gmail.com','fabi123','CUSTOMER');
INSERT INTO users(name, surname, username, email, password, role) VALUES ('anila', 'hoxha','anila90','anila@gmail.com','anila123','CUSTOMER');
INSERT INTO users(name, surname, username, email, password, role) VALUES ('arjola', 'grembi','arjola','arjola@gmail.com','admin123','ADMIN');
INSERT INTO site(code, description, location) VALUES (1003, 'site 1 me akses te te gjitha faciliteteve qe ofron fshati, si qender shendetsore, markete etj','qendra e fshatit');
INSERT INTO site(code, description, location) VALUES (1004, 'site 2 zone me pak e populluar nga banoret vendas dhe larg sherbimeve qe ofron fshati ','3 km larg qendres se fshatit ');
INSERT INTO cabin(cabin_number, number_of_floors, number_of_kitchens,number_of_bathrooms, number_of_bedrooms, price, max_capacity,site_id) 
VALUES (505, 1,1,1,1,25,2,2);
INSERT INTO cabin(cabin_number, number_of_floors, number_of_kitchens,number_of_bathrooms, number_of_bedrooms, price, max_capacity,site_id) 
VALUES (400, 1,1,1,2,50,4,1);
INSERT INTO attribute(name, type) VALUES ('pamje nga mali', 'SITE');
INSERT INTO attribute(name, type) VALUES ('pamje nga deti', 'SITE');
INSERT INTO attribute(name, type) VALUES ('pet friendly', 'CABIN');
INSERT INTO attribute(name, type) VALUES ('garazh', 'CABIN');
INSERT INTO site_attribute(site_id, attribute_id) VALUES (1,1);
INSERT INTO site_attribute(site_id, attribute_id) VALUES (2,2);
INSERT INTO cabin_attribute(cabin_id, attribute_id) VALUES (1,3);
INSERT INTO cabin_attribute(cabin_id, attribute_id) VALUES (2,4);
INSERT INTO booking(booking_date, check_in_date, check_out_date,number_of_people,user_id,cabin_id)
VALUES('2020-11-04', '2020-11-6','2020-11-7',2,1,1);