package com.springjpa.twitter.entitiy;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "twitter_ids")
@ToString
public class TwitterIds {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String url;
    String name;

    @Column(name = "twitter_id")
    String twitter_id;

    @Column(name = "sent", columnDefinition = "BOOLEAN")
    Boolean sent;
}
