CREATE TABLE `android_executioner` (
  `id` integer AUTO_INCREMENT,
  `description` longtext AUTO_INCREMENT,
  `android_id` integer NOT NULL,
  `executioner_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp,
  PRIMARY KEY (`id`, `description`)
);

ALTER TABLE `android_executioner`
ADD FOREIGN KEY (`android_id`)
REFERENCES `androids` (`id`);

ALTER TABLE `android_executioner`
ADD FOREIGN KEY (`executioner_id`)
REFERENCES `executioners` (`id`);