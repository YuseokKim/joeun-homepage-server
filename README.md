# joeun-homepage-server
조은건설중기 홈페이지의 푸시알림 서버입니다

## 사용 기술 스펙
- Toast Cloud, AWS RDS, Firebase Cloud Messaging
- CentOS 7, nginx
- Java(JDK 1.8), Springboot, JPA, MariaDB
- REST API

## 서비스 소개
- 안성 조은건설중기(http://www.joeunjunggi.co.kr) 푸시알림 서버 영역
  * 클라이언트 영역은 HTML와 Javascript로 구현, 서버와 물리적으로 분리
- 관리자 스마트폰에 앱 설치 후 관리자 기기 정보 및 FCM 토큰 정보를 DB에 저장
- 실제 견적문의 화면(http://www.joeunjunggi.co.kr/contact-us.html)에서 견적 문의 발생 시 저장된 FCM 토큰을 조회하여 문의내용을 푸시알림 전송
