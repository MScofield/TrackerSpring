package com.theironyard.services;

import com.theironyard.entities.Thought;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by scofieldservices on 1/2/17.
 */
public interface ThoughtRepository extends CrudRepository<Thought, Integer>{
    List<Thought> findAllByOrderByCategory();
    List<Thought> findByCategory(String subject);

//    Thought findFirstByCategory(String category);
//    int countByCategory(String Category);
//    List<Thought> findByCategoryOrderByDateTimeDesc();

    @Query ("SELECT t FROM Thought t WHERE t.description LIKE ?1%")
    List<Thought> findByDescription(String description);
}
