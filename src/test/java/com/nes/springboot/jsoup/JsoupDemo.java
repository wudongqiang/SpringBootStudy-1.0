package com.nes.springboot.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wdq on 17-6-29.
 */
public class JsoupDemo {


    @Test
    public void testLocalHtmlStr() {
        String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc = Jsoup.parse(html);
//        System.out.println(doc.html());
        System.out.println(doc.head().children());

        //直接获取某一标签内容
        System.out.println(doc.getElementsByTag("p").text());
    }

    //测试防止Xss攻击
    @Test
    public void testCleanerXss() {
        String unsafe =
                "<p><a href='http://example.com/' onclick='stealCookies()'>Link</a></p>";
        String safe = Jsoup.clean(unsafe, Whitelist.basic());//Whitelist.basic() 可以做到
        System.out.println(safe);
        // now: <p><a href="http://example.com/" rel="nofollow">Link</a></p>
    }

    @Test
    public void testUrlGetHtml() throws IOException {
//        Document document = Jsoup.connect("http://www.baidu.com").get();
        Document document = Jsoup.connect("http://www.163.com").get();
        //打印HTML
        //System.out.println(document.html());
        //获取html中的title
        String title = document.title();
        System.out.println(title);

        //获取所有a标签
//        Elements a = document.getElementsByTag("a");
//        a.forEach(item ->{
//             //item.attr("abs:href"); 取绝对路径值
//            System.out.println(item.attr("href")+"==="+item.text());
//        });

        //获取图片  select方法可以像使用jQuery选择器一样使用
//        select("img[src$=.png]") 只获取src中后缀是png的图片
        Elements select = document.select("img[src~=.(png|gif|jpe?g)]");
        select.forEach(item -> {
            String src = item.attr("src");
            System.out.println(src);

            //下载图片
            //downloadImg(src);
            downloadImg2(src);

        });
    }

    //restTemplate 方式
    private void downloadImg(String url) {
        url = "http://" + url.replace("//", "");
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        System.out.println(url + "---------" + fileName);


        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "*/*");
        ResponseEntity<byte[]> entity = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
        byte[] body = entity.getBody();

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            inputStream = new ByteArrayInputStream(body);
            outputStream = new FileOutputStream(new File("imge/" + fileName));

            byte[] b = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //URL 方式
    private void downloadImg2(String url) {
        if (!url.contains("http:")) {
            url = "http://" + url.replace("//", "");
        }
        int end = url.indexOf("?");
        int start = url.lastIndexOf("/");
        if (end < start) {
            end = url.length();
        }
        String fileName = url.substring(start + 1, end);
        System.out.println(url + "---------" + fileName);

        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL ur = new URL(url);
            URLConnection urlConnection = ur.openConnection();
            inputStream = urlConnection.getInputStream();

            outputStream = new FileOutputStream(new File("imge/" + fileName));

            byte[] b = new byte[1024];
            int len;
            while ((len = inputStream.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void testGetData() throws IOException {
        Map<String, String> map = new HashMap();
        map.put("scm-token", "5d2b44affec528f581ff1a0211dae125f625e45f03a40a38b9d46c460a881a8dbc9dd8a61a1cb4d642ce78a1a330a467a5145c57a0d77da9");
        map.put("scm-source", "SC_WEB");
        map.put("content-type", "*/*");
        map.put("Mimetype", "application/json;charset=UTF-8");

        Document document = Jsoup.connect("http://120.27.194.231:9003/monitorList")
                .headers(map).get();
//        System.out.println(document.html());

        Elements select = document.select("img[src]");
        select.forEach(item -> {
            generateImage(item.attr("src"));
        });

    }


    //base64字符串转化成图片
    public boolean generateImage(String imgStr) {

        if (imgStr == null) //图像数据为空
            return false;
        imgStr = imgStr.replace("data:image/png;base64,", "");
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片
            String imgFilePath = "imge/" + System.currentTimeMillis() + ".png";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
