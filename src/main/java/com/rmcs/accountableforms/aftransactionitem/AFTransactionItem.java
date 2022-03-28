package com.rmcs.accountableforms.aftransactionitem;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rmcs.accountableforms.afpurchaseitementry.AFPurchaseItemEntry;
import com.rmcs.accountableforms.afrequestitem.AFRequestItem;
import com.rmcs.accountableforms.aftranasctionhistory.AFTransactionHistory;
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
public class AFTransactionItem {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToOne
    private AFRequestItem requestItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    private AFTransactionHistory transactionHistory;

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

    public AFTransactionItem() {}

    public AFTransactionItem(AFRequestItem requestItem,
                             AFTransactionHistory transactionHistory,
                             AFType type,
                             AFTransactionStatus status,
                             Integer quantity,
                             AFPurchaseItemEntry itemEntry) {

        this.requestItem = requestItem;
        this.transactionHistory = transactionHistory;
        this.type = type;
        this.status = status;
        this.quantity = quantity;
        this.itemEntry = itemEntry;
    }

    public AFTransactionItem(String id) {
        this.id = UUID.fromString(id);
    }

    public AFPurchaseItemEntry getItemEntry() {
        return itemEntry;
    }

    public void setItemEntry(AFPurchaseItemEntry itemEntry) {
        this.itemEntry = itemEntry;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonGetter("requestItem")
    public UUID getRequestItemId(){
        if(requestItem == null)
            return null;

        return requestItem.getId();
    }

    public AFRequestItem getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(AFRequestItem requestItem) {
        this.requestItem = requestItem;
    }

    @JsonGetter("transactionHistory")
    public UUID getTransactionHistoryId(){
        return transactionHistory.getId();
    }

    public AFTransactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(AFTransactionHistory transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public AFType getType() {
        return type;
    }

    public void setType(AFType type) {
        this.type = type;
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
        return "AFTransactionItem{" +
                "id=" + id +
                ", requestItem=" + requestItem +
                ", transactionHistory=" + transactionHistory +
                ", type=" + type +
                ", status=" + status +
                ", quantity=" + quantity +
                ", itemEntry=" + itemEntry +
                '}';
    }
}
