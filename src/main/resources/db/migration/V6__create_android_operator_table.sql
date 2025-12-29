CREATE TABLE `android_operator` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `android_id` integer NOT NULL,
  `operator_id` integer NOT NULL,
  `created_at` timestamp,
  `updated_at` timestamp
);

ALTER TABLE `android_operator`
ADD FOREIGN KEY (`android_id`)
REFERENCES `androids` (`id`);

ALTER TABLE `android_operator`
ADD FOREIGN KEY (`operator_id`)
REFERENCES `operators` (`id`);