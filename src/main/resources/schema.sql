START TRANSACTION;

SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP TABLE IF EXISTS `Comments`;
DROP TABLE IF EXISTS `Posts`;
DROP TABLE IF EXISTS `Users`;

CREATE TABLE `Users`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `username` VARCHAR(255) NOT NULL,
    `profil_picture` VARCHAR(255) NULL,
    `description` TINYTEXT NULL,
    `mail` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
);

CREATE TABLE `Posts`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `likes` BIGINT DEFAULT 0,
    `post_title` VARCHAR(255) NOT NULL,
    `post_picture` VARCHAR(255) NULL,
    `content` TEXT NOT NULL,
    `created_at` DATETIME NOT NULL,
    CONSTRAINT `posts_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`) ON DELETE CASCADE
);

CREATE TABLE `Comments`(
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `user_id` BIGINT UNSIGNED NOT NULL,
    `post_id` BIGINT UNSIGNED NOT NULL,
    `content` TINYTEXT NOT NULL,
    `created_at` DATETIME NOT NULL,
    CONSTRAINT `comments_user_id_foreign` FOREIGN KEY (`user_id`) REFERENCES `Users`(`id`) ON DELETE CASCADE,
    CONSTRAINT `comments_post_id_foreign` FOREIGN KEY (`post_id`) REFERENCES `Posts`(`id`) ON DELETE CASCADE
);

COMMIT;

INSERT INTO `Users` (`username`, `profil_picture`, `description`, `mail`, `password`) VALUES ('Grace', 'test', 'test', 'test', '1234'); 
INSERT INTO `Users` (`username`, `profil_picture`, `description`, `mail`, `password`) VALUES ('Ada', 'test', 'test', 'test', '1234'); 
INSERT INTO `Posts` (`user_id`, `likes`, `post_title`, `post_picture`, `content`, `created_at`) VALUES (1, 0, 'test', 'test', 'test', '2021-01-01 00:00:00');

