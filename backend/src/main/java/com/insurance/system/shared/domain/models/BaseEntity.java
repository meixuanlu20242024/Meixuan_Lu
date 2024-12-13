package com.insurance.system.shared.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.*;
import org.springframework.data.annotation.Version;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @CreationTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "Africa/Harare", locale = "en_ZW", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    @CreatedBy
    private String createdBy;

    @UpdateTimestamp
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "Africa/Harare", locale = "en_ZW", shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastModifiedDate;

    @LastModifiedBy

    private String lastModifiedBy;

//    @Column(name = "deleted")
//    private boolean deleted = false;


}
