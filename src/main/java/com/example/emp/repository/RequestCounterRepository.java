package com.example.emp.repository;

import com.example.emp.model.db.RequestCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCounterRepository extends JpaRepository<RequestCounter, String> {
}
