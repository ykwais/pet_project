package ru.mai.lessons.rpks.kafka;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Kafka API", description = "API для работы с Kafka: отправка и получение сообщений")
public interface KafkaApi {

    @Operation(
            summary = "Отправить сообщение в Kafka",
            description = "Отправляет DTO объект в указанный топик Kafka в формате JSON",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Сообщение успешно отправлено",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject("Sent to topic 'user-topic': {\"name\":\"John\",\"age\":30,\"sex\":\"M\"}")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Неверный формат сообщения",
                            content = @Content(
                                    mediaType = "text/plain",
                                    examples = @ExampleObject("Invalid message format")
                            )
                    )
            }
    )
    @PostMapping("/send")
    ResponseEntity<String> sendMessage(
            @Parameter(
                    description = "DTO объект для отправки в Kafka",
                    required = true,
                    content = @Content(
                            schema = @Schema(implementation = PersonDtoForKafka.class),
                            examples = @ExampleObject(value = "{\"name\":\"John\",\"age\":30,\"sex\":\"M\"}")
                    )
            )
            @RequestBody PersonDtoForKafka userDto);

    @Operation(
            summary = "Получить последние сообщения",
            description = "Возвращает 10 последних полученных сообщений из Kafka",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Список сообщений",
                            content = @Content(
                                    mediaType = "application/json",
                                    examples = @ExampleObject("[\"{\\\"name\\\":\\\"John\\\",\\\"age\\\":30,\\\"sex\\\":\\\"M\\\"}\"]")
                            )
                    )
            }
    )
    @GetMapping("/messages")
    ResponseEntity<List<String>> getMessages();
}