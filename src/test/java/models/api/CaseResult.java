package models.api;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import models.TestCase;

@Builder
@Data
public class CaseResult {
    @Expose
    int id;
    @Expose
    int position;
    @Expose
    String title;
    @Expose
    TestCase result;
}
