package com.huawei.baicai.datatransform.dao;

import com.huawei.baicai.datatransform.entiry.Prs;
import org.apache.ibatis.annotations.Param;

/**
 * @author
 * @date 2019-11-24-11:33
 */
public interface DbOperationDao {
    Prs queryDateById(@Param("id") int id);
}
