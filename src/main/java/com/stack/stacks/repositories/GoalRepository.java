package com.stack.stacks.repositories;

import com.stack.stacks.models.Goal;
import com.stack.stacks.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    Goal findByUser (User user);
}