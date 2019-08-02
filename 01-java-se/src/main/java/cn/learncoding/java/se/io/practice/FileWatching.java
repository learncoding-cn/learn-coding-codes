package cn.learncoding.java.se.io.practice;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Properties;

/**
 * @author learncoding.cn
 */
public class FileWatching {
	
	private static String root = System.getProperty("user.dir");
	private static Path path = Paths.get(root,"config.properties");
	
	private static volatile boolean flag = true;
	
	public static void main(String[] args) {
		
		//启动文件监听程序
		new Thread(new Watcher()).start();
		
		int i = 0;
		
		try {
			Files.deleteIfExists(path);
			Files.createFile(path);
			
			while (flag) {
				if (++i == 5) {
					flag = false;
				}
				//文件变更
				write(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void write(int i) {
		SeekableByteChannel channel = null;
		try {
			//打开文件通道
			channel = Files.newByteChannel(path, StandardOpenOption.WRITE);
			//设置写入位置为文件起始位置
			channel.position(0);
			StringBuilder sb = new StringBuilder()
					.append("url=learncoding.cn ").append(i).append("\r\n");
			//写入数据  会覆盖原有数据
			channel.write(ByteBuffer.wrap(sb.toString().getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			IOUtil.close(channel);
		}
	}
	
	public static class Watcher implements Runnable{

		@Override
		public void run() {
			Path dictory = Paths.get(root);
			WatchService watcher = null;
			try {
				//创建文件监听器
				watcher = dictory.getFileSystem().newWatchService();
				//注册监听事件类型  监听器必须注册在文件夹上
				dictory.register(watcher, ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE);
				while (flag) {
					//阻塞获取监听事件
					WatchKey watchKey = watcher.take();
					//得到监听的所有事件
					List<WatchEvent<?>> list = watchKey.pollEvents();
					for (WatchEvent<?> watchEvent : list) {
						System.out.println("文件 "+ watchEvent.context() +" 触发事件： "+ watchEvent.kind());
						// 如果是对应的文件触发了对应的事件，则执行对应的操作
						if (watchEvent.context().equals(path.getFileName()) && watchEvent.kind().equals(ENTRY_MODIFY)) {
							dealModify();
						}
					}
					watchKey.reset();
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}finally {
				IOUtil.close(watcher);
			}
		}

		private void dealModify() throws IOException {
			Properties p = new Properties();
			p.load(Files.newInputStream(path, StandardOpenOption.READ));
			System.out.println(path.getFileName() + " 发生变更，读取最新内容：" + p);
		}
		
	}
	
	static class IOUtil {
		/**
		 * 关闭流通道
		 */
		public static void close(Closeable...closeables){
			if (closeables == null || closeables.length == 0) {
				return;
			}
			try {
				for (Closeable closeable : closeables) {
					if (closeable != null) {
						closeable.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
