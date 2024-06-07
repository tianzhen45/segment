CREATE TABLE IF NOT EXISTS `t_word`  (
    `cn_name` varchar(255),
    `en_name` varchar(255)
);
CREATE TABLE IF NOT EXISTS `t_common_col`  (
     `ID` int(11),
    `COL_NAME` varchar(255),
    `COL_CN_NAME` varchar(255),
    `TYPE` varchar(255),
    `LEN` varchar(255),
    `SYSTEM_CODE` varchar(255),
    PRIMARY KEY (`ID`)
);