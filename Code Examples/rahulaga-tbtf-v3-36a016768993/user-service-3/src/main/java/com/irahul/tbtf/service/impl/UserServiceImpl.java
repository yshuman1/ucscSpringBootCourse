package com.irahul.tbtf.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.irahul.tbtf.entity.User;
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.repository.UserRepository;
import com.irahul.tbtf.service.UserService;
import com.irahul.tbtf.service.exception.ErrorCode;
import com.irahul.tbtf.service.exception.InvalidFieldException;
import com.irahul.tbtf.service.exception.TBTFException;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUser(long userId) {
		User found = userRepository.findOne(userId);
		if(found==null){
			throw new TBTFException(ErrorCode.NOT_FOUND, "User not found");
		}
		return found;
	}

	@Override
	public boolean isATMPinValid(long userId, String pin) {
		User user = getUser(userId);
		if(user==null || pin==null){
			return false;
		}
		return user.getPin()!=null && user.getPin().equals(md5base64(pin));
	}

	@Override
	public User addUser(User user) {
		if(user.getPin()==null){
			throw new InvalidFieldException("Pin is required");
		}
		
		//let us hash the pin - TBTF bank does basic MD5
		UserImpl impl = (UserImpl)user;
		impl.setPin(md5base64(user.getPin()));
		
		return userRepository.save(impl);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Iterable<UserImpl> getUsers(String firstName, String lastName) {
		if(StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)){
			throw new TBTFException(ErrorCode.MISSING_DATA,"Atleast 1 required");
		}
		if(!StringUtils.isEmpty(firstName) && StringUtils.isEmpty(lastName)){
			return userRepository.findByFirstNameContaining(firstName);
		}
		if(StringUtils.isEmpty(firstName) && !StringUtils.isEmpty(lastName)){
			return userRepository.findByLastNameContaining(lastName);
		}		
		
		return userRepository.findByFirstNameContainingAndLastNameContaining(firstName, lastName);		
	}
	
	private String md5base64(String pin){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(pin.getBytes("UTF-8"));
			return Base64.encodeBase64String(digest);				
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			logger.error("failed to md5",e);
		}
		throw new IllegalArgumentException("Server fail");
	}
}
