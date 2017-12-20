package com.light.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class CustomSessionDAO extends AbstractSessionDAO {

	private static final int EXPIRE_TIME = 600;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String,Object> redisTemplate;
	
	
	public void update(Session session) throws UnknownSessionException {
		this.redisTemplate.opsForValue().set(
				session.getId().toString(), 
				session, 
				EXPIRE_TIME, 
				TimeUnit.SECONDS);
	}

	public void delete(Session session) {
		this.redisTemplate.delete(session.getId().toString());
	}

	public Collection<Session> getActiveSessions() {
		// TODO
		return null;
	}

	@Override
	protected Serializable doCreate(Session session) {
		// 生成 sessionId
		Serializable sessionId = this.generateSessionId(session);
		// session 绑定 sessionId
		this.assignSessionId(session, sessionId);
		
		this.redisTemplate.opsForValue().set(
				session.getId().toString(), 
				session, 
				EXPIRE_TIME, 
				TimeUnit.SECONDS);
		
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		Session session = (Session) this.redisTemplate.opsForValue().get(sessionId.toString());
		
		if (session != null) {
			this.redisTemplate.opsForValue().set(
					session.getId().toString(), 
					session, 
					EXPIRE_TIME, 
					TimeUnit.SECONDS);
		}
		
		return session;
	}

}
