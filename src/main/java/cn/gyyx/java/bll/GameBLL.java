package cn.gyyx.java.bll;

import java.util.List;

import cn.gyyx.java.entity.GameInfo;
import cn.gyyx.java.persistence.GameDao;

public class GameBLL {
	private GameDao gameDao=new GameDao();
	
	@SuppressWarnings("unchecked")
	public List<GameInfo> queryList() {
		
		Object result=Memcached.getInstance().Get("gameDao");
		if(result==null){
			
			result= gameDao.queryList();
			
			Memcached.getInstance().Set("gameDao", 3600, result);
		}
		
		return (List<GameInfo>) result;
	   
	}
}
