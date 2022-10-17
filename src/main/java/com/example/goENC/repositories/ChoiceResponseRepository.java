package com.example.goENC.repositories;

import com.example.goENC.models.ChoiceResponse;
import com.example.goENC.models.compositeKey.ChoiceResponseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChoiceResponseRepository extends JpaRepository<ChoiceResponse, ChoiceResponseId> {
}
