package cn.learncoding.java.se.io.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * 文件和字节流的转换
 * 
 * @author learncoding.cn
 *
 */
public class FileAndByteStream {
	
	public static void main(String[] args){

	    FileInputStream fin = null;
	    FileOutputStream fout = null;

	    String root = System.getProperty("user.dir");
	    String path = root + File.separator + "src" + File.separator + "cn" + File.separator+ "learncoding" 
	    		+ File.separator+ "file" + File.separator + "FileAndByteStream.java";
	    File file = new File(path);
	    try {
			fin = new FileInputStream(file);
			fout = new FileOutputStream(root + File.separator + file.getName());
			  byte[] buffer = new byte[1024];
			    int bytesRead;
			    while ((bytesRead = fin.read(buffer)) > 0) {
			      fout.write(buffer, 0, bytesRead);
			    }
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		    try {
		    	if (fin != null) {
		    		fin.close();
				}
		    	if (fout != null) {
		    		fout.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		   
		}
	  }

}
