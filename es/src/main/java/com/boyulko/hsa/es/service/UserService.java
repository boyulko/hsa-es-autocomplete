package com.boyulko.hsa.es.service;

import com.boyulko.hsa.es.model.User;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private ElasticsearchOperations elasticsearchOperations;

  public List<User> listAll() {
    Query query = new StringQuery("{\"match_all\": {}}");
    SearchHits<User> result = elasticsearchOperations.search(query, User.class);
    return result.stream().map(SearchHit::getContent).collect(Collectors.toList());
  }

  public Set<User> saveAll(List<User> users) {
    if (listAll().isEmpty()) {
      return users.stream().map(elasticsearchOperations::save).collect(Collectors.toSet());
    }
    return Set.of();
  }

  public User save(User user) {
    return elasticsearchOperations.save(user);
  }

  public void deleteAll() {
    Query query = new StringQuery("{\"match_all\": {}}");
    elasticsearchOperations.delete(query, User.class);
  }


  public List<User> search(String keyword) {

    Query autocompleteQuery = new StringQuery(

        " {\"multi_match\": { "
            + " \"query\": \" " + keyword + " \", "
            + " \"fuzziness\": 2,"

            + "         \"type\": \"bool_prefix\", "
            + "   \"fields\": [ "
            + "\"last\""
            + ", "
            + "   \"last._2gram\", "
            + " \"last._3gram\" "
            + "]"
            + "}}");

    SearchHits<User> result = this.elasticsearchOperations.search(autocompleteQuery, User.class);
    return result.stream().map(SearchHit::getContent).collect(Collectors.toList());

  }


}
