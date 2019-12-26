GoodsSpuAndSpuDao  ,这个类  用来操作 spu与sku表



##批量删除的  input没有出来，是因为没有加这个，待完善
```js
  layui.use(['laydate','form'], function(){
        
      });
```




##商品删除只需要spuid,就可以删除了

商品的添加修改,可以在同一个页面完成, 


还有三个表未连接
##这里应该是商品规格值管理     查询：商品名称,品牌名称,类型, 规格
spu表与spu规格表之间的关系

sku表与spu规格值 之间的关系

sku与sku增值表单 的关系



# 商城系统商品表ddl
# 多表查询
# select * from shop_member_info join goods_brand gb on shop_member_info.gmt_create = gb.gmt_create;

select id, brand_name, gmt_create, gmt_update from goods_brand where brand_name like '%克%';

drop database mall;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mall` /*!40100 DEFAULT CHARACTER SET utf8 */;

# create database mall;
use `mall`;

# 商家信息表（shop_member_info）






# 店铺表
drop table if exists `shop_info`;
create table `shop_info` (
                             `id` bigint(20) unsigned NOT NULL auto_increment,
                             `shop_name` varchar(50) NOT NULL COMMENT '店铺名称',
                             `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                             `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                             PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';
insert into shop_info(shop_name, gmt_create, gmt_update) values ('手机专卖店',default,default);

insert into shop_info(shop_name, gmt_create, gmt_update) values ('衣服',default,default);

#  店铺表 店铺id =1
select * from shop_info;








#一个店铺有可以有多个员工管理，所以店铺与商家信息表是一对多的关系
drop table if exists `shop_member_info`;
 create table `shop_member_info` (
                                    `id` bigint(20) unsigned NOT NULL auto_increment,
                                    `username` varchar(50) NOT NULL COMMENT '用户账号',
                                    `password` varchar(50) NOT NULL COMMENT '用户密码',
                                    `phone` varchar(50) NOT NULL COMMENT '用户电话',
                                    `head_portrait` varchar(100) NOT NULL default '/static/images/1.jpg' COMMENT '头像图片路径',
                                    `shop_id` bigint(20) NOT NULL COMMENT '商铺id',
                                    `member_state` bigint(20) default 0 NOT NULL COMMENT '用户状态  默认0正常,1禁用',
                                    `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                                    `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                    PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家信息表';
insert into shop_member_info(username, password, phone, shop_id) values ('admin','123456','15917066545',1);

insert into shop_member_info(username, password, phone, shop_id) values ('admin2','123456','15917066545',2);
select * from shop_member_info;
# 上面 的表相当于用户






drop table if exists `shop_member_info_role`;
create table `shop_member_info_role` (

                         `shop_member_info_id` bigint(20) NOT NULL COMMENT '用户id',
                         `role_id` bigint(20) NOT NULL COMMENT '角色id',
                        `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                        `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与用户表id';







drop table if exists `role`;
create table `role` (
                                    `id` bigint(20) unsigned NOT NULL auto_increment,
                                    `role_name` varchar(50) NOT NULL COMMENT '职位名称',
                                    `description` varchar(50) NOT NULL COMMENT '职位描述',
                                    `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                                    `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                    PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职位表';
insert into role(role_name, description) values ('管理员','拥有全部权限');
insert into role(role_name, description) values ('商品管理员','负责管理商品');
insert into role(role_name, description) values ('订单管理员','负责管理订单物流');
select * from role;


# 角色与权限的结合

drop table if exists `role_privilege`;
create table `role_privilege` (
                                         `role_id` bigint(20) NOT NULL COMMENT '角色id',
                                         `privilege_id` bigint(20) NOT NULL COMMENT '权限id',
                                         `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                                         `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与权限id';



# 权限表

drop table if exists `privilege`;
create table `privilege` (
                             `id` bigint(20) unsigned NOT NULL auto_increment,
                            `privilege_name` varchar(50) NOT NULL COMMENT '权限名称',
                            `privilege_url` varchar(50) NOT NULL COMMENT '权限名称',
                            `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                            `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                            PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';



















# 品牌表
drop table if exists `goods_brand`;
create table `goods_brand` (
                               `id` bigint(20) unsigned NOT NULL auto_increment,
                               `brand_name` varchar(50) NOT NULL COMMENT '品牌名称',
                               `img_path` varchar(200) NOT NULL default '/static/images/1.jpg' COMMENT '品牌图片路径',
                               `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP ,
                               `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                               PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='品牌表';

insert into goods_brand(brand_name, img_path,gmt_create, gmt_update) values('华为1',default,default,default);
insert into goods_brand(id,brand_name, gmt_create, gmt_update) values(2,'耐克',default,default);
UPDATE goods_brand  SET brand_name = '华为' WHERE `id` = 1;

# 品牌表  品牌id =1
select * from goods_brand;
# 分类表
drop table if exists `goods_category`;
create table `goods_category` (
                                  `id` bigint(20) unsigned NOT NULL auto_increment,
                                  `category_name` varchar(50) NOT NULL COMMENT '分类名称',
                                  `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP,
                                  `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分类表';
insert into goods_category(category_name, gmt_create, gmt_update) VALUES ('手机',default,default);


insert into goods_category(category_name, gmt_create, gmt_update) VALUES ('衣服',default,default);
# 分类表
select *from goods_category;


# spu表
drop table if exists `goods_spu`;
create table `goods_spu` (
                             `id` bigint(20) unsigned NOT NULL auto_increment,
                             `spu_no` varchar(50) NOT NULL COMMENT '商品编号，唯一',
                             `goods_name` varchar(50) NOT NULL COMMENT '商品名称',
                             `low_price` decimal(9,2) NOT NULL COMMENT '厂家价格，商家可以利用这个差价盈利',
                             `category_id` bigint(20) NOT NULL COMMENT '分类id',
                             `brand_id` bigint(20) NOT NULL COMMENT '品牌id',
                             `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP  ,
                             `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                             PRIMARY KEY  (`id`),
                             UNIQUE KEY  (`spu_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='spu表';

insert into goods_spu(spu_no, goods_name, low_price, category_id, brand_id) values ('100001','华为p10',2999,1,1);


delete from goods_spu where id=4;

insert into goods_spu(spu_no, goods_name, low_price, category_id, brand_id) values ('100002','耐克联名',599,2,2);
# spu表
select * from goods_spu;


# 这个表与spu是一对多的关系 ，
drop table if exists `goods_sku`;
create table `goods_sku` (
                             `id` bigint(20) unsigned NOT NULL auto_increment,
                             `sku_title` varchar(200) NOT NULL COMMENT '标题售卖',
                             `price` decimal(9,2) NOT NULL COMMENT '售价买的价格',
                             `stock` int(11) NOT NULL COMMENT '库存',
                             `sku_img_path` varchar(50) NOT NULL COMMENT '商品图片地址',
                             `shop_id` bigint(20) NOT NULL COMMENT '商铺id,为0表示自营',
                             `spu_id` bigint(20) NOT NULL COMMENT 'spu_id  外键id',
                             `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP  ,
                             `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                             PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sku表';

insert into goods_sku( sku_title, price, stock, shop_id, spu_id) values ('华为p30',2999,20,1,1);


insert into goods_sku( sku_title, price, stock, shop_id, spu_id) values ('耐克联名sku',799,20,2,2);

# sku
select * from goods_sku;

# 增值保障表
drop table if exists `goods_safeguard`;
create table `goods_safeguard` (
                                   `id` bigint(20) unsigned NOT NULL auto_increment,
                                   `safeguard_name` varchar(50) NOT NULL COMMENT '保障名称',
                                   `price` decimal(9,2) NOT NULL COMMENT '保障价格',
                                   `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP ,
                                   `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                   PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='增值保障';
insert into goods_safeguard(safeguard_name, price) values ('保修二年',299);

# 增值表
select * from goods_safeguard;


drop table if exists `goods_sku_safeguard`;
#sku增值保障与增值保障表是一对多的关系，
# sku表是用来与sku关联的
create table `goods_sku_safeguard` (
                                       `id` bigint(20) unsigned NOT NULL auto_increment,
                                       `sku_id` bigint(20) NOT NULL COMMENT 'sku_id',
                                       `safeguard_id` bigint(20) NOT NULL COMMENT 'safeguard_id',
                                       `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP  ,
                                       `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                       PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sku增值保障';

insert into goods_sku_safeguard(sku_id, safeguard_id) values (1,1);
# sku增值保值
select * from goods_sku_safeguard;


# 规格表
drop table if exists `goods_spec`;
create table `goods_spec` (
                              `id` bigint(20) unsigned NOT NULL auto_increment,
                              `spec_no` varchar(50) NOT NULL COMMENT '规格编号',
                              `spec_name` varchar(50) NOT NULL COMMENT '规格名称',
                              `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP  ,
                              `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                              PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格表';
insert into goods_spec(spec_no, spec_name) values ('00001','内存');

insert into goods_spec(spec_no, spec_name) values ('00002','尺码');
# 规格表
select * from goods_spec;


# 这个表是否与规格表缺一个外键
# 规则值表
drop table if exists `goods_spec_value`;
create table `goods_spec_value` (
                                    `id` bigint(20) unsigned NOT NULL auto_increment,
                                    `spec_id` bigint(20) NOT NULL COMMENT '规格id',
                                    `spec_value` varchar(50) NOT NULL COMMENT '规格值',
                                    `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP ,
                                    `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                    PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规格值表';

insert into goods_spec_value(spec_id, spec_value) values ('00001','32G');


insert into goods_spec_value(spec_id, spec_value) values ('00002','M码');
# 规格值表
select * from goods_spec_value;
delete from goods_spec_value where id=2;



drop table if exists `goods_sku_spec_value`;
create table `goods_sku_spec_value` (
                                        `id` bigint(20) unsigned NOT NULL auto_increment,
                                        `spu_id` bigint(20) NOT NULL COMMENT 'sku_id',
                                        `spec_value_id` bigint(20) NOT NULL COMMENT '规格值id',
                                        `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP ,
                                        `gmt_update` timestamp NOT NULL default CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                        PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='sku规格值';

insert into goods_sku_spec_value(spu_id, spec_value_id) values (1,1);
insert into goods_sku_spec_value(spu_id, spec_value_id) values (2,2);
select * from goods_sku_spec_value;




drop table if exists `goods_spu_spec`;


create table `goods_spu_spec` (
                                  `id` bigint(20) unsigned NOT NULL auto_increment,
                                  `spu_id` bigint(20) NOT NULL COMMENT 'spu_id',
                                  `spec_id` bigint(20) NOT NULL COMMENT 'spec_id 规格表id',
                                  `gmt_create` timestamp NOT NULL default CURRENT_TIMESTAMP ,
                                  `gmt_update` timestamp NOT NULL default  CURRENT_TIMESTAMP on update CURRENT_TIMESTAMP,
                                  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='spu规格表';
insert into goods_spu_spec(spu_id, spec_id) values (1,1);


insert into goods_spu_spec(spu_id, spec_id) values (2,2);

select * from goods_spu_spec;
# 品牌表一个，分类表一个
#
#
#



# 当一个人想开一个网店 ，要先注册一个店铺， 有了店铺才注册用户
# 通过他注册店铺的id ，来注册用户 ，因为每个店长或者店店员都需要店铺id
# 如果没有店铺id，这个人是不可能存在的，
# 在mybatis里 有一个 添加成功后，可以获得当前的id，

# 当网店做大之后 ，自己忙不过来，就可以开通一个新员工来一起帮助自己来完成





# 每个店铺都有唯一的id ，所以这个店铺有店员的员工登录时
# 所以可以获得该店铺id  ，又因为 sku相当与该店铺的商品仓库
# 所以可以通过店铺id，来查询该店铺的所有商品 sku 表
select * from goods_sku where shop_id =2;
# 所有这个表可以查询 商品名称，价格 ，库存，spu的外键

select * from goods_sku sku join goods_spu spu on sku.spu_id = spu.id ;


select * from goods_sku;
select * from goods_spu join goods_sku gs on goods_spu.id = gs.id;




# 品牌表  品牌id =1
select * from goods_brand;

#  店铺表 店铺id =1
select * from shop_info;

# 分类表
select *from goods_category;







# 增值表
select * from goods_safeguard;

# sku增值保值
select * from goods_sku_safeguard;





# 规格表
select * from goods_spec;

# 规格值表
select * from goods_spec_value where spec_id =1;

# sku 规格值
select * from goods_sku_spec_value;


# spu表
select * from goods_spu;

# sku
select * from goods_sku;


# 由于每个店，买什么东西都是不用的，
# 所有要通过商家id ，来查询，本商家的 仓库货物与所买的货物
# 需要查询的数据有， 商品编号， 商品名称  库存  ，成本价格 ，  卖出价格  品牌名称 ， 类型名称




select * from goods_sku sku left join goods_spu spu on sku.spu_id=spu.id ;



select * from goods_spu spu right join goods_sku sku on sku.spu_id=spu.id ;



select * from goods_spu spu right join goods_brand gb  on spu.category_id = gb.id
                            left join goods_category gc on gc.id = spu.category_id;

# right join goods_sku sku on sku.spu_id = spu.id;


select * from  goods_spu spu right join goods_brand gb  on spu.category_id = gb.id
    left join goods_category gc on gc.id = spu.category_id ;



select * from goods_sku sku inner join goods_spu spu on sku.spu_id = spu.id;

# 由于每个店，买什么东西都是不用的，
# 所有要通过商家id ，来查询，本商家的 仓库货物与所买的货物
# 需要查询的数据有， 商品编号， 商品名称  库存  ，成本价格 ，  卖出价格  品牌名称 ， 类型名称 ，标题， 图片路径
#

#这个时这个商家所售卖的商品 详情 ，
#


select spu.spu_no,spu.goods_name,spu.low_price,sku.price,sku.stock,gb.brand_name,gc.category_name ,sku.sku_title,sku.sku_img_path
from  goods_spu spu right join goods_brand gb  on spu.category_id = gb.id
    left join goods_category gc on gc.id = spu.category_id
    inner join goods_sku sku on sku.spu_id = spu.id where sku.shop_id=1 and spu.brand_id=1 and spu.category_id = 1;




#spu表查询
select spu.spu_no,spu.goods_name,spu.low_price,gb.brand_name,gc.category_name
from  goods_spu spu right join goods_brand gb  on spu.category_id = gb.id
                    left join goods_category gc on gc.id = spu.category_id where gb.id = 1 or gc.id =2;


# 添加商品流程，
# 添加时，要先获取商家的自身的id
# spu与sku表合在一起添加，搞个事务 ,  添加时  要查询出所有的类型和品牌名称 ，然后 ，选择，  进货价格 ,库存  商家id ，spu_id
#在添加sku表时,要先获得spu刚刚添加的自身id，mybatis有一个这样的功能，
# 添加一个商品，需要填写  订单编号，商品名称，进货价，选择品牌，选择类型，售卖价格，库存 ,售卖标题，商品图片

# 品牌表  品牌id =1
select * from goods_brand;

# 分类表
select *from goods_category;


select spu.spu_no,spu.goods_name,spu.low_price,sku.price,sku.stock, gb.brand_name,gc.category_name ,sku.sku_title,sku.sku_img_path from goods_spu spu right join goods_brand gb on spu.category_id = gb.id left join goods_category gc on gc.id = spu.category_id inner join goods_sku sku on sku.spu_id = spu.id WHERE sku.shop_id= 1;

select spu.id,spu.spu_no,spu.goods_name,spu.low_price,sku.price,sku.stock, gb.brand_name,gc.category_name ,sku.sku_title,sku.sku_img_path
FROM goods_spu spu
         RIGHT JOIN goods_brand gb
                    ON spu.category_id = gb.id
         LEFT JOIN goods_category gc
                   ON gc.id = spu.category_id
         INNER JOIN goods_sku sku
                    ON sku.spu_id = spu.id
WHERE sku.shop_id= 1;


select gs.spec_name,gsv.id,gsv.spec_value from goods_spec gs inner join goods_spec_value gsv on gs.id=gsv.spec_id where spec_id =1 ;



select * from goods_spu;




select * from goods_spu_spec;



select * from goods_spec;





select * from goods_spec_value;


select * from goods_sku_spec_value;





# 商品名称
select * from goods_spu gs inner join goods_spu_spec gss on gs.id = gss.spu_id ;

# specId    spuId
select * from goods_spu_spec;

# ==========================================================================================
# 商品规格管理查询
select gs.id,  gs.goods_name,gsc.spec_name,gss.spec_id,gss.spu_id,gss.id from goods_spu_spec gss
    inner join goods_spu gs
    on gss.spu_id = gs.id inner join
    goods_spec gsc on gsc.id = gss.spec_id  order by gss.spu_id;


select goods_name,id
from goods_spu ;

select * from goods_spec;

delete from goods_spu_spec where id = 11;

# 商品名称, 规格,规格值
# 修改商品当前的规格是需要  商品本身的 spuId ,然后通过下拉框，选择商品规格，取当前商品规格
# 的id，然后录入进去
update goods_spu_spec set spec_id = 1 where spu_id =5;
# 商品规格的添加 ，需要商品当前的spuId，与之间所选的规格id，然后添加就可以了，
insert into goods_spu_spec(spu_id, spec_id, gmt_create, gmt_update) values (1,2,default,default);
# 通过上面只能提交当前商品的规格，如果该商品一个规格都没有呢，
# 我目前想到的是查出全部规格值,和查出全部的商品值,然后进行添加,
#
# 删除 ,要想删除一个商品的规格,首先要先获得他的 goods_spu_spec 当前id
# 然后通过这个id 删除当前



# 商品规格值管理,
#
select gs.sku_title,gsv.spec_value ,gssv.sku_id,gssv.spec_value_id,gs2.spec_name
    from goods_sku_spec_value gssv inner join
    goods_sku gs on gssv.sku_id = gs.id inner join goods_spec_value gsv
        on gsv.id = gssv.spec_value_id inner join goods_spec gs2 on gs2.id = gsv.spec_id;



# 商品名称,商品规格,规格值

select spu.goods_name,gsc.spec_name,gsv.spec_value,gss.spec_id,gss.spu_id from goods_spu_spec gss inner join goods_spu spu
  on gss.spu_id = spu.id inner join goods_spec gsc on gsc.id = gss.spec_id  inner join goods_spec_value gsv
on gsv.spec_id = gsc.id inner join goods_sku_spec_value gssv on gssv.spec_value_id =  gsv.id inner join goods_sku sku
on sku.id = gssv.sku_id order by spu.id ;

# spu
select * from goods_spu;
# gss
select * from goods_spu_spec;
#gs
select * from goods_spec;
# gsv
select * from goods_spec_value;
#  sku
select * from goods_sku;
#  gssv
select * from goods_sku_spec_value order by id;

# 1		32G
# 2		64G
# 3		128G
# 7		白色
# 8		黑色
# 9		红色

# 分页,
# 上面弄一个下拉框,然后通过点击下拉框来进行搜索规格名称,
# 然后,在弄一个模糊搜索商品的搜索的,通过这个模糊搜索可以搜索到该商品,
#
# 问题一:如果该商品,没有规格和规格值,是搜索不到到的,
# 问题二:如果你在进货时,有该商品,但是没有该规格值,
#       要进行添加,如何添加
#      解决这个问题,我目前想到的是把添加分为两种,
#      第一种,在当前列进行点击添加,然后获取当前列的,商品spuId,与skuId,然后在
#       跳转到另外一个页面,进行添加,添加时,还是获取当前的gssId,通过gssId,来
#       获取gssvId,进行选择,然后进行添加,
#       第二种:跳转到另外一个界面,该界面显示只有没有规格与规格值的商品,
#       然后进行选择,添加,这个页面与在添加商品时共用
#
#
# 删除该规格值
# 删除只需要gssvId 就可以了,


# gsv
select * from goods_spec_value where spec_id = 1;
# 修改:
# 修改需要把规格全部查询出来,然后在修改页面显示传过来的修改规格的id
# 然后通过该规格id,进 行搜索该规格值,  然后点击修改,获得 规格id和规格值id,
# 然后还需要把gssvId ,gssId ,传过去,因为修改是通过 这两个id来进行修改的
# 修改只需要把gssvId和gssId的值传过去,还需要把规格值id传过去,
#
#
#
#
#
#
# like '%' #{name} '%'
# 分页  ,搜索 goodsName , specId
#  GoodsSpecAdministration
select spu.goods_name,gs.spec_name,gsv.spec_value ,gssv.id as gssvId , gss.id as gssId
,spu.id as spuId,sku.id as skuId,gs.id as gsId,gsv.id as gsvId
from goods_spu spu
inner join goods_spu_spec gss on spu.id = gss.spu_id
inner join goods_spec gs on gs.id = gss.spec_id
inner join goods_spec_value gsv on gsv.spec_id = gs.id
inner join goods_sku_spec_value gssv on gssv.spec_value_id = gsv.id
inner join goods_sku sku on sku.id = gssv.sku_id and spu.id = sku.spu_id
where 1=1
order by spu.id;


select *
from goods_spu_spec;


update goods_spu_spec set spec_id=1 where id = 21;

select *from goods_sku_spec_value ;

select *
from goods_spu;

update goods_spu_spec set spec_id = 2 where id = 21 and


select * from goods_spec_value;


update goods_spu set goods_name = '华为p10spu22' where id = 1;

select * from goods_spu_spec;


#
# UPDATE goods_spu_spec t1
# SET column1 = t2.columnname1
# FROM (select columnname1,columnname2 from table2) t2
# WHERE t1.column3 = t2.column3
#   AND t1.column = '111';






# 耐克联名spu	尺码	XXL码	29	13	2	2	3	14


select spu.goods_name,gs.spec_name,gsv.spec_value ,gssv.id as gssvId , gss.id as gssId
        ,spu.id as spuId,sku.id as skuId,gs.id as gsId,gsv.id as gsvId
from goods_spu spu
    inner join goods_spu_spec gss on spu.id = gss.spu_id
    inner join goods_spec gs on gs.id = gss.spec_id
    inner join goods_spec_value gsv on gsv.spec_id = gs.id
    inner join goods_sku_spec_value gssv on gssv.spec_value_id = gsv.id
    inner join goods_sku sku on sku.id = gssv.sku_id and spu.id = sku.spu_id
where 1=1
order by spu.id;


select spu.goods_name,spu.id as spuId,sku.sku_title,sku.id as skuId from goods_spu spu inner join goods_sku sku on spu.id = sku.spu_id where spu.goods_name = '%华为%'


select *from goods_sku;
select * from goods_sku_safeguard;
select * from goods_safeguard;


# =================================
# 商品增值管理
#   商品增值删除,只需获取 gssId,就可以删除了
#
#   商品增值修改,要获取 gssId, 然后进行选择增值 gsId,进行修改,
#
#   增值添加,  要获得 skuId,和 gsId, 进行 gss表添加,就完成了添加,
#
#   另外一个添加,是 需要知道 skuId,与gsId, 进行添加,
#
#



select sku.sku_title,gs.safeguard_name,gs.price,gss.id as gssId,sku.id as skuId,spu.goods_name as goodsName
from goods_sku sku
    inner join goods_spu spu on spu.id = sku.spu_id
    inner join goods_sku_safeguard gss on sku.id = gss.sku_id
    inner join goods_safeguard gs on gs.id = gss.safeguard_id
     where 1=1 and spu.goods_name like '%热%' or sku.sku_title like '%热%'
order by sku.id;

select * from goods_sku_safeguard;


update goods_sku_safeguard set safeguard_id = 2 where id =2


