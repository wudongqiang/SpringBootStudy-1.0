-- 创建表
DROP TABLE IF EXISTS resource;
CREATE TABLE resource(
  ID varchar(64) PRIMARY KEY,
  NAME VARCHAR(255),
  url varchar(255)
);

select * from resource
