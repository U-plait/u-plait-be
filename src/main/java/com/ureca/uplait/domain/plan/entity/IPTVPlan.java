package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.domain.plan.dto.request.IPTVPlanUpdateRequest;
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

    public IPTVPlan toIPTV() {
        return IPTVPlan.builder()
            .planName(getPlanName())
            .planPrice(getPlanPrice())
            .planBenefit(getPlanBenefit())
            .availability(getAvailability())
            .description(getDescription())
            .channel(channel)
            .iptvDiscountRate(iptvDiscountRate)
            .build();
    }

    public void IPTVUpdateForm(IPTVPlanUpdateRequest request) {
        
        super.updateFrom(request);

        this.channel = request.getChannel();
        this.iptvDiscountRate = request.getIptvDiscountRate();
    }
}