package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.model.User2;
import com.example.repository.UserRepository;

@Service
@CacheConfig(cacheNames = "user")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Cacheable(value = "ticketsCache", key = "#p0", unless = "#result==null")
	public Optional<User2> findOne(int id) {
		System.out.println("cache");
		return userRepository.findById(id);
	}

	@Cacheable(value = "userCache", key = "#p0")
	public void save(User2 user) {

		userRepository.save(user);

	}

	@Cacheable(value = "ticketsCache")
	public Iterable<User2> findAll() {
		/*ArrayList<User2> arrayList = new ArrayList<>();
		userRepository.findAll().forEach(arrayList::add);
		return arrayList;*/
		return userRepository.findAll();
		
	}

	@CacheEvict(value = "ticketsCache", key = "#p0")
	public void delectUser(int id) {
		userRepository.deleteById(id);

	}

	@Caching(evict = { @CacheEvict(value = "ticketsCache", allEntries = true) }, put = {
			@CachePut(value = "ticketsCache", key = "#p0", unless = "#result != null") })

	// @CachePut(value="ticketsCache", key = "#p0")
	public void updateUser(User2 user, int id) {

		userRepository.save(user);

	}

}
