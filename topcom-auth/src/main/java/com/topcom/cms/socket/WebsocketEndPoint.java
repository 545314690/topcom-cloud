package com.topcom.cms.socket;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author lism
 *
 */
// relationId和code是我的业务标识参数,websocket.ws是连接的路径，可以自行定义
@ServerEndpoint("/websocket.ws/{relationId}/{code}")
public class WebsocketEndPoint {

	private static Log log = LogFactory.getLog(WebsocketEndPoint.class);

	/**
	 * 打开连接时触发
	 * 
	 * @param relationId
	 * @param code
	 * @param session
	 */
	@OnOpen
	public void onOpen(@PathParam("relationId") String relationId, @PathParam("code") String code,
			Session session) {
		log.info("Websocket Start Connecting:" + SessionUtils.getKey(relationId, code));
		SessionUtils.put(relationId, code, session);
	}

	/**
	 * 收到客户端消息时触发
	 * 
	 * @param relationId
	 * @param code
	 * @param message
	 * @return
	 */
	@OnMessage
	public String onMessage(@PathParam("relationId") String relationId, @PathParam("code") String code,
			String message) {
		return "Got your message (" + message + ").Thanks !";
	}

	/**
	 * 异常时触发
	 * 
	 * @param relationId
	 * @param code
	 * @param session
	 */
	@OnError
	public void onError(@PathParam("relationId") String relationId, @PathParam("code") String code,
			Throwable throwable, Session session) {
		log.info("Websocket Connection Exception:" + SessionUtils.getKey(relationId, code));
		log.info(throwable.getMessage(), throwable);
		SessionUtils.remove(relationId, code);
	}

	/**
	 * 关闭连接时触发
	 * 
	 * @param relationId
	 * @param code
	 * @param session
	 */
	@OnClose
	public void onClose(@PathParam("relationId") String relationId, @PathParam("code") String code,
			Session session) {
		log.info("Websocket Close Connection:" + SessionUtils.getKey(relationId, code));
		SessionUtils.remove(relationId, code);
	}

}