package cn.axboy.demo.interview.test;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/4/24 19:37
 * 面试题
 */
@Data
public class TcRule {
    //0表示无期限；1表示指定时间范围
    private int timeType;

    //规则开始日期和结束日期。当timeType=1时，有效。格式如"2020-01-01"
    private String startDate;
    private String endDate;
    //timeType=1时，有效。生效的星期几，取值0-6，周日是0，用|分隔。如果星期1，2，则存储1|2
    private String week;
    //timeType=1时，有效。每天生效的时间。精确到分钟。格式如“21:00”
    private String startTime;
    private String endTime;

    //0表示所有员工，1表示指定的员工
    private int personType;
    //如果personType=1时，有效。用竖线隔开，每个都是员工ID，为整数 。比如1|2|10
    private String personList;

    /**
     * 要求：在同一时刻，一个具体的员工，只能有一个有效的规则生效（可以没有生效的规则，但不能有冲突的规则）。
     *
     * @param tcRule;     待校验的规则
     * @param listTcRule; 已有的规则列表; 假定已存在的不冲突
     * @return 返回所有与tcRule冲突的规则
     *
     * <p>
     * 冲突判定条件举例：
     * 规则1：时间-2020-3-16到2020-4-16，每周一，8：00到20点，员工-1号员工和2号员工
     * 规则2：时间-所有时间，员工-1号员工和3号员工
     * 规则3：时间-2020-3-20到2020-5-20， 每周二，8：00到20点，所有员工
     * <p>
     * 规则2与规则1冲突；这是因为：比如在2020-3-23（周一）的9点， 员工1，会有两条可以生效的规则。他即符合规则1，又符合规则2，所以冲突
     * 规则3与规则1不冲突。因为：在任意时刻，任意员工，生效的规则都只有1条。比如员工1， 这个时间内的周一的9：00，他生效的是规则1；周二的9:00，他生效的是规则3
     */
    public static List<TcRule> checkTcRule(TcRule tcRule, List<TcRule> listTcRule) {
        if (tcRule == null) {
            throw new RuntimeException("参数异常");
        }
        if (listTcRule == null || listTcRule.isEmpty()) {
            return Collections.emptyList();
        }
        //// startDate > endDate 、 startTime > endTime 和 其它数据数据问题不做校验了
        //不限时间 && 不限员工，全部冲突
        if (tcRule.timeType == 0 && tcRule.personType == 0) {
            return listTcRule;
        }
        List<TcRule> list = new ArrayList<>();
        listTcRule.forEach(it -> {
            if (checkUser(tcRule, it) && checkTime(tcRule, it)) {
                //比较两个rule，用户冲突 && 时间冲突
                list.add(it);
            }
        });
        return list;
    }

    private static boolean checkUser(TcRule a, TcRule b) {
        if (a.personType == 0 || b.personType == 0) {
            return true;
        }
        String[] aUserArr = a.personList.split("\\|");
        String[] bUserArr = b.personList.split("\\|");
        for (String aUser : aUserArr) {
            for (String bUser : bUserArr) {
                if (aUser.equals(bUser)) {
                    return true;
                }
            }
        }
        return false;
    }

    //校验时间是否冲突，格式如2020-01-01 03:05，只用compareTo比较，时间格式不处理
    private static boolean checkTime(TcRule a, TcRule b) {
        if (a.timeType == 0 || b.timeType == 0) {
            return true;
        }
        return a.startDate.compareTo(b.endDate) < 0 && b.startDate.compareTo(a.endDate) < 0
                && a.startTime.compareTo(b.endTime) < 0 && b.startTime.compareTo(a.endTime) < 0
                && checkWeek(a, b);
    }

    // 校验星期是否冲突
    private static boolean checkWeek(TcRule a, TcRule b) {
        //week,不做空判断、错误数据校验
        for (char c : a.week.toCharArray()) {
            if (c < '0' || c > '6') {
                continue;
            }
            for (char e : b.week.toCharArray()) {
                if (c == e) {
                    return true;
                }
            }
        }
        return false;
    }
}
