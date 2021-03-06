package com.rmcs.accountableforms.afrequestitem;



import com.fasterxml.jackson.annotation.JsonGetter;
import com.rmcs.accountableforms.afpurchaseitementry.AFPurchaseItemEntry;
import com.rmcs.accountableforms.afrequesthistory.AFRequestHistory;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatus;
import com.rmcs.accountableforms.aftype.AFType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
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

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @Valid
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AFPurchaseItemEntry itemEntry;

    public AFRequestItem(AFRequestHistory requestHistory, AFType type, AFTransactionStatus status, Integer quantity, AFPurchaseItemEntry itemEntry) {
        this.requestHistory = requestHistory;
        this.type = type;
        this.status = status;
        this.quantity = quantity;
        this.itemEntry = itemEntry;
    }

    public AFRequestItem(String id) {
        this.id = UUID.fromString(id);
    }

    public AFPurchaseItemEntry getItemEntry() {
        return itemEntry;
    }

    public void setItemEntry(AFPurchaseItemEntry itemEntry) {
        this.itemEntry = itemEntry;
    }

    public AFRequestItem() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AFRequestHistory getRequestHistory() {
        return requestHistory;
    }

    @JsonGetter("requestHistory")
    public UUID getRequestHistoryID(){
        return  requestHistory.getId();
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
                ", type=" + type +
                ", status=" + status +
                ", quantity=" + quantity +
                ", itemEntry=" + itemEntry +
                '}';
    }
}
