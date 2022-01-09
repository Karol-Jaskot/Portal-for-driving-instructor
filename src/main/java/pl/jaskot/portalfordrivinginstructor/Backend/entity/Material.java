package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.format.DateTimeFormatter;

@Data
@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String linkToFile;
    private String description;
    private boolean isPublic = false;
}
