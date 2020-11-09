package models.api;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;
import models.Project;

@Data
@Builder
public class ProjectResult {
    boolean status;
    @Expose
    Project result;
}
