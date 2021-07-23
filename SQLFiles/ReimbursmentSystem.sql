CREATE TABLE ers_reimbursement(
reimb_id integer NOT NULL PRIMARY KEY,
reimb_amount numeric(20,2) NOT NULL,
reimb_submitted timestamp NOT NULL,
reimb_resolved timestamp,
reimb_description varchar(250),
reimb_receipt bytea,
reimb_author integer NOT NULL FOREIGN KEY,
reimb_resolver integer FOREIGN KEY,
reimb_status_id integer FOREIGN KEY REFERENCES ers_reimbursement_status(reimb_status_id), 
reimb_type_id integer NOT NULL FOREIGN KEY REFERENCES ers_reimbursement_type(reimb_type_id)
)

CREATE TABLE ers_users(
ers_user_id integer NOT NULL PRIMARY KEY,
ers_username varchar(50) UNIQUE,
ers_password varchar(50) NOT NULL,
user_first_name varchar(100) NOT NULL,
user_last_name varchar(100) NOT NULL,
user_email varchar(150) UNIQUE NOT NULL,
user_role_id integer NOT NULL FOREIGN KEY REFERENCES ers_user_role(ers_user_role_id)
)

CREATE TABLE ers_reimbursement_status(
reimb_status_id integer NOT NULL PRIMARY KEY,
reimb_status varchar(10) NOT NULL
)

CREATE TABLE ers_reimbursement_type(
reimb_type_id integer NOT NULL PRIMARY KEY,
reimb_type varchar(10) NOT NULL
)

CREATE TABLE ers_user_roles(
ers_user_role_id integer NOT NULL PRIMARY KEY,
user_role varchar(10)NOT NULL
)
