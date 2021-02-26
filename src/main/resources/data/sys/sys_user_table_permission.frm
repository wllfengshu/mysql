CREATE TABLE `sys_user_table_permission` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `table_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;