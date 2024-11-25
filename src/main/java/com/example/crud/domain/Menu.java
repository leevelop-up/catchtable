package com.example.crud.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "menu")
public class Menu extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop shop;
    private String name;
    private long price;
    private String description;

    public String toString() {
        return "Member{" +
                "id=" + id +
                ", shop_id='" + shop + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", description=" + description;
    }
}
