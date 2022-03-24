package com.rmcs.accountableforms.afrequestitem;



import com.rmcs.accountableforms.afrequesthistory.AFRequestHistory;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatus;
import com.rmcs.accountableforms.aftype.AFType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table
public class AFRequestItem {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private AFRequestHistory requestHistory;

    @OneToOne
    @NotNull
    private AFType type;

    @OneToOne
    private AFTransactionStatus status;

    @Min(value = 1)
    private Integer quantity;

    public AFRequestItem(AFType type,
                         AFTransactionStatus status,
                         Integer quantity) {

        this.type = type;
        this.status = status;
        this.quantity = quantity;
    }

    public AFRequestItem() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getRequestHistory() {
        return requestHistory.getId();
    }

    public void setRequestHistory(AFRequestHistory requestHistory) {
        this.requestHistory = requestHistory;
    }

    public AFType getType() {
        return type;
    }

    public void setType(AFType afType) {
        this.type = afType;
    }

    public AFTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(AFTransactionStatus status) {
        this.status = status;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "AFRequestItem{" +
                "id=" + id +
                ", requestHistory=" + requestHistory +
                ", afType=" + type +
                ", status=" + status +
                ", quantity=" + quantity +
                '}';
    }
}
