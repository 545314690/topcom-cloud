#1.start consul server 

#2. start consul agent join cluster

```bash
 consul agent -data-dir /home/lism/data/consul/node118 -join 192.168.0.151 -bind 192.168.0.118 -node node118 -datacenter webtest -client 192.168.0.118

```
# upload  image 到 docker　私服
- 登陆私服仓库 
注意在push上传之前必须要先登录 
```bash
docker login limushui.top:8082
docker login -u admin -p admin123 limushui.top:8082
```

- 打tag 

在上传镜像之前需要先打一个tag，用于版本标记。 
格式是这样的： 
docker tag <imageId or imageName> <nexus-hostname>:<repository-port>/<image>:<tag> 
例如： 
```bash
docker tag admin-server limushui.top:8082/admin-server:latest
```

- 最后上传镜像： 
# docker push limushui.top:8082/admin-server:latest