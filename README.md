![header](https://capsule-render.vercel.app/api?type=venom&color=gradient&height=300&section=header&text=Uplait&fontSize=90&fontColor=ffffff)

# U-Plait 프로젝트 소개

**U-Plait**는 LG U+ 고객의 통신성향에 맞는 요금제 및 결합 상품을 추천해주는 서비스입니다.  
LLM 기반 챗봇과의 대화를 통해 사용자는 자신에게 알맞는 요금제를 비교, 추천 받을 수 있습니다.


### [프론트엔드 레포지토리 바로가기](https://github.com/U-plait/u-plait-fe)
### [AI 레포지토리 바로가기](https://github.com/U-plait/u-plait-ai)
<br><br />
# 1. 프로젝트의 배경
### 1.1 문제인식
  <img src="https://github.com/user-attachments/assets/4e88a7ca-3cfe-4c20-a2b9-3cc015eb3117" width="500"/>

2022년 통계에 따르면, 요금제 정보 수집에 어려움을 겪는 원인으로는 과도한 정보량으로 인한 **검색의 어려움(47.2%), 정보의 분산(21.8%)** 이높은 응답률을 보였습니다.
이처럼 복잡하고 이해하기 어려운 요금제 구조로 인해 많은 사용자가 어려움을 겪고 있다는 사실을 알 수 있습니다. 
이는 단순한 불편함을 넘어, 사용자가 부적절한 요금제를 선택해 통신사에 대한 불만족을 초래할 수 있습니다.
이를 개선하기 위해 빠른 상담을 위한 챗봇 서비스의 필요성을 느꼈습니다.
<br><br />
### 1.2 프로젝트의 목적

1. LLM 기반 챗봇을 활용해 사용자에게 맞춤형 통신 요금제를 추천함으로써,
통신 상품 탐색 과정을 보다 효율적이고 편리하게 만들어 사용자 경험을 향상시키고자 합니다.

2. 챗봇을 통해 고객센터로 연결되는 반복적이고 단순한 상담 업무 부담을 줄임으로써,
유플러스의 AICC 영역에서 실제 적용 가능한 상담 자동화 솔루션을 제공합니다.
<br><br />

# 3. 팀원 소개

<table>
  <tr>
    <td align="center"><b>조장</b></td>
    <td align="center"><b>Backend</b></td>
    <td align="center"><b>Backend</b></td>
    <td align="center"><b>Backend</b></td>
    <td align="center"><b>Backend</b></td>
    <td align="center"><b>Backend</b></td>
    <td align="center"><b>Backend</b></td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/dev-kim">
        <img src="https://avatars.githubusercontent.com/dev-kim" width="100px;" alt="dev-kim"/><br />
        <sub><b>임동준</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/hayong39">
        <img src="https://avatars.githubusercontent.com/hayong39" width="100px;" alt="hayong39"/><br />
        <sub><b>변하영</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Yyang-YE">
        <img src="https://avatars.githubusercontent.com/Yyang-YE" width="100px;" alt="Yyang-YE"/><br />
        <sub><b>양여은</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/songhyeongyu">
        <img src="https://avatars.githubusercontent.com/songhyeongyu" width="100px;" alt="songhyeongyu"/><br />
        <sub><b>송현규</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/etoile0626">
        <img src="https://avatars.githubusercontent.com/etoile0626" width="100px;" alt="etoile0626"/><br />
        <sub><b>최윤제</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Heoooo">
        <img src="https://avatars.githubusercontent.com/Heoooo" width="100px;" alt="Heoooo"/><br />
        <sub><b>허진혁</b></sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Suhun0331">
        <img src="https://avatars.githubusercontent.com/Suhun0331" width="100px;" alt="Suhun0331"/><br />
        <sub><b>김수훈</b></sub>
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">기획 및 발표 총괄</td>
    <td align="center">챗봇 개발, 배포</td>
    <td align="center">챗봇 개발, 디자인</td>
    <td align="center">Spring 서버 개발</td>
    <td align="center">Spring 서버 개발</td>
    <td align="center">Spring 서버 개발</td>
    <td align="center">기획 및 문서작업</td>
  </tr>
</table>

<br><br />
# 4. 기능 소개
### 4.1 로그인
<td align="center">
      <img src="https://github.com/user-attachments/assets/86462c75-5d0e-485a-a533-f77bc3b7069b" width="800" height="400" />
</td>

- 사용자는 카카오 소셜 로그인을 통해 로그인이 가능합니다.
<br><br />

### 4.2 회원가입
<tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/2560ad54-5f7d-4197-8edf-715c27081aa0" width="500" height="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/bc177a45-d2f7-48a7-b0dd-6e4e1d533743" width="500" height="250"/>
    </td>
  </tr>

- 최초 로그인한 사용자의 경우, 추가 회원 정보 입력을 위한 회원가입 페이지로 이동합니다.
- 추가 회원 정보 입력 후, 사용자의 관심 분야를 파악하기 위한 선호 태그를 최대 4개까지 선택 가능합니다.
<br><br />

### 4.3 메인 (요금제 목록)
<td align="center">
      <img src="https://github.com/user-attachments/assets/d9c445fe-1615-4fde-8b40-fab9879e7456" width="800" height="400" />
</td>

- 메인 화면에서는 사용자가 Uplait의 모바일 요금제 목록을 확인할 수 있습니다.
- 모바일 요금제 이외에도 화면 상단의 네비게이션 바를 통해 인터넷, IPTV의 요금제 목록도 확인할 수 있습니다.
<br><br />

### 4.4 챗봇
<td align="center">
      <img src="https://github.com/user-attachments/assets/cdb8c68a-bd1c-4cc9-bfea-c637c35c0940" width="800" height="400" />
</td>

- 사용자는 화면 우측 하단 아이콘을 통해 챗봇과 대화할 수 있습니다.
- 원하는 요금제에 대한 정보를 얻고, 요금제 추천이 가능합니다.
<br><br />

### 4.5 요금제 상세보기
<tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/46aabc08-0d93-4c1b-aa9d-6508bdf61779" width="500" height="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/0e117790-08f8-46f7-8d39-8250a53c8e6c3" width="500" height="250"/>
    </td>
  </tr>

- 요금제 목록에서 상세보기 버튼을 누르면, 해당 요금제의 상세 정보를 확인할 수 있습니다.
- 해당 요금제를 사용한 사용자는 리뷰를 남길 수 있으며, 다른 유저들도 리뷰를 확인할 수 있습니다.
<br><br />

### 4.6 요금제 비교하기
<tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/438f0c0d-ed49-4f67-88b3-cca91be15387" width="500" height="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/fb3ce68f-f479-4bab-a5ce-812d7d70b89c" width="500" height="250"/>
    </td>
  </tr>

- 요금제 목록 중 2개를 선택하여 요금제를 비교할 수 있습니다.
- 모바일-모바일과 같이 같은 종류의 요금제만 비교 가능합니다.
<br><br />

### 4.7 마이페이지
<tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/a055f190-893b-4734-94eb-5b2bea7a19b6" width="500" height="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/0611b4c0-a038-402a-b1ab-46fba1541e03" width="500" height="250"/>
    </td>
  </tr>

- 화면 상단의 'xxx 님'이라고 적힌 버튼을 통해 마이페이지로 이동하여 사용자의 개인정보를 확인, 수정할 수 있습니다.
- 'Bookmark' 버튼을 눌러 내가 즐겨찾기한 요금제 목록을 확인할 수 있습니다.
- 'Reviews' 버튼을 눌러 내가 쓴 리뷰들을 확인, 수정, 삭제가 가능합니다.
<br><br />

### 4.8 관리자 화면

**8.1 리뷰 관리 / 요금제 관리**
<tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/4977c4fb-4e0f-45a0-8d17-2b36925185a8" width="500" height="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/05363411-79a3-4736-a4a8-4ffb63472f2e" width="500" height="250"/>
    </td>
  </tr>

- 관리자는 관리 페이지에서 리뷰와 요금제 관리가 가능합니다.
<br><br />
**8.2 금칙어 관리 / 허용어 관리**
<tr>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/ce1fc47f-ee14-4afe-9b8d-75e2790aef75" width="500" height="250"/>
    </td>
    <td align="center">
      <img src="https://github.com/user-attachments/assets/ba88433f-ef92-473e-a14f-155ca3f7a48b" width="500" height="250"/>
    </td>
  </tr>

- 관리자는 관리 페이지에서 금칙어와 허용어 관리가 가능합니다.
   
<br><br />
# 5. 기술 스택 (Tech Stack)

### 5.1 백엔드

| 구분 | 기술 |
|------|------|
| 언어 | ![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white) |
| 프레임워크 | ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white) |
| ORM | ![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat&logo=spring&logoColor=white) |
| 문서화 | ![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=flat&logo=swagger&logoColor=black) |
| 인증/인가 | ![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=flat&logo=spring-security&logoColor=white)<br>![OAuth2](https://img.shields.io/badge/OAuth2-005C9C?style=flat&logo=oauth&logoColor=white)<br>![JWT](https://img.shields.io/badge/JWT-000000?style=flat&logo=jsonwebtokens&logoColor=white) |
| 테스트 | ![JUnit5](https://img.shields.io/badge/JUnit%205-25A162?style=flat&logo=jest&logoColor=white)<br>![Mockito](https://img.shields.io/badge/Mockito-5A6268?style=flat&logo=mockito&logoColor=white) |
| DB 마이그레이션 | ![Liquibase](https://img.shields.io/badge/Liquibase-2962FF?style=flat&logo=liquibase&logoColor=white) |

### 5.2 프론트엔드

| 구분 | 기술 |
|------|------|
| 라이브러리 | ![React](https://img.shields.io/badge/React-61DAFB?style=flat&logo=react&logoColor=black) |
| HTTP 클라이언트 | ![Axios](https://img.shields.io/badge/Axios-5A29E4?style=flat&logo=axios&logoColor=white) |

### 5.3 AI

| 구분 | 기술 |
|------|------|
| 백엔드 연동 | ![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat&logo=fastapi&logoColor=white) |
| AI 플랫폼 | ![OpenAI](https://img.shields.io/badge/OpenAI-412991?style=flat&logo=openai&logoColor=white)<br>![Hugging Face](https://img.shields.io/badge/Hugging%20Face-FFD21F?style=flat&logo=huggingface&logoColor=black) |
| LLM 프레임워크 | ![LangChain](https://img.shields.io/badge/LangChain-000000?style=flat&logo=python&logoColor=white) |

### 5.4 데이터베이스

| 구분 | 기술 |
|------|------|
| RDBMS | ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=flat&logo=postgresql&logoColor=white) |
| 벡터DB 확장 | ![pgvector](https://img.shields.io/badge/pgvector-000000?style=flat&logo=postgresql&logoColor=white) |

### 5.5 협업 & 형상 관리

| 구분 | 기술 |
|------|------|
| 버전 관리 | ![Git](https://img.shields.io/badge/Git-F05032?style=flat&logo=git&logoColor=white)<br>![GitHub](https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white) |
| 프로젝트 관리 | ![Jira](https://img.shields.io/badge/Jira-0052CC?style=flat&logo=jira&logoColor=white) |
| 문서 협업 | ![Notion](https://img.shields.io/badge/Notion-000000?style=flat&logo=notion&logoColor=white) |
| 커뮤니케이션 | ![Discord](https://img.shields.io/badge/Discord-5865F2?style=flat&logo=discord&logoColor=white) |



## 5.6 프로젝트 구조
- 시스템 아키텍처
<br>![Image](https://github.com/user-attachments/assets/dfb7d2f7-f93f-46d5-a135-7e75038697d5)
- ERD
<br>![Image](https://github.com/user-attachments/assets/271fbd65-50ba-4528-a96c-3ae0b16a7d34)


# 6. 기대 효과

### 사용자 측면
- 복잡한 요금제를 직접 찾아볼 필요 없이 자신에게 적합한 요금제를 빠르고 정확하게 추천합니다.
- 대기할 필요 없이, 사용자는 원하는 시간에 원하는 내용의 상담을 진행할 수 있습니다.

### 통신사 측면
<img src="https://github.com/user-attachments/assets/0059c1c8-c5e2-4965-9db4-7346565d8953" width="500"/>
- 많은 사용자가 통신사 홈페이지를 정보 탐색의 주요 수단으로 사용하므로, 통신사 홈페이지에 챗봇 서비스를 제공할 경우, 높은 활용도를 나타낼 것으로 기대됩니다.
- 반복적이고 단순한 상담 업무를 챗봇으로 대체하여, 상담 인력의 부담을 줄이고 운영의 효율성을 높일 수 있습니다.
- 최신 요금제 정보를 기반으로 한 챗봇을 운영함으로써 상담 내용의 일관성 및 정확도를 높일 수 있습니다.
