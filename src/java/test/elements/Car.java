package test.elements;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ejoseph
 */
@Entity
@Table(name = "cars") 
public class Car implements Serializable{
    
    @Id
    @Column(name = "car_code")
    private String carCode;
    
    @Column(name = "car_brand")
    private String car_brand;

    public Car() {
    }

    public Car(String carCode, String car_brand) {
        this.carCode = carCode;
        this.car_brand = car_brand;
    }

    public String getCarCode() {
        return carCode;
    }

    public void setCarCode(String carCode) {
        this.carCode = carCode;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    @Override
    public String toString() {
        return "Car{" + "carCode=" + carCode + ", car_brand=" + car_brand + '}';
    }
  
}
