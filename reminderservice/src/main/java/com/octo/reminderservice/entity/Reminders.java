package com.octo.reminderservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reminders implements Serializable {

    @Id
    private UUID _id = UUID.randomUUID();

    @NotEmpty(message = "cannot be empty or null.")
    @Size(min = 5, message = "must be at least of length 5.")
    private String name;

    @NotEmpty(message = "cannot be empty or null.")
    private String eventType;

    @Min(value = 1,message = "cannot be less than 1.")
    @Max(value = 31,message = "cannot be greater than 31.")
    private short eventDay;

    @Min(value = 1,message = "cannot be less than 1.")
    @Max(value = 12,message = "cannot be greater than 12.")
    private short eventMonth;
}
