package com.readme.novels.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Builder
@ToString
public class Episodes {
    @Id
    @GeneratedValue()
    private Long id;
    private Long novelId;
    private String title;
    private String content;
    private Long views;
    private Boolean free;
}
