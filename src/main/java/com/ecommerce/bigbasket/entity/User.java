package com.ecommerce.bigbasket.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Salman Gada
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Setter(AccessLevel.NONE)
//    @TableGenerator(
//            name = "tableGen",
//            table = "hibernate_sequences",
//            pkColumnName = "sequence_name",
//            valueColumnName = "next_val",
//            pkColumnValue = "default",
//            allocationSize = 100)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "EMAIL", unique = true)
    private String email;

    @NotNull
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "MIDDLE_NAME")
    private String middleName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;

    @Column(name = "IS_EXPIRED")
    private Boolean isExpired;

    @Column(name = "IS_LOCKED")
    private Boolean isLocked;

    @Column(name = "INVALID_ATTEMPT_COUNT")
    private Integer invalidAttemptCount;

    @UpdateTimestamp
    @Column(name = "PASSWORD_UPDATE_DATE")
    private LocalDateTime passwordUpdateDate;

    @CreationTimestamp
    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(name = "CREATED_BY")
    private String createdBy;

    @UpdateTimestamp
    @Column(name = "UPDATED_AT")
    private LocalDateTime lastUpdated;

    @LastModifiedBy
    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


}
