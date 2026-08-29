USE `MijuPrice`;
-- ----------------------------
-- 原料色号表
-- ----------------------------
CREATE TABLE `mtrcolor` (
  `materialtypestrucname` VARCHAR (50) DEFAULT NULL,
  `materialtypestruc` VARCHAR (45) NOT NULL,
  `colorid` VARCHAR (45) DEFAULT NULL,
  `coloridx` VARCHAR (45) NOT NULL,
  PRIMARY KEY (`materialtypestruc`, `coloridx`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '原料色号表 从erp中导入数据';

-- ----------------------------
-- 包袱样主表
-- ----------------------------
DROP TABLE
IF EXISTS `dsgpackageh`;
  CREATE TABLE `dsgpackageh` (
    `id` INT NOT NULL COMMENT '编号 主键',
    `patternid` VARCHAR (50) DEFAULT NULL COMMENT '款号',
    `flower` VARCHAR (45) DEFAULT NULL COMMENT '花本',
    `DESIGNER` VARCHAR (45) DEFAULT NULL COMMENT '设计师',
    `DSGDATE` DATETIME NULL DEFAULT NULL COMMENT '设计日期',
    `DISC` VARCHAR (45) DEFAULT NULL COMMENT '花本，不确定是否这么叫',
    `CONST` VARCHAR (300) DEFAULT NULL COMMENT '成分',
    `picks_inch` DOUBLE NULL DEFAULT NULL COMMENT '纬密英寸',
    `picks_cm` DOUBLE NULL DEFAULT NULL COMMENT '纬密厘米',
    `warp` VARCHAR (200) DEFAULT NULL COMMENT '经纱排列',
    `breadth` DOUBLE NULL DEFAULT NULL COMMENT '门幅',
    `yarn` VARCHAR (200) DEFAULT NULL COMMENT '经纱类型',
    `totalends` INT NULL DEFAULT NULL COMMENT '总经',
    `target_price` DOUBLE NULL DEFAULT NULL COMMENT '价格',
    `shipto` VARCHAR (200) DEFAULT NULL COMMENT '邮寄地址，不确定叫法',
    `confirmflag` VARCHAR (5) DEFAULT 'N' COMMENT '设计师确认标准，确认后才能被ERP查询调用',
    `quoteflag` VARCHAR (3) DEFAULT 'N' COMMENT '引用标志，被ERP调用过的就不在出来的可调用列表里面',
    PRIMARY KEY (`id`) USING BTREE
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '包袱样主表';
  
  -- ----------------------------
  -- 包袱样明细表
  -- ----------------------------
  CREATE TABLE `dsgpackageb` (
    `id` INT NOT NULL COMMENT 'id 主键  ',
    `yarntype` VARCHAR (100) DEFAULT NULL COMMENT '纬纱规格',
    `shorts` VARCHAR (100) DEFAULT NULL,
    `idx` INT NOT NULL COMMENT '纬纱组编码',
    `colorcode` VARCHAR (100) DEFAULT NULL COMMENT '纬纱组颜色名称',
    `colorname` VARCHAR (100) DEFAULT NULL COMMENT '纱线颜色编号',
    `color` VARCHAR (100) DEFAULT NULL COMMENT '纱线颜色',
    `selectors` INT NOT NULL COMMENT '选纬，纬纱第几个',
    PRIMARY KEY (`id`, `selectors`, `idx`) USING BTREE
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '包袱样明细表';