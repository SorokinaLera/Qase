package tests.api.models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Suites {
    @Expose
    boolean status;
    @Expose
    Result result;
}
