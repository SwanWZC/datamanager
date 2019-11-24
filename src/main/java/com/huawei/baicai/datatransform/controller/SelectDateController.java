package com.huawei.baicai.datatransform.controller;

import com.huawei.baicai.datatransform.entiry.Prs;
import com.huawei.baicai.datatransform.service.DbOperationDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * @date 2019-11-24-17:30
 */
@RestController
@RequestMapping("/api")
public class SelectDateController {
    @Autowired
    private DbOperationDaoService dbOperationService;

    @RequestMapping(value = "selectData/id={id}",method = RequestMethod.GET)
    @ResponseBody
    public String selectData(@PathVariable int id){
        System.out.println("id:"+id);
        Prs prs = new Prs();
        prs = dbOperationService.queryDateById(id);

        return "id:"+prs.getId()+";cgisai:"+prs.getCgisai()+";cqi:"+prs.getCqi();
    }
}
