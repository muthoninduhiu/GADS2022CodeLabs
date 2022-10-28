/*Problem 1: Write a SQL query to get the names of all parks with fewer than 1,000,000 visitors.*/
SELECT name FROM park
WHERE park_visitors < 1000000
/*Problem 2: Write a SQL query to get the number of distinct cities in the park table*/
SELECT COUNT(DISTINCT city) FROM park
/*Problem 3: Write a SQL query to get the total number of visitors to parks in San Francisco.*/
SELECT SUM(park_visitors) FROM park
WHERE city = "San Francisco"
/*Problem 4: Write a SQL query to the top 5 parks (names only) along with their
visitor count that had the most visitors, in descending order.*/
SELECT name, park_visitors FROM park
ORDER BY park_visitors DESC
LIMIT 5
/*insert*/
INSERT INTO park
VALUES (null, 'Googleplex', 'Mountain View', 12, null, 0, '')
/*check by*/
SELECT * FROM park
WHERE name = 'Googleplex'
/*update*/
UPDATE park
SET area_acres = 46,
established = 1088640000,
type = 'office'
WHERE name = 'Googleplex'
/*delete*/
DELETE FROM park
WHERE name = 'Googleplex'
