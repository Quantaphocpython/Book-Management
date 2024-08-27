package com.devteria.api.notification.controller;

import com.devteria.api.notification.dto.request.SendEmailRequest;
import com.devteria.api.notification.dto.response.ApiResponse;
import com.devteria.api.notification.dto.response.EmailResponse;
import com.devteria.api.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailController {
    EmailService emailService;

    @PostMapping("/email/send")
    ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest request) {
        var res = ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(request))
                .build();
        log.info("Response: {}", res);
        return res;
    }

    @KafkaListener(topics = "onboard-successful")
    public void listen(String message) { // khi co message toi no se map message vao bien message
        log.info("Message received: {}", message);
    }
}
