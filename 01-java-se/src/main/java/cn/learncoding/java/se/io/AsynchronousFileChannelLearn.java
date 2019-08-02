package cn.learncoding.java.se.io;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author learncoding.cn
 */
public class AsynchronousFileChannelLearn {
	
	private static volatile boolean flag = false;
	
	public static void main(String[] args) {
		//项目根目录
		 String root = System.getProperty("user.dir");
		 
		//项目根目录下获取测试文件testAsync.txt的路径
		 Path path = Paths.get(root, "testAsync.txt");
		 
		 //使用jdk1.7提供的try-with-resources，避免手动关闭资源
		try ( AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, READ, CREATE, WRITE)){
			
			String msg = "learncoding.cn";
			byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
			
			//使用Future方式异步写入数据
			ByteBuffer buffer = ByteBuffer.wrap(bytes);
			Future<Integer> future = channel.write(buffer, 0);
			
			while (!future.isDone()) {
				System.out.println("等待写操作完成, todo others...");
				//在此可以做其他操作，然后使用future.get()获取异步执行结果，如果有结果了，则直接返回，如果没有结果，则阻塞等待
			}
			System.out.println("写入文件内容：" + msg + "，结果：" + future.get());
			
			buffer.position(0);
			//使用方法回调方式异步写入数据
			channel.write(buffer, bytes.length, future, new CompletionHandler<Integer, Future<Integer>>() {

				@Override
				public void completed(Integer result, Future<Integer> attachment) {
					try {
					    System.out.println("写入文件内容：" + msg + "，结果：" + result);
						
						ByteBuffer buffer = ByteBuffer.allocate(bytes.length * 2);
						//使用Future方式异步读取数据
						Future<Integer> future = channel.read(buffer, 0);
						
						while (!future.isDone()) {
							System.out.println("等待读操作完成, todo others...");
						}
						buffer.flip();
						System.out.println("读取文件内容：" + new String(buffer.array(), 0, future.get(), StandardCharsets.UTF_8));
						flag = true;
					} catch (InterruptedException | ExecutionException e) {
						e.printStackTrace();
					}
				}

				@Override
				public void failed(Throwable exc, Future<Integer> attachment) {
					System.out.println("写入错误");
					flag = true;
				}
			});
			
			while (!flag) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}
