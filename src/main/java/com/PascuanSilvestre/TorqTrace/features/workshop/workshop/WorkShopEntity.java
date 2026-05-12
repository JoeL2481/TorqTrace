package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import com.PascuanSilvestre.TorqTrace.common.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopClient.WorkShopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStaff.WorkShopStaffEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workShopStock.WorkShopStockEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table (name = "workshop")
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class WorkShopEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name",nullable = false, length = 255)
    private String name;
    @Column(name="descripcion",nullable = false, columnDefinition = "TEXT")
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address", column = @Column(name = "workshop_address")),
            @AttributeOverride(name = "city", column = @Column(name = "workshop_city")),
            @AttributeOverride(name = "state", column = @Column(name = "workshop_state")),
            @AttributeOverride(name = "country", column = @Column(name = "workshop_country"))
    })
    private AddressInfo workshopAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "phoneNumber", column = @Column(name = "workshop_phone")),
            @AttributeOverride(name = "email", column = @Column(name = "workshop_email"))
    })
    private ContactInfo workshopContactInfo;

    @OneToMany(mappedBy = "workshop")
    private List<WorkShopStaffEntity> workers;

    @OneToMany(mappedBy = "workshop")
    private List<WorkShopClientEntity> clients;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    private List<WorkShopStockEntity> stockItems;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    private List<WorkOrderEntity> orderItems;


    @Column(name="status",nullable = false)
    private boolean status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;


}
