package br.vp.controller;

import org.apache.commons.codec.binary.Base64;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ImageController {
	
	
    public static String getBase64FromResource(String resourceName) {
        byte[] byteChunk = new byte[4096];
        int n;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        InputStream is = null;
        try {
            is = loadResource(resourceName);
            while ((n = is.read(byteChunk)) > 0) {
                baos.write(byteChunk, 0, n);
            }

            return Base64.encodeBase64String(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) { }
            }
            try {
                baos.close();
            } catch (IOException e) { }
        }
        return "";
    }
    
    public static InputStream loadResource(String resource) {
    	InputStream	is;
    	
    	String urlImage = "c:\\git\\vendas-plus\\image\\" + resource;
    	System.out.println(urlImage);
    	
		try {
			is = new BufferedInputStream(new FileInputStream(urlImage));
		} catch (FileNotFoundException e) {
			System.out.println("N carregou a imagem");
			return null;
		}
		
        return is;
    }
}
