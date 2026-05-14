package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType;
import com.PascuanSilvestre.TorqTrace.common.AuditableBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name="work_order_type")
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class WorkOrderTypeEntity extends AuditableBase {


    @Column(name="name",nullable = false,length = 50)
    private String name;


}
