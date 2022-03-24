package com.rmcs.accountableforms.afrequesthistory;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.rmcs.account.user.User;
import com.rmcs.accountableforms.afprefix.AFPrefix;
import com.rmcs.accountableforms.afrequestitem.AFRequestItem;
import com.rmcs.accountableforms.aftransactionstatus.AFTransactionStatus;
import com.rmcs.accountableforms.aftransactiontype.AFTransactionType;
import com.sun.istack.NotNull;
import org.hibernate.annotations.Type;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table
public class AFRequestHistory {

    @Id
    @GeneratedValue
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;

    @OneToOne
//TODO    @NotNull
    private User requester;

    @OneToOne
    private AFPrefix prefix;

    @OneToOne
    private AFTransactionStatus transactionStatus;

    @OneToOne
    @NotNull
    private AFTransactionType transactionType;

    @NotNull
    private LocalDate requestDate = LocalDate.now();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AFRequestItem> requestItems;

    public AFRequestHistory(
            User requester,
            AFPrefix prefix,
            AFTransactionStatus transactionStatus,
            AFTransactionType transactionType,
            LocalDate requestDate,
            List<AFRequestItem> requestItems) {

        this.requester = requester;
        this.prefix = prefix;
        this.transactionStatus = transactionStatus;
        this.transactionType = transactionType;
        this.requestDate = requestDate;
        this.requestItems = requestItems;
    }

    public AFRequestHistory() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }


    public List<AFRequestItem> getRequestItems() {
        return requestItems;
    }

    public void setRequestItems(List<AFRequestItem> requestItems) {
        this.requestItems = requestItems;
    }

    @Override
    public String toString() {
        return "AFRequestHistory{" +
                "id=" + id +
                ", requester=" + requester +
                ", prefix=" + prefix +
                ", transactionStatus=" + transactionStatus +
                ", transactionType=" + transactionType +
                ", requestDate=" + requestDate +
                ", requestItems=" + requestItems +
                '}';
    }
}
