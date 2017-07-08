package com.irahul.tbtf.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Override
	public User getUser(long userId) {
		// TODO Auto-generated method stub
		return new UserImpl(userId);
	}

	@Override
	public boolean isATMPinValid(long userId, String pin) {
		// TODO Auto-generated method stub
		if(userId==1 && "1234".equals(pin)){
			return true;
		}
		return false;
	}

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method stub		
		return user;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<User> getUsers(String firstName, String lastName) {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		list.add(new UserImpl(1));
		list.add(new UserImpl(2));
		return list;
	}

}
