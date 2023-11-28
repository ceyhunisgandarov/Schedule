package az.orient.homework.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;

import java.util.Date;

@Entity
@Getter
@Setter
@DynamicInsert
@Table(name = "user_tbl")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String token;

    @ColumnDefault(value = "1")
    private Integer active;
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Date createdAt;

}
