package com.PascuanSilvestre.TorqTrace.features.workOrder.workOrderType;
import com.PascuanSilvestre.TorqTrace.common.utils.AuditableBase;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="work_order_type")
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class WorkOrderTypeEntity extends AuditableBase {


    @Column(name="name",nullable = false,length = 50)
    private String name;


}
