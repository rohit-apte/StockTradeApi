package com.hackerrank.stocktrade.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "User")
public class UserDto {

    @Id
    private Long id;
    @Column
    private String name;
}
