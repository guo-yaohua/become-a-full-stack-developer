use king_of_honor;

# 查询名字长度为 3 的近战英雄
select name from heros where char_length(name)=3;

# 查询既有主要角色定位，又有辅助角色定位的英雄有哪些？
select name, role_main, role_assist from heros where role_main is not null AND role_assist is not null;

# 查询李姓的英雄有哪些？
select name from heros where name like '李%';

# 查询不同的攻击范围？
select DISTINCT attack_range from heros;

# 查询最大生命值在[6500, 8000]范围, 并且主要角色定位为刺客或者战士的英雄有哪些，并且按照生命值从大到小排序。
select name, role_main, hp_max
from heros 
where role_main='刺客' OR role_main='战士'
order by hp_max DESC;

# 查询初始物理攻击最高的前3名英雄
select name, attack_start
from heros
ORDER BY attack_start DESC LIMIT 3;

