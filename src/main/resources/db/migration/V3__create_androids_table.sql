CREATE TABLE `androids` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `number` integer NOT NULL,
  `status` enum NOT NULL,
  `description` longtext,
  `model` varchar(120) NOT NULL,
  `appearance` varchar(100) NOT NULL,
  `type_id` integer,
  `created_at` timestamp,
  `updated_at` timestamp,
  `deleted_at` timestamp
);

ALTER TABLE `androids`
ADD FOREIGN KEY (`type_id`)
REFERENCES `types` (`id`);