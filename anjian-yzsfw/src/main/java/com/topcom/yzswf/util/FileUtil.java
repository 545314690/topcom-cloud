package com.topcom.yzswf.util;

import org.apache.commons.codec.binary.Base64;

import java.io.*;


public class FileUtil {

    public static String readContentAsBase64( File file ) throws IOException {
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		InputStream input = new BufferedInputStream( new FileInputStream( file ) );
		
		int read = -1;
		
		while ( ( read = input.read() ) != -1 ) {
			output.write(read);
		}
		
		input.close();
		return Base64.encodeBase64String( output.toByteArray()  );
	}
	
}