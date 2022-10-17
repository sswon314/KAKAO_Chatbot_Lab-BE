package com.example.goENC.repositories;

import com.example.goENC.models.SubjectiveResponse;
import com.example.goENC.models.compositeKey.SubjectiveResponseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectiveResponseRepository extends JpaRepository<SubjectiveResponse, SubjectiveResponseId> {
}
