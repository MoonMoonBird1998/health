package com.yyn.service;

import com.yyn.utils.PageResult;
import com.yyn.utils.QueryPageBean;
import com.yyn.pojo.CheckGroup;

import java.util.List;

public interface CheckGroupService {

    PageResult getPage(QueryPageBean queryPageBean);

    void removeById(Integer id);

    CheckGroup getById(Integer id);

    void alter(CheckGroup checkGroup, Integer[] checkitemIds);

    void save(CheckGroup checkGroup, Integer[] checkitemIds);

    List<Integer> getCheckitemIdsByGroupId(Integer id);
}
