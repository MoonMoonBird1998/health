package com.yyn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyn.utils.MessageConstant;
import com.yyn.utils.PageResult;
import com.yyn.utils.QueryPageBean;
import com.yyn.utils.Result;
import com.yyn.pojo.CheckGroup;
import com.yyn.service.CheckGroupService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("checkgroup")
public class CheckGroupController {

    @Reference//只有在一个spring容器中才可以使用@Autowired注解
    private CheckGroupService checkGroupService;

    @PostMapping("add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try {
            checkGroupService.save(checkGroup, checkitemIds);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }


    @PostMapping("findPage")
    public PageResult getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkGroupService.getPage(queryPageBean);
        return pageResult;
    }

    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        try{
            checkGroupService.removeById(id);
        }catch (Exception e){
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    @GetMapping("findById/{id}")
    public Result findById(@PathVariable Integer id){
        CheckGroup checkGroup = checkGroupService.getById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkGroup);
    }

    @PutMapping("edit")
    public Result update(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try{
            checkGroupService.alter(checkGroup, checkitemIds);
        }catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    @GetMapping("findCheckitemIdsByGroupId/{id}")
    public Result findCheckitemIdsByGroupId(@PathVariable Integer id){
        List<Integer> checkItemIds = checkGroupService.getCheckitemIdsByGroupId(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemIds);
    }


}
