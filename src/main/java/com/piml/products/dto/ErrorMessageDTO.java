package com.piml.products.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder @Getter @Setter
public class ErrorMessageDTO {
    private String message;
}
