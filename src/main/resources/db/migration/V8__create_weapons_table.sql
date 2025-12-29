CREATE TABLE `weapons` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(120) NOT NULL,
  `description` longtext NOT NULL,
  `weapon_type_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

ALTER TABLE `weapons`
ADD FOREIGN KEY (`weapon_type_id`)
REFERENCES `weapon_types` (`id`);