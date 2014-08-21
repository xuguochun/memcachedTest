package cn.gyyx.java.bll;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

public class Memcached {

	private static Memcached memcache=null;
	private MemcachedClient client=null;
	private Memcached(){
		
	}
	
	public static Memcached getInstance()
	{
		if(memcache==null){
			memcache=new Memcached();
			try {
				memcache.client=new XMemcachedClient("192.168.6.195",10001);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return memcache;
		
	}
	
	public void Set(String key,int exp,Object value){
			try {
				client.set(key, exp, value);
			
			} catch (Exception e) {
			}
	}
	
	public void delete(String key)
	{
		try {
			client.delete(key);
		
		} catch (Exception e) {
			
		}
	}
	
	public Object Get(String key){
		
		try {
		   return client.get(key);
		
		} catch (Exception e) {		
		}
		
		return null;
	}
    
	
	
}
