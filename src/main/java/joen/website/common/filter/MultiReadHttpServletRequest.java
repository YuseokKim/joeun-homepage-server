package joen.website.common.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.tomcat.util.http.fileupload.IOUtils;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {
	private byte[] cacheBytes;
	
	public MultiReadHttpServletRequest(HttpServletRequest request) {
		super(request);
	}
	
	@Override
	public ServletInputStream getInputStream() throws IOException {
		if(cacheBytes == null) {
			cacheInputStream();
		}
		
		return new CachedServletInputStream();
	}
	
	private void cacheInputStream() throws IOException{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		IOUtils.copy(super.getInputStream(), out);
		cacheBytes = out.toByteArray();
	}
	
	@Override
	public BufferedReader getReader() throws IOException {	
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}
	
	/* An inputstream which reads the cached request body */
	public class CachedServletInputStream extends ServletInputStream{
		private ByteArrayInputStream input;
		
		public CachedServletInputStream() {
			/* create a new input stream from the cached request body */
			input = new ByteArrayInputStream(cacheBytes);
		}
		
		@Override
		public boolean isFinished() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean isReady() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public int read() throws IOException {
			return input.read();
		}
		
		@Override
		public void setReadListener(ReadListener listener) {
			// TODO Auto-generated method stub
			
		}
	}
	

}
