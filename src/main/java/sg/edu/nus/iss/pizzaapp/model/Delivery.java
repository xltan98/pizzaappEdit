package sg.edu.nus.iss.pizzaapp.model;

import java.io.Serializable;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Delivery implements Serializable{
    @NotNull(message="Please state your name")
    @Size(min=3, message="Your name cannot be less than 3 chars")
    @NotBlank(message="Please state your name")
    private String name;

    @NotNull(message="Please state your address")
    @NotBlank(message="Please state your address")
    private String address;

    @NotNull(message="Please state your name")
    @Pattern(regexp="^[0-9]{8,}$", message="Must be a valid phone number")
    @NotBlank(message="Please state your phone")
    private String phone;

    private boolean rush = false;
    private String comments;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public static Delivery create(JsonObject o){
        Delivery d = new Delivery();
        d.setName(o.getString("name"));
        d.setAddress(o.getString("address"));
        d.setPhone(o.getString("phone"));
        d.setRush(o.getBoolean("rush"));
        d.setComments(o.getString("comments"));
        return d;       
    }
}
