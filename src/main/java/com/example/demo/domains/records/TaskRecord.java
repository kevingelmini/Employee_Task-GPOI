package com.example.demo.domains.records;

import java.time.LocalDate;
//DTO = DATA TRANSFER OBJECT --> DTOs or Data Transfer Objects are objects that carry data between processes in order to reduce the number and information of methods calls
public record TaskRecord(Long task_id,
        String task_name,
        String task_status,
        LocalDate task_start_date,
        LocalDate task_end_date,
        Long project_id,
        Long coordinator_id) {
}