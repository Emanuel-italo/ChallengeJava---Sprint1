package br.com.clyvo.project.dto;

import br.com.clyvo.project.enums.AlertType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlertResponseDTO {

    private Long id;
    private Long petId;
    private String petName;
    private AlertType type;
    private String message;
    private LocalDate dueDate;
    private Boolean sent;
    private LocalDateTime createdAt;
}

