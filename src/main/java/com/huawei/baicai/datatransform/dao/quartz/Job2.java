package com.huawei.baicai.datatransform.dao.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author
 * @date 2019-11-24-21:27
 */
public class Job2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("++++++++++hello world+++++++++++");
    }
}
