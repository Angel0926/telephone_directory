package org.example.controller;

import org.example.service.ContactService;
import org.example.service.PdfReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ContactService contactService;
    private final PdfReportService pdfReportService;

    public ReportController(ContactService contactService, PdfReportService pdfReportService) {
        this.contactService = contactService;
        this.pdfReportService = pdfReportService;
    }

    @GetMapping("/contacts/pdf")
    public ResponseEntity<byte[]> generateContactListPdf() throws IOException {
        List<String> contacts = contactService.getAllContacts()
                .stream()
                .map(contact -> contact.getName() + " - " + contact.getPhoneNumber())
                .toList();

        byte[] pdfBytes = pdfReportService.generateContactListPdf(contacts);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=contacts.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
