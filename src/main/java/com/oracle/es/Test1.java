package com.oracle.es;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import java.net.InetAddress;
public class Test1 {
    public static void main(String[] args) throws Exception {
        //1、创建es客户端连接对象
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY).addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//2、创建文档内容
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("id",1)
                .field("title","elasticsearch是一个基于lucene的搜索服务")
                .field("content","ElasticSearch是一个基于Lucene的搜索服务器。" +
                        "它提供了一个分布式多用户能力的全文搜索引擎，基于RESTful web接口。" +
                        "Elasticsearch是用Java开发的，并作为Apache许可条款下的开放源码发布，" +
                        "是当前流行的企业级搜索引擎。设计用于云计算中，能够达到实时搜索，稳定，" +
                        "可靠，快速，安装使用方便。")
                .endObject();
//3、建立文档对象
        client.prepareIndex("blog1", "article", "1").setSource(builder).get();
//4、释放资源
        client.close();
    }
}