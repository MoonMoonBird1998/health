package com.yyn.service;

import com.yyn.utils.PageResult;
import com.yyn.utils.QueryPageBean;
import com.yyn.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    void save(CheckItem checkItem);

    PageResult getPage(QueryPageBean queryPageBean);

    void removeById(Integer id);

    CheckItem getById(Integer id);

    void update(CheckItem checkItem);

    List<CheckItem> getList();

}
