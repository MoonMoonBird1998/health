package com.yyn.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yyn.entity.MessageConstant;
import com.yyn.entity.PageResult;
import com.yyn.entity.QueryPageBean;
import com.yyn.entity.Result;
import com.yyn.pojo.CheckItem;
import com.yyn.service.CheckItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("checkitem")
public class CheckItemController {

    @Reference//只有在一个spring容器中才可以使用@Autowired注解
    private CheckItemService checkItemService;

    @PostMapping("add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            checkItemService.save(checkItem);
        }catch (Exception e){
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }

        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }


    @PostMapping("findPage")
    public PageResult getPage(@RequestBody QueryPageBean queryPageBean){
        PageResult pageResult = checkItemService.getPage(queryPageBean);
        return pageResult;
    }

    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable Integer id){
        try{
            checkItemService.removeById(id);
        }catch (Exception e){
            return new Result(false, e.getMessage());
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    @GetMapping("findById/{id}")
    public Result findById(@PathVariable Integer id){
        CheckItem checkItem = checkItemService.getById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    @PutMapping("update")
    public Result update(@RequestBody CheckItem checkItem){
        try{
            checkItemService.update(checkItem);
        }catch (Exception e){
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @GetMapping("findAll")
    public Result findAll(){
        List<CheckItem> checkItemList = checkItemService.getList();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
    }

}
