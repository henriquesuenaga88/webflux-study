package com.study.webflux.reactivestudy.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
@Builder
@Getter
@ToString
public class Player {

    @Id
    private UUID id;
    private String name;
    private String nickname;
    private Integer age;
}
