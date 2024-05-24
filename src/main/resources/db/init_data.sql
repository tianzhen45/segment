CREATE TABLE IF NOT EXISTS `t_word`  (
                           `cn_name` varchar(255),
                           `en_name` varchar(255)
);
CREATE TABLE `t_common_col`  (
                                 `ID` int(11),
                                 `COL_NAME` varchar(255),
                                 `COL_EN_NAME` varchar(255),
                                 `TYPE` varchar(255),
                                 `LEN` varchar(255),
                                 `SYSTEM_CODE` varchar(255),
                                 PRIMARY KEY (`ID`)
);

INSERT INTO `t_common_col` VALUES (1, '创建人ID', 'CREATED_BY', 'varchar', '255', 'TY');
INSERT INTO `t_common_col` VALUES (2, '创建人名称', 'CREATED_BY_NAME', 'varchar', '255', 'TY');
INSERT INTO `t_common_col` VALUES (3, '创建时间', 'CREATED_DATE', 'datetime', '', 'TY');
INSERT INTO `t_common_col` VALUES (4, '创建人所在机构编码', 'CREATED_ORG_ID', 'varchar', '255', 'TY');
INSERT INTO `t_common_col` VALUES (5, '租户号', 'GROUP_CODE', 'varchar', '32', 'TY');
INSERT INTO `t_common_col` VALUES (6, '主键', 'ID', 'varchar', '32', 'TY');
INSERT INTO `t_common_col` VALUES (7, '租户号', 'TENANT_ID', 'varchar', '16', 'TY');
INSERT INTO `t_common_col` VALUES (8, '修改人ID', 'UPDATED_BY', 'varchar', '255', 'TY');
INSERT INTO `t_common_col` VALUES (9, '修改人名称', 'UPDATED_BY_NAME', 'varchar', '255', 'TY');
INSERT INTO `t_common_col` VALUES (10, '修改时间', 'UPDATED_DATE', 'datetime', '', 'TY');
INSERT INTO `t_common_col` VALUES (11, '修改人所在机构编码', 'UPDATED_ORG_ID', 'varchar', '64', 'TY');
INSERT INTO `t_common_col` VALUES (12, '创建时间', 'create_time', 'datetime', '', 'ZD');
INSERT INTO `t_common_col` VALUES (13, '创建人', 'create_user', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (14, '创建机构', 'create_inst', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (15, '更新时间', 'update_time', 'datetime', '', 'ZD');
INSERT INTO `t_common_col` VALUES (16, '更新人', 'update_user', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (17, '更新机构', 'update_inst', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (18, '经办机构', 'hdl_inst', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (19, '经办时间', 'hdl_time', 'datetime', '', 'ZD');
INSERT INTO `t_common_col` VALUES (20, '经办人', 'hdl_user', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (21, '扩展编号', 'expd_id', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (22, '删除标志', 'del_ind', 'varchar', '1', 'ZD');
INSERT INTO `t_common_col` VALUES (23, '版本号', 'version', 'int', '10', 'ZD');
INSERT INTO `t_common_col` VALUES (24, '租户ID', 'tenant_id', 'varchar', '32', 'ZD');
INSERT INTO `t_common_col` VALUES (25, '分片键', 'distribute_key', 'varchar', '64', 'ZD');