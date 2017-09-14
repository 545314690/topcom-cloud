package com.topcom.cms.yuqing.task.briefing;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;
import com.topcom.cms.common.utils.LogUtil;
import com.topcom.cms.domain.User;
import com.topcom.cms.exception.BusinessException;
import com.topcom.cms.service.UserManager;
import com.topcom.cms.utils.MongoDBUtil;
import com.topcom.cms.yuqing.config.Constants;
import com.topcom.cms.yuqing.domain.Briefing;
import com.topcom.cms.yuqing.domain.BriefingTask;
import com.topcom.cms.yuqing.domain.CustomSubject;
import com.topcom.cms.yuqing.domain.Keywords;
import com.topcom.cms.yuqing.service.CustomSubjectManager;
import com.topcom.cms.yuqing.service.KeywordsManager;
import com.topcom.cms.yuqing.utils.FileServer;
import com.topcom.cms.yuqing.utils.FreemarkerUtil;
import com.topcom.cms.yuqing.utils.HttpUtil;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by lism on 15:46.
 * Author：<a href="mailto:545314690@qq.om">lisenmiao</a>
 */
@Component
public class BriefingCreatorImpl implements BriefingCreator {

    @Autowired
    private UserManager userManager;
    @Autowired
    private KeywordsManager keywordsManager;
    @Autowired
    private CustomSubjectManager subjectManager;
    //创建线程池
    private ExecutorService executorService = Executors.newScheduledThreadPool(100);
    private final static String filepath = FileServer.filepath;

    private static String briefingServer = "http://192.168.0.12:8081/briefingJson";

    @Value("${briefing.server}")
    public void setBriefingServer(String briefingServer) {
        BriefingCreatorImpl.briefingServer = briefingServer;
    }

    @Override
    public Future createDaily() throws BusinessException {

        return null;
    }

    @Override
    public Future createWeekly(BriefingTask briefingTask) throws BusinessException {
        Calendar calendar = Calendar.getInstance();
        long startTime = 0l;
        long endTime = 0l;
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        //从周一开始计算，先加一天
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        startTime = calendar.getTimeInMillis();
        calendar.add(Calendar.DATE, 7);
        endTime = calendar.getTimeInMillis();
        briefingTask.setIssue(calendar.get(Calendar.YEAR) + "年第" + calendar.get(Calendar.WEEK_OF_YEAR) + "期");
        Future future = sendRequestAndCreateBriefing(briefingTask, startTime, endTime);
        return future;
    }

    public static void main(String[] args) {
//        Calendar calendar = Calendar.getInstance();
//        long startTime = 0l;
//        long endTime = 0l;
//        calendar.set(Calendar.DAY_OF_WEEK, 1);
//        calendar.add(Calendar.DATE, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//
//        startTime = calendar.getTimeInMillis();
//        calendar.add(Calendar.DATE, 7);
//        endTime = calendar.getTimeInMillis();
        Calendar calendar = Calendar.getInstance();
//        long startTime = 0;
//        long endTime = 0l;
//        calendar.set(Calendar.DAY_OF_MONTH, 1);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        startTime = calendar.getTimeInMillis();
//        calendar.add(Calendar.MONTH, 1);
//        endTime = calendar.getTimeInMillis();
//
//        System.out.println(new Date(startTime));
//        System.out.println(new Date(endTime));
        System.out.println(calendar.get(Calendar.YEAR) + "年第" + (calendar.get(Calendar.MONTH) + 1) + "期");
        System.out.println(calendar.get(Calendar.YEAR) + "年第" + calendar.get(Calendar.WEEK_OF_YEAR) + "期");
    }

    @Override
    public Future createMonthly(BriefingTask briefingTask) throws BusinessException {
        Calendar calendar = Calendar.getInstance();
        long startTime = 0;
        long endTime = 0l;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        startTime = calendar.getTimeInMillis();
        calendar.add(Calendar.MONTH, 1);
        endTime = calendar.getTimeInMillis();
        briefingTask.setIssue(calendar.get(Calendar.YEAR) + "年第" + (calendar.get(Calendar.MONTH) + 1) + "期");
        Future future = sendRequestAndCreateBriefing(briefingTask, startTime, endTime);
        return future;
    }

    @Override
    public Future createQuarterly() {
        return null;
    }

    @Override
    public Future createAsemiAnnualReport() {
        return null;
    }

    @Override
    public Future createAnnualReport() {
        return null;
    }

    @Override
    public Future createSpecial(CustomSubject customSubject) throws ExecutionException, InterruptedException {
        FutureTask<Boolean> task = new FutureTask<>(() -> {
            LogUtil.logger.info("----------------专报分析开始:" + customSubject.getId() + "---------------------");
            String id = customSubject.getId().toString();
            String mustWord = customSubject.getMustWord();
            String shouldWord = customSubject.getShouldWord();
            String mustNotWord = customSubject.getMustNotWord();
            Long startTime = customSubject.getStartDate().getTime();
            Long endTime = customSubject.getEndDate().getTime();
            String subjectName = customSubject.getName();
            String briefingString = requestBriefingString(Briefing.BriefingType.SPECIAL, mustWord, shouldWord, mustNotWord, startTime, endTime, subjectName);
            JSONObject jsonObject = JSONObject.fromObject(briefingString);
            jsonObject.put("subjectId", Long.valueOf(id));
            jsonObject.put("userId", customSubject.getUserId());
            boolean status = saveBriefingToMongo(jsonObject);
            //更新状态
            subjectManager.updateState(customSubject.getId(), CustomSubject.State.COMPLETED);
            LogUtil.logger.info("----------------专报分析完成:" + customSubject.getId() + "---------------------");
            return status;
        });
        executorService.submit(task);
        return task;
    }

    /**
     * 根据专题id，更新专报
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Future updateSpecial(String id) throws Exception {
        CustomSubject customSubject = subjectManager.findById(Long.valueOf(id));
        //更新状态
        subjectManager.updateState(Long.valueOf(id), CustomSubject.State.CREATING);
        return createSpecial(customSubject);

    }

    @Override
    public Future create(BriefingTask task, Integer month, Integer week) throws Exception {

        long startTime = 0l;
        long endTime = 0l;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        if (Briefing.BriefingType.MONTHLY.equals(task.getBriefingType())) {

            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.MONTH, month - 1);
            startTime = calendar.getTimeInMillis();
            task.setIssue(calendar.get(Calendar.YEAR) + "年第" + (calendar.get(Calendar.MONTH) + 1) + "期");

            calendar.add(Calendar.MONTH, 1);
            endTime = calendar.getTimeInMillis();
        } else if (Briefing.BriefingType.WEEKLY.equals(task.getBriefingType())) {
            calendar.set(Calendar.MONTH, month - 1);
            calendar.set(Calendar.WEEK_OF_MONTH, week);
            calendar.set(Calendar.DAY_OF_WEEK, 1);
            calendar.add(Calendar.DATE, 1);
            task.setIssue(calendar.get(Calendar.YEAR) + "年第" + calendar.get(Calendar.WEEK_OF_YEAR) + "期");
            startTime = calendar.getTimeInMillis();
            calendar.add(Calendar.DATE, 7);
            endTime = calendar.getTimeInMillis();

        }

        Future future = sendRequestAndCreateBriefing(task, startTime, endTime);
        return future;
    }

    /**
     * 报存专报到mongodb
     *
     * @param briefing
     * @return
     */
    public Boolean saveBriefingToMongo(JSONObject briefing) {
        try {
            DBObject dbObject = (DBObject) JSON.parse(briefing.toString());
            Date endDate = new Date();
            if (briefing.get("dateCreated")!=null){
                endDate.setTime((Long) briefing.get("dateCreated"));
            }
            dbObject.put("dateCreated", endDate);
            dbObject.put("dateModified", endDate);
            Long userId = briefing.getLong("userId");
            User currentUser = userManager.findById(userId);
            dbObject.put("author", currentUser.getFullName() == null ? currentUser.getUsername() : currentUser.getFullName());

            JSONObject json = JSONObject.fromObject(dbObject);
            String attachment_doc = null;
            attachment_doc = createAttachment(json, "doc");

            String attachment_html = createAttachment(json, "html");
            if (attachment_doc != null && attachment_html != null) {
                dbObject.put("attachment", new String[]{attachment_doc, attachment_html});
            }
            MongoDBUtil.insertDocument(Constants.Mongo.COLLECTION_BRIEFING, dbObject);
        } catch (Exception e) {
            e.printStackTrace();
            String message = "生成报告失败";
            LogUtil.logger.error(message);
            return false;
        }
        String message = "生成报告成功@";
        LogUtil.logger.info(message);
        return true;
    }

    /**
     * 根据报告类型和开始结束时间发送请求生成json，并创建附件，保存到数据库
     *
     * @param briefingTask
     * @param startTime
     * @param endTime
     * @return
     * @throws BusinessException
     */
    private Future sendRequestAndCreateBriefing(BriefingTask briefingTask, long startTime, long endTime) throws BusinessException {
        FutureTask<JSONObject> futureTask = new FutureTask<>(() -> {
            Briefing.BriefingType type = briefingTask.getBriefingType();
            List<Keywords> keywordList = keywordsManager.findByUserIdAndType(briefingTask.getUserId(), Keywords.Type.BASIC);
            if (keywordList.size() > 0) {
                Keywords keyword = keywordList.get(0);
                String mustWord = keyword.getMustWord();
                String shouldWord = keyword.getShouldWord();
                String mustNotWord = keyword.getMustNotWord();
                try {
                    String object = requestBriefingString(type, mustWord, shouldWord, mustNotWord, startTime, endTime, null);
                    DBObject dbObject = (BasicDBObject) JSON.parse(object);
                    dbObject.put("userId", briefingTask.getUserId());
                    dbObject.put("type", type.name());
                    dbObject.put("issue", briefingTask.getIssue());
                    JSONObject json = JSONObject.fromObject(dbObject);
                    json.put("dateCreated",endTime);
                    saveBriefingToMongo(json);
                    String message = "生成报告成功@" + new Date() + "@" + type.name();
                    LogUtil.logger.info(message);
                    return json;
                } catch (Exception e) {
                    e.printStackTrace();
                    String message = "生成报告失败@" + new Date() + "@" + type.name();
                    LogUtil.logger.error(message);
                    throw new BusinessException(message);
                }
            }
            return null;
        });
        executorService.submit(futureTask);
        return futureTask;
    }

    private String requestBriefingString(Briefing.BriefingType type, String mustWord, String shouldWord, String mustNotWord, long startTime, long endTime, String subjectName) throws UnsupportedEncodingException {

        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("type", type.name());
        paramMap.put("startTime", String.valueOf(startTime));
        paramMap.put("endTime", String.valueOf(endTime));
        paramMap.put("mustWord", mustWord);
        paramMap.put("shouldWord", shouldWord);
        paramMap.put("mustNotWord", mustNotWord);
        paramMap.put("name", subjectName);
        return HttpUtil.doGet(briefingServer, paramMap);
    }

    /**
     * 创建附件
     *
     * @param json
     * @param fileType
     * @return
     * @throws Exception
     */
    private String createAttachment(JSONObject json, String fileType) throws Exception {
        String title = json.getString("title");
        String issue = json.getString("issue");
        String filename = title + issue + System.currentTimeMillis() + "." + fileType;
        File file = new File(filepath, filename);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        String outLine = json.getString("outline");
        if (!StringUtils.isEmpty(outLine)){
            outLine = Jsoup.clean(outLine, Whitelist.none());  //过滤html标签，只保留文本
            json.put("outline",outLine);
        }
        dataMap.put("briefing", json);
        FreemarkerUtil.processTemplateIntoFile(dataMap, file, "briefing_" + fileType + ".ftl");
        return filename.replace(filepath, "");
    }


}
