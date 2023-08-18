package ai.moneymate.api.payments.entities;

import ai.moneymate.api.accounts.entities.AccountsEntity;
import ai.moneymate.api.categories.entities.CategoriesEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "Payments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "account_id"))
    private AccountsEntity account;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "category_id"))
    private CategoriesEntity category;
}
