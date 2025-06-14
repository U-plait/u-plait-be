package com.ureca.uplait.domain.community.repository;

import com.ureca.uplait.domain.community.entity.CommunityBenefitPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommunityBenefitPriceRepository extends JpaRepository<CommunityBenefitPrice, Long> {
    @Query(value = """
    SELECT cbp.* FROM community_benefit_price cbp
    INNER JOIN (
        SELECT community_benefit_id, MAX(headcount) AS max_headcount
        FROM community_benefit_price
        WHERE community_benefit_id IN (:ids)
        GROUP BY community_benefit_id
    ) grouped ON cbp.community_benefit_id = grouped.community_benefit_id AND cbp.headcount = grouped.max_headcount
    """, nativeQuery = true)
    List<CommunityBenefitPrice> findMaxHeadcountPricesByCommunityBenefitIds(@Param("ids") List<Long> ids);
}
