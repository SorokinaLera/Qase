package tests.apiForProject.models;

import com.google.gson.annotations.Expose;
import lombok.Data;
import tests.api.models.Result;

@Data
public class Projects {
    @Expose
    boolean status;
    @Expose
    Result result;
}
