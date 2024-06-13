use mydb;

show tables;

describe user;
describe customer;
describe vendor;
describe admin;
describe vehicle;
describe reservation;
describe review;
describe photo;
describe customer_address;
describe vendor_address;

-- --------------------INSERTIONS------------------------------------------

insert into user(user_id, user_username, user_password, user_role) values
(28, 'ankitsingh', '1234', 'Super Admin');

insert into user(user_id, user_username, user_password, user_role) values
(29, 'anandsingh', '3232', 'Fleet Manager'),
(30, 'honeysingh', '7456', 'Fleet Manager'),

(31, 'abhisheksingh', '6645', 'Customer'),
(32, 'gopalsingh', '6778', 'Customer'),
(33, 'priyanshsingh', '2234', 'Customer'),

(34, 'johndoe', '8764', 'Vendor'),
(35, 'janesmit', '0876', 'Vendor'),
(36, 'robertjohnson', '7844', 'Vendor');

select * from user;

insert into admin(admin_id, admin_first_name, admin_last_name, admin_email, 
admin_phone_number, admin_role, admin_join_date, user_id) values
(10, 'Ankit', 'Singh', 'ank@gmail.com', '6263094474', 'H 786, Awadhpuri', '2024-04-13', 28),
(11, 'Anand', 'Singh', 'anand@gmail.com', '8776573389', 'H - 95, Indrapuri', '2003-11-23', 29),
(12, 'Honey', 'Singh', 'yoyok@gmail.com', '7775677473', 'Awadhpuri', '2024-03-03', 30);

select * from admin;


insert into customer(customer_id, customer_first_name, customer_last_name, customer_email, 
customer_phone_number, customer_registration_date,  user_id, customer_address_id) values
(10, 'Abhishek', 'Singh', 'abhi@gmail.com', '9163094474', '2023-03-3', 31, 10),
(11, 'Gopal', 'Singh', 'gopal@gmail.com', '9263094474', '1999-04-22', 32, 11),
(12, 'Priyansh', 'Singh', 'pri@gmail.com', '9263094474', '2024-03-13', 33, 12);

select * from customer;


insert into vendor(vendor_id, vendor_first_name, vendor_last_name, vendor_email, vendor_phone_number, 
vendor_registration_date, user_id, vendor_address_id) values
(10, 'John', 'Doe', 'john@gmail.com', '9163094474', '2001-04-11', 34, 10),
(11, 'Jane', 'Smit', 'jane@gmail.com', '9163094474', '1994-04-29', 35, 11),
(12, 'Robert', 'Johnson', 'robert@gmail.com', '9163094474', '1947-08-15', 36, 12);

select * from vendor;



insert into vehicle(vehicle_id, vehicle_model, vehicle_make, vehicle_year, vehicle_color, 
vehicle_registration_no, vehicle_availability, vehicle_daily_rate, vendor_id) values
(10, 'Civic', 'Honda', 2022, 'Blue', 'ABC1234', 1, 500.00, 10),
(11, 'Corolla', 'Toyota', 2021, 'Red', 'XYZ5678', 0, 600.00, 11),
(12, 'Fortuner', 'Toyota', 2020, 'Black', 'XYZ5638', 1, 700.00, 12);

select * from vehicle;

insert into reservation(customer_id, vehicle_id, reservation_id, reservation_start_date, 
reservation_end_date, reservation_total_cost, reservation_status, admin_id) values
(10, 10, 10, '2024-05-01', '2024-06-10', 2000.00, 'Confirmed', 11),
(11, 11, 11, '2023-06-10', '2023-06-15', 3000.00, 'Pending', 12),
(11, 12, 12, '2024-06-12', '2024-11-15', 30000.00, 'Pending', 10);

select * from reservation;

insert into review(customer_id, vehicle_id, review_comment, review_id, review_rating) values
(11, 12, 'Great car, excellent service!', 10, 5),
(11, 11, 'Smooth ride, highly recommend.', 11, 4),
(10, 10, 'Smooth ride, highly recommend.', 12, 4);

select * from review;

insert into photo(photo_id, photo_link, photo_upload_date, vehicle_id) values
(10, 'https://example.com/photo1.jpg', '2024-04-20', 10),
(11, 'https://example.com/photo2.jpg', '2024-04-21',11),
(12, 'https://example.com/photo2.jpg', '2024-04-11', 12);

select * from photo;


insert into customer_address(customer_address_id, customer_address_state, customer_address_city, customer_address_pincode) values
(10, 'Madhya Pradesh', 'Bhopal', 322022),
(11, 'Utter Pradesh', 'Jhansi', 462022),
(12, 'Delhi', 'Delhi', 662022);

select * from customer_address;

insert into vendor_address(vendor_address_id, vendor_address_state, vendor_address_city, vendor_address_pincode) values
(10, 'Maharashtra', 'Mumbai', 782022),
(11, 'Utter Pradesh', 'Noida', 232021),
(12, 'Punjab', 'Faisalabad', 542023);

select * from vendor_address;

-- -------------------------------------------------------------------



-- Reservation Table ----------------------------

-- Create/Insert - 
-- insertIntoReservation(...)
insert into reservation(customer_id, vehicle_id, reservation_id, reservation_start_date, 
reservation_end_date, reservation_total_cost, reservation_status, admin_id) values
(10, 10, 10, '2024-05-01', '2024-06-10', 2000.00, 'Confirmed', 11);

-- Read - 
-- list getAllReservation()
select * from reservation;

-- Update - 
-- void updateReservation(...)
update reservation
set reservation_total_cost = 500
where reservation_id = 10;

-- Delete - 
-- void deleteReservation(...)
delete from reservation
where reservation_id = 1;

-- Methods
-- 1. Give discount to customers if they have more than one reservations in the past with us - 
-- eligibleForDiscount(customer_id)
-- return type: boolean
select count(*) as NumberOfBookings
from reservation
group by customer_id;

-- 2. Count number of active/pending/completed reservation - 
-- countReservationStatus(status)
-- return type: int
select count(*)
from reservation
where reservation_status = "Pending"
group by reservation_status;

-- 3. Search in reservation according to customer_id, date, status, etc - 
-- searchReservation(...)

-- Vendor Table ----------------------------------
-- Create/Insert
-- void insertIntoVendor(...)
insert into vendor_address(vendor_address_id, vendor_address_state, vendor_address_city, vendor_address_pincode) values
(10, 'Maharashtra', 'Mumbai', 782022);

-- Read
-- list getAllVendor();
select *
from vendor;

-- Update
-- void updateVendor(...);
update vendor
set vendor_email = 'ank@gmail.com'
where vendor_id = 10;

-- Delete
-- void deleteVendor(...);
delete from vendor
where vendor_id = 1;

-- Methods
-- 1. Vendor can read reviews for his/her car
-- list readReviews(vendor_id);
select *
from vendor v join vehicle vcl
on v.vendor_id = vcl.vendor_id
join review r on r.vehicle_id = vcl.vehicle_id
group by vendor_id;

-- 2. Vendor should get notified of new reservations on his/her vehicles
-- newReservation();
-- 3 Vendor should be able to list all his/her vehicle
-- list listVehicle(list);