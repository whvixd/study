# 分页查，并查总数
SELECT SQL_CALC_FOUND_ROWS id, teacherId, teacherName FROM studentTask WHERE subjectName = '高中数学' LIMIT 1,20;
SELECT FOUND_ROWS() as total;

# 与当前时间差小于1小时
SELECT TIMESTAMPDIFF(HOUR, '2020-10-21 00:00:00', NOW()) < 1;