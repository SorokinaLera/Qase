package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestCase {
    String title;
    String status;
    String description;
    String suite;
    String severity;
    String priority;
    String type;
    String milestone;
    String behavior;
    String automationStatus;
    String preConditions;
    String postConditions;
}
