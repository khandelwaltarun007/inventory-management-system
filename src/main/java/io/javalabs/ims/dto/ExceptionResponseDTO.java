package io.javalabs.ims.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponseDTO {

	private LocalDateTime timestamp;
	private String message;
	private String details;

}