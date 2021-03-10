CREATE TABLE bot_baned_group (
    group_id BIGINT NOT NULL,
    add_user BIGINT NOT NULL,
    add_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (group_id)
) COMMENT '被禁用的群';

CREATE TABLE bot_baned_user (
    user_id BIGINT NOT NULL,
    add_user BIGINT NOT NULL,
    add_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
) COMMENT '被禁用的用户';

CREATE TABLE bot_config (
    config_name VARCHAR (255) NOT NULL COMMENT 'key',
    config_value VARCHAR (255) NOT NULL COMMENT 'value',
    add_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '添加事件',
    CONSTRAINT `PRIMARY` PRIMARY KEY (config_name)
) COMMENT '机器人配置表，采用key-value模式。主键为int自增。';

CREATE TABLE bot_function_trigger (
    id INT auto_increment,
    char_id VARCHAR (63) NOT NULL COMMENT '字符id',
    trigger_name VARCHAR (256) NOT NULL COMMENT '功能名',
    trigger_comment VARCHAR (512) NOT NULL COMMENT '功能描述',
    keyword VARCHAR (256) NOT NULL COMMENT '触发关键字',
    impl_class VARCHAR (256) NOT NULL COMMENT '触发功能的实现函数',
    enabled TINYINT (1) DEFAULT 1 NULL COMMENT '是否启用，默认为true',
    shown TINYINT (1) DEFAULT 1 NULL COMMENT '是否在功能列表中展示',
    CONSTRAINT `PRIMARY` PRIMARY KEY (id),
    CONSTRAINT bot_function_trigger_pk UNIQUE (char_id)
) COMMENT '关键词功能触发表';

CREATE TABLE bot_game_fishing (
    fish_id VARCHAR (256) NOT NULL COMMENT '鱼名称id',
    fish_name VARCHAR (256) NOT NULL COMMENT '鱼的名称',
    fish_probability DOUBLE NOT NULL COMMENT '钓到的概率',
    fish_value BIGINT NOT NULL COMMENT '鱼的价值',
    is_special TINYINT (1) DEFAULT 0 NOT NULL COMMENT '是否为特殊种类的鱼',
    special_group BIGINT NULL COMMENT '如果是特殊鱼的话，则需要注明是哪个群的',
    CONSTRAINT `PRIMARY` PRIMARY KEY (fish_id)
) COMMENT '钓鱼游戏表';

CREATE TABLE bot_reply_message (
    reply_key VARCHAR (256) NOT NULL,
    reply_value VARCHAR (4096) NOT NULL,
    add_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (reply_key)
) COMMENT '固定回复文案';

CREATE TABLE group_coin (
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    coin BIGINT DEFAULT 0 NOT NULL,
    get_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (group_id, user_id)
) COMMENT '领金币';

CREATE TABLE group_exp (
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    exp BIGINT DEFAULT 0 NOT NULL,
    sign_in_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id, group_id)
) COMMENT '群聊签到经验值';

CREATE TABLE group_exp_rank_name (
    group_id BIGINT NOT NULL COMMENT '群号',
    exp_offset INT DEFAULT 0 NULL,
    rank_1 VARCHAR (6) DEFAULT 'Ⅰ级' NULL,
    rank_2 VARCHAR (6) DEFAULT 'Ⅱ级' NULL,
    rank_3 VARCHAR (6) DEFAULT 'Ⅲ级' NULL,
    rank_4 VARCHAR (6) DEFAULT 'Ⅳ级' NULL,
    rank_5 VARCHAR (6) DEFAULT 'Ⅴ级' NULL,
    rank_6 VARCHAR (6) DEFAULT 'Ⅵ级' NULL,
    rank_7 VARCHAR (6) DEFAULT 'Ⅶ级' NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (group_id)
) COMMENT '群内等级名称';

CREATE TABLE group_fishing (
    group_id BIGINT NOT NULL COMMENT '群号',
    user_id BIGINT NOT NULL COMMENT 'QQ号',
    fish_1 VARCHAR (256) NULL,
    fish_2 VARCHAR (256) NULL,
    fish_3 VARCHAR (256) NULL,
    fish_4 VARCHAR (256) NULL,
    fish_5 VARCHAR (256) NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (group_id, user_id),
    CONSTRAINT group_fishing_fk1 FOREIGN KEY (fish_1) REFERENCES bot_game_fishing (fish_id) ON DELETE
    SET NULL,
    CONSTRAINT group_fishing_fk2 FOREIGN KEY (fish_2) REFERENCES bot_game_fishing (fish_id) ON DELETE
    SET NULL,
    CONSTRAINT group_fishing_fk3 FOREIGN KEY (fish_3) REFERENCES bot_game_fishing (fish_id) ON DELETE
    SET NULL,
    CONSTRAINT group_fishing_fk4 FOREIGN KEY (fish_4) REFERENCES bot_game_fishing (fish_id) ON DELETE
    SET NULL,
    CONSTRAINT group_fishing_fk5 FOREIGN KEY (fish_5) REFERENCES bot_game_fishing (fish_id) ON DELETE
    SET NULL
) COMMENT '群内钓鱼信息表';

CREATE TABLE group_timely_message (
    group_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    message_string VARCHAR (256) NOT NULL,
    send_time datetime NOT NULL,
    enabled TINYINT (1) DEFAULT 1 NOT NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (group_id, user_id)
);

CREATE TABLE hs_card (
    id VARCHAR (32) NOT NULL COMMENT '卡的数字id',
    `set` VARCHAR (128) NOT NULL COMMENT '卡所属的扩展包',
    NAME VARCHAR (128) NOT NULL COMMENT '卡的名字',
    rarity VARCHAR (16) NOT NULL COMMENT '稀有度',
    imgUrl VARCHAR (1024) NULL COMMENT '渲染卡牌的图片链接',
    CONSTRAINT `PRIMARY` PRIMARY KEY (id)
) COMMENT '炉石卡牌';

CREATE TABLE user_coin (
    user_id BIGINT NOT NULL,
    coin BIGINT DEFAULT 0 NOT NULL,
    get_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
) COMMENT '用户领金币';

CREATE TABLE user_exp (
    user_id BIGINT NOT NULL,
    exp BIGINT DEFAULT 0 NOT NULL,
    sign_in_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
) COMMENT '用户签到经验值';

CREATE TABLE user_exp_rank_name (
    user_id BIGINT NOT NULL COMMENT '群号',
    exp_offset INT DEFAULT 0 NULL,
    rank_1 VARCHAR (6) DEFAULT 'Ⅰ级' NULL,
    rank_2 VARCHAR (6) DEFAULT 'Ⅱ级' NULL,
    rank_3 VARCHAR (6) DEFAULT 'Ⅲ级' NULL,
    rank_4 VARCHAR (6) DEFAULT 'Ⅳ级' NULL,
    rank_5 VARCHAR (6) DEFAULT 'Ⅴ级' NULL,
    rank_6 VARCHAR (6) DEFAULT 'Ⅵ级' NULL,
    rank_7 VARCHAR (6) DEFAULT 'Ⅶ级' NULL,
    CONSTRAINT `PRIMARY` PRIMARY KEY (user_id)
) COMMENT '私聊签到等级名称';