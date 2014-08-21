package cn.gyyx.test;

import static org.junit.Assert.*;

import org.junit.Test;

import cn.gyyx.java.bll.Memcached;

public class test {

	@Test
	public void testMemcached()
	{
		Memcached.getInstance().Set("myvalue", 3600, "xuguochun");
		
		String myvalue=(String)Memcached.getInstance().Get("myvalue");
		
		assertEquals(myvalue,"xuguochun");
		
	
	}
}
