package com.nes.springboot.study;

import com.nes.springboot.domain.User;
import com.nes.springboot.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by wdq on 16-11-10.
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"xxxx","bbb"})
public class UserController {

    //创建线程安全的map
    private static Map<Long,User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    @Autowired
    private UserService userService;

    //获取用户集
    @ApiOperation(value="获取用户列表", notes="这是说明")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<User> getUserList(){
        List<User> rs = new ArrayList<User>(users.values());
        return rs;
    }

    @ApiOperation(value = "添加用户",notes = "/user/add")
    //@ApiImplicitParam(required = true, name = "user",value = "用户详情实体",dataType = "User")
    //可以标注
    //@ApiImplicitParam(name = "teachers",value = "教师集合",dataType = "Teacher")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user){
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(),user);
        return "success";
    }

    @ApiOperation(value="根据用户id获取用户", notes="根据url的id来获取用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path")
    @RequestMapping(value = "/get/{id}",method = RequestMethod.GET)
    @ApiResponse(response = User.class,code = 200,message = "xxxx")
    public User getUser(@PathVariable Long id){
        return users.get(id);
    }


    @ApiOperation(value="更新用户详细信息", notes="根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User",paramType = "body")
    })
    @RequestMapping(value = "/update/{id}",method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id,@ModelAttribute User user) {
        User u = users.get(id);
        u.setAge(user.getAge());
        u.setName(user.getName());
        users.put(id,u);
        return "success";
    }

    @ApiOperation(value="删除用户信息", notes="根据url的id来删除用户")
    @ApiImplicitParam(name = "id",value = "用户id",required = true,dataType = "Long")
    @RequestMapping(value = "/del/{id}",method = RequestMethod.DELETE)
    public String remove(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }


    @ApiOperation(value="获取所有用户信息")
    @RequestMapping(value = "/db/all",method = RequestMethod.GET)
    public List<User> getUserAlls() {
        return userService.getUserAlls();
    }

}
