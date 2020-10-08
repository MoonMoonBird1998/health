package com.yyn.mapper;

import com.github.pagehelper.Page;
import com.yyn.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

public interface CheckGroupMapper {

    void insertGroup(CheckGroup checkGroup);

    void insertItemAndGroup(Map<String, Integer> map);

    Page<CheckGroup> selectPage(String queryString);

    CheckGroup selectById(Integer id);

    List<Integer> selectCheckItemIdsByGroupId(Integer id);

    void updateGroup(CheckGroup checkGroup);

    void deleteAssociation(Integer id);

    void deleteCheckGroup(Integer id);
}
