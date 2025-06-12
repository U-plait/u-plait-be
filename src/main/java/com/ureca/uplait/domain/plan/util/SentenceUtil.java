package com.ureca.uplait.domain.plan.util;

import com.ureca.uplait.domain.community.entity.CommunityBenefit;
import com.ureca.uplait.domain.community.entity.CommunityBenefitPrice;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.user.entity.Tag;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SentenceUtil {

    public static String createSentence(Plan plan, List<Tag> tagList, Map<CommunityBenefit, CommunityBenefitPrice> communityBenefitList) {
        return String.join("\n",
            makePlanInfo(plan),
            makeTagInfo(tagList),
            makeCommunityBenefitInfo(communityBenefitList)
        ).trim();
    }

    private static String makePlanInfo(Plan plan) {
        if (plan instanceof MobilePlan mp) return makeMobileInfo(mp);
        if (plan instanceof InternetPlan ip) return makeInternetInfo(ip);
        if (plan instanceof IPTVPlan iptv) return makeIPTVInfo(iptv);
        return "";
    }

    private static String makeTagInfo(List<Tag> tagList) {
        if (tagList == null || tagList.isEmpty()) return "";
        String tags = tagList.stream().map(Tag::getName).collect(Collectors.joining(", "));
        return "이 요금제는 " + tags + " 등의 키워드와 관련이 있습니다.";
    }

    private static String formatBenefits(String benefitRaw) {
        if (benefitRaw == null || benefitRaw.isBlank()) return "";
        String[] benefits = benefitRaw.split("\\+");
        return benefits.length > 0 ? "추가 혜택: " + String.join(", ", benefits) + "." : "";
    }

    private static String makeMobileInfo(MobilePlan plan) {
        return ("모바일 요금제 '" + plan.getPlanName() + "'은 월 " + plan.getPlanPrice() + "원입니다.\n" +
            "기본 제공: 데이터 " + plan.getData() +
            ", 통화 " + plan.getVoiceCall() +
            ", 문자 " + plan.getMessage() + ".\n" +
            "추가 제공: 데이터 " + plan.getExtraData() +
            ", 공유 데이터 " + plan.getSharedData() + ".\n" +
            "약정 할인율 " + plan.getDurationDiscountRate() + "%, 프리미어 할인 적용 시 추가 " + plan.getPremierDiscountRate() + "원 할인\n" +
            (plan.getMediaBenefit() ? "미디어 혜택: 아이들 나라 스탠다드 러닝, 바이브 300회 음악 감상, 유플레이, 밀리의 서재 중 하나를 선택하여 제공받을 수 있습니다.\n" : "") +
            formatBenefits(plan.getPlanBenefit())).trim();
    }

    private static String makeInternetInfo(InternetPlan plan) {
        return ("인터넷 요금제 '" + plan.getPlanName() + "'은 월 " + plan.getPlanPrice() + "원이며,\n" +
            "온라인 전용 할인가 적용 시 " + plan.getInternetDiscountRate() + "원입니다.\n" +
            "속도: " + plan.getVelocity() + "\n" +
            formatBenefits(plan.getPlanBenefit())).trim();
    }

    private static String makeIPTVInfo(IPTVPlan plan) {
        return ("IPTV 요금제 '" + plan.getPlanName() + "'은 월 " + plan.getPlanPrice() + "원이며,\n" +
            "온라인 전용 할인가 적용 시 " + plan.getIptvDiscountRate() + "원입니다.\n" +
            "채널 수: " + plan.getChannel() + "개\n" +
            formatBenefits(plan.getPlanBenefit())).trim();
    }

    private static String makeCommunityBenefitInfo(Map<CommunityBenefit, CommunityBenefitPrice> cbMap) {
        if (cbMap == null || cbMap.isEmpty()) return "";

        StringBuilder sb = new StringBuilder("결합 가능한 상품 요약:\n");

        for (var entry : cbMap.entrySet()) {
            CommunityBenefit cb = entry.getKey();
            CommunityBenefitPrice price = cbMap.get(cb);

            sb.append("- ").append(cb.getName()).append(":\n최대 휴대폰 ").append(cb.getMaxPhone())
                .append("대, 인터넷 ").append(cb.getMaxPhone()).append("회선, IPTV ").append(cb.getMaxIptv()).append("대 결합 가능합니다.\n")
                .append(cb.getCommunityCondition()).append(" 조건을 만족해야합니다.\n");

            // 최대 정보 삽입
            sb.append("최대 ").append(price.getHeadcount()).append("명 까지 결합 가능합니다.\n");
            boolean added = false;
            if (price.getYouthDiscount() > 0) {
                sb.append("최대 청소년 할인은 ").append(price.getYouthDiscount()).append("원");
                added = true;
            }
            if (price.getMobileDiscount() > 0) {
                if (added) sb.append(", ");
                sb.append("최대 모바일 요금제 할인은 ").append(price.getMobileDiscount()).append("원");
                added = true;
            }
            if (price.getInternetDiscount() > 0) {
                if (added) sb.append(", ");
                sb.append("최대 인터넷 요금제 할인은 ").append(price.getInternetDiscount()).append("원");
            }
            if (added) sb.append("까지 가능합니다.\n");
        }
        return sb.toString().trim();
    }
}
