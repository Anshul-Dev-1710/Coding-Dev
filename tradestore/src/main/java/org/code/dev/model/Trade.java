package org.code.dev.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Trades")
public class Trade {

    @Id
    private String Id;

    private int version;

    private String counterPartyId;

    private String bookId;

    private String maturityDate;

    private String createdDate;

    private String expired;

}
