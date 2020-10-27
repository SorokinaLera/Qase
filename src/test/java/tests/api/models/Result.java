package tests.api.models;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Result {
    @Expose
    int total;
    @Expose
    ArrayList<Entities> entities;
}
