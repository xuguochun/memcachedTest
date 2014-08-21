package cn.gyyx.java.bll;

import java.util.List;

import cn.gyyx.java.entity.ServerInfo;
import cn.gyyx.java.persistence.ServerDao;

public class ServerBLL {
	private ServerDao serverDao=new ServerDao();
	
	@SuppressWarnings("unchecked")
	public List<ServerInfo> queryListByGameId(int gameId) {
		Object result=Memcached.getInstance().Get(""+gameId);
		if(result==null){
			
			result= serverDao.queryListByGameId(gameId);
			
			Memcached.getInstance().Set(""+gameId, 3600, result);
		}
		
		return (List<ServerInfo>) result;
	}
}
