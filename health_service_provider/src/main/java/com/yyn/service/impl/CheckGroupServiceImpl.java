package com.yyn.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yyn.utils.PageResult;
import com.yyn.utils.QueryPageBean;
import com.yyn.mapper.CheckGroupMapper;
import com.yyn.pojo.CheckGroup;
import com.yyn.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public PageResult getPage(QueryPageBean queryPageBean) {
        //开启分页
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //调用mapper进行查询
        Page<CheckGroup> groupPage = checkGroupMapper.selectPage(queryPageBean.getQueryString());
        //封装查询结果
        return new PageResult(groupPage.getTotal(), groupPage.getResult());
    }

    @Override
    public void removeById(Integer id) {
        //先删除检查项与检查组的关系
        checkGroupMapper.deleteAssociation(id);
        //删除检查项信息
        checkGroupMapper.deleteCheckGroup(id);
    }

    @Override
    public CheckGroup getById(Integer id) {
        CheckGroup checkGroup = checkGroupMapper.selectById(id);
        return checkGroup;
    }

    @Override
    public void alter(CheckGroup checkGroup, Integer[] checkitemIds) {
        //修改检查组信息
        checkGroupMapper.updateGroup(checkGroup);
        //重置检查组与检查项之间的关系
        Integer groupId = checkGroup.getId();
        //先删除原有的关系
        checkGroupMapper.deleteAssociation(groupId);
        //新建联系
        Map<String, Integer> map = null;
        for (Integer checkitemId : checkitemIds) {
            map = new HashMap<>();
            map.put("groupId", groupId);
            map.put("itemId", checkitemId);
            checkGroupMapper.insertItemAndGroup(map);
        }

    }


    @Override
    public void save(CheckGroup checkGroup, Integer[] checkitemIds) {
        //调用mapper存储检查组的信息
        checkGroupMapper.insertGroup(checkGroup);
        //获得插入后的id
        Integer groupId = checkGroup.getId();
        //存储检查项和检查组的关系信息
        Map<String, Integer> map = null;
        for (Integer checkitemId : checkitemIds) {
            map = new HashMap<>();
            map.put("groupId", groupId);
            map.put("itemId", checkitemId);
            //循环插入
            checkGroupMapper.insertItemAndGroup(map);
        }
    }

    @Override
    public List<Integer> getCheckitemIdsByGroupId(Integer id) {
        List<Integer> ids = checkGroupMapper.selectCheckItemIdsByGroupId(id);
        return ids;
    }
}
