nacos安装：
https://nacos.io/zh-cn/docs/quick-start.html
wget https://github.com/alibaba/nacos/releases/download/2.0.3/nacos-server-2.0.3.zip
unzip nacos-server-2.0.3.zip
cd nacos/bin
sh startup.sh -m standalone

conf/application.properties 配置：
配置mysql
spring.datasource.platform=mysql
db.num=1
db.url.0=jdbc:mysql://localhost:3306/ry-config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC
db.user=root
db.password=12345678

sh shutdown.sh 

http://127.0.0.1:8848/nacos/#/login
用户名/密码:nacos/nacos

安装redis
wget https://download.redis.io/releases/redis-6.2.5.tar.gz
tar xzf redis-6.2.5.tar.gz
cd redis-6.2.5
make
src/redis-server



熔断降级监控
wget https://github.com/alibaba/Sentinel/releases/download/1.8.2/sentinel-dashboard-1.8.2.jar
用户名/密码：sentinel/sentinel
java -Dserver.port=8718 -Dcsp.sentinel.dashboard.server=localhost:8718 -Dproject.name=sentinel-dashboard -Dcsp.sentinel.api.port=8719 -jar ./sentinel-dashboard-1.8.0.jar

http://127.0.0.1:8718/




项目启动：
创建数据库 用户名/密码：root/12345678

运行
RuoYiGatewayApplication （网关模块 必须）
RuoYiAuthApplication （认证模块 必须）
RuoYiSystemApplication （系统模块 必须）


cd ruoyi-ui
npm install --registry=https://registry.npm.taobao.org
npm run dev
(http://localhost:80) 默认账户/密码 admin/admin123）


ruoyi-monitor用户名密码：ruoyi/123456