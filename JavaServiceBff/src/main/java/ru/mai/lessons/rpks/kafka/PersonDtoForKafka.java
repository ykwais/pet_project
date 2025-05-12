package ru.mai.lessons.rpks.kafka;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "DTO для передачи данных о персоне в Kafka")
public record PersonDtoForKafka(
        @Schema(description = "Имя человека", example = "John")
        String name,

        @Schema(description = "Возраст", example = "30")
        int age,

        @Schema(description = "Пол (M/F)", example = "M")
        String sex
) {}