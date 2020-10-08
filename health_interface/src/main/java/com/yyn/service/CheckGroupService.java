package com.yyn.service;

import com.yyn.entity.PageResult;
import com.yyn.entity.QueryPageBean;
import com.yyn.pojo.CheckGroup;
import com.yyn.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {

    PageResult getPage(QueryPageBean queryPageBean);

    void removeById(Integer id);

    CheckGroup getById(Integer id);

    void alter(CheckGroup checkGroup, Integer[] checkitemIds);

    void save(CheckGroup checkGroup, Integer[] checkitemIds);

    List<Integer> getCheckitemIdsByGroupId(Integer id);
}
