package com.study.webflux.reactivestudy.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PlayerDto {

    private String name;
    private String nickname;
    private Integer age;

}
