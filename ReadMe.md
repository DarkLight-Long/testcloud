# Note
## Nacos服务订阅者
指的是访问服务的人，而不是指消费者

## 关于项目支持服务启动(docker镜像启动)
### Nacos(version: v2.1.1)
nacos-server需要多开放两个端口（nacos2.0版本修改的）
8848，9848，9849
### RocketMQ(4.7.1)
启动nameserver和broker
单机启动时需要修改broker配置（brokerIP默认是主机外网ip）（修改内容和相关脚本见）