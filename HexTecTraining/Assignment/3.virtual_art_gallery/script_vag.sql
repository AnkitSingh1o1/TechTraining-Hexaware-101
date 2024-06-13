use mydb_vag;

show tables;

describe artists;
describe artworks;
describe categories;
describe exhibition;
describe exhibition_artwork;

ALTER TABLE categories
RENAME COLUMN catogeries_name TO categories_name;

alter table artists
add column isActive varchar(255)
default 'yes'; 

-- ----------------INSERTION------------------------------------------------------------------
insert into artists(artists_id, artists_name, artists_biography, artists_nationality) values
(1, 'Pablo Picasso', 'Renowned Spanish painter and sculptor.', 'Spanish'),
(2, 'Vincent van Gogh', 'Dutch post-impressionist painter.', 'Dutch'),
(3, 'Leonardo da Vinci', 'Italian polymath of the Renaissance.', 'Italian');

select * from artists;


INSERT INTO categories (categories_id, catogeries_name) VALUES
(1, 'Painting'),
(2, 'Sculpture'),
(3, 'Photography');

select * from categories;


insert into artworks(categories_id, artists_id, artwork_id, artwork_title, artwork_year, artwork_description, artwork_image_url) values
(1, 2, 1, 'Starry Night', 1889, 'A famous painting by Vincent van Gogh.', 'starry_night.jpg'),
(1, 3, 2, 'Mona Lisa', 1503, 'The iconic portrait by Leonardo da Vinci.', 'mona_lisa.jpg'),
(1, 1, 3, 'Guernica', 1937, 'Pablo Picasso\'s powerful anti-war mural.', 'guernica.jpg');

select * from artworks;

INSERT INTO exhibition (exhibition_id, exhibition_title, exhibition_start_date, exhibition_end_date, exhibition_description) VALUES
(1, 'Modern Art Masterpieces', '2023-01-01', '2023-03-01', 'A collection of modern art masterpieces.'),
(2, 'Renaissance Art', '2023-04-01', '2023-06-01', 'A showcase of Renaissance art treasures.');

select * from exhibition;

INSERT INTO exhibition_artwork (exhibition_id, artwork_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2);

select * from exhibition_artwork;

-- -------------------------------------------------

/* 
1. Retrieve the names of all artists along with the number of artworks they have in the gallery, and 
list them in descending order of the number of artworks.
*/
-- projection: artists
-- criteria: artwork
select a.artists_name, count(*) as Number_of_Artwork
from artists a join artworks aw on a.artists_id = aw.artists_id
group by a.artists_name
order by Number_of_Artwork desc;
/* Output
'Pablo Picasso','1'
'Vincent van Gogh','1'
'Leonardo da Vinci','1'

*/


-- 2. List the titles of artworks created by artists from 'Spanish' and 'Dutch' nationalities, and order them by the year in ascending order.
-- projection: artwork_titles
-- criteria: artists
select aw.artwork_title, a.artists_nationality
from artists a join artworks aw on a.artists_id = aw.artists_id
where a.artists_nationality in ('Spanish', 'Dutch')
order by aw.artwork_year;
/* Output
'Starry Night','Dutch'
'Guernica','Spanish'
*/


-- 3. Find the names of all artists who have artworks in the 'Painting' category, and the number of artworks they have in this category.
-- projection: artists
-- criteria: artwork
select a.artists_name, count(*) as number_of_artwork, c.categories_name
from artists a join artworks aw on a.artists_id = aw.artists_id
join categories c on c.categories_id = aw.categories_id
where c.catogeries_name = 'Painting'
group by a.artists_name;


-- 4. List the names of artworks from the 'Modern Art Masterpieces' exhibition, along with their artists and categories.
-- projection: artworks, artists, categories
-- criteria: exhibition
select artwork_title , artists_name, categories_name, exhibition_title 
from artwork aw join artists a on aw.artists_id = aw.artists_id
				join categories c on c.categories_id = aw.categories_id 
				join exhibition_artwork exaw on exaw.artwork_id = aw.artwork_id
                join exhibition e on e.exhibition_id = exaw.exhibition_id
where exhibition_ttile = 'Modern Art Masterpieces';


-- 5. Find the artists who have more than two artworks in the gallery.
-- projection: artists
-- criteria: artwork
select artist_name, count(*) as number_of_artwork
from artists a join artwork aw on a.artists_id = aw.artists_id
group by artists_id
having number_of_artwork > 2;

-- 6. Find the titles of artworks that were exhibited in both 'Modern Art Masterpieces' and 'Renaissance Art' exhibitions
-- projection:artworks
 -- criteria:exhibition
select artwork_title, e.exhibition_title
from artworks aw join exhibition_artwork exaw on exaw.artwork_id = aw.artwork_id
                join exhibition e on e.exhibition_id = exaw.exhibition_id
                where e.exhibition_title in ('Modern Art Masterpieces','Renaissance Art');


-- 7. Find the total number of artworks in each category
select categories_name, count(*) as number_of_artwork
from artworks a right join categories c on a.categories_id = c.categories_id
group by categories_name;
/* output
'Painting','3'
'Sculpture','1'
'Photography','1'
*/


-- 8. List artists who have more than 3 artworks in the gallery.
select artists_name, count(*) as number_of_artworks
from artists a join artworks aw on a.artists_id = aw.artists_id
group by artists_name
having number_of_artworks > 0;
/* Output
'Pablo Picasso','1'
'Vincent van Gogh','1'
'Leonardo da Vinci','1'
*/


-- 9. Find the artworks created by artists from a specific nationality (e.g., Spanish).
select a.*, aw.*
from artists a join artworks aw on a.artists_id = aw.artists_id
where artists_nationality = 'Spanish';
/* output
'1','Pablo Picasso','Renowned Spanish painter and sculptor.','Spanish','1','1','3','Guernica','1937','Pablo Picasso\'s powerful anti-war mural.','guernica.jpg'
*/


-- 10. List exhibitions that feature artwork by both Vincent van Gogh and Leonardo da Vinci.
select artists_name, e.*
from artists a join artworks aw on a.artists_id = aw.artists_id
				join exhibition_artwork ea on aw.artwork_id = ea.artwork_id
                join exhibition e on e.exhibition_id = ea.exhibition_id
where artists_name in ('Vincent van Gogh', 'Leonardo da Vinci');
/* output
'Vincent van Gogh','1','Modern Art Masterpieces','2023-01-01','2023-03-01','A collection of modern art masterpieces.'
'Leonardo da Vinci','1','Modern Art Masterpieces','2023-01-01','2023-03-01','A collection of modern art masterpieces.'
'Leonardo da Vinci','2','Renaissance Art','2023-04-01','2023-06-01','A showcase of Renaissance art treasures.'
*/
      

-- 11. Find all the artworks that have not been included in any exhibition.
select *
from artworks
where artwork_id not in (select aw.artwork_id
						from artworks aw join exhibition_artwork ea on aw.artwork_id = ea.artwork_id
						join exhibition e on e.exhibition_id = ea.exhibition_id);
/* output
NULL
*/
                
-- 12. List artists who have created artworks in all available categories.
select a.*
from artists a join artworks aw on a.artists_id = aw.artists_id
				join categories c on aw.categories_id = c.categories_id
group by a.artists_name
having c.categories_name in (select distinct categories_name from categories);

select * from categories;


-- 13. List the total number of artworks in each category.
select categories_name, count(*) as number_of_artwork
from artworks aw right join categories c on aw.categories_id = c.categories_id
group by categories_name;
/* output
'Painting','3'
'Sculpture','1'
'Photography','1'
*/

-- 14. Find the artists who have more than 2 artworks in the gallery.
select a.artists_name, count(*) as number_of_artworks
from artists a join artworks aw on a.artists_id = aw.artists_id
group by a.artists_name
having number_of_artworks > 0;
/* output
'Pablo Picasso','1'
'Vincent van Gogh','1'
'Leonardo da Vinci','1'
*/

-- 15. List the categories with the average year of artworks they contain, only for categories with more than 1 artwork.
select categories_name, avg(artwork_year)
from artworks aw right join categories c on aw.categories_id = c.categories_id
group by categories_name
having count(*) > 1;
/* output
'Painting','1776.3333'
*/


-- 16. Find the artworks that were exhibited in the 'Modern Art Masterpieces' exhibition.
select exhibition_title, aw.artwork_id, aw.artwork_title
from artworks aw join exhibition_artwork ea on aw.artwork_id = ea.artwork_id
				join exhibition e on e.exhibition_id = ea.exhibition_id
where exhibition_title = 'Modern Art Masterpieces';

/* output
'Modern Art Masterpieces','1','Starry Night'
'Modern Art Masterpieces','2','Mona Lisa'
'Modern Art Masterpieces','3','Guernica'
*/


-- 17. Find the categories where the average year of artworks is greater than the average year of all artworks.
select distinct c.*
from artworks aw join categories c on c.categories_id = aw.categories_id
group by artwork_title
having avg(aw.artwork_year) > (select avg(aw.artwork_year) from artworks);


-- 18. List the artworks that were not exhibited in any exhibition.
select *
from artworks aw join exhibition_artwork ea on aw.artwork_id = ea.artwork_id
where aw.artwork_id not in (
    select artwork_id
    from exhibition_artwork
);


-- 19. Show artists who have artworks in the same category as "Mona Lisa."
select a.*
from artists a 
join artworks aw on a.artists_id = aw.artists_id
where aw.artwork_title = 'Mona Lisa';
/* output
'3','Leonardo da Vinci','Italian polymath of the Renaissance.','Italian'
*/


-- 20. List the names of artists and the number of artworks they have in the gallery.
select aw.artists_id, count(*) as no_of_artwork
from artists a 
join artworks aw on a.artists_id = aw.artists_id
group by artists_id;
/* output
'1','1'
'2','1'
'3','1'
*/