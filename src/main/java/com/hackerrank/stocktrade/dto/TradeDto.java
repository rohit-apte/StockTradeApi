package com.hackerrank.stocktrade.dto;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Trade")
public class TradeDto {
    @Id
    private Long id;
    @Column
    private String type;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")

    private UserDto user;
    @Column
    private String symbol;
    @Column
    private Integer shares;
    @Column
    private Float price;
    @Column(updatable = false)
    private Timestamp timestamp;
}
