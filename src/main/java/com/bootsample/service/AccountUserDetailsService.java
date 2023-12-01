package com.bootsample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bootsample.entity.MyUser;
import com.bootsample.repository.UserDaoImpl;

@Service
public class AccountUserDetailsService implements UserDetailsService {

	@Autowired
    private UserDaoImpl userDaoImpl;

	
    public UserDetails loadUserByUsername(String userName)
    	throws UsernameNotFoundException{
    	
    	if(userName == null || "".equals(userName)) {
    		throw new UsernameNotFoundException(userName + "is not found");
    	}
    	
    	try {
    		MyUser myUser = userDaoImpl.findUserByUserName(userName);
    		
            if (myUser != null) {
                return new AccountUserDetails(myUser); // --- (2) UserDetailsの実装クラスを生成

            } else {
                throw new UsernameNotFoundException(userName + "is not found");
            }
    	} catch(EmptyResultDataAccessException e) {
    		throw new UsernameNotFoundException(userName + "is not found");
    	}
        
    }
}
