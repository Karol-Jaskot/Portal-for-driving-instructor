package pl.jaskot.portalfordrivinginstructor.Backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@Entity
@Builder @NoArgsConstructor @AllArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private long authorId;
    private String message;
    private LocalDateTime createTime;
    private boolean isPublic = false;

    public String getFullDate(){
        return createTime.format(DateTimeFormatter.ofPattern("HH:mm   dd.MM.yyyy"));
    }
}