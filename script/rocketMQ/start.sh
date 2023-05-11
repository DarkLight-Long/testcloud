#!/bin/bash
nohup sh ../bin/mqnamesrv &
nohup sh ../bin/mqbroker -c ../sh/broker.conf &
# wait命令等待前边命令执行完成
# (此处可用于解决容器启动后无守护线程退出的问题 => 原理未知)
wait
 