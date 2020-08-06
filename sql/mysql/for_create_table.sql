-- 控制台中需要用 && 判断是否为一整句，结束后 设为默认 ;

DELIMITER &&
-- 循环建表
DROP PROCEDURE IF EXISTS create_test_table&&

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
  END&&

DELIMITER ;

-- 执行程序
CALL create_test_table(0, 10);

-- 删除
DROP PROCEDURE IF EXISTS drop_test_table;
CREATE PROCEDURE drop_test_table(IN begin_index INT, IN end_index INT)
  BEGIN
    DECLARE i INT;
    SET i = begin_index;
    WHILE i < end_index + 1 DO
      SET @sql_create_table = concat(
          'DROP TABLE IF EXISTS test_db_log_', i);
      PREPARE sql_create_table FROM @sql_create_table;
      EXECUTE sql_create_table;
      SET i = i + 1;
    END WHILE;
  END;

CALL drop_test_table(0,10);