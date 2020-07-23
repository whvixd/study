# 分页查，并查总数
SELECT SQL_CALC_FOUND_ROWS id, teacherId, teacherName FROM studentTask WHERE subjectName = '高中数学' LIMIT 1,20;
SELECT FOUND_ROWS() as total;