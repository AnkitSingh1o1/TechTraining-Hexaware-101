/* 
Joins
	- Inner Join (Join) / Natural Join
    - Left Join / Left Outer Join 
    - Right Join / Right Outer Join 
    - Full Outer Join** 
    - Self Join**   
*/ 
/* 
Q1. Display all students that are living in given city.
 
*/
select *
from student s join address a ON s.address_id = a.id
where a.city='mumbai';
/* output
*/


-- Q2. Display number of students that are living in each state.
-- projection: students
-- criteria: address
select a.state, count(s.id) as number_of_students
from student s join address a ON s.address_id=a.id
group by a.state
order by number_of_students desc;
/* output
*/


/* 
Q3. Display courses that belong to given department  
projection:courses 
criteria:department
*/
select d.name, c.*
from course c join department d on c.department_id = d.id
where d.name = 'IT';
/* output
*/


/* 
Q4. Display number of courses for each department.  
projection: courses 
criteria: department
*/
select d.name, count(c.name)
from course c join department d on c.department_id = d.id
group by d.name;
/* output
*/


/* 
Q5. Display students that have enrolled in given course.  
projection: student 
criteria: course
*/
select c.name, s.*
from student s join student_course sc on s.id = sc.student_id
join course c on sc.course_id = c.id
where c.name = 'Java Programming';
/* output
*/


/* 
Q6. Display students associated with given department.  
projection: student
criteria: department 
*/
select distinct s.*
from student s join student_course sc on s.id = sc.student_id
join course c on sc.course_id = c.id
join department d on c.department_id = d.id
where d.name = 'IT';
/* output
*/


/* 
Q7. Display number of students associated with each department.
project: students
criteria: department    
*/
select d.name, count(distinct s.id)
from student s join student_course sc on s.id = sc.student_id
join course c on sc.course_id = c.id
join department d on c.department_id = d.id
group by d.name;
/* output
*/
 
 
/* 
Q8. Display students that have enrolled before given date 
projection: student
criteria: student_course   
*/
select s.*
from student s join student_course sc on s.id = sc.student_id
where sc.date_of_enrollment < '2024-04-13';
/* output
*/


/* 
Q9. Display courses for which the discount of more than 5% is given.   
projection: course 
criteria: student_course
*/
select c.name
from course c join student_course sc on sc.course_id = c.id
where sc.discount > 5;
/* output
*/


 
/* 
Q10. Display avg discount given for each course 
projection: student_course
criteria: course 
*/
select c.name, avg(sc.discount)
from student_course sc join course c on sc.course_id = c.id
group by c.name;
/* output
*/

 
/* 
Q11. Display avg discount given to each student 
projection: student_course
criteria: student 
*/
select s.name, avg(sc.discount)
from student_course sc join student s on s.id = sc.student_id
group by s.name;
/* output
*/





-- ------------------------------ Nested Queries - Sub Queries-----------------------------------

-- Q1. Display all students that are living in given city.
-- projection: student
-- criteria: address
select * 
from student 
where address_id in (select id
			from address
            where city = 'mumbai');
/* output
*/

 
/* 
Q3. Display courses that belong to given department  
projection:courses 
criteria:department
*/
select *
from course
where department_id in (select id
						from department
                        where name = 'IT');
/* output
*/

                        
/* 
Q5. Display students that have enrolled in given course.  
projection: student 
criteria: course
*/
select *
from student
where id in (select student_id
			from student_course
            where course_id in (select id
								from course
                                where name = 'Java Programming'));
/* output
*/


/* 
Q6. Display students associated with given department.  
projection: student
criteria: department 
*/
 select *
from student
where id in (select student_id
			from student_course
            where course_id in (select id
								from course
                                where department_id in (select id
														from department
                                                        where name = 'IT')));
 
/* output
*/


/* 
Q8. Display students that have enrolled before given date 
projection: student
criteria: student_course   
*/
select *
from student
where id in (select student_id
			from student_course
            where date_of_enrollment < '2024-04-13');
/* output
*/


/* 
Q9. Display courses for which the discount of more than 5% is given.   
projection: course 
criteria: student_course
*/
select *
from course
where id in (select course_id
			from student_course
            where discount > 5);
/* output
*/


/* 
-- display all courses that have fee more than average fee of all courses. 
projection: course 
condition: course(more than average fee of all courses.)
*/
select *
from course
where fee > (select avg(fee)
			from course);
/* output
*/


/* 
-- display all courses that have discount more than average discount of all courses. 
projection: course 
criteria: student_course 
*/
select * 
from course
where id in (select course_id
			from student_course
            where discount > (select avg(discount)
							from student_course));
 /* output
*/                           
                            
