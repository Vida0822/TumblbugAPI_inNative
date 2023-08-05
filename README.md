# :pushpin: Tumblbug JSP Project

> 크라우드 펀딩 사이트 '텀블벅' - DispatcherServlet을 활용한  jsp를 활용한(Servlet을 직접 한땀한땀 구현한...) 모델 2방식(MVC) 웹사이트 구현
> https://go-quality.dev  

</br>



### 목차

1. 제작기간 & 참여 인원  <br>
2. 사용 기술  <br>
3. 프로젝트 개요 <br>
4. 요구분석 : User - Diagram 설계 
5. DB 모델링 : ERD / EXERD 설계    <br>
6. 프로젝트 흐름도  
7. 핵심 기능 코딩    <br>
8. 트러블 슈팅  <br>
9. 프로젝트 회고 <br>

</br></br>



## 1. 제작 기간 & 참여 인원

- 👩‍👧‍👧 팀 프로젝트 (7인)<br>

- 📆 2023 5월 30일 ~ 6월 19일 (21일)  <br>

  

  </br>

## 2. 사용 기술

#### `Back-end`

  - Java 8
  - Spring Framework 2.3
  - Gradle
  - Spring Data JPA
  - QueryDSL
  - H2
  - MySQL 5.7
  - Spring Security
  - Jsoup

#### `Front-end`

  - Vue.js 3.0
  - Element UI

</br>



## 3. 프로젝트 개요

​	이 프로젝트는  프레임워크의 도움을 받지않고 고전적인 방식으로 모델2 방식의 MVC 구조를 구현한 jsp 기반 프로젝트이다. 실제 존재하는 사이트의 프론트/백앤드 기능을 구현하는 웹 프로젝트로, 사이트는 리워드(보상형) 크라우드 펀딩 업체, '텀블벅(tumblbug)'을 선택하였다. 

​	웹 컨테이너 개발환경을 설정하고 서블릿 및 Model, Controller 클래스를 직접 작성, 등록, 매핑하며 스프링을 본격적으로 공부하기전 MVC 및 웹 어플리케이션의 동작 원리와 구조를 정확히 파악할 수 있는 기회가 되었다.   

</br>

<details>
<summary><b>개발 단계 및 일정 </b></summary>
<div markdown="1">
* 5월 30일 ~ 7월 03일 : 기존 프로젝트 컨벌팅
* 7월 04일 ~ 7월 07일 : 회원가입/로그인 기능 + Spring Security 적용
* 7월 08일 ~ 7월 11일 : 관리자 기능 + spring security 적용 

</div>
</details> 
    
</br>



## 4. 요구분석 : User - Diagram 설계 





## 5. DB 모델링 :  ERD / EXERD 설계







## 6. 프로젝트 흐름

### `구조 : 모델2 방식(MVC 패턴)  `











<details>
<summary><b>프로젝트 구조 설명 펼치기</b></summary>
<div markdown="1">


### 6.1. View

* 의외로 가장 시간이 많이 들고 어렵게 구현
  * 페이지 설계(html 태그)를 공개하지 않아 개발자 도구에 출력되는 태그들을 화면 캡쳐하듯이 가져옴. 
  * 스타일(css) 태그도 의도적으로 알아보기 힘들게 작성되어있어 개발자 도구로 각 태그별 적용된 스타일을 하나씩 클래스명으로 매치시켜가면서 작성
  * 동적인 요소가 많은데 스크립트 코딩이 공개되어있지 않아 모든 동적인 기능 일일히 구현

* 프로젝트 구조에 실제 사이트 요구사항 반영

  * 요청 url로 접근할 수 없고 단계별로 접근 해야하는 페이지는 web-inf아래 view 폴더에 배치 (직접접근방지) 
  * 요청 url만으로도 직접 접근할 수 있는 view 페이지는 web-inf 밖의 publicWeb 폴더에 배치

* jsp 페이지로 제작

  * 사용자 동작에 맞게 스크립트 처리 (**프론트엔드 개발)

  * 사용자 입력값 (요청값) 보내기

  * html, css 로 화면 구성

  * 서버로부터 넘어온 응답 데이터 el,jstl로 출력 

    

### 6.2. DispatcherServlet

​	모든 요청에 대해 들어가기 전 수행해야할 공통작업, 필수작업을  수행하는 frontController로서 전달받은 요청 url에 따라 업무를 수행할 핸들러를 호출하는 Mapping 기능을 담당했다.  

* 서블릿 등록 

  ```xml
  <servlet>
  	<servlet-name>ControllerUsingURI</servlet-name>
  	<servlet-class>mvc.controller.ControllerUsingURI</servlet-class>
  	<init-param>
  		<param-name>configFile</param-name>
  		<param-value>
                 /WEB-INF/commandHandlerURI.properties
           </param-value>
  	</init-param>
  <load-on-startup>1</load-on-startup>
  </servlet>
  
  <servlet-mapping>
  	<servlet-name>ControllerUsingURI</servlet-name>
  	<url-pattern>*.do</url-pattern>
  </servlet-mapping>
  ```

  * servlet 등록 : web.xml로 서블릿 컨테이너(톰캣)에 생성 - 매개변수로 properties 파일 넣어줌 
  * servlet 매핑 : *.do로 들어오는 모든 요청을 이 컨트롤러(ControllerUsingURI)가 담당

  * properties 파일 :  각 요청 url 별 처리할 Handler를 매치시켜 목록으로 작성해놓은 일반 파일 
  * application이 실행될 때 init() 호출  : properties 파일을 FileReader로 읽어 commandHandlerMap라는 Map 형태로 Handler 목록 저장 => (요청 url, 담당할 Handler)

* 매핑

  * 요청이 들어오면 commandHandlerMap에서 해당 요청 url에 해당하는 Handler를 찾아 객체를 생성해 interface형 참조변수(CommandHandler)에 대입

  * interface CommandHandler: 요청을 처리하는 함수 process()가 선언되어 있음 

    : 모든 핸들러는 해당 process()를 오버라이딩해서 매핑된 요청을 다뤄야 함  

  * 생성된 handler 객체의 process() 호출 

    : 전송방식이 get이던 post던 해당 메서드가 요청을 처리 (doGet(), doPost() 둘다 process() 호출)  

    => 매핑된 Handler로 요청, 응답 객체 전달 

  * Mapping 된 Handler가 없으면 NullHandler라는 Handler 객체 생성 

    * SC_NOT_FOUND : 404 에러 -> 이 에러를 응답객체에  클라이언트에 send(보내겠다)

    ```java
    public class NullHandler implements CommandHandler {
    
    	@Override
    	public String process(HttpServletRequest req, HttpServletResponse res)
    	throws Exception {
    		res.sendError(HttpServletResponse.SC_NOT_FOUND); 
    		return null;
    	}
    }
    ```

    

* 포워딩 

  * return된 viewPage(String)로 request, response 객체 포워딩

  * redirect는 Handler에서 임의로 처리 : redirect시 컨트롤러로 돌아오지 않고 핸들러에서 로직 종료 

     

### 6.3. Handler

​	매핑으로 전달받은 요청과 응답 객체를 직접 다루며 모델과 뷰를 제어한다. 사용자의 요청을 받아서 분석하고 비즈니스 로직을 처리하는 Model을 호출한다. 모델이 결과값을 반환하면 출력할 뷰(jsp 페이지)를 선택한 후 전달한다. 

*  Model 호출 :  Service, DAO의 메서드 호출 

​	: 생성된 자바 응답 데이터를 request 객체에 담고 포워딩 시킬 뷰페이지 컨트롤러에 반환 

*  process() 오버라이딩 : 이 handler에서 매핑된 요청을 어떻게 처리할지 본격적으로 구현 

  * processGet()  : 요청이 get방식으로 들어오면 실행 

     => 주로 포워딩 : return FORMVIEW (String)

  *  processSubmit()  : 요청이 post방식으로 들어오면 실행 

    => 주로 리다이렉트: 원래는 Controller에서 해줘야하지만 편의상 Handler가 처리 

* 바인딩 : 비즈니스 로직 부분에 요청을 다루는 코딩(요청, 응답 객체)을 직접 넣어주지 않고 매개변수를 추출해  자바 데이터 형태로 변환해서 넣어줌

  ```java
  public class MakeProjectHandler implements CommandHandler {
  	private static final String FORM_VIEW = "/WEB-INF/view/projectForm.jsp";
  	
  	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
  		if (req.getMethod().equalsIgnoreCase("GET")) {
  			return processForm(req, res); // 폼 띄우는함수 
  		} else if (req.getMethod().equalsIgnoreCase("POST")) {
  			return processSubmit(req, res); // db에 submit 하는 함수 
  		} else {
  			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED); // get, post 방식 외 요청방식이 있는데 (안배웠지만) 그걸로 들어왔다면 !
  			return null;
  		}
  	}// process
  ```

  

### 6.4. Model(Service & DAO)

​	업무 처리 로직(비즈니스 로직) 혹은 데이터 베이스와 관련된 작업을 담당한다 

* Service

  * 비즈니스 로직 수행: 로직 처리 후 view 페이지로 전달할 객체로 구성(생성)해서 반환
  * 트랜잭션 처리 - commit , rollback 

* DAO 

  * DB를 직접적으로 다룸 :  주로 dto 를 단위로 넘기고 넘겨받는다

  * java의 jdbc 기능 사용 : SQL 및 DB연결, Java언어가 모두 존재하기때문에 재사용성이 좋지 않았다. 

    

</div>
</details> 
    
</br>



## 7. 핵심 기능

#### 7.1. 회원가입- <a href="https://github.com/Vida0822/Tumblbug_JSP-MVC-Project/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A9%94%EC%9D%B8-Page)" >상세보기 - WIKI 이동</a>

- DB값 검증 : 이미 있는 계정
- 입력값 유효성 검사: 형식에 맞는 이메일/ 비밀번호
- 입력값 확인 
- 약관 동의 체크박스 
- 회원 탈퇴

#### 7.2. 로그인 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Member)" >상세보기 - WIKI 이동</a>

- 주소 API 연동
- ID 중복 체크
- 로그아웃 

#### 7.2. 회원 정보 관리 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Member)" >상세보기 - WIKI 이동</a>

- 주소 API 연동
- ID 중복 체크

#### 7.3. 메인 페이지 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Member)" >상세보기 - WIKI 이동</a>

- 프로젝트 목록보기
- ID 중복 체크

#### 7.4. 조건별로 검색하기 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(Member)" >상세보기 - WIKI 이동</a>

- 주소 API 연동
- 회원정보 변경

#### 7.4. 상세 페이지 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EC%98%81%ED%99%94-%EC%98%88%EB%A7%A4)" >상세보기 - WIKI 이동</a>

- 영화 선택(날짜 지정)
- 영화관 선택(대분류/소분류 선택) 및 시간 선택
- 좌석 선택
- 결제 페이지
- 예매 완료

#### 7.5. 후원하기 - <a href="https://github.com/chaehyuenwoo/SpringBoot-Project-MEGABOX/wiki/%EC%A3%BC%EC%9A%94-%EA%B8%B0%EB%8A%A5-%EC%86%8C%EA%B0%9C(%EB%A9%94%EC%9D%B8-Page)" >상세보기 - WIKI 이동</a>

- YouTube API 연동
- 메인 포스터(영화) 이미지 슬라이드(CSS)

#### 7.6. 프로젝트 올리기  - <a href="" >상세보기 - WIKI 이동</a> 

- 글 작성, 읽기, 수정, 삭제(CRUD)











<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">


### 4.1. 전체 흐름

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow1.png)

### 4.2. 사용자 요청

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_vue.png)

- **URL 정규식 체크** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b587bbff4dce02e3bec4f4787151a9b6fa326319/frontend/src/components/PostInput.vue#L67)
  - Vue.js로 렌더링된 화면단에서, 사용자가 등록을 시도한 URL의 모양새를 정규식으로 확인합니다.
  - URL의 모양새가 아닌 경우, 에러 메세지를 띄웁니다.

- **Axios 비동기 요청** :pushpin: [코드 확인]()
  - URL의 모양새인 경우, 컨텐츠를 등록하는 POST 요청을 비동기로 날립니다.

### 4.3. Controller

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_controller.png)

- **요청 처리** :pushpin: [코드 확인](https://github.com/Integerous/goQuality/blob/b2c5e60761b6308f14eebe98ccdb1949de6c4b99/src/main/java/goQuality/integerous/controller/PostRestController.java#L55)
  - Controller에서는 요청을 화면단에서 넘어온 요청을 받고, Service 계층에 로직 처리를 위임합니다.

- **결과 응답** :pushpin: [코드 확인]()
  - Service 계층에서 넘어온 로직 처리 결과(메세지)를 화면단에 응답해줍니다.

### 4.4. Service

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service1.png)

- **Http 프로토콜 추가 및 trim()** :pushpin: [코드 확인]()

  - 사용자가 URL 입력 시 Http 프로토콜을 생략하거나 공백을 넣은 경우,  
    올바른 URL이 될 수 있도록 Http 프로토콜을 추가해주고, 공백을 제거해줍니다.

- **URL 접속 확인** :pushpin: [코드 확인]()

  - 화면단에서 모양새만 확인한 URL이 실제 리소스로 연결되는지 HttpUrlConnection으로 테스트합니다.
  - 이 때, 빠른 응답을 위해 Request Method를 GET이 아닌 HEAD를 사용했습니다.
  - (HEAD 메소드는 GET 메소드의 응답 결과의 Body는 가져오지 않고, Header만 확인하기 때문에 GET 메소드에 비해 응답속도가 빠릅니다.)

  ![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_service2.png)

- **Jsoup 이미지, 제목 파싱** :pushpin: [코드 확인]()

  - URL 접속 확인결과 유효하면 Jsoup을 사용해서 입력된 URL의 이미지와 제목을 파싱합니다.
  - 이미지는 Open Graphic Tag를 우선적으로 파싱하고, 없을 경우 첫 번째 이미지와 제목을 파싱합니다.
  - 컨텐츠에 이미지가 없을 경우, 미리 설정해둔 기본 이미지를 사용하고, 제목이 없을 경우 생략합니다.


### 4.5. Repository

![](https://zuminternet.github.io/images/portal/post/2019-04-22-ZUM-Pilot-integer/flow_repo.png)

- **컨텐츠 저장** :pushpin: [코드 확인]()
  - URL 유효성 체크와 이미지, 제목 파싱이 끝난 컨텐츠는 DB에 저장합니다.
  - 저장된 컨텐츠는 다시 Repository - Service - Controller를 거쳐 화면단에 송출됩니다.

</div>
</details>

</br>

## 8. 핵심 트러블 슈팅 (프젝 올리기 스크립트 쪽 여기에)

### 5.1. 컨텐츠 필터와 페이징 처리 문제

- 저는 이 서비스가 페이스북이나 인스타그램 처럼 가볍게, 자주 사용되길 바라는 마음으로 개발했습니다.  
  때문에 페이징 처리도 무한 스크롤을 적용했습니다.

- 하지만 [무한스크롤, 페이징 혹은 “더보기” 버튼? 어떤 걸 써야할까](https://cyberx.tistory.com/82) 라는 글을 읽고 무한 스크롤의 단점들을 알게 되었고,  
  다양한 기준(카테고리, 사용자, 등록일, 인기도)의 게시물 필터 기능을 넣어서 이를 보완하고자 했습니다.

- 그런데 게시물이 필터링 된 상태에서 무한 스크롤이 동작하면,  
  필터링 된 게시물들만 DB에 요청해야 하기 때문에 아래의 **기존 코드** 처럼 각 필터별로 다른 Query를 날려야 했습니다.

<details>
<summary><b>기존 코드</b></summary>
<div markdown="1">


~~~java
/**
 * 게시물 Top10 (기준: 댓글 수 + 좋아요 수)
 * @return 인기순 상위 10개 게시물
 */
public Page<PostResponseDto> listTopTen() {

    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.DESC, "rankPoint", "likeCnt");
    return postRepository.findAll(pageRequest).map(PostResponseDto::new);
}

/**
 * 게시물 필터 (Tag Name)
 * @param tagName 게시물 박스에서 클릭한 태그 이름
 * @param pageable 페이징 처리를 위한 객체
 * @return 해당 태그가 포함된 게시물 목록
 */
public Page<PostResponseDto> listFilteredByTagName(String tagName, Pageable pageable) {

    return postRepository.findAllByTagName(tagName, pageable).map(PostResponseDto::new);
}

// ... 게시물 필터 (Member) 생략 

/**
 * 게시물 필터 (Date)
 * @param createdDate 게시물 박스에서 클릭한 날짜
 * @return 해당 날짜에 등록된 게시물 목록
 */
public List<PostResponseDto> listFilteredByDate(String createdDate) {

    // 등록일 00시부터 24시까지
    LocalDateTime start = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MIN);
    LocalDateTime end = LocalDateTime.of(LocalDate.parse(createdDate), LocalTime.MAX);

    return postRepository
                    .findAllByCreatedAtBetween(start, end)
                    .stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());
    }
~~~

</div>
</details>

- 이 때 카테고리(tag)로 게시물을 필터링 하는 경우,  
  각 게시물은 최대 3개까지의 카테고리(tag)를 가질 수 있어 해당 카테고리를 포함하는 모든 게시물을 질의해야 했기 때문에  
- 아래 **개선된 코드**와 같이 QueryDSL을 사용하여 다소 복잡한 Query를 작성하면서도 페이징 처리를 할 수 있었습니다.

<details>
<summary><b>개선된 코드</b></summary>
<div markdown="1">


~~~java
/**
 * 게시물 필터 (Tag Name)
 */
@Override
public Page<Post> findAllByTagName(String tagName, Pageable pageable) {

    QueryResults<Post> results = queryFactory
            .selectFrom(post)
            .innerJoin(postTag)
                .on(post.idx.eq(postTag.post.idx))
            .innerJoin(tag)
                .on(tag.idx.eq(postTag.tag.idx))
            .where(tag.name.eq(tagName))
            .orderBy(post.idx.desc())
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
            .fetchResults();

    return new PageImpl<>(results.getResults(), pageable, results.getTotal());
}
~~~

</div>
</details>

</br>

## 6. 그 외 트러블 슈팅

<details>
<summary>npm run dev 실행 오류</summary>
<div markdown="1">


- Webpack-dev-server 버전을 3.0.0으로 다운그레이드로 해결
- `$ npm install —save-dev webpack-dev-server@3.0.0`

</div>
</details>

<details>
<summary>vue-devtools 크롬익스텐션 인식 오류 문제</summary>
<div markdown="1">


  - main.js 파일에 `Vue.config.devtools = true` 추가로 해결
  - [https://github.com/vuejs/vue-devtools/issues/190](https://github.com/vuejs/vue-devtools/issues/190)

</div>
</details>

<details>
<summary>ElementUI input 박스에서 `v-on:keyup.enter="메소드명"`이 정상 작동 안하는 문제</summary>
<div markdown="1">


  - `v-on:keyup.enter.native=""` 와 같이 .native 추가로 해결

</div>
</details>

<details>
<summary> Post 목록 출력시에 Member 객체 출력 에러 </summary>
<div markdown="1">


  - 에러 메세지(500에러)
    - No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationConfig.SerializationFeature.FAIL_ON_EMPTY_BEANS)
  - 해결
    - Post 엔티티에 @ManyToOne 연관관계 매핑을 LAZY 옵션에서 기본(EAGER)옵션으로 수정

</div>
</details>
    

<details>
<summary> 프로젝트를 git init으로 생성 후 발생하는 npm run dev/build 오류 문제 </summary>
<div markdown="1">


  ```jsx
    $ npm run dev
    npm ERR! path C:\Users\integer\IdeaProjects\pilot\package.json
    npm ERR! code ENOENT
    npm ERR! errno -4058
    npm ERR! syscall open
    npm ERR! enoent ENOENT: no such file or directory, open 'C:\Users\integer\IdeaProjects\pilot\package.json'
    npm ERR! enoent This is related to npm not being able to find a file.
    npm ERR! enoent

    npm ERR! A complete log of this run can be found in:
    npm ERR!     C:\Users\integer\AppData\Roaming\npm-cache\_logs\2019-02-25T01_23_19_131Z-debug.log
  ```

  - 단순히 npm run dev/build 명령을 입력한 경로가 문제였다.

</div>
</details>    

<details>
<summary> 태그 선택후 등록하기 누를 때 `object references an unsaved transient instance - save the transient instance before flushing` 오류</summary>
<div markdown="1">


  - Post 엔티티의 @ManyToMany에 영속성 전이(cascade=CascadeType.ALL) 추가
    - JPA에서 Entity를 저장할 때 연관된 모든 Entity는 영속상태여야 한다.
    - CascadeType.PERSIST 옵션으로 부모와 자식 Enitity를 한 번에 영속화할 수 있다.
    - 참고
      - [https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218](https://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/10680218)

</div>
</details>    

<details>
<summary> JSON: Infinite recursion (StackOverflowError)</summary>
<div markdown="1">


  - @JsonIgnoreProperties 사용으로 해결
    - 참고
      - [http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html](http://springquay.blogspot.com/2016/01/new-approach-to-solve-json-recursive.html)
      - [https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue](https://stackoverflow.com/questions/3325387/infinite-recursion-with-jackson-json-and-hibernate-jpa-issue)

</div>
</details>  
    

<details>
<summary> H2 접속문제</summary>
<div markdown="1">


  - H2의 JDBC URL이 jdbc:h2:~/test 으로 되어있으면 jdbc:h2:mem:testdb 으로 변경해서 접속해야 한다.
        

</div>
</details> 
    

<details>
<summary> 컨텐츠수정 모달창에서 태그 셀렉트박스 드랍다운이 뒤쪽에 보이는 문제</summary>
<div markdown="1">


   - ElementUI의 Global Config에 옵션 추가하면 해결
     - main.js 파일에 `Vue.us(ElementUI, { zIndex: 9999 });` 옵션 추가(9999 이하면 안됌)
   - 참고
     - [https://element.eleme.io/#/en-US/component/quickstart#global-config](https://element.eleme.io/#/en-US/component/quickstart#global-config)

</div>
</details> 

<details>
<summary> HTTP delete Request시 개발자도구의 XHR(XMLHttpRequest )에서 delete요청이 2번씩 찍히는 이유</summary>
<div markdown="1">


  - When you try to send a XMLHttpRequest to a different domain than the page is hosted, you are violating the same-origin policy. However, this situation became somewhat common, many technics are introduced. CORS is one of them.

      In short, server that you are sending the DELETE request allows cross domain requests. In the process, there should be a **preflight** call and that is the **HTTP OPTION** call.

      So, you are having two responses for the **OPTION** and **DELETE** call.

      see [MDN page for CORS](https://developer.mozilla.org/en-US/docs/Web/HTTP/Access_control_CORS).

    - 출처 : [https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o](https://stackoverflow.com/questions/35808655/why-do-i-get-back-2-responses-of-200-and-204-when-using-an-ajax-call-to-delete-o)

</div>
</details> 

<details>
<summary> 이미지 파싱 시 og:image 경로가 달라서 제대로 파싱이 안되는 경우</summary>
<div markdown="1">


  - UserAgent 설정으로 해결
      - [https://www.javacodeexamples.com/jsoup-set-user-agent-example/760](https://www.javacodeexamples.com/jsoup-set-user-agent-example/760)
      - [http://www.useragentstring.com/](http://www.useragentstring.com/)

</div>
</details> 
    

<details>
<summary> 구글 로그인으로 로그인한 사용자의 정보를 가져오는 방법이 스프링 2.0대 버전에서 달라진 것</summary>
<div markdown="1">


  - 1.5대 버전에서는 Controller의 인자로 Principal을 넘기면 principal.getName(0에서 바로 꺼내서 쓸 수 있었는데, 2.0대 버전에서는 principal.getName()의 경우 principal 객체.toString()을 반환한다.

    - 1.5대 버전에서 principal을 사용하는 경우
    - 아래와 같이 사용했다면,

    ```jsx
    @RequestMapping("/sso/user")
    @SuppressWarnings("unchecked")
    public Map<String, String> user(Principal principal) {
        if (principal != null) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            Authentication authentication = oAuth2Authentication.getUserAuthentication();
            Map<String, String> details = new LinkedHashMap<>();
            details = (Map<String, String>) authentication.getDetails();
            logger.info("details = " + details);  // id, email, name, link etc.
            Map<String, String> map = new LinkedHashMap<>();
            map.put("email", details.get("email"));
            return map;
        }
        return null;
    }
    ```

    - 2.0대 버전에서는
    - 아래와 같이 principal 객체의 내용을 꺼내 쓸 수 있다.

    ```jsx
    UsernamePasswordAuthenticationToken token =
                    (UsernamePasswordAuthenticationToken) SecurityContextHolder
                            .getContext().getAuthentication();
            Map<String, Object> map = (Map<String, Object>) token.getPrincipal();
    
            String email = String.valueOf(map.get("email"));
            post.setMember(memberRepository.findByEmail(email));
    ```

</div>
</details> 
    

<details>
<summary> 랭킹 동점자 처리 문제</summary>
<div markdown="1">


  - PageRequest의 Sort부분에서 properties를 "rankPoint"를 주고 "likeCnt"를 줘서 댓글수보다 좋아요수가 우선순위 갖도록 설정.
  - 좋아요 수도 똑같다면..........
        

</div>
</details> 
    
</br>

## 9. 회고 / 느낀점

>프로젝트 개발 회고 글: https://zuminternet.github.io/ZUM-Pilot-integer/