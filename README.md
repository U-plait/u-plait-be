# 🛠️ U-Plait Backend

## 📌 프로젝트 소개

**U-Plait**는 LG U+ 고객의 통신성향에 맞는 요금제 및 결합 상품을 추천해주는 서비스입니다.  
LLM 기반 챗봇과의 대화를 통해 사용자는 자신에게 알맞는 요금제를 비교, 추천 받을 수 있습니다.

---
## 프론트엔드 레포지토리

[프론트엔드 README 바로가기](https://github.com/U-plait/u-plait-fe)

## AI 레포지토리

[AI README 바로가기](https://github.com/U-plait/u-plait-ai)

---
## 🎯 프로젝트의 배경

![배경 설명 사진1](https://github.com/user-attachments/assets/4e88a7ca-3cfe-4c20-a2b9-3cc015eb3117)

통계에 따르면, 요금제 정보 수집에 어려움을 겪는 원인으로는 과도한 정보량으로 인한 검색의 어려움( 47.2%), 정보의 분산( 21.8%) 이높은 응답률을 보였습니다.
이처럼 복잡하고 이해하기 어려운 요금제 구조로 인해 많은 사용자가 어려움을 겪고 있다는 사실을 알 수 있습니다. 
이는 단순한 불편함을 넘어, 사용자가 부적절한 요금제를 선택해 통신사에 대한 불만족을 초래할 수 있습니다.

더 나아가, 고객 문의가 몰리는 시간대에는 상담 대기 시간이 늘어나, 고객 만족도 저하 및 이탈률 증가로 이어질 위험 또한 존재합니다. 
이는 기업의 상담 부담 증가 및 이미지 저하 등의 부정적 영향을 미칩니다.
이를 개선하기 위해 빠른 상담을 위한 챗봇 서비스의 필요성을 느꼈습니다.

---
## 📜 프로젝트의 목적

LLM 기반 챗봇을 활용해 사용자에게 맞춤형 통신 요금제를 추천함으로써,
통신 상품 탐색 과정을 보다 효율적이고 편리하게 만들어 사용자 경험을 향상시키고자 합니다.

챗봇을 통해 고객센터로 연결되는 반복적이고 단순한 상담 업무 부담을 줄임으로써,
유플러스의 AICC 영역에서 실제 적용 가능한 상담 자동화 솔루션을 제공합니다.

---
## 👥 팀원 소개

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/dev-kim">
        <img src="https://avatars.githubusercontent.com/dev-kim" width="100px;" alt="dev-kim"/>
        <br />
        <sub><b>임동준</b></sub>
      </a>
      <br />
    </td>
    <td align="center">
      <a href="https://github.com/hayong39">
        <img src="https://avatars.githubusercontent.com/hayong39" width="100px;" alt="frontend-lee"/>
        <br />
        <sub><b>변하영</b></sub>
      </a>
      <br />
    </td>
    <td align="center">
      <a href="https://github.com/Yyang-YE">
        <img src="https://avatars.githubusercontent.com/Yyang-YE" width="100px;" alt="ai-park"/>
        <br />
        <sub><b>양여은</b></sub>
      </a>
      <br />
    </td>
    <td align="center">
      <a href="https://github.com/songhyeongyu">
        <img src="https://avatars.githubusercontent.com/songhyeongyu" width="100px;" alt="pm-choi"/>
        <br />
        <sub><b>송현규</b></sub>
      </a>
      <br />
    </td>
    <td align="center">
      <a href="https://github.com/etoile0626">
        <img src="https://avatars.githubusercontent.com/etoile0626" width="100px;" alt="pm-choi"/>
        <br />
        <sub><b>최윤제</b></sub>
      </a>
      <br />
    </td>
    <td align="center">
      <a href="https://github.com/Heoooo">
        <img src="https://avatars.githubusercontent.com/Heoooo" width="100px;" alt="pm-choi"/>
        <br />
        <sub><b>허진혁</b></sub>
      </a>
      <br />
    </td>
    <td align="center">
      <a href="https://github.com/Suhun0331">
        <img src="https://avatars.githubusercontent.com/Suhun0331" width="100px;" alt="pm-choi"/>
        <br />
        <sub><b>김수훈</b></sub>
      </a>
      <br />
    </td>
  </tr>
</table>

---

## ✨ 주요 기능
실제 화면은 프론트엔드 README를 확인해주세요.

- **회원 관리** – 회원가입 / 로그인 / 인증 / 마이페이지
- **요금제 시스템** – 요금제 비교 / 모바일, 인터넷, IPTV 요금제 CRUD
- **리뷰 시스템** – 사용자 리뷰 조회, 작성, 수정, 삭제
- **챗봇 시스템** – 요금제 추천, 상담 / 멀티턴 지원 / 채팅내역 저장
- **선호 태그 기반 이메일 발송** – 최근 사용자가 관심을 가진 분야에 대한 태그를 저장, 신규 요금제 출시 시 이메일로 관련 알림 발송
- **관리자 기능** – 리뷰 / 요금제 / 금칙어 관리

---
## 🛠 기술 스택 (Tech Stack)

| 구분 | 기술 |
|------|------|
| 언어 | ![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white) |
| 프레임워크 | ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) |
| ORM | ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat&logo=spring&logoColor=white) |
| 문서화 | ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=swagger&logoColor=black) |
| 인증/인가 | ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=spring-security&logoColor=white)<br>![OAuth2](https://img.shields.io/badge/OAuth2-005C9C?style=flat&logo=oauth&logoColor=white)<br>![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white) |
| 테스트 | ![JUnit 5](https://img.shields.io/badge/JUnit%205-25A162?style=flat&logo=jest&logoColor=white)<br>![Mockito](https://img.shields.io/badge/Mockito-5A6268?style=flat&logo=mockito&logoColor=white) |
| DB 마이그레이션 | ![Liquibase](https://img.shields.io/badge/Liquibase-2962FF?style=flat&logo=liquibase&logoColor=white) |

---

## 📁 프로젝트 구조
- 시스템 아키텍처
<br>![Image](https://github.com/user-attachments/assets/dfb7d2f7-f93f-46d5-a135-7e75038697d5)
- ERD
<br>![Image](https://github.com/user-attachments/assets/271fbd65-50ba-4528-a96c-3ae0b16a7d34)
---
## 💡 기대 효과

### 사용자 측면
- **복잡한 요금제를 직접 찾아볼 필요 없이 자신에게 적합한 요금제를 빠르고 정확하게 추천합니다.**
- **대기할 필요 없이, 사용자는 원하는 시간에 원하는 내용의 상담을 진행할 수 있습니다.**

### 통신사 측면
![Image](https://github.com/user-attachments/assets/0059c1c8-c5e2-4965-9db4-7346565d8953)
- **이동전화 회사간 비교 정보를 어디에서 수집하는지 묻는 질문에 조사회사패널 표본은 인터넷 검색(65.6%), 해당 회사 홈페이지(57.3%) 순으로 나타난 응답 비율이 나타났습니다.**
<br>**많은 사용자가 통신사 홈페이지를 정보 탐색의 주요 수단으로 사용하므로, 통신사 홈페이지에 챗봇 서비스를 제공할 경우, 높은 활용도를 나타낼 것으로 기대됩니다.**
- **반복적이고 단순한 상담 업무를 챗봇으로 대체하여, 상담 인력의 부담을 줄이고 운영의 효율성을 높일 수 있습니다.**
- **최신 요금제 정보를 기반으로 한 챗봇을 운영함으로써 상담 내용의 일관성 및 정확도를 높일 수 있습니다.**
