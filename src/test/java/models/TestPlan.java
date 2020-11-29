package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestPlan {
    @Expose
    @SerializedName("title")
    String testPlanTitle;
    @Expose
    String description;
}
