CREATE TABLE `androids` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `number` integer NOT NULL,
  `status` varchar(150) NOT NULL,
  `description` longtext,
  `model` varchar(120) NOT NULL,
  `appearance` varchar(100) NOT NULL,
  `type_id` integer,
  `operator_id` integer NULL,
  `created_at` timestamp,
  `updated_at` timestamp,
  `deleted_at` timestamp
);

ALTER TABLE `androids`
ADD FOREIGN KEY (`type_id`)
REFERENCES `types` (`id`);

ALTER TABLE `androids`
ADD CONSTRAINT fk_operator
FOREIGN KEY (operator_id)
REFERENCES androids(id);