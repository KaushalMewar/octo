package com.octo.reminderservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Reminders {

    @Id
    private UUID _id = UUID.randomUUID();
}
