-- 循环建表
DROP PROCEDURE IF EXISTS create_test_table;
CREATE PROCEDURE create_test_table(IN begin_index INT, IN end_index INT)
  BEGIN
    DECLARE i INT;
    SET i = begin_index;
    WHILE i < end_index + 1 DO
      SET @sql_create_table = concat(
          'CREATE TABLE IF NOT EXISTS test_db_log_', i,
          "(
            `id` int(11) NOT NULL AUTO_INCREMENT,
            `createTime` datetime DEFAULT NULL COMMENT '创建时间',
            PRIMARY KEY (`id`),
          INDEX `createTime` (`createTime`) USING BTREE
          ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='pattern'
          ");
      PREPARE sql_create_table FROM @sql_create_table;
      EXECUTE sql_create_table;
      SET i = i + 1;
    END WHILE;
  END;

-- 执行程序
CALL create_test_table(0, 10);