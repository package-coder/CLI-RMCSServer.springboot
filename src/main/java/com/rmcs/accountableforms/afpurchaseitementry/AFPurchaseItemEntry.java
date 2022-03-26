package com.rmcs.accountableforms.afpurchaseitementry;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.rmcs.accountableforms.afrequestitem.AFRequestItem;
import com.rmcs.accountableforms.aftransactionitem.AFTransactionItem;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table
public class AFPurchaseItemEntry {

    @Id
    @GeneratedValue
    private UUID id;

    @OneToOne
    @JoinColumn(nullable = false)
    private AFRequestItem requestItem;

    @OneToOne
    private AFTransactionItem transactionItem;

    @PositiveOrZero
    @NotNull
    private Integer startSeries;

    private Integer endSeries;

    @PositiveOrZero
    @NotNull
    private Integer startStub;

    private Integer endStub;

    private Character prefix;

    private Character suffix;

    public AFPurchaseItemEntry(AFRequestItem requestItem,
                               AFTransactionItem transactionItem,
                               Integer startSeries,
                               Integer endSeries,
                               Integer startStub,
                               Integer endStub,
                               Character prefix,
                               Character suffix) {

        this.requestItem = requestItem;
        this.transactionItem = transactionItem;
        this.startSeries = startSeries;
        this.endSeries = endSeries;
        this.startStub = startStub;
        this.endStub = endStub;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public AFPurchaseItemEntry() {}

    public AFPurchaseItemEntry(String id) {
        this.id = UUID.fromString(id);
    }

    @JsonGetter("transactionItem")
    public UUID getTransactionItemId(){
        if(transactionItem == null)
            return null;

        return transactionItem.getId();
    }

    public AFTransactionItem getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem(AFTransactionItem transactionItem) {
        this.transactionItem = transactionItem;
    }

    @JsonGetter("requestItem")
    public UUID getRequestItemId(){
        return  requestItem.getId();
    }

    public AFRequestItem getRequestItem() {
        return requestItem;
    }

    public void setRequestItem(AFRequestItem requestItem) {
        this.requestItem = requestItem;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getStartSeries() {
        return startSeries;
    }

    public void setStartSeries(Integer startSeries) {
        this.startSeries = startSeries;
    }

    public Integer getEndSeries() {
        return endSeries;
    }

    public void setEndSeries(Integer endSeries) {
        this.endSeries = endSeries;
    }

    public Integer getStartStub() {
        return startStub;
    }

    public void setStartStub(Integer startStub) {
        this.startStub = startStub;
    }

    public Integer getEndStub() {
        return endStub;
    }

    public void setEndStub(Integer endStub) {
        this.endStub = endStub;
    }

    public Character getPrefix() {
        return prefix;
    }

    public void setPrefix(Character prefix) {
        this.prefix = prefix;
    }

    public Character getSuffix() {
        return suffix;
    }

    public void setSuffix(Character suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "AFPurchaseRequestItemEntry{" +
                "id=" + id +
                ", startSeries=" + startSeries +
                ", endSeries=" + endSeries +
                ", startStub=" + startStub +
                ", endStub=" + endStub +
                ", prefix=" + prefix +
                ", suffix=" + suffix +
                '}';
    }
}
