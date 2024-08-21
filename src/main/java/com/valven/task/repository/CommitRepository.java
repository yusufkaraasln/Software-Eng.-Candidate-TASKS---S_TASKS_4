package com.valven.task.repository;

import com.valven.task.entity.Commit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommitRepository extends JpaRepository<Commit, Long> {
    Page<Commit> findByAuthorName(String authorName, Pageable pageable);
    Commit findBySha(String sha);
}
