package org.launchcode.models;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class CarDetails extends AbstractEntity{

    @Size(max = 100,message = "Description too long")
    private String description;

    @NotBlank
    @Email(message = "Invalid EMAIL! Try again.")
    private String contactEmail;

    public CarDetails(@Size(max = 100, message = "Description too long") String description, @NotBlank @Email(message = "Invalid EMAIL! Try again.") String contactEmail) {
        this.description = description;
        this.contactEmail = contactEmail;
    }

    public CarDetails() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }
}
