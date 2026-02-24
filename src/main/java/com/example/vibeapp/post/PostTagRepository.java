package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostTagRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 태그 정보 저장
     */
    public void save(PostTag postTag) {
        em.persist(postTag);
    }

    /**
     * 해당 게시글의 모든 태그 삭제
     */
    public void deleteByPostNo(Long postNo) {
        em.createQuery("DELETE FROM PostTag pt WHERE pt.postNo = :postNo")
                .setParameter("postNo", postNo)
                .executeUpdate();
    }

    /**
     * 해당 게시글의 태그 목록 조회
     */
    public List<PostTag> findByPostNo(Long postNo) {
        return em.createQuery("SELECT pt FROM PostTag pt WHERE pt.postNo = :postNo", PostTag.class)
                .setParameter("postNo", postNo)
                .getResultList();
    }
}
