use mydb;

-- SET SQL_SAFE_UPDATES = 0;

-- For softDelete in vendor
alter table vendor
add column isActive varchar(255)
default 'yes'; 
-- For softDelete in customer
alter table customer
add column isActive varchar(255)
default 'yes'; 

ALTER TABLE vendor
RENAME COLUMN isActive TO vendor_isActive;
ALTER TABLE customer
RENAME COLUMN isActive TO customer_isActive;
select * from user;

-- User
INSERT INTO User (user_id, user_username, user_password, user_role)
VALUES 
    (1, 'rahul_customer', 'password123', 'customer'),
    (2, 'priya_customer', 'password456', 'customer'),
    (3, 'neha_customer', 'password789', 'customer'),
    (4, 'raj_customer', 'passwordabc', 'customer'),
    (5, 'sneha_customer', 'passworddef', 'customer'),
    (6, 'anuj_customer', 'passwordghi', 'customer'),
    (7, 'kavita_customer', 'passwordjkl', 'customer'),
    (8, 'vivek_customer', 'passwordmno', 'customer'),
    (9, 'deepak_customer', 'passwordpqr', 'customer'),
    (10, 'megha_customer', 'passwordstu', 'customer'),
    (11, 'anjali_customer', 'passwordvwx', 'customer'),
    (12, 'isha_customer', 'passwordyz1', 'customer'),
    (13, 'sakshi_customer', 'password234', 'customer'),
    (14, 'vishal_customer', 'password567', 'customer'),
    (15, 'shreya_customer', 'password890', 'customer'),
    (16, 'akash_vendor', 'password123', 'vendor'),
    (17, 'poonam_vendor', 'password456', 'vendor'),
    (18, 'vijay_vendor', 'password789', 'vendor'),
    (19, 'priti_vendor', 'passwordabc', 'vendor'),
    (20, 'rohit_vendor', 'passworddef', 'vendor'),
    (21, 'radha_vendor', 'passwordghi', 'vendor'),
    (22, 'arjun_vendor', 'passwordjkl', 'vendor'),
    (23, 'snehal_vendor', 'passwordmno', 'vendor'),
    (24, 'ravi_vendor', 'passwordpqr', 'vendor'),
    (25, 'ritu_vendor', 'passwordstu', 'vendor'),
    (26, 'abhishek_vendor', 'passwordvwx', 'vendor'),
    (27, 'komal_vendor', 'passwordyz1', 'vendor'),
    (28, 'vikas_vendor', 'password234', 'vendor'),
    (29, 'aarti_vendor', 'password567', 'vendor'),
    (30, 'aditi_vendor', 'password890', 'vendor'),
    (31, 'admin', 'admin123', 'admin');


-- Admin
describe admin;
insert into admin(admin_id, admin_first_name, admin_last_name, admin_email, admin_phone_number, admin_role, admin_join_date, user_id) values
(1, 'Harry', 'Potter', 'harry@gmail.com', '78678678678' , 'Admin', '2024-04-13', 31);

select * from admin;

-- Customer
INSERT INTO Customer (customer_id, customer_first_name, customer_last_name, customer_email,
customer_phone_number, customer_registration_date, user_id, address_id)
VALUES 
    (1, 'Rahul', 'Kumar', 'rahul@example.com', '9876543210', '2024-04-01', 1, 1),
    (2, 'Priya', 'Gupta', 'priya@example.com', '9876543211', '2023-03-02', 2, 2),
    (3, 'Neha', 'Sharma', 'neha@example.com', '9876543212', '2022-02-03', 3, 3),
    (4, 'Raj', 'Singh', 'raj@example.com', '9876543213', '2020-03-04', 4, 4),
    (5, 'Sneha', 'Patel', 'sneha@example.com', '9876543214', '2015-11-05', 5, 5),
    (6, 'Anuj', 'Jain', 'ankit@example.com', '9876543215', '2020-04-06', 6, 6),
    (7, 'Kavita', 'Shah', 'pooja@example.com', '9876543216', '2024-04-07', 7, 7),
    (8, 'Vivek', 'Verma', 'amit@example.com', '9876543217', '2001-04-08', 8, 8),
    (9, 'Deepak', 'Yadav', 'deepak@example.com', '9876543218', '2000-04-09', 9, 9),
    (10, 'Megha', 'Rastogi', 'megha@example.com', '9876543219', '2022-04-10', 10, 10),
    (11, 'Anjali', 'Gandhi', 'anjali@example.com', '9876543220', '2000-04-11', 11, 11),
    (12, 'Isha', 'Kapoor', 'rohit@example.com', '9876543221', '2024-04-12', 12, 12),
    (13, 'Sakshi', 'Malhotra', 'sakshi@example.com', '9876543222', '2019-04-13', 13, 13),
    (14, 'Vishal', 'Agarwal', 'vishal@example.com', '9876543223', '2002-04-14', 14, 14),
    (15, 'Shreya', 'Tiwari', 'shreya@example.com', '9876543224', '2010-04-15', 15, 15);

select * from customer;


-- Address
INSERT INTO Address (address_id, address_state, address_city, address_pincode)
VALUES 
    (1, 'Maharashtra', 'Mumbai', '400001'),
    (2, 'Maharashtra', 'Pune', '411001'),
    (3, 'Karnataka', 'Bangalore', '560001'),
    (4, 'Delhi', 'New Delhi', '110001'),
    (5, 'Uttar Pradesh', 'Lucknow', '226001'),
    (6, 'Tamil Nadu', 'Chennai', '600001'),
    (7, 'Gujarat', 'Ahmedabad', '380001'),
    (8, 'Rajasthan', 'Jaipur', '302001'),
    (9, 'West Bengal', 'Kolkata', '700001'),
    (10, 'Telangana', 'Hyderabad', '500001'),
    (11, 'Madhya Pradesh', 'Indore', '452001'),
    (12, 'Bihar', 'Patna', '800001'),
    (13, 'Kerala', 'Kochi', '682001'),
    (14, 'Andhra Pradesh', 'Visakhapatnam', '530001'),
    (15, 'Punjab', 'Chandigarh', '160001'),
    (16, 'Maharashtra', 'Mumbai', '400002'),
    (17, 'Maharashtra', 'Pune', '411002'),
    (18, 'Karnataka', 'Bangalore', '560002'),
    (19, 'Delhi', 'New Delhi', '110002'),
    (20, 'Uttar Pradesh', 'Lucknow', '226002'),
    (21, 'Tamil Nadu', 'Chennai', '600002'),
    (22, 'Gujarat', 'Ahmedabad', '380002'),
    (23, 'Rajasthan', 'Jaipur', '302002'),
    (24, 'West Bengal', 'Kolkata', '700002'),
    (25, 'Telangana', 'Hyderabad', '500002'),
    (26, 'Madhya Pradesh', 'Indore', '452002'),
    (27, 'Bihar', 'Patna', '800002'),
    (28, 'Kerala', 'Kochi', '682002'),
    (29, 'Andhra Pradesh', 'Visakhapatnam', '530002'),
    (30, 'Punjab', 'Chandigarh', '160002');

select * from address;

-- Vendor
INSERT INTO Vendor (vendor_id, vendor_first_name, vendor_last_name, vendor_email, vendor_phone_number, 
user_id, vendor_registration_date, address_id)
VALUES 
    (1, 'Akash', 'Gupta', 'akash@example.com', '9876543225', 16, '2023-04-06', 16),
    (2, 'Poonam', 'Singh', 'poonam@example.com', '9876543226', 17, '2014-05-17', 17),
    (3, 'Vijay', 'Sharma', 'vijay@example.com', '9876543227', 18, '2020-07-12', 18),
    (4, 'Priti', 'Yadav', 'priti@example.com', '9876543228', 19, '2004-02-19', 19),
    (5, 'Rohit', 'Patel', 'rohit@example.com', '9876543229', 20, '2000-08-20', 20),
    (6, 'Radha', 'Kumar', 'radha@example.com', '9876543230', 21, '2001-09-26', 21),
    (7, 'Arjun', 'Verma', 'arjun@example.com', '9876543231', 22, '2023-11-29', 22),
    (8, 'Snehal', 'Gandhi', 'snehal@example.com', '9876543232', 23, '2022-01-13', 23),
    (9, 'Ravi', 'Malhotra', 'ravi@example.com', '9876543233', 24, '2021-06-04', 24),
	(10, 'Ritu', 'Agarwal', 'ritu@example.com', '9876543234', 25, '2019-07-05', 25),
    (11, 'Abhishek', 'Shah', 'abhishek@example.com', '9876543235', 26, '2004-09-26', 26),
    (12, 'Komal', 'Tiwari', 'komal@example.com', '9876543236', 27, '2018-04-07', 27),
    (13, 'Vikas', 'Gupta', 'vikas@example.com', '9876543237', 28, '2017-04-28', 28),
    (14, 'Aarti', 'Singh', 'aarti@example.com', '9876543238', 29, '2016-03-19', 29),
    (15, 'Aditi', 'Yadav', 'aditi@example.com', '9876543239', 30, '2015-04-30', 30);


select * from vendor;
-- delete from vendor;
-- Vehicle
INSERT INTO Vehicle (vehicle_id, vehicle_model, vehicle_make, vehicle_year, vehicle_color,
 vehicle_registration_no, vehicle_availability, vehicle_daily_rate, vendor_id)
VALUES 
    (1, 'Punch', 'Tata', 2020, 'Black', 'MH01AB1234', 1, 800.00, 1),
    (2, 'Bolero', 'Mahindra', 2019, 'White', 'MH02CD5678', 1, 1200.00, 2),
    (3, 'Swift', 'Maruti', 2021, 'Red', 'KA03EF9101', 0, 900.00, 3),
    (4, 'Baleno', 'Maruti', 2018, 'Silver', 'DL04GH1122', 1, 1100.00, 4),
    (5, 'Fortuner', 'Toyota', 2020, 'Grey', 'UP05IJ3344', 1, 2500.00, 5),
    (6, 'Creta', 'Hyundai', 2019, 'Blue', 'TN06KL5566', 0, 1500.00, 6),
    (7, 'XUV700', 'Mahindra', 2017, 'Brown', 'GJ07MN7788', 1, 1800.00, 7),
    (8, 'Alto', 'Maruti', 2022, 'White', 'RJ08OP9900', 1, 800.00, 8),
    (9, 'Innova', 'Toyota', 2018, 'Black', 'WB09QR1122', 0, 2000.00, 9),
    (10, 'Ecosport', 'Ford', 2019, 'Red', 'TS10ST3344', 1, 1600.00, 10),
    (11, 'Dzire', 'Maruti', 2020, 'Silver', 'MP11UV5566', 1, 1000.00, 11),
    (12, 'XUV300', 'Mahindra', 2021, 'Blue', 'BR12WX7788', 1, 1700.00, 12),
    (13, 'Polo', 'Volkswagen', 2017, 'Grey', 'KL13YZ9900', 0, 1400.00, 13),
    (14, 'Thar', 'Mahindra', 2022, 'Brown', 'AP14AB1122', 0, 2200.00, 14),
     (15, 'Sonet', 'Kia', 2021, 'Orange', 'PB15CD3344', 1, 1800.00, 15);

select * from vehicle;
-- delete from vehicle;

-- Reservation
INSERT INTO Reservation (reservation_id, reservation_start_date, reservation_end_date, 
reservation_total_cost, reservation_status, customer_id, vehicle_id, admin_id)
VALUES 
    (1, '2024-01-21', '2024-01-25', 3200.00, 'completed', 13, 2, 1),
    (2, '2024-02-12', '2024-02-13', 1200.00, 'completed', 12, 4, 1),
    (3, '2024-03-23', '2024-03-27', 3600.00, 'due', 9, 3, 1),
    (4, '2024-04-04', '2024-04-08', 4400.00, 'pending', 4, 7, 1),
    (5, '2024-05-15', '2024-05-17', 5000.00, 'completed', 4, 7, 1),
    (6, '2024-05-08', '2024-05-10', 3000.00, 'pending', 6, 6, 1),
    (7, '2024-01-09', '2024-01-11', 3600.00, 'completed', 15, 7, 1),
    (8, '2024-02-03', '2024-02-12', 13500.00, 'due', 2, 6, 1),
    (9, '2024-03-06', '2024-03-13', 14000.00, 'due', 9, 9, 1),
    (10, '2024-02-11', '2024-02-14', 4800.00, 'completed', 3, 10, 1),
    (11, '2024-01-12', '2024-01-15', 3000.00, 'completed', 11, 14, 1),
    (12, '2024-03-13', '2024-03-16', 5100.00, 'completed', 12, 11, 1),
    (13, '2024-05-18', '2024-05-19', 1400.00, 'completed', 11, 13, 1),
    (14, '2024-02-10', '2024-02-11', 1400.00, 'due', 15, 13, 1),
    (15, '2024-05-15', '2024-05-19', 8800.00, 'confirmed', 1, 14, 1);
select * from reservation;
-- delete from reservation where reservation_id = 1967298446;
-- Review
INSERT INTO Review (customer_id, vehicle_id, review_comment, review_id, review_rating)
VALUES 
    (13, 2, 'Great experience with this car!', 1, 5),
    (12, 4, 'Smooth ride, excellent service.', 2, 4),
    (9, 3, 'Nice vehicle, comfortable seats.', 3, 4),
    (4, 7, 'Good car, would rent again.', 4, 4),
    (4, 7, 'Very satisfied with the performance.', 5, 5),
    (6, 6, 'Average experience, could be better.', 6, 3),
    (15, 7, 'Decent vehicle, but could improve.', 7, 3),
    (2, 6, 'Not as expected, needs maintenance.', 8, 2),
    (9, 9, 'Poor condition, disappointed.', 9, 1),
    (3, 10, 'Excellent service, highly recommended.', 10, 5),
    (11, 14, 'Smooth transaction, great car.', 11, 5),
    (12, 11, 'Car was dirty, needs cleaning.', 12, 2),
    (11, 13, 'Comfortable ride, no complaints.', 13, 4),
    (15, 13, 'Average experience, nothing special.', 14, 3),
    (1, 14, 'Overall satisfied, would rent again.', 15, 4);

select * from review;
-- delete from review;

select * from vehicle;
-- delete from reservation where reservation_id = 784197925;
select * from reservation;

select r.*
from reservation r join vehicle v on r.vehicle_id = v.vehicle_id
join vendor vd on vd.vendor_id = v.vendor_id
where v.vendor_id = 7 AND r.reservation_status = "Pending";

update reservation
set customer_id = 4,
vehicle_id = 7,
reservation_start_date = '2024-04-04',
reservation_end_date = '2024-04-08',
reservation_total_cost = 4400,
reservation_status = 'pending',
admin_id = 1
where reservation_id = 4;

update reservation
set reservation_status = 'due'
where reservation_id = 3;


-- getReservationCountByArea
select a.address_state, count(*) as Count
from address a join customer c on a.address_id = c.address_id
join reservation r on r.customer_id = c.customer_id
group by a.address_state;

-- getReservationCountByCustomer
select customer_first_name, 
customer_last_name, count(*) as ReservationCount
from reservation r join customer c
on r.customer_id = c.customer_id 
group by customer_first_name, customer_last_name
order by ReservationCount desc;

select * from review;
describe vendor;
-- vendorWithGoodReviewCount()
select vendor_first_name, vendor_last_name, count(*) as Above4StarReviewCount
from vendor vd left join vehicle v on vd.vendor_id = v.vendor_id
left join review r on r.vehicle_id = v.vehicle_id
where r.review_rating >= 4
group by vendor_first_name, vendor_last_name
order by Above4StarReviewCount desc;

-- vendorWithBadReviewCount()
select vendor_first_name, vendor_last_name, count(*)
from vendor vd join vehicle v on vd.vendor_id = v.vendor_id
join review r on r.vehicle_id = v.vehicle_id
where r.review_rating <= 3
group by vendor_first_name, vendor_last_name;


select * from vendor;
-- countVendorVehicles
select vendor_first_name, vendor_last_name, count(*) as VehicleCount
from vendor vd left join vehicle v on vd.vendor_id = v.vendor_id
group by vendor_first_name, vendor_last_name
order by VehicleCount desc;

-- SubQueryVersion of vendorWithGoodReviewCount
select vendor_first_name, vendor_last_name, count(*) as ReviewsCount
from vehicle v join vendor vd on v.vendor_id = vd.vendor_id
where v.vehicle_id in (select vehicle_id
						from review r
						where review_rating >= 4)
group by vendor_first_name, vendor_last_name;


select *
from vendor vd join vehicle v on vd.vendor_id = v.vendor_id
join review r on r.vehicle_id = v.vehicle_id;


select * from user;
select * from vendor;

select v.vendor_id
from user u join vendor v on u.user_id = v.user_id
where u.user_username = 'aditi_vendor' and u.user_password = 'password890';

select * from reservation;

select vehicle_id
from reservation
where reservation_status = 'pending' OR  reservation_status = 'completed';

select v.vendor_id
from user u join vendor v on u.user_id = v.user_id 
where u.user_username = "akash_vendor" and u.user_password = "password123";

update reservation
set reservation_status = 'pending'
where reservation_id = 6;

describe user;

alter table customer add isActive varchar(255) Default "yes";

select customer_first_name, customer_last_name, count(*) as ReservationCount 
				from reservation r join customer c on r.customer_id = c.customer_id 
				group by customer_first_name, customer_last_name;
                
select * from reservation where reservation_id = 15;
select * from reservation where reservation_id = 6;

update reservation
set reservation_status = 'pending'
where reservation_id = 6;
update reservation set customer_id = 1, vehicle_id = 14, reservation_start_date = '2024-05-15', reservation_end_date = '2024-05-19', reservation_total_cost = 8800, reservation_status = 'confirmed', admin_id = 1 where reservation_id = 15;