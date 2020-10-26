package tests.api.models;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Suite {
    @Expose
    boolean status;
    @Expose
    SuiteDetails result;
}
