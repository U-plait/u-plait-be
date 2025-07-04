package com.ureca.uplait.domain.plan.entity;

import com.ureca.uplait.domain.admin.dto.request.AdminInternetPlanUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "internet_plan")
@DiscriminatorValue("InternetPlan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class InternetPlan extends Plan {

    @Column(nullable = false)
    private String velocity;

    @Column(name = "internet_discount_rate", nullable = true)
    private Integer internetDiscountRate;


    public void InternetUpdateForm(AdminInternetPlanUpdateRequest request) {

        super.updateFrom(request);

        this.velocity = request.getVelocity();
        this.internetDiscountRate = request.getInternetDiscountRate();
    }
}
