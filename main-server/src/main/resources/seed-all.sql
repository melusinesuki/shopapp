-- ============================================
-- 珍品酒仓 完整种子数据
-- ============================================
-- 使用方法：在 MySQL 中执行此文件即可插入所有测试数据
-- 注意：如果已有同名数据，请先清理相关表
-- int_price 单位为分（1元 = 100分）
-- ============================================

-- ============================================
-- 一、商品数据（新增 12 款，加上原有的共 24 款）
-- ============================================
INSERT INTO product (str_no, str_surface, str_title, int_price, str_use, content, inventory, sales, created_at, updated_at, is_deleted, deleted_time)
VALUES
('SP013', '', '茅台 生肖纪念酒 丙申猴年 53度 500ml', 249900, '酱香突出，酒体醇厚，回味悠长，生肖收藏佳品，限量发行。', '', 300, 568, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP014', '', '五粮液 1618 52度 500ml', 139900, '香气悠久，味醇厚，入口甘美，入喉净爽，各味谐调。', '', 650, 876, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP015', '', '洋河梦之蓝 M3 52度 500ml', 49900, '绵柔顺喉，高而不烈，柔而不寡，丰满协调，余味净爽。', '', 700, 1120, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP016', '', '汾酒青花30 53度 500ml', 59900, '清香纯正，醇甜柔和，自然谐调，余味爽净，一清到底。', '', 400, 678, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP017', '', '古井贡酒 年份原浆 古16 50度 500ml', 45900, '色清如水晶，香纯似幽兰，入口甘美醇和，回味经久不息。', '', 520, 445, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP018', '', '舍得 品味舍得 52度 500ml', 39900, '香气馥郁，绵甜爽净，柔和协调，余味悠长，浓香典范。', '', 600, 723, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP019', '', '酒鬼酒 内参 52度 500ml', 89900, '馥郁香型，前浓中清后酱，一口三香，酒体醇厚丰满。', '', 280, 234, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP020', '', '董酒 国密 54度 500ml', 36900, '药香型代表，晶莹透亮，药香舒适，醇和浓郁，余味悠长。', '', 350, 189, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP021', '', '郎酒 青花郎 53度 500ml', 129900, '酱香突出，醇厚净爽，优雅细腻，回味悠长，空杯留香持久。', '', 200, 345, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP022', '', '习酒 君品 53度 500ml', 89900, '微黄透明，酱香突出，醇厚丰满，细腻体净，回味悠长。', '', 180, 156, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP023', '', '剑南春 东方红 52度 500ml', 89900, '陈香舒适，醇厚绵柔，细腻圆润，甘洌净爽，余味悠长。', '', 250, 267, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP024', '', '水井坊 典藏 52度 500ml', 99900, '陈香飘逸，甘润幽雅，醇厚丰满，余味爽净，高端典藏之选。', '', 150, 123, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL);


-- ============================================
-- 二、测试用户账号
-- 登录方式：邮箱验证码登录，无需密码
-- ============================================
INSERT INTO member_account (id, username, password_hash, nickname, avatar, gender, created_time, update_time, birthday, cellphone)
VALUES
(10001, 'test1@example.com', '', '品酒达人', 'https://ts3.tc.mm.bing.net/th/id/OIP-C.D_0j-989FquhjlnrH_gUbgHaHa?cb=thfc1&rs=1&pid=ImgDetMain&o=7&rm=3', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, '1990-05-15', '13800001001'),
(10002, 'test2@example.com', '', '美酒收藏家', 'https://ts3.tc.mm.bing.net/th/id/OIP-C.D_0j-989FquhjlnrH_gUbgHaHa?cb=thfc1&rs=1&pid=ImgDetMain&o=7&rm=3', 2, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, '1988-10-20', '13800001002'),
(10003, 'test3@example.com', '', '小酌怡情', 'https://ts3.tc.mm.bing.net/th/id/OIP-C.D_0j-989FquhjlnrH_gUbgHaHa?cb=thfc1&rs=1&pid=ImgDetMain&o=7&rm=3', 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, '1995-03-08', '13800001003');


-- ============================================
-- 三、收货地址
-- ============================================
INSERT INTO member_address (id, member_account_id, nickname, cellphone, provinces, city, district, is_default, detail_address, created_time, updated_time)
VALUES
(20001, 10001, '张先生', '13800001001', '北京市', '北京市', '朝阳区', 1, '建国路88号国贸大厦A座1201室', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(20002, 10001, '张先生', '13800001001', '上海市', '上海市', '浦东新区', 0, '陆家嘴金融城世纪大道100号', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(20003, 10002, '李女士', '13800001002', '广东省', '广州市', '天河区', 1, '珠江新城花城大道66号', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(20004, 10002, '李女士', '13800001002', '浙江省', '杭州市', '西湖区', 0, '文三路138号浙大科技园B座501', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(20005, 10003, '王先生', '13800001003', '四川省', '成都市', '武侯区', 1, '天府大道北段999号高新国际广场', UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);


-- ============================================
-- 四、订单数据（5个不同状态的订单）
-- status: 0=待付款, 1=待发货, 2=待收货, 3=已完成, 4=已取消
-- ============================================

-- 订单1：待付款（status=0）
INSERT INTO shop_order (id, member_account_id, order_code, product_list_json, total_price, pay_price, address_json, status, created_time, updated_time, is_deleted, deleted_time)
VALUES
(30001, 10001, 'ZC202605290001', '[{"productId":1,"productNo":"SP001","title":"茅台飞天 53度 500ml","price":149900,"num":2}]', 299800, 299800, '{"nickname":"张先生","cellphone":"13800001001","provinces":"北京市","city":"北京市","district":"朝阳区","detailAddress":"建国路88号国贸大厦A座1201室"}', 0, UNIX_TIMESTAMP() * 1000 - 86400000, UNIX_TIMESTAMP() * 1000 - 86400000, 0, NULL);

-- 订单2：待发货（status=1）
INSERT INTO shop_order (id, member_account_id, order_code, product_list_json, total_price, pay_price, address_json, status, created_time, updated_time, is_deleted, deleted_time)
VALUES
(30002, 10002, 'ZC202605280002', '[{"productId":4,"productNo":"SP004","title":"剑南春 水晶剑 52度 500ml","price":45800,"num":3},{"productId":9,"productNo":"SP009","title":"泸州老窖 特曲 52度 500ml","price":26800,"num":2}]', 191000, 191000, '{"nickname":"李女士","cellphone":"13800001002","provinces":"广东省","city":"广州市","district":"天河区","detailAddress":"珠江新城花城大道66号"}', 1, UNIX_TIMESTAMP() * 1000 - 172800000, UNIX_TIMESTAMP() * 1000 - 172800000, 0, NULL);

-- 订单3：待收货（status=2）
INSERT INTO shop_order (id, member_account_id, order_code, product_list_json, total_price, pay_price, address_json, status, created_time, updated_time, is_deleted, deleted_time)
VALUES
(30003, 10001, 'ZC202605270003', '[{"productId":5,"productNo":"SP005","title":"洋河梦之蓝 M6+ 52度 500ml","price":79900,"num":1}]', 79900, 79900, '{"nickname":"张先生","cellphone":"13800001001","provinces":"北京市","city":"北京市","district":"朝阳区","detailAddress":"建国路88号国贸大厦A座1201室"}', 2, UNIX_TIMESTAMP() * 1000 - 259200000, UNIX_TIMESTAMP() * 1000 - 172800000, 0, NULL);

-- 订单4：已完成（status=3）
INSERT INTO shop_order (id, member_account_id, order_code, product_list_json, total_price, pay_price, address_json, status, created_time, updated_time, is_deleted, deleted_time)
VALUES
(30004, 10003, 'ZC202605250004', '[{"productId":6,"productNo":"SP006","title":"汾酒青花20 53度 475ml","price":29900,"num":2},{"productId":11,"productNo":"SP011","title":"郎酒 红花郎15 53度 500ml","price":69900,"num":1}]', 129700, 129700, '{"nickname":"王先生","cellphone":"13800001003","provinces":"四川省","city":"成都市","district":"武侯区","detailAddress":"天府大道北段999号高新国际广场"}', 3, UNIX_TIMESTAMP() * 1000 - 432000000, UNIX_TIMESTAMP() * 1000 - 345600000, 0, NULL);

-- 订单5：已取消（status=4）
INSERT INTO shop_order (id, member_account_id, order_code, product_list_json, total_price, pay_price, address_json, status, created_time, updated_time, is_deleted, deleted_time)
VALUES
(30005, 10002, 'ZC202605240005', '[{"productId":3,"productNo":"SP003","title":"国窖1573 52度 500ml","price":109900,"num":1}]', 109900, 109900, '{"nickname":"李女士","cellphone":"13800001002","provinces":"浙江省","city":"杭州市","district":"西湖区","detailAddress":"文三路138号浙大科技园B座501"}', 4, UNIX_TIMESTAMP() * 1000 - 518400000, UNIX_TIMESTAMP() * 1000 - 432000000, 0, NULL);


-- ============================================
-- 五、物流数据（对应订单2、3、4）
-- status: 0=已揽件, 1=运输中, 2=派送中, 3=已签收, 4=异常, 5=已退货
-- ============================================

-- 订单2（待发货）的物流：已揽件
INSERT INTO shop_logistics VALUES
(40001, 30002, 'SF1234567890', 0, '【深圳市】快递员已揽件，快递员：李师傅，电话：13900001111', UNIX_TIMESTAMP() * 1000 - 172800000, UNIX_TIMESTAMP() * 1000 - 172800000);

-- 订单3（待收货）的物流：运输中 → 派送中
INSERT INTO shop_logistics VALUES
(40002, 30003, 'YT9876543210', 0, '【北京市】快递员已揽件，快递员：赵师傅，电话：13900002222', UNIX_TIMESTAMP() * 1000 - 259200000, UNIX_TIMESTAMP() * 1000 - 259200000),
(40003, 30003, 'YT9876543210', 1, '【北京市】快件已到达北京分拣中心，正在发往下一站', UNIX_TIMESTAMP() * 1000 - 216000000, UNIX_TIMESTAMP() * 1000 - 216000000),
(40004, 30003, 'YT9876543210', 2, '【北京市朝阳区】快件正在派送中，快递员：孙师傅，电话：13900003333', UNIX_TIMESTAMP() * 1000 - 172800000, UNIX_TIMESTAMP() * 1000 - 172800000);

-- 订单4（已完成）的物流：已签收
INSERT INTO shop_logistics VALUES
(40005, 30004, 'ZTO1122334455', 0, '【成都市】快递员已揽件，快递员：周师傅，电话：13900004444', UNIX_TIMESTAMP() * 1000 - 432000000, UNIX_TIMESTAMP() * 1000 - 432000000),
(40006, 30004, 'ZTO1122334455', 1, '【成都市】快件已到达成都分拣中心', UNIX_TIMESTAMP() * 1000 - 388800000, UNIX_TIMESTAMP() * 1000 - 388800000),
(40007, 30004, 'ZTO1122334455', 2, '【成都市武侯区】快件正在派送中，快递员：吴师傅，电话：13900005555', UNIX_TIMESTAMP() * 1000 - 370000000, UNIX_TIMESTAMP() * 1000 - 370000000),
(40008, 30004, 'ZTO1122334455', 3, '【成都市武侯区】快件已签收，签收人：本人签收', UNIX_TIMESTAMP() * 1000 - 345600000, UNIX_TIMESTAMP() * 1000 - 345600000);


-- ============================================
-- 六、购物车数据
-- ============================================
INSERT INTO shop_cart (id, product_id, member_account_id, num, created_time, updated_time)
VALUES
(50001, 1, 10001, 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(50002, 4, 10001, 2, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000),
(50003, 7, 10002, 1, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000);
