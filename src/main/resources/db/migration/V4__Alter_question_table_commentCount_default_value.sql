ALTER TABLE `question`
MODIFY COLUMN `comment_count`  int(11) NULL DEFAULT 0 AFTER `creator`;
