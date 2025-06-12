package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.domain.admin.dto.request.AdminIPTVPlanUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "iptv_plan")
@DiscriminatorValue("IPTVPlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class IPTVPlan extends Plan {

    @Column(nullable = true)
    private Integer channel;

    @Column(name = "iptv_discount_rate", nullable = true)
    private Integer iptvDiscountRate;

    public void IPTVUpdateForm(AdminIPTVPlanUpdateRequest request) {

        super.updateFrom(request);

        this.channel = request.getChannel();
        this.iptvDiscountRate = request.getIptvDiscountRate();
    }
}