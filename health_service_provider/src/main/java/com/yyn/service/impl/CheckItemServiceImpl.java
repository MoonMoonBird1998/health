package com.yyn.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyn.entity.PageResult;
import com.yyn.entity.QueryPageBean;
import com.yyn.mapper.CheckItemMapper;
import com.yyn.pojo.CheckItem;
import com.yyn.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(interfaceClass = CheckItemService.class)//注意要添加的是alibaba的service注解
@Transactional//开启事务
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemMapper checkItemMapper;

    @Override
    public void save(CheckItem checkItem) {
        checkItemMapper.insertItem(checkItem);
    }

    @Override
    public PageResult getPage(QueryPageBean queryPageBean) {
        //使用分页查询插件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //进行分页查询
        Page<CheckItem> page = checkItemMapper.selectByCondition(queryPageBean.getQueryString());
        //将查询结果封装到PageResult对象中
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public void removeById(Integer id) {
        //先根据检查项id来查询检查组中是否有该检查项
        Long count = checkItemMapper.countCheckItemInCheckGroup(id);
        //如果有，则不能删除，抛出异常
        if (count > 0){
            throw new RuntimeException("该检查项被包括在某个检查组中，请删除检查组后再删除该检查项");
        }
        //如果没有，则进行正常删除操作
        checkItemMapper.deleteById(id);
    }

    @Override
    public CheckItem getById(Integer id) {
        CheckItem checkItem = checkItemMapper.selectById(id);
        return checkItem;
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemMapper.updateCheckItem(checkItem);
    }

    @Override
    public List<CheckItem> getList() {
        List<CheckItem> list = checkItemMapper.selectList();
        return list;
    }

}
