CREATE TABLE `comment` (
`id`  int NOT NULL AUTO_INCREMENT ,
`parent_id`  int NULL DEFAULT 0 ,
`type`  int NULL ,
`content`  varchar(500) NULL ,
`commentator`  varchar(32) NULL ,
`gmt_create`  bigint NULL ,
`gmt_modified`  bigint NULL ,
`like_count`  int NULL DEFAULT 0 ,
PRIMARY KEY (`id`)
)
;
