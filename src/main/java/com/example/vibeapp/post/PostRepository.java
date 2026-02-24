package com.example.vibeapp.post;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PostRepository {

    @PersistenceContext
    private EntityManager em;

    /**
     * 모든 게시글 조회 (페이징 적용)
     * JPQL을 사용하여 특정 범위의 데이터를 조회합니다.
     */
    public List<Post> findAll(int offset, int size) {
        return em.createQuery("SELECT p FROM Post p ORDER BY p.no DESC", Post.class)
                .setFirstResult(offset)
                .setMaxResults(size)
                .getResultList();
    }

    /**
     * 전체 게시글 수 조회
     * JPQL count 함수를 사용하여 총 개수를 구합니다.
     */
    public long count() {
        return em.createQuery("SELECT COUNT(p) FROM Post p", Long.class)
                .getSingleResult();
    }

    /**
     * PK를 사용하여 게시글 상세 조회
     * em.find()는 영속성 컨텍스트에서 엔티티를 찾습니다.
     */
    public Post findById(Long no) {
        return em.find(Post.class, no);
    }

    /**
     * 게시글 저장 (Insert)
     * em.persist()를 통해 새로운 엔티티를 영속화합니다.
     */
    public void save(Post post) {
        em.persist(post);
    }

    /**
     * 게시글 수정 (Update)
     * JPA는 트랜잭션 종료 시 변경 감지(Dirty Checking)를 통해 자동으로 Update SQL을 실행하지만,
     * 준영속 상태의 엔티티일 경우 em.merge()를 사용하여 영속 상태로 병합할 수 있습니다.
     */
    public void update(Post post) {
        em.merge(post);
    }

    /**
     * 게시글 삭제 (Delete)
     * em.remove()를 사용하며, 먼저 엔티티를 조회해야 합니다.
     */
    public void deleteById(Long no) {
        Post post = findById(no);
        if (post != null) {
            em.remove(post);
        }
    }

    /**
     * 조회수 증가
     * 엔티티의 상태를 변경하면 JPA 변경 감지에 의해 DB에 반영됩니다.
     */
    public void increaseViews(Long no) {
        Post post = findById(no);
        if (post != null) {
            post.setViews(post.getViews() + 1);
        }
    }
}
