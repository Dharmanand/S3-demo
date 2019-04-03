package com.kd.aws.testdemo;

import java.util.concurrent.ConcurrentHashMap;

import org.testng.annotations.Test;

public class JunitDemoTest {

	ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
	static boolean filter = true;

	@Test
	public void test1() throws Exception {
		if (filter) {
			int count = 3;
			while (count > 0) {
				new Thread(() -> {
					String name = Thread.currentThread().getName();
					System.out.println("Thread name : " + name);
					try {
						Thread.sleep(1000);
						map.put(name, System.nanoTime());
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Thread \"" + name + "\" is executed successfully !!");
				}).start();
				count--;
			}
			filter = false;
			while(map.size() < 3) {
				Thread.sleep(1000);
			}
			System.out.println("---------inside filter---------");
		}
		System.out.println("---------test0 finished---------");
	}

	@Test(dependsOnMethods="test1")
	public void tes2() {
		System.out.println("------test2------map has size : " + map.size());
		System.out.println("Thread-0 has value: "+map.get("Thread-0"));
	}

	@Test(dependsOnMethods="test1")
	public void tes3() {
		System.out.println("------test3------map has size : " + map.size());
		System.out.println("Thread-1 has value: "+map.get("Thread-1"));
	}

	@Test(dependsOnMethods="test1")
	public void tes4() {
		System.out.println("------test4------map has size : " + map.size());
		System.out.println("Thread-2 has value: "+map.get("Thread-2"));
	}

}
