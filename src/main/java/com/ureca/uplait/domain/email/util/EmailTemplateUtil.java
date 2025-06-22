package com.ureca.uplait.domain.email.util;

import com.ureca.uplait.domain.email.entity.Email;
import com.ureca.uplait.domain.plan.entity.IPTVPlan;
import com.ureca.uplait.domain.plan.entity.InternetPlan;
import com.ureca.uplait.domain.plan.entity.MobilePlan;
import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.global.exception.GlobalException;

import static com.ureca.uplait.global.response.ResultCode.INVALID_PLAN;

public class EmailTemplateUtil {

    public static Email buildEmail(User user, Plan plan) {
        String title = String.format("[Uplait] 신규 요금제 '%s' 출시! 특별 혜택 확인하세요!", plan.getPlanName());
        String content = String.format(
            "안녕하세요 %s님.\n\n" +
            "Uplait에서 %s님이 관심있어하시는 주제와 관련된 새로운 요금제 '%s'가 출시되었습니다!\n" +
            "요금: %,d원\n" +
            buildDetail(plan) +
            "아래 링크에서 자세한 혜택을 확인해보세요.\n\n" +
            "자세히 보기: https://uplait.site/%s/plan/%s" +
            "\n감사합니다,\nUplait 드림",
            user.getName() != null ? user.getName() : "고객",
            user.getName() != null ? user.getName() : "고객",
            plan.getPlanName(),
            plan.getPlanPrice(),
            getType(plan),
            plan.getId()
        );
        return new Email(title, content);
    }

    private static String buildDetail(Plan plan) {
        if(plan instanceof MobilePlan mp) {
            return buildMobile(mp);
        } else if(plan instanceof InternetPlan ip) {
            return buildInternet(ip);
        } else if(plan instanceof IPTVPlan ip) {
            return buildIptv(ip);
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }

    private static String getType(Plan plan) {
        if(plan instanceof MobilePlan) {
            return "mobile";
        } else if(plan instanceof InternetPlan) {
            return "internet";
        } else if(plan instanceof IPTVPlan) {
            return "iptv";
        } else {
            throw new GlobalException(INVALID_PLAN);
        }
    }

    private static String buildMobile(MobilePlan mp) {
        return String.format(
            "데이터: %s\n" +
            "음성통화: %s\n" +
            "문자: %s\n\n",
            mp.getData(),
            mp.getVoiceCall(),
            mp.getMessage()
        );
    }

    private static String buildInternet(InternetPlan ip) {
        return String.format(
            "인터넷 속도: %s\n",
            ip.getVelocity()
        );
    }

    private static String buildIptv(IPTVPlan ip) {
        return String.format(
            "채널 수: %s\n",
            ip.getChannel()
        );
    }
}
