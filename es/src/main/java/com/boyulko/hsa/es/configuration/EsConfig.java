package com.boyulko.hsa.es.configuration;

import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class EsConfig {


  @Bean
  public RestHighLevelClient elasticsearchClient() {
    RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(
        new HttpHost("elasticsearch", 9200, "http")

    ).setDefaultHeaders(new Header[]{
        new BasicHeader("X-Elastic-Product", "Elasticsearch")})
    );
    return client;
  }


}
