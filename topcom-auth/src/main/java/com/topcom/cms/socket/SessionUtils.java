package com.topcom.cms.socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

public class SessionUtils {

    public static Map<String, Session> clients = new ConcurrentHashMap<>();

    public static void put(String relationId, String code, Session session) {
        clients.put(getKey(relationId, code), session);
    }

    /**
     * 根据业务标识和用户标识获取当前用户当前业务session
     * @param relationId
     * @param code
     * @return
     */
    public static Session get(String relationId, String code) {
        return clients.get(getKey(relationId, code));
    }

    /**
     * 根据业务标识获取当前业务所有session
     * @param relationId
     * @return
     */
    public static List<Session> get(String relationId) {
        List<Session> sessions = new ArrayList<>();
        Set<String> keySet = clients.keySet();
        for (String key : keySet) {
            if (key != null && key.startsWith(relationId + "_")) {
                sessions.add(clients.get(key));
            }
        }
        return sessions;
    }

    public static void remove(String relationId, String code) {
        clients.remove(getKey(relationId, code));
    }

    /**
     * 判断是否有连接
     *
     * @param relationId
     * @param code
     * @return
     */
    public static boolean hasConnection(String relationId, String code) {
        return clients.containsKey(getKey(relationId, code));
    }

    /**
     * 组装唯一识别的key
     *
     * @param relationId
     * @param code
     * @return
     */
    public static String getKey(String relationId, String code) {
        return relationId + "_" + code;
    }

    /**
     * 根据业务关系id和用户标识发送单播消息
     *
     * @param relationId 业务关系
     * @param code       用户标识
     * @param msg        发送的文本消息
     */
    public static void sendMessage(String relationId, String code, String msg) {
        Session webSession = SessionUtils.get(relationId, code);
        try {
            if (webSession != null) {
                webSession.getBasicRemote().sendText(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据业务关系id发送广播消息
     *
     * @param relationId
     * @param msg
     */
    public static void sendMessage(String relationId, String msg) {
        List<Session> webSessions = SessionUtils.get(relationId);
        for (Session webSession : webSessions) {
            try {
                if (webSession != null) {
                    webSession.getBasicRemote().sendText(msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
