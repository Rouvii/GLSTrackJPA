package org.example.persistence.entities;


import jakarta.persistence.*;
import lombok.*;
import org.example.HibernateConfig;
import org.example.persistence.entities.Package;
import org.example.persistence.entities.Package.DeliveryStatus;
import java.util.Date;

/**
 * @author Rouvi
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity

@Table(name = "package")
public class Package {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "trackingnmb",unique = true)
    private String trackingNumber;
    @Column(name = "sendername")
    private String senderName;

    private String receiverName;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    public enum DeliveryStatus {
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;

    @PreUpdate
    @PrePersist
    protected void onUpdate() {
        lastUpdated = new Date();
    }


}
