package de.l3s.boilerpipe.sax;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * A very simple HTTP/HTML fetcher, really just for demo purposes.
 * 
 * @author Christian Kohlschütter
 */
public class HTMLFetcher {
	private HTMLFetcher() {
	}

	private static final Pattern PAT_CHARSET = Pattern.compile("charset=([^; ]+)$");

	/**
	 * Fetches the document at the given URL, using {@link URLConnection}.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static HTMLDocument fetch2( URL url, String proxies) throws IOException {
		
		String [] proxList = proxies.split(" ");
		HttpURLConnection httpcon = null;
		String ct = null;
		Boolean b= true;
		
	    for (int i = 0; i < proxList.length; i++)
	     {

	    	    String mypro = proxList[i].substring(0, proxList[i].indexOf(":"));
	    	    int puerto =  Integer.parseInt(proxList[i].substring(proxList[i].indexOf(":")+1, proxList[i].length()));
	    	    
	    	    //System.out.println("host: "+mypro+" puerto: "+puerto);
		
		        //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.126.152.119", 3128));
		        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(mypro, puerto));
                httpcon = (HttpURLConnection) url.openConnection(proxy);                
                
                //URLConnection yc = url.openConnection(proxy);
                
                httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
                ct = httpcon.getContentType();
                //System.out.println("PRIMER RESULTADO: " + httpcon.getResponseCode());
                
                if (httpcon.getResponseCode() == 200) {
                	i=proxies.length();
                	b=false;
                }
 
	     }
	    
	     if(b)
	     {
	    	 httpcon = (HttpURLConnection) url.openConnection(); 
             if (httpcon.getResponseCode() != 200) {
            	 System.exit(0);
             }
	     }
	     
	            
                
               /* if (httpcon.getResponseCode() != 200) {
                	System.out.println("REINTENTANDO");
    		        //proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("192.126.152.119", 3128));
                    httpcon = (HttpURLConnection) url.openConnection(); 
                	System.out.println("SEGUNDO RESULTADO: " + httpcon.getResponseCode());
                }
                
                if (httpcon.getResponseCode() != 200) {
                   System.exit(0);
                }*/
                
	     
                Charset cs = Charset.forName("Cp1252");
                if (ct != null) {
                        Matcher m = PAT_CHARSET.matcher(ct);
                        if(m.find()) {
                                final String charset = m.group(1);
                                try {
                                        cs = Charset.forName(charset);
                                } catch (UnsupportedCharsetException e) {
                                        // keep default
                                }
                        }
                }
                
                InputStream in = httpcon.getInputStream();

                final String encoding = httpcon.getContentEncoding();
                if(encoding != null) {
                        if("gzip".equalsIgnoreCase(encoding)) {
                                in = new GZIPInputStream(in);
                        } else {
                                System.err.println("WARN: unsupported Content-Encoding: "+encoding);
                        }
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[4096];
                int r;
                while ((r = in.read(buf)) != -1) {
                        bos.write(buf, 0, r);
                }
                in.close();

                final byte[] data = bos.toByteArray();
                
                return new HTMLDocument(data, cs);
        }
	
	public static HTMLDocument fetch(final URL url) throws IOException {
        final HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
        httpcon.addRequestProperty("User-Agent", "Mozilla/4.76");
        final String ct = httpcon.getContentType();

        Charset cs = Charset.forName("Cp1252");
        if (ct != null) {
                Matcher m = PAT_CHARSET.matcher(ct);
                if(m.find()) {
                        final String charset = m.group(1);
                        try {
                                cs = Charset.forName(charset);
                        } catch (UnsupportedCharsetException e) {
                                // keep default
                        }
                }
        }
        
        InputStream in = httpcon.getInputStream();

        final String encoding = httpcon.getContentEncoding();
        if(encoding != null) {
                if("gzip".equalsIgnoreCase(encoding)) {
                        in = new GZIPInputStream(in);
                } else {
                        System.err.println("WARN: unsupported Content-Encoding: "+encoding);
                }
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[4096];
        int r;
        while ((r = in.read(buf)) != -1) {
                bos.write(buf, 0, r);
        }
        in.close();

        final byte[] data = bos.toByteArray();
        
        return new HTMLDocument(data, cs);
}
}
