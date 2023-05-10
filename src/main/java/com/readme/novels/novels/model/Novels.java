package com.readme.novels.novels.model;

import com.readme.novels.utils.BaseTimeEntity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Novels extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String author;
    private Date startDate;
    private String serializationDay;
    private String serializationStatus;
    private String thumbnail;
    private String authorComment;
    private Integer grade;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Getter
    public enum Genre {
        판타지,
        현판,
        무협,
        드라마,
        로판,
        로맨스;
    }

}
