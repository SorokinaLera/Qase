package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestCaseJson {
    @Expose
    @SerializedName("case_id")
    Integer caseId;
    @Expose
    @SerializedName("assignee_id")
    Integer assigneeId;
}
