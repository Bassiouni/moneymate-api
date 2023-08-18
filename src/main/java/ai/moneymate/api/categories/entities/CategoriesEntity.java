package ai.moneymate.api.categories.entities;

import ai.moneymate.api.payments.entities.PaymentsEntity;
import ai.moneymate.api.users.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Categories")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double assigned_budget;

    @Column(nullable = false)
    private CategoryType type;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Set<PaymentsEntity> payment = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "user_id"))
    private UserEntity user;
}
