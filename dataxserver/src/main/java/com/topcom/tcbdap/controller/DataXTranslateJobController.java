package com.topcom.tcbdap.controller;

import com.topcom.tcbdap.domain.DataTranslateLog;
import com.topcom.tcbdap.util.DataXUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
@RestController
@RequestMapping("dataxTranslateJob")
public class DataXTranslateJobController {
    @RequestMapping(value = "run", method = RequestMethod.POST)
    @ResponseBody
    public String run(@RequestBody JSONObject jobJson, @RequestParam(required = false,value = "paramMap") Map paramMap) throws Exception {
        String result = DataXUtil.runJob(jobJson, paramMap);
        return result;
    }

    @RequestMapping(
            value = {"/refresh"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    public DataTranslateLog refresh(@RequestBody DataTranslateLog dataTranslateLog) throws Exception {
        dataTranslateLog.parseLogFile();
        return dataTranslateLog;
    }

    @RequestMapping(
            value = {"logContent"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public Map<String,String> logContent(@RequestParam(value = "logPath") String logPath) throws Exception{
        Map<String,String> map = new HashMap<>();
        String content = FileUtils.readFileToString(new File(logPath), "utf-8");
        map.put("content",content);
        return map;
    }
}
