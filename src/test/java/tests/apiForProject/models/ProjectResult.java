package tests.apiForProject.models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectResult {
    @Expose
    String title;
    @Expose
    String code;
}
