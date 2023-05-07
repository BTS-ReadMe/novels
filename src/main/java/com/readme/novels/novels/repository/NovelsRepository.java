package com.readme.novels.novels.repository;

import com.readme.novels.novels.dto.NovelsSearchParamDto;
import com.readme.novels.novels.dto.PaginationDto;
import com.readme.novels.novels.model.Novels;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NovelsRepository {

    private final EntityManager em;

    public List<Novels> getNovels(NovelsSearchParamDto novelsSearchParamDto) {

        String jpql = "select n from Novels n ";
        Boolean isFirst = true;
        if (novelsSearchParamDto.getAuthor() != null) {
            if (isFirst) {
                jpql += "where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " n.author LIKE :author";
        }
        if (novelsSearchParamDto.getTitle() != null) {
            if (isFirst) {
                jpql += "where";
                isFirst = false;
            } else {
                jpql += " and";
            }
            jpql += " n.title LIKE :title";
        }

        jpql += " order by id";

        TypedQuery<Novels> query;
        int page = 0;
        if (novelsSearchParamDto.getPage() != null) {
            page = novelsSearchParamDto.getPage();
        }
        query = em.createQuery(jpql, Novels.class)
            .setFirstResult(page*10)
            .setMaxResults(10);


        if (novelsSearchParamDto.getAuthor() != null) {
            query.setParameter("author", "%"+novelsSearchParamDto.getAuthor()+"%");
        }
        if (novelsSearchParamDto.getTitle() != null) {
            query.setParameter("title", "%"+novelsSearchParamDto.getTitle()+"%");
        }

        return query.getResultList();
    }

    public PaginationDto getPagination(NovelsSearchParamDto novelsSearchParamDto) {
        String countJpql = "select count(n) from Novels n ";

        Boolean isFirst = true;
        if (novelsSearchParamDto.getAuthor() != null) {
            if (isFirst) {
                countJpql += "where";
                isFirst = false;
            } else {
                countJpql += " and";
            }
            countJpql += " n.author LIKE :author";
        }
        if (novelsSearchParamDto.getTitle() != null) {
            if (isFirst) {
                countJpql += "where";
                isFirst = false;
            } else {
                countJpql += " and";
            }
            countJpql += " n.title LIKE :title";
        }

        int page = 0;
        if (novelsSearchParamDto.getPage() != null) {
            page = novelsSearchParamDto.getPage();
        }

        TypedQuery<Long> countQuery = em.createQuery(countJpql, Long.class);

        if (novelsSearchParamDto.getAuthor() != null) {
            countQuery.setParameter("author", "%"+novelsSearchParamDto.getAuthor()+"%");
        }
        if (novelsSearchParamDto.getTitle() != null) {
            countQuery.setParameter("title", "%"+novelsSearchParamDto.getTitle()+"%");
        }

        Long count = countQuery.getSingleResult();

        int size = 10;
        long totalElements = count;
        int totalPage = count % size == 0 ? (int)(count / size) : (int)(count / size + 1);


        PaginationDto paginationDto = PaginationDto.builder()
            .page(page)
            .size(size)
            .totalElements(totalElements)
            .totalPage(totalPage)
            .build();


        return paginationDto;
    }


}
