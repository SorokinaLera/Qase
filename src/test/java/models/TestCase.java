package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
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
    @Expose
    int id;
    @SerializedName("title")
    @Expose
    String caseName;

}
