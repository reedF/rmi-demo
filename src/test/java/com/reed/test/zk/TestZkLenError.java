package com.reed.test.zk;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.ACLProvider;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryOneTime;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;

/**
 * 复现zk bug:https://issues.apache.org/jira/browse/ZOOKEEPER-706
 * 使用zk 3.4.6版复现，使用3.4.7以上可修复
 * @author reed
 *
 */
public class TestZkLenError {
	private static final String PATH = "/node/list";
	private static final String NS = "test-zk-len";
	private static final String ZK = "192.168.59.103:2181";

	private static CuratorFramework client = null;

	public static void main(String[] args) {
		// System.setProperty("jute.maxbuffer", 4096 * 1024 * 1000 + "");
		testLargeWatchBug();
		quit();
	}

	public static void quit() {
		System.out.println("请开始您的输入，Q 退出");
		// 怎么让程序一直运行
		Scanner scan = new Scanner(System.in);
		while (scan.hasNext()) {
			String in = scan.next().toString();
			if ("Q".equals(in.toUpperCase())) {
				System.out.println("您成功已退出！");
				break;
			}
			System.out.println("您输入的值：" + in);
		}
	}

	public static void testLargeWatchBug() {
		try {
			CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder().connectString(ZK)
					.retryPolicy(new RetryOneTime(1000)).connectionTimeoutMs(30000).sessionTimeoutMs(30000)
					.namespace(NS);
			// setAuth(builder);
			client = builder.build();
			client.start();

			prepareData();
			startWatch();
			//deleteNode(PATH);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void setAuth(CuratorFrameworkFactory.Builder builder) throws Exception {
		builder.authorization("digest", NS.getBytes("utf-8")).aclProvider(new ACLProvider() {
			@Override
			public List<ACL> getDefaultAcl() {
				return ZooDefs.Ids.CREATOR_ALL_ACL;
			}

			@Override
			public List<ACL> getAclForPath(final String path) {
				return ZooDefs.Ids.CREATOR_ALL_ACL;
			}
		});
	}

	private static void prepareData() throws Exception {
		int childNum = 1024;
		// 1K
		char[] chars = new char[1024];
		Arrays.fill(chars, 'x');

		String nameSuffix = new String(chars);
		// client.create().forPath(PATH);
		for (int i = 0; i < childNum; i++) {
			String childPath = PATH + "/" + i + "-" + nameSuffix;
			//String childPath = PATH + "/" + i;
			if (client.checkExists().forPath(childPath) == null) {
				client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(childPath,
						nameSuffix.getBytes("utf-8"));
			}
		}
	}

	private static void startWatch() throws Exception {
		PathChildrenCache watcher = new PathChildrenCache(client, PATH, false);
		watcher.getListenable().addListener(new PathChildrenCacheListener() {
			@Override
			public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
				ChildData data = event.getData();
				if (data == null) {
					System.out.println("=========No data in event[" + event + "]");
				} else {
					System.out.println("=========Receive event: " + "type=[" + event.getType() + "]" + ", path=["
							+ data.getPath() + "]" + ", data=[" + new String(data.getData()) + "]" + ", stat=["
							+ data.getStat().toString().trim() + "]");
				}
			}
		}

		);
		watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
	}

	private static void deleteNode(String path) throws Exception {
		client.delete().deletingChildrenIfNeeded().forPath(path);
	}
}
