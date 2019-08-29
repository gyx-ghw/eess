package com.oracle.es;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.Iterator;

public class Test2 {
    public static void main(String[] args) throws Exception {
        //1、创建es客户端连接对象
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new
                        InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
//2、设置搜索条件
        SearchResponse searchResponse = client.prepareSearch("blog1")
                .setTypes("article").setQuery(QueryBuilders.matchAllQuery())
                .get();
//3、遍历搜索结果数据
        SearchHits hits = searchResponse.getHits(); // 获取命中次数，查询结果有多少对象
        System.out.println("查询结果有：" + hits.getTotalHits() + "条");
        Iterator<SearchHit> iterator = hits.iterator();
        while (iterator.hasNext()) {
            SearchHit searchHit = iterator.next(); // 每个查询对象
            System.out.println(searchHit.getSourceAsString()); // 获取字符串格式打印
            System.out.println("title:" + searchHit.getSource().get("title"));
        }
//4、释放资源
        client.close();
    }
}
