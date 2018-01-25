package com.topcom.tcbdap.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.topcom.cms.base.model.nosql.BaseEntityModel;
import com.topcom.cms.common.utils.DateUtil;
import com.topcom.tcbdap.util.RegExpUtil;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by lism on 17-10-31.
 *
 * @author lism
 */
public class DataTranslateLog extends BaseEntityModel {

    public enum State {
        DISABLE, RUNNING, SUCCESS, FAILED
    }

    /**
     * job状态
     */

    private State state;
    /**
     * jobId
     */
    private String jobId;

    /**
     * 日志文件
     */
    private String logFile;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 使用时间
     */
    private String useTime;
    /**
     * 平均速度
     */
    private String avgSpeed;
    /**
     * 成功数据条数
     */
    private Long succeedCount;
    /**
     * 数据条数
     */
    private Long totalCount;
    /**
     * 失败数据条数
     */
    private Long failedCount;
    /**
     * 完成百分比
     */
    private String percentage;
    /**
     * 上次解析到哪一行
     */
    private int lastParseRowNum = 1;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(String avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(Long failedCount) {
        this.failedCount = failedCount;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public int getLastParseRowNum() {
        return lastParseRowNum;
    }

    public void setLastParseRowNum(int lastParseRowNum) {
        this.lastParseRowNum = lastParseRowNum;
    }

    public Long getSucceedCount() {
        return succeedCount;
    }

    public void setSucceedCount(Long succeedCount) {
        this.succeedCount = succeedCount;
    }

    /**
     * //TODO：
     * 任务启动时刻                    : 2018-01-05 16:03:16
     * 任务结束时刻                    : 2018-01-05 16:03:26
     * 任务总计耗时                    :                 10s
     * 任务平均流量                    :               12B/s
     * 记录写入速度                    :              0rec/s
     * 读出记录总数                    :                   3
     * 读写失败总数                    :                   0
     * 如果状态是RUNNGING，则每次查看就要调用解析日志文件，赋值最新的信息
     */

    public void parseLogFile() throws IOException {
        File logFile = new File(this.getLogFile());
        List<String> lines = FileUtils.readLines(logFile, "utf-8");
//        if(lastParseRowNum >= lines.size()){
//            this.setState(State.SUCCESS);
//        }
        if(lastParseRowNum ==0){
            lastParseRowNum = 1;//防止从-1行开始读
        }
        for (int i = lastParseRowNum - 1; i < lines.size(); i++) {
            String line = lines.get(i);
//            System.out.println(line);
            //2018-01-10 10:34:22.397 [job-0] INFO  StandAloneJobContainerCommunicator - Total 29568 records, 512996175 bytes | Speed 2.59MB/s, 147 records/s | Error 0 records, 0 bytes |  All Task WaitWriterTime 315.319s |  All Task WaitReaderTime 23.604s | Percentage 0.00%
            if (line != null && line.contains("Percentage")) {
                String percentage = line.split("Percentage")[1].trim();
                this.setPercentage(percentage);
                try {
                    String infos[] = line.split("\\|");


                    String total = RegExpUtil.extractByReg("Total(.*)(records)", infos[0].split(",")[0], 1).trim();
                    this.setTotalCount(Long.parseLong(total));
                    String speed = RegExpUtil.extractByReg("Speed(.*)", infos[1], 1).trim();
                    this.setAvgSpeed(speed);
                    String errorCount = RegExpUtil.extractByReg("Error(.*)(records)", infos[2].split(",")[0], 1).trim();
                    this.setFailedCount(Long.valueOf(errorCount));
                    this.setSucceedCount(this.totalCount - this.failedCount);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            if (line != null && line.contains("异常Msg")) {
                String errorMsg = line.split("异常Msg:")[1].trim();
                this.setErrorMsg(errorMsg);
                this.setState(State.FAILED);
            }
            //下一行为错误信息
            if (line != null && line.contains("经DataX智能分析,该任务最可能的错误原因是:")) {
                this.setErrorMsg(lines.get(i+1));
                this.setState(State.FAILED);
            }
            if (line != null && line.contains("任务启动时刻")) {
                String startTime = line.split(":")[1].trim();
                this.setStartTime(DateUtil.parseDate(startTime));

            }
            if (line != null && line.contains("任务结束时刻")) {
                String endTime = line.split(":")[1].trim();
                this.setEndTime(DateUtil.parseDate(endTime));

            }
            if (line != null && line.contains("任务总计耗时")) {
                String userTime = line.split(":")[1].trim();
                this.setUseTime(userTime);

            }
            if (line != null && line.contains("记录写入速度")) {
                String speed = line.split(":")[1].trim();
                this.setAvgSpeed(speed);

            }
            if (line != null && line.contains("读出记录总数")) {
                String total = line.split(":")[1].trim();
                this.setTotalCount(Long.valueOf(total));

            }
            if (line != null && line.contains("读写失败总数")) {
                String failedCount = line.split(":")[1].trim();
                this.setFailedCount(Long.valueOf(failedCount));
                this.setState(State.SUCCESS);
            }
        }
        this.setLastParseRowNum(lines.size());
    }

    @JsonIgnore
    public String getLogContent() throws IOException {
        String content = FileUtils.readFileToString(new File(this.logFile), "utf-8");
        return content;
    }
    public static void main(String[] args) throws IOException {
        DataTranslateLog log = new DataTranslateLog();
        log.setState(State.RUNNING);
//        log.setLogFile("/home/lism/Downloads/datax/log/2018-01-05/37-a0eb-628ab0be7693-16_03_15.280.log");
//        log.setLogFile("/home/lism/Downloads/datax/log/2018-01-10/in_spiderWachet_json-10_02_57.537.log");
//        log.setLogFile("/home/lism/Downloads/datax/log/2018-01-10/in_spiderWachet_json-10_31_59.661.log");
        log.setLogFile("/home/lism/Downloads/datax/log/2018-01-10/in_spiderWachet_json-11_41_41.555.log");
        log.parseLogFile();
        System.out.println(log);
//        String logContent = log.getLogContent();
//        System.out.println(logContent);
    }
}
