package com.ureca.uplait.domain.plan.dto.request;

import com.ureca.uplait.domain.review.entity.Review;
import java.util.List;

public record IptvCreateDto(String planName,
                            Integer planPrice,
                            String planBenefit,
                            Boolean avaliability,
                            Boolean combinability,
                            List<Review> reviews,
                            Integer channel,
                            Integer iptvDiscountRate) {

}
