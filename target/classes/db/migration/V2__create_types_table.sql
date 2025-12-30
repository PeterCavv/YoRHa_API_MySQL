CREATE TABLE `types` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(150) NOT NULL,
  `description` longtext,
  `created_at` timestamp,
  `updated_at` timestamp,
  `deleted_at` timestamp
);