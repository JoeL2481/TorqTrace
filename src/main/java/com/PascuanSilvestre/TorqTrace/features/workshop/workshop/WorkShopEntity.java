package com.PascuanSilvestre.TorqTrace.features.workshop.workshop;

import com.PascuanSilvestre.TorqTrace.common.utils.AddressInfo;
import com.PascuanSilvestre.TorqTrace.common.aspects.AuditableBase;
import com.PascuanSilvestre.TorqTrace.common.utils.ContactInfo;
import com.PascuanSilvestre.TorqTrace.features.workOrder.workOrder.WorkOrderEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopClient.WorkshopClientEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStaff.WorkshopStaffEntity;
import com.PascuanSilvestre.TorqTrace.features.workshop.workshopStock.WorkshopStockEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Table (name = "workshop")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class WorkShopEntity  extends AuditableBase {



    @Column(name="name",unique = true,nullable = false, length = 255)
    private String name;
    @Column(name="descripcion",nullable = false, columnDefinition = "TEXT")
    private String description;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "workshop_street")),
            @AttributeOverride(name = "city", column = @Column(name = "workshop_city")),
            @AttributeOverride(name = "state", column = @Column(name = "workshop_state")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "workshop_zip_code")),
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
    private List<WorkshopStaffEntity> workers;

    @OneToMany(mappedBy = "workshop")
    private List<WorkshopClientEntity> clients;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    private List<WorkshopStockEntity> stockItems;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.LAZY)
    private List<WorkOrderEntity> orderItems;


    @Column(name="status",nullable = false)
    private boolean status;



}
