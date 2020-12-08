package models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Data
@Log4j2
public class TestPlanResponse <T>{
    @Expose
    int id;
    @Expose
    @SerializedName("title")
    String testPlanTitle;
    @Expose
    String description;
    @Expose
    List<T> cases;
}
