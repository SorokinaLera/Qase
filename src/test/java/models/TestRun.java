package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestRun {
    String testRunTitle;
    String description;
    String plan;
    String environment;
    String milestone;
}
