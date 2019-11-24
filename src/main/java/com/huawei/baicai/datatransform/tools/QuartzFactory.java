package com.huawei.baicai.datatransform.tools;

import com.huawei.baicai.datatransform.dao.quartz.Job1;
import com.huawei.baicai.datatransform.dao.quartz.Job2;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author
 * @date 2019-11-24-20:41
 */
public class QuartzFactory {

    public static String createQuartz(String triggerName,String jobName,String cron,String groupName,Class jobClass) {

        JobDetail jobDetail = JobBuilder.newJob(jobClass)
                .withIdentity(jobName, groupName).build();

        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, groupName)
                .forJob(jobDetail)
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))//cron表达式
                .build();

        SchedulerFactory factory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = factory.getScheduler();
            scheduler.scheduleJob(jobDetail, trigger1);
            scheduler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "success";
    }

    public static void main(String[] args) {
        QuartzFactory.createQuartz("t1","job1","*/3 * * * * ?","group",Job1.class);
        QuartzFactory.createQuartz("t2","job2","*/10 * * * * ?","group", Job2.class);
    }
}
