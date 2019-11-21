package main.java.chapter05.storm;

import java.io.InputStream;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class TestHdfsFileSystem {
	
	FileSystem fs;
	int last;
	InputStream in;
	
	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		new TestHdfsFileSystem().testFile();
	}
	
	
	public void testFile() throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = new Date();
		String dir = sdf.format(d);
		String uri = "hdfs://192.168.11.132:9000/flume/data/" + dir;
		try {
			fs = FileSystem.get(URI.create(uri), new Configuration());
			FileStatus[] status = fs.listStatus(new Path(uri)); //Filter files/directories in the given list of paths using user-supplied path filter.
			Path[] listedPaths = FileUtil.stat2Paths(status);

			for (int i = 0; i < listedPaths.length; i++) {
				if (i == listedPaths.length - 1) {   //
				   last = i;
					in = fs.open(new Path(listedPaths[i].toString()));
				}
			}

		}finally{
			
		}
	}

}
