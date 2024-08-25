package com.devteria.api.notification.service;

import com.devteria.api.notification.dto.request.EmailRequest;
import com.devteria.api.notification.dto.request.SendEmailRequest;
import com.devteria.api.notification.dto.request.Sender;
import com.devteria.api.notification.dto.response.EmailResponse;
import com.devteria.api.notification.exception.AppException;
import com.devteria.api.notification.exception.ErrorCode;
import com.devteria.api.notification.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class EmailService {
    EmailClient emailClient;

    @NonFinal
    String apiKey = "brevo-key"; // de tam thoi do de brevo key truc tiep vo day ko duoc day len github
    //

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Kaitou")
                        .email("kaitoukido0204@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .htmlContent(request.getHtmlContent())
                .subject(request.getSubject())
                .build();

        try {
            return emailClient.sendEmail(apiKey, emailRequest);
        } catch (FeignException exception) {
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
