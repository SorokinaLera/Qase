package tests.apiForProject.models;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestProjectAPI {
    @Expose
    String title;
    @Expose
    String code;
}
