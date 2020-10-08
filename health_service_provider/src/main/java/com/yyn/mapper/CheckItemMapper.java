package com.yyn.mapper;

import com.github.pagehelper.Page;
import com.yyn.pojo.CheckItem;

import java.util.List;

public interface CheckItemMapper {

    void insertItem(CheckItem checkItem);

    Page<CheckItem> selectByCondition(String queryString);

    Long countCheckItemInCheckGroup(Integer id);

    void deleteById(Integer id);

    CheckItem selectById(Integer id);

    void updateCheckItem(CheckItem checkItem);

    List<CheckItem> selectList();
}
