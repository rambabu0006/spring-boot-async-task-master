package com.async.task.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.async.task.model.User;

@Service
public class RemoteUserApiService {

	private Map<String,User> userCacheMap = new HashMap<String,User>();
	
	public RemoteUserApiService() {
		userCacheMap.put("ram", new User("ram","www.ram_blogger.com"));
		userCacheMap.put("ramesh", new User("ramesh","www.ramesh_blogger.com"));
		userCacheMap.put("sandeep", new User("sandeep","www.sandeep_blogger.com"));
		userCacheMap.put("sai", new User("sai","www.sai_blogger.com"));
	}
	
	public User getUser(String userName) {
		return userCacheMap.get(userName);
	}
}
