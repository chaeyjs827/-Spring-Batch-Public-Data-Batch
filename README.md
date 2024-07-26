# 1. 프로젝트 소개
## 1.1 Git 브랜치
* main
  * development (통합 브랜치 겸 작업 브랜치)

## 1.2 사용 기술
* Java 버전 : openjdk 17.0.10(LTS 버전)
* DB : mysql
* 컨테이너 : docker
* DB 인터페이스 : JdbcTemplate
* Jacoco : 테스트 커버리지 진행 검증

## 1.3 프로젝트 구조
* public_data
  * domain.general.restaurant
    * dto
    * listener (리스너 패키지)
    * mapper (맵퍼 패키지)
    * policy (배치 정책 패키지. ex:skipPolicy)
    * repository (DB 비지니스 로직 패키지)
    * step (Job 실행 Step 패키지)
    * GeneralRestaurantImportConfig (Job Config 파일)
  * global
    * application
      * TriggerController(배치 수동 실행 컨트롤러) 
    * enums
    * service
      * TriggerService(배치 수동 실행 service layer) 
  * PublicDataApplication (배치 Application)

# 2. 요구사항 처리
* 배치어플리케이션으로 CSV 파일을 읽어 데이터베이스(MySQL)에 CSV의 데이터를 열을 구분하여 저장합니다.
  * reader 메소드 in GeneralRestaurantReadAndSaveStep 클래스
* 배치 작업의 진행상황과 발생한 오류를 추적할 수 있도록 로깅합니다.
  * 배치 실패 로깅 및 로직 예외 처리 수행
* 배치의 기능을 검증하기 위해 단위테스트를 작성합니다.
  * 비지니스로직 단위 테스트를 진행했습니다.(Repository 클래스)
 
# 3. 개발 내용
* 스키마
  * 입력 받는 데이터와 동일하게 구현
  * ID 항목은 PK로 등록 
* 배치 운영 정책
  * 데이터의 저장 시 순차성 보장 x (데이터의 ID를 primary key로 저장)
  * 무료 데이터 처리(skip)
* 배치 성능 개선
  * 약 210 건 데이터 처리를 위한 chunk 기반 로직 개발
    * 성능 테스트
      * 5,000건
        * 800~950ms (10회 실행)
      * 50,000건
        * 5,000~5,500ms (10회 실행)
      * 전체(2,152,081건)
        * 3m20s~3m30s (10회 실행)
  * 추가 성능 개선을 위한 partition 도입 [노트북 성능 문제인지, 단순 처리 로직을 굳이 partition으로 처리해서인지 성능이 오히려 저하되어 롤백했습니다.]
    * 성능 테스트
      * 5,000건
        * 1.3~1.5ms (10회 실행)
      * 50,000건
        * 10,000~12,000ms (10회 실행)
      * 전체(2,152,081건)
        * 10m~10m30s (10회 실행)
  * 데이터 Read 후 Write 시 Message Queue 사용 고려
    *  특별히 가공을 하는 로직이나, 외부 API 등을 호출하는 부분이 없기 떄문에 성능 이슈가 예측되지 않아 따로 사용하지 않았습니다.
 
# 4. 실행 가이드
1. docker engine 다운로드 (https://docs.docker.com/engine/install/)
2. project import
3. docker-compose 실행(project root/docker 이동 및 docker-compose up 실행)
4. src/main/resources/general-restaurant 디렉토리에 csv 파일명을 'all.csv' 로 변경하여 저장(https://www.data.go.kr/data/15096283/standard.do csv파일 다운로드. 용량 약 1GB)
6. PublicDataApplication 실행 시 Job 자동 실행
