CREATE TABLE `operators` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `android_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

ALTER TABLE `operators`
ADD FOREIGN KEY (`android_id`)
REFERENCES `androids` (`id`);