package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query(value = "select a from Answer a join a.question q where q.id =:questionId order by SIZE(a.voter) desc")
    Page<Answer> findByQuestion(@Param("questionId") Integer questionId, Pageable pageable);

    Page<Answer> findByQuestion(Question question, Pageable pageable);
}
