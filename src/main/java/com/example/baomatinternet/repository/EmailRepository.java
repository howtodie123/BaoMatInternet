package com.example.baomatinternet.repository;

import com.example.baomatinternet.entity.email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<email, Integer> {
}
