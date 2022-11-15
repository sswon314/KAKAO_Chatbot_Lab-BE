package com.example.goENC.models.compositeKey;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectiveResponseId implements Serializable {
    private Long questionId;
    private Long responseId;
}
