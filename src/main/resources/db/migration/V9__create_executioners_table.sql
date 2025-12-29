CREATE TABLE `executioners` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `android_id` integer NOT NULL,
  `weapon_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

ALTER TABLE `executioners`
ADD FOREIGN KEY (`android_id`)
REFERENCES `androids` (`id`);

ALTER TABLE `executioners`
ADD FOREIGN KEY (`weapon_id`)
REFERENCES `weapons` (`id`);