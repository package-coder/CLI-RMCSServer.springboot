package com.rmcs.accountableforms.aftranasctionhistory;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.rmcs.accountableforms.afprefix.AFPrefix;
import com.rmcs.accountableforms.afrequesthistory.AFRequestHistory;
import com.rmcs.accountableforms.afrequestitem.AFRequestItem;
import com.rmcs.accountableforms.aftransactionitem.AFTransactionItem;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatus;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionType;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class AFTransactionHistory {
    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

////TODO    @NotNull
//    @OneToOne
//    private User requester;

    @OneToOne
    private AFRequestHistory requestHistory;

    @OneToOne
    private AFPrefix prefix;

    @OneToOne
    private AFTransactionStatus transactionStatus;

    @OneToOne
    private AFTransactionType transactionType;

    @NotNull
    private LocalDate transactionDate = LocalDate.now();


    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer controlNumber;

    @Valid
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AFTransactionItem> transactionItems;

    @Transient
    private List<AFRequestItem> approvedRequestItems;

    public AFTransactionHistory() {}

    public AFTransactionHistory(AFRequestHistory requestHistory,
                                AFPrefix prefix,
                                AFTransactionStatus transactionStatus,
                                AFTransactionType transactionType,
                                LocalDate transactionDate,
                                List<AFTransactionItem> transactionItems,
                                List<AFRequestItem> approvedRequestItems) {

        this.requestHistory = requestHistory;
        this.prefix = prefix;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.transactionItems = transactionItems;
        this.approvedRequestItems = approvedRequestItems;
    }

    public List<AFRequestItem> getApprovedRequestItems() {
        return approvedRequestItems;
    }

    public void setApprovedRequestItems(List<AFRequestItem> approvedRequestItems) {
        this.approvedRequestItems = approvedRequestItems;
    }

    public Integer getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(Integer controlNumber) {
        this.controlNumber = controlNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @JsonGetter("requestHistory")
    public UUID getRequestHistoryId() {
        if(requestHistory == null)
            return null;

        return  requestHistory.getId();
    }

    public AFRequestHistory getRequestHistory() {
        return requestHistory;
    }

    public void setRequestHistory(AFRequestHistory requestHistory) {
        this.requestHistory = requestHistory;
    }

    public AFPrefix getPrefix() {
        return prefix;
    }

    public void setPrefix(AFPrefix prefix) {
        this.prefix = prefix;
    }

    public AFTransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(AFTransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public AFTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(AFTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public List<AFTransactionItem> getTransactionItems() {
        return transactionItems;
    }

    public void setTransactionItems(List<AFTransactionItem> transactionItems) {
        this.transactionItems = transactionItems;
    }

    @Override
    public String toString() {
        return "AFTransactionHistory{" +
                "id=" + id +
                ", requestHistory=" + requestHistory +
                ", prefix=" + prefix +
                ", transactionStatus=" + transactionStatus +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                ", controlNumber=" + controlNumber +
                ", transactionItems=" + transactionItems +
                ", approvedRequestItems=" + approvedRequestItems +
                '}';
    }
}
