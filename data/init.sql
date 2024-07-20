CREATE DATABASE IF NOT EXISTS db_item;
CREATE DATABASE IF NOT EXISTS db_cart;

USE db_item;
DROP TABLE IF EXISTS tb_item;
CREATE TABLE `tb_item` (
   `id` int NOT NULL AUTO_INCREMENT,
   `description` varchar(45) DEFAULT NULL,
   `price` double NOT NULL,
   PRIMARY KEY (`id`)
 );
INSERT INTO tb_item VALUES
(NULL, 'shoes', 20),
(NULL, 'shirt', 50);

USE db_cart;
DROP TABLE IF EXISTS tb_cart;
CREATE TABLE `tb_cart` (
   `user_id` int NOT NULL,
   `item_id` int NOT NULL,
   `quantity` smallint NOT NULL,
   PRIMARY KEY (`user_id`, `item_id`)
 );
INSERT INTO tb_cart VALUES
(1, 1, 1), (1, 2, 2), (2, 2, 2);