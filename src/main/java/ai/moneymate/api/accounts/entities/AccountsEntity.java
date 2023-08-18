package ai.moneymate.api.accounts.entities;

import ai.moneymate.api.payments.entities.PaymentsEntity;
import ai.moneymate.api.users.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String account_number;

    @Column(nullable = false)
    private boolean is_default;

    @Column(nullable = false)
    private int available_balance;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "user_id")
    )
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private Set<PaymentsEntity> payments = new HashSet<>();
}
