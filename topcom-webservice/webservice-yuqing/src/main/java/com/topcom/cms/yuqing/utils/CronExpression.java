package com.topcom.cms.yuqing.utils;


import org.apache.commons.lang3.StringUtils;

public class CronExpression {

    public static final String WEEKENDS = "MON-FRI";

    private boolean atWeekends = false;

    public enum Type {
        INTERVAL, EVERY
    }

    private String second = "0";

    private String minute = "0";

    private String hour = "0";

    private String day = "*";

    private String month = "*";

    private String week = "?";

    private String year = "*";

    private Type type;

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getWeek() {
        /**
         * 如果是周末
         */
        if (atWeekends) {
            this.week = "MON-FRI";
        }
        return week;
    }

    public void setWeek(String week) {

        this.week = week;
        if (!StringUtils.equals(this.week, "?")) {
            this.day = "?";
        }
//        if (!StringUtils.equals(this.week, "?")) {
//            this.day = "?";
//            int weekNum = 0;
//            try {
//                weekNum = Integer.parseInt(this.getWeek());
//            } catch (NumberFormatException e) {
////				e.printStackTrace();
//                return;
//            }
//            if (weekNum == 7) {
//                this.week = "1";
//            } else {
//                this.week = weekNum + 1 + "";
//            }
//        }
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean isAtWeekends() {
        return atWeekends;
    }

    public void setAtWeekends(boolean atWeekends) {
        this.atWeekends = atWeekends;
    }

    // 0 0 1 * * ?
    // 0 0 0 1 * ?
    // 0 0 0 ? * wen
    // 0 0 0 * 1 ?
    @Override
    public String toString() {
        String cronExpresson = null;

        cronExpresson = this.getSecond() + " " + this.getMinute() + " "
                + this.getHour() + " " + this.getDay() + " " + this.getMonth()
                + " " + this.getWeek();
        return cronExpresson;
    }

    public static CronExpression formCronString(String str) {
        CronExpression expression = new CronExpression();
        String[] arr = null;
        if (str.contains("/")) {
            //如果表达式中包含0/或者 1/ 替换为空
            str = str.replaceAll("[0-9]/", "");
            expression.setType(Type.INTERVAL);
            arr = str.split(" ");
        } else {
            arr = str.split(" ");
            expression.setType(Type.EVERY);
        }
        expression.setSecond(arr[0]);
        expression.setMinute(arr[1]);
        expression.setHour(arr[2]);
        expression.setDay(arr[3]);
        expression.setMonth(arr[4]);
        if (!StringUtils.equals(arr[5], "?") && arr[5] != null) {
            int weekNum = 0;
            try {
                weekNum = Integer.parseInt(arr[5]);
                if (weekNum == 1) {
                    weekNum = 6;
                } else {
                    weekNum = weekNum - 2;
                }
                expression.setWeek(weekNum + "");
            } catch (NumberFormatException e) {
//                e.printStackTrace();
            }
            expression.setWeek(arr[5]);
        }
        if (WEEKENDS.equals(expression.getWeek())) {
            expression.setAtWeekends(true);
        }
        return expression;
    }

    public static void main(String[] args) {
        /*
         * CronExpression expression = new CronExpression();
		 * expression.setSecond("0"); expression.setMinute("12");
		 * expression.setHour("*"); expression.setDay("*");
		 * expression.setWeek("?"); expression.setYear("?");
		 * expression.setType(Type.INTERVAL);
		 * System.out.println(expression.toString());
		 */
        //String str = "0 0 0 ? * 1";
        String str = "0 0 0/6 * * ?";
        int day = -1;
        int hours = 18;
//        CronExpression expression = new CronExpression();
//        if (day < 0) {
//            day = -day;
//            expression.setDay(day + "l");
//        } else expression.setDay(day + "");
//        expression.setHour(hours + "");
        CronExpression expression = CronExpression.formCronString(str);
        System.err.println(expression);
        System.err.println(new CronExpression());
    }
}