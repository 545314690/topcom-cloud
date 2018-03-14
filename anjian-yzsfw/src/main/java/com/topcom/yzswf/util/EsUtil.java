package com.topcom.yzswf.util;

import org.elasticsearch.client.transport.TransportClient;

/**
 * Created by lsm on 2018/3/12 0012.
 */
public class EsUtil {
    TransportClient client = null;
    public TransportClient getClient(){
//        if(client == null){
//            try {
//                Settings settings = Settings.settingsBuilder()
//                        .put("cluster.name", "myClusterName").build();
//                client = TransportClient.builder().settings(settings).build()
//                        .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("host2"), 9300));
//            } catch (UnknownHostException e) {
//                e.printStackTrace();
//            }
//        }
        return client;

    }
}
