package com.example.demo.domains.records;
import java.time.LocalDate;
import java.util.Objects;
public record TaskRecord (Long task_id,
String task_name,
String task_status,
LocalDate task_start_date,
LocalDate task_end_date,
Long project_id,
Long coordinator_id){

    // Long task_id,
    // String task_name,
    // String task_status,
    // LocalDate task_start_date,
    // LocalDate task_end_date,
    // Long project_id,
    // Long coordinator_id








    // Long getTask_id();
    // String getTask_name();
    // String getTask_status();
    // LocalDate getTask_start_date();
    // LocalDate getTask_end_date();
    // Long getProject_id();
    // Long getCoordinator_id();

    // Long setTask_id();
    // String setTask_name();
    // String setTask_status();
    // LocalDate setTask_start_date();
    // LocalDate setTask_end_date();
    // Long setProject_id();
    // Long setCoordinator_id();
}