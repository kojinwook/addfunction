package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    @Query(value="select "
            + "distinct a.*, count(av.answer_id) as voter_count "
            + "from answer a "
            + "left outer join answer_voter av on av.answer_id=a.id "
            + "where a.question_id = :questionId "
            + "group by a.id, av.answer_id "
            + "order by voter_count desc, a.create_date desc "
            , countQuery = "select count(*) from answer"
            , nativeQuery = true)
    Page<Answer> findByQuestion(@Param("questionId") Integer questionId, Question question, Pageable pageable);
}
