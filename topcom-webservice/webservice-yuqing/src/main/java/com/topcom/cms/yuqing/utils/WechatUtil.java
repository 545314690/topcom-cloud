package com.topcom.cms.yuqing.utils;
import com.topcom.cms.es.utils.RedisTemplateUtils;
import com.topcom.cms.yuqing.domain.SubscriptionFollower;
import com.topcom.cms.yuqing.vo.request.WechatSendRequest;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 48小时内用户没有与公众号互动则限制发送消息。
 * 用户与公众号有互动后，可以发送20条信息。
 * Created by topcom on 2017/8/11 0011.
 */
@Component
public class WechatUtil {
    private static final String WEB_CHAT_ACCESS_TOKEN_API_URL = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String WEB_CHAT_ACCESS_TOKEN_API_PARAM = "grant_type=client_credential&appid=wxf8cb3a0d796ad7a9&secret=6f273f91cdc934534dafe7f3baf98a9a";
    private static final String WEB_CHAT_TEMPLATE_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
    private static final String WEB_CHAT_USER_GETLIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get";
    private static final String WEB_CHAT_USER_INFO_URL="https://api.weixin.qq.com/cgi-bin/user/info";
    @Autowired
    public RedisTemplateUtils redisTemplateUtils;

    /**
     * 缓存令牌到redis，令牌有效时间为2小时，缓存时间为1小时，每天调用上限为2000次。
     */
    private final Long EXPORATION_TIME=3600L;
    private final String ACCESS_TOKEN= "access_token";
    private final String NEXT_OPENID="next_openid";
    private final String OPENID = "openid";
    private final String WECHAT_REDIS_KEY="wechat-redis-key";
    public static void main(String[] args) {
        JSONObject result1 = new JSONObject();
        result1.put("value","北京发生13级地震目前无人生还");
        result1.put("color","#173177");
        JSONObject result2 = new JSONObject();
        result2.put("value","上海发生19级地震目前无人生还");
        result2.put("color","#666666");
        JSONObject result3 = new JSONObject();
        result1.put("value","西安发生27级地震目前无人生还");
        result1.put("color","#888888");
        JSONObject name = new JSONObject();
        name.put("value","boos");
        name.put("color","#888888");
        JSONObject num = new JSONObject();
        num.put("value","11231");
        num.put("color","#111111");
        JSONObject data = new JSONObject();
        data.put("result1",result1);
        data.put("result2",result2);
        data.put("result",result3);
        data.put("name",name);
        data.put("num",num);
        JSONObject param = new JSONObject();
        param.put("data",data);
        param.put("touser","ozC850c9D3CCDlkd017cEP62xYWw");
        param.put("template_id","N_sOGso5v-NU7esOVO2iU9lGsvWooiz7MbJ4IQmHE5w");
        param.put("url","http://tcbds.xicp.io:8090/yuqing/module/login.html");
        WechatUtil wechatUtil = new WechatUtil();
        wechatUtil.sendTemplate(param);
        List<String> userList = wechatUtil.getUserList();
        SubscriptionFollower subscriptionFollower = wechatUtil.getUserInfo(userList.get(0));
        System.out.println(subscriptionFollower);
    }
    /**
     * 获取用户列表
     * @return
     */
    public List<String> getUserList(){
        return getUserList("");
    }
    public List<String> getUserList(String openid){
        String accessToken = getAccessToken();
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put(ACCESS_TOKEN,accessToken);
        paramMap.put(NEXT_OPENID,openid);
        String result = HttpUtil.doGet(WEB_CHAT_USER_GETLIST_URL,paramMap);
        JSONObject userListJson =  JSONObject.fromObject(result);
        JSONObject dataJson = userListJson.getJSONObject("data");
        if (dataJson.size()==0){
            return null;
        }else return dataJson.getJSONArray("openid");
    }

    /**
     * 获取用户详情
     * @param openid
     * @return
     */
    public SubscriptionFollower getUserInfo(String openid){
        String accessToken = getAccessToken();
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put(OPENID,openid);
        paramMap.put(ACCESS_TOKEN,accessToken);
        String result = HttpUtil.doGet(WEB_CHAT_USER_INFO_URL,paramMap);
        JSONObject userJson =  JSONObject.fromObject(result);
        return (SubscriptionFollower) JSONObject.toBean(userJson,SubscriptionFollower.class);
    }

    /**
     * 发送模版信息
     * @param jsonObject
     */
    public void sendTemplate(JSONObject jsonObject){
        String accessToken = getAccessToken();
        String templateUrl = WEB_CHAT_TEMPLATE_URL+accessToken;
//        for (int i=0;i<20;i++){
//            String s = HttpUtil.doPost(templateUrl, jsonObject.toString());
//            System.out.println(s);
//        }
        String s = HttpUtil.doPost(templateUrl, jsonObject.toString());
        System.out.println(s);
    }

    private  String getAccessToken() {
        String accessToken =(String) redisTemplateUtils.getValue(WECHAT_REDIS_KEY);
        if (StringUtils.isEmpty(accessToken)){
            // 自己获取token
            String accessStr = HttpUtil.doGet(WEB_CHAT_ACCESS_TOKEN_API_URL,WEB_CHAT_ACCESS_TOKEN_API_PARAM);
            Map<?, ?> accessTokenMap = (Map<?, ?>) JSONObject.toBean(JSONObject.fromObject(accessStr),Map.class);
            if (accessTokenMap != null && accessTokenMap.containsKey(ACCESS_TOKEN) && accessTokenMap.get("access_token") != null) {
                accessToken = accessTokenMap.get(ACCESS_TOKEN).toString();
            }
            redisTemplateUtils.saveValue(WECHAT_REDIS_KEY,accessToken,EXPORATION_TIME);
        }

        return accessToken;
    }




}
