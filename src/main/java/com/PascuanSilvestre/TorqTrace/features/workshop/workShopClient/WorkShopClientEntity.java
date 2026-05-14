package com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient;
import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import com.PascuanSilvestre.TorqTrace.features.user.user.UserEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshop.WorkShopEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="client_workshop")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class WorkShopClientEntity  extends AuditableBase {


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="workshop_id", nullable = false)

    private WorkShopEntity workshop;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable = false)
    private UserEntity user;

    @Column(name="description", columnDefinition = "TEXT" )
    private String description;


}
