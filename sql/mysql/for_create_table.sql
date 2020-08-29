-- @link http://www.cs.jhu.edu/~nikhil/proc_examples.txt
-- 控制台中需要用 && 判断是否为一整句，结束后 设为默认 ;

######################################## create_split_table ########################################
DELIMITER &&
-- 循环建表
DROP PROCEDURE IF EXISTS create_split_table&&

CREATE PROCEDURE create_split_table(IN begin_index INT, IN end_index INT, IN table_name VARCHAR(20))
  BEGIN
    DECLARE i INT;
    SET i = begin_index;
    WHILE i < end_index + 1 DO
      SET @create_table_pre = 'CREATE TABLE IF NOT EXISTS ';
      SET @table_name_index = concat(table_name, '_', i);
      -- 建表
      SET @create_table_sql = "
          (
            `id` int(11) NOT NULL AUTO_INCREMENT,
            `createTime` datetime DEFAULT NULL COMMENT '创建时间',
             PRIMARY KEY (`id`),
             INDEX `createTime` (`createTime`) USING BTREE
           ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='pattern';
         ";
      SET @sql_create_table = concat(@create_table_pre, @table_name_index, @create_table_sql);
      PREPARE sql_create_table FROM @sql_create_table;
      EXECUTE sql_create_table;
      SET i = i + 1;
    END WHILE;
  END&&

-- 恢复默认
DELIMITER ;

-- 执行程序
CALL create_split_table(0, 10, 'test_split');
######################################## drop_split_table ########################################

######################################## drop_split_table ########################################

-- 删除
DELIMITER &&
DROP PROCEDURE IF EXISTS drop_split_table&&
CREATE PROCEDURE drop_split_table(IN begin_index INT, IN end_index INT, IN table_name VARCHAR(20))
  BEGIN
    DECLARE i INT;
    SET i = begin_index;
    WHILE i < end_index + 1 DO
      SET @sql_create_table = concat(
          'DROP TABLE IF EXISTS ', table_name, '_', i);
      PREPARE sql_create_table FROM @sql_create_table;
      EXECUTE sql_create_table;
      SET i = i + 1;
    END WHILE;
  END&&

-- 恢复默认
DELIMITER ;

CALL drop_split_table(0, 9, 'test_split');
########################################################################################################


######################################## create_split_table_sql ########################################
DELIMITER &&
-- 循环建表
DROP PROCEDURE IF EXISTS create_split_table_sql&&

CREATE PROCEDURE create_split_table_sql(IN begin_index INT, IN end_index INT,
                                        IN table_name  VARCHAR(20), IN create_table_sql VARCHAR(2048))
  BEGIN
    DECLARE i INT;
    SET i = begin_index;
    WHILE i < end_index + 1 DO
      SET @create_table_pre = 'CREATE TABLE IF NOT EXISTS ';
      SET @table_name_index = concat(table_name, '_', i);
      -- 建表
      SET @sql_create_table = concat(@create_table_pre, @table_name_index, create_table_sql);
      PREPARE sql_create_table FROM @sql_create_table;
      EXECUTE sql_create_table;
      SET i = i + 1;
    END WHILE;
  END&&

-- 恢复默认
DELIMITER ;

CALL create_split_table_sql(0, 9, 'test_split_log',
                            "(
            `id` int(11) NOT NULL AUTO_INCREMENT,
            `createTime` datetime DEFAULT NULL COMMENT '创建时间',
             PRIMARY KEY (`id`),
             INDEX `createTime` (`createTime`) USING BTREE
           ) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='pattern';");

########################################################################################################
SHOW TABLES;