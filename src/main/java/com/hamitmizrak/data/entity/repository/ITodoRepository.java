package com.hamitmizrak.data.entity.repository;

import com.hamitmizrak.data.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITodoRepository extends JpaRepository<TodoEntity,Long> {
}
