package models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class TestPlanRequestResult {
    boolean status;
    @Expose
    TestPlanRequest result;
}
