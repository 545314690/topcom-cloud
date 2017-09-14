#1.start consul server 

#2. start consul agent join cluster

```bash
 consul agent -data-dir /home/lism/data/consul/node118 -join 192.168.0.151 -bind 192.168.0.118 -node node118 -datacenter webtest -client 192.168.0.118

```