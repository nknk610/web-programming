package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private List<User> userList;

    //userList 초기화
    public UserServiceImpl()
    {
        userList = new ArrayList<User>();
        userList.add(new User("user1", "허", "user111"));
        userList.add(new User("user2", "남", "user222"));
        userList.add(new User("user3", "경", "user333"));
    }

    //userList 반환
    @Override
    public List<User> list() 
    {
        return userList;
    }

    //id로 user 탐색
    @Override
    public User view(String id) 
    {
        User user = userList.stream()
            .filter(found -> found.getId().equals(id))
            .findAny()
            .orElse(null);

        return user;
    }

    //userList에 user 추가
    @Override
    public boolean add(User user) {
        User found = view(user.getId());
        if(found == null)
            return userList.add(user);
        return false;
    }

    //user 정보 변경
    @Override
    public User update(User user) {
        User found = view(user.getId());
        if(found != null)
        {
            int idx = userList.indexOf(found);
            userList.set(idx, user);
        }
        return found;
    }

    //user 정보 삭제
    @Override
    public boolean delete(String id) {
        User found = view(id);
        return userList.remove(found);
    }

}