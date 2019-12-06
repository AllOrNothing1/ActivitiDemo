package com.workflow.demo.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtil {
    private static Logger logger = LoggerFactory.getLogger(getClass());

    // 字符编码
    public static final String CHARSET_UTF_8 = "utf-8";
    // HTTP内容类型
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";
    // HTTP内容类型，相当于form表单的形式，提交数据
    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
    // HTTP内容类型，相当于json的形式，提交数据
    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
    // 连接管理器
    private static PoolingHttpClientConnectionManager pool;
    // 请求配置
    private static RequestConfig requestConfig;

    static {
        try{
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(builder.build());
            //配置同时支持 HTTP 和 HTTPS
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", factory).build();
            //初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(registry);
            //设置最大连接数
            pool.setMaxTotal(200);
            //设置最大路由
            pool.setDefaultMaxPerRoute(300);
            //根据默认超时限制初始化requestConfig
            int socketTimeout = 100000;
            int connectTimeout = 100000;
            int connectionRequestTimeout = 100000;
            requestConfig = RequestConfig.custom()
                    .setConnectTimeout(connectTimeout)
                    .setSocketTimeout(socketTimeout)
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .build();
        }catch (Exception e){
            logger.error("连接管理器、请求配置--异常：" + e.getMessage());
        }
    }


    /**
     * 发送POST请求
     * @param url
     * @param paramsJson
     * @return
     */
    public static String sendHttpPostJson(String url,String paramsJson){
        HttpPost httpPost = new HttpPost(url);
        try {
            //设置参数
            if (paramsJson != null && paramsJson.trim().length() > 0 ){
                StringEntity stringEntity = new StringEntity(paramsJson,CHARSET_UTF_8);
                stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
                httpPost.setEntity(stringEntity);
            }
        } catch (Exception e) {
            logger.error("配置Post请求参数异常" + e.getMessage());
        }
        String response = sendHttpPost(httpPost);
        logger.info(response);
        return response;
    }

    /**
     * 发送Post请求
     * @param httpPost
     * @return
     */
    private static String sendHttpPost(HttpPost httpPost){
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        // 响应内容
        String responseContent = null;
        try{
            //创建默认的httpClient实例
            httpClient = getHttpClient();
            //配置请求信息
            httpPost.setConfig(requestConfig);
            //执行请求
            response = httpClient.execute(httpPost);
            //得到的响应实例
            HttpEntity entity = response.getEntity();
            logger.info("sendHttpPost 返回状态：" + response.getStatusLine().getStatusCode());

            responseContent = EntityUtils.toString(entity,CHARSET_UTF_8);
            EntityUtils.consume(entity);

            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){

            } else {
                logger.error("调用Post接口异常：" + httpPost.getRequestLine());
                logger.error("返回结果：" + responseContent);
                responseContent = "";
            }
        } catch (Exception e) {
            logger.error("sendHttpHost Exception：" + e.getMessage());
        } finally {
            try {
                if (response != null){
                    response.close();
                }
            } catch (Exception e) {
                logger.error("sendHttpPost IOException：" + e.getMessage());
            }
        }
        logger.info("sendHttpPost：返回结果："+ responseContent);
        return responseContent;
    }

    /**
     * 创建默认的httpClient实例
     * @return
     */
    public static CloseableHttpClient getHttpClient(){
        CloseableHttpClient httpClient = HttpClients.custom()
                //设置连接池管理
                .setConnectionManager(pool)
                //设置请求配置
                .setDefaultRequestConfig(requestConfig)
                //设置重试次数
                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
                .build();
        return httpClient;
    }

}
