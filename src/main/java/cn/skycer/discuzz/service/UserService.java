package cn.skycer.discuzz.service;

import cn.skycer.discuzz.mapper.UserMapper;
import cn.skycer.discuzz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Johnny on 2019/8/5.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;


    public void createOrUpdate(User user) {
      User dbUser =   userMapper.findByID(user.getAccountID());
      if(dbUser ==null){
          //insert
          user.setGmtCreate(System.currentTimeMillis());
          user.setGmtModified(user.getGmtCreate());
          userMapper.insert(user);
      }else {
          //update
          user.setGmtModified(System.currentTimeMillis());
          dbUser.setAvatarUrl(user.getAvatarUrl());
          dbUser.setName(user.getName());
          dbUser.setToken(user.getToken());
          userMapper.update(dbUser);
      }
    }
}
