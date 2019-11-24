package com.huawei.baicai.datatransform.service;

import com.huawei.baicai.datatransform.dao.DbOperationDao;
import com.huawei.baicai.datatransform.entiry.Prs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author
 * @date 2019-11-24-17:19
 */
@Service
public class DbOperationDaoService {
    @Autowired
    private DbOperationDao dbOperationDao;

    public Prs queryDateById(int id) {
        return dbOperationDao.queryDateById(id);
    }
}
