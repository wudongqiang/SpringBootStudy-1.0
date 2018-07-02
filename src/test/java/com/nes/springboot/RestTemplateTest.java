package com.nes.springboot;

import io.netty.handler.ssl.SslContextBuilder;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.Netty4ClientHttpRequestFactory;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 * @author wdq
 * @date 2018-07-02-上午11:30
 */
public class RestTemplateTest {

    //    private static final String URL = "https://api.github.com/users/octocat";
    private static final String URL = "http://localhost:8080/hello/index";
    private static final int DEFAULT_SLEEP_MILLIS = 20;
    private static final int DEFAULT_TIMEOUT = 10000;


    @Test//(timeout = DEFAULT_TIMEOUT)
    public void syncRest() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        long start = System.currentTimeMillis();
        for (int i = 1; i < 20000; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
            System.out.println("response = " + response.getStatusCode());
        }
        System.out.println("use time : " + (System.currentTimeMillis() - start));

    }


    @Test//(timeout = DEFAULT_TIMEOUT)
    public void syncRestNetty() throws Exception {
        Netty4ClientHttpRequestFactory nettyFactory = new Netty4ClientHttpRequestFactory();
        nettyFactory.setSslContext(SslContextBuilder.forClient().build());
        RestTemplate restTemplate = new RestTemplate(nettyFactory);

        long start = System.currentTimeMillis();
        for (int i = 1; i < 2000; i++) {
            ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
            System.out.println("response = " + response.getStatusCode());
//            System.out.println("\n\n");
        }//8068
        System.out.println("use time : " + (System.currentTimeMillis() - start));
    }

    @Test//(timeout = DEFAULT_TIMEOUT)
    public void asyncRestNetty() throws Exception {
        Netty4ClientHttpRequestFactory nettyFactory = new Netty4ClientHttpRequestFactory();
        nettyFactory.setSslContext(SslContextBuilder.forClient().build());
        AsyncRestTemplate restTemplate = new AsyncRestTemplate(nettyFactory);
        ListenableFuture<ResponseEntity<String>> listenableFuture = restTemplate.getForEntity(URL, String.class);
        listenableFuture.addCallback(result -> System.out.println("result = " + result), Throwable::printStackTrace);
        while (!listenableFuture.isDone()) {
            Thread.sleep(DEFAULT_SLEEP_MILLIS);
        }
        System.out.println("the end");
    }

}
