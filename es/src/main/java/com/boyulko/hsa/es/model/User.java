package com.boyulko.hsa.es.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "users")
@Setting(settingPath = "es-config/analyzer.json")
@Getter
@Setter
@NoArgsConstructor
public class User {

  @Id
  private String id;

  private String firstName;

  @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
  private String last;


}
