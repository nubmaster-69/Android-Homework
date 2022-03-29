package lab6.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Shoe implements Serializable {
    private int imageResource;
    private String brand;
    private String name;
    private double price;
    private double discount;
    private String desc;
}