package cn.axboy.demo.mixjpajooq.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public abstract class AbstractAuditingEntity {

    @Id
    @Column(name = "id", unique = true, nullable = false, precision = 20)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedBy
    @Column(name = "created_by", length = 50, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "created_time", updatable = false)
    private Long createdTime;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_time")
    private Long lastModifiedTime;

    @Column(name = "flag", columnDefinition = "int(1) NULL DEFAULT 1")
    private boolean flag = true;

    @PrePersist
    public void prePersist() {
        createdTime = System.currentTimeMillis();
        lastModifiedTime = createdTime;
    }

//    @PostPersist
//    public void postPersist() {
//    }
//
//    @PreRemove
//    public void preRemove() {
//    }
//
//    @PostRemove
//    public void postRemove() {
//    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedTime = System.currentTimeMillis();
    }

//    @PostUpdate
//    public void postUpdate() {
//    }
}
