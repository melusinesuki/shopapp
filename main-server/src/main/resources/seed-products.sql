-- 珍品酒仓 商品种子数据
-- int_price 单位为分（1元 = 100分）
-- 使用方法：在 MySQL 中执行此文件即可插入商品数据

INSERT INTO product (str_no, str_surface, str_title, int_price, str_use, content, inventory, sales, created_at, updated_at, is_deleted, deleted_time)
VALUES
('SP001', '', '茅台飞天 53度 500ml', 149900, '酱香突出，幽雅细腻，酒体醇厚，回味悠长，空杯留香持久。', '', 999, 1523, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP002', '', '五粮液 普五 52度 500ml', 129900, '香气悠久，味醇厚，入口甘美，入喉净爽，各味谐调，恰到好处。', '', 888, 1201, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP003', '', '国窖1573 52度 500ml', 109900, '窖香优雅，绵甜爽净，柔和协调，尾净香长，风格典型。', '', 666, 896, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP004', '', '剑南春 水晶剑 52度 500ml', 45800, '芳香浓郁，纯正典雅，醇厚绵柔，甘洌净爽，余香悠长。', '', 1200, 2100, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP005', '', '洋河梦之蓝 M6+ 52度 500ml', 79900, '绵柔顺喉，高而不烈，柔而不寡，绵长而尾净，丰满而协调。', '', 500, 687, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP006', '', '汾酒青花20 53度 475ml', 29900, '清香纯正，醇甜柔和，自然谐调，余味爽净，一清到底。', '', 800, 1543, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP007', '', '古井贡酒 年份原浆 古8 50度 500ml', 25800, '色清如水晶，香纯似幽兰，入口甘美醇和，回味经久不息。', '', 750, 932, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP008', '', '水井坊 井台 52度 500ml', 65900, '陈香飘逸，甘润幽雅，柔和绵甜，余味爽净，风格高雅。', '', 450, 534, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP009', '', '泸州老窖 特曲 52度 500ml', 26800, '醇香浓郁，清洌甘爽，饮后尤香，回味悠长，浓香正宗。', '', 1000, 1876, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP010', '', '西凤酒 华山论剑 52度 500ml', 32800, '醇香典雅，甘润挺爽，诸味协调，尾净悠长，凤型典范。', '', 680, 421, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP011', '', '郎酒 红花郎15 53度 500ml', 69900, '酱香突出，醇厚净爽，优雅细腻，回味悠长，空杯留香。', '', 550, 789, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL),
('SP012', '', '习酒 窖藏1988 53度 500ml', 55900, '微黄透明，酱香突出，醇厚丰满，细腻体净，回味悠长。', '', 420, 345, UNIX_TIMESTAMP() * 1000, UNIX_TIMESTAMP() * 1000, 0, NULL);
