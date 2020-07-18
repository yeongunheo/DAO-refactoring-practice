# DAO-refactoring-practice

- 자바 웹 프로그래밍 Next Step 스터디(2020. 03 ~) - DAO 리팩토링 실습
- Forked from [https://github.com/slipp/web-application-server](https://github.com/slipp/web-application-server)

### Reference
- [자바 웹 프로그래밍 Next Step 하나씩 벗겨가는 양파껍질 학습법(박재성 저. 로드북. 2016)](http://book.naver.com/bookdb/book_detail.nhn?bid=11037465)
- [HTTP 완벽 가이드(데이빗고울리 외 5명 저, 이응준 외 1명 역, 인사이트, 2014)](http://book.naver.com/bookdb/book_detail.nhn?bid=8509980)

# 각 요구사항별 학습 내용 정리

### 7.1 회원 데이터를 DB에 저장하기 실습

| 구분     | 내용                                                                                         |
| :----- | ---------------------------------------- |
| 요구사항 1 | 실습 코드 리뷰 및 JDBC 복습 |
| 요구사항 2 | UserDao 클래스에 회원 전체 목록을 조회하는 findAll() 메소드 구현 |
| 요구사항 3 | UserDao 클래스에 개인정보를 수정할 수 있는 update() 메소드 구현 |
| 요구사항 4 | 모든 컨트롤러가 추가한 UserDao 메소드를 사용하도록 변경 |

* 구현 단계에서는 각 요구사항을 구현하는데 집중한다. 
* 구현을 완료한 후 구현 과정에서 새롭게 알게된 내용, 궁금한 내용을 기록한다.
* 각 요구사항을 구현하는 것이 중요한 것이 아니라 구현 과정을 통해 학습한 내용을 인식하는 것이 배움에 중요하다. 

### 개요

HTTP 웹 서버가 가지고 있었던 문제 중 하나는 `사용자가 입력한 데이터가 서버를 재시작하면 사라진다.`는 것이다. 데이터를 영구적으로 저장하고 조회할 필요가 있는데 이 문제에 대한 해결책은 데이터베이스 서버를 도입해 해결할 수 있다.
실습 소스코드를 보면 next.dao 패키지에 UserDao 클래스를  통해 데이터베이스 접근 로직을 구현하고 있다.

> 자바 진영은 데이터 베이스에 대한 접근 로직 처리를 담당하는 객체를 별도로 분리해 구현하는 것을 추천한다. 이 객체를 DAO(Data Access Object)라고 부른다.

### Issue TimeLine

요구사항 4에서 기존의 DataBase에 데이터를 저장하던 것을 새로 추가한 UserDao 메소드를 사용하도록 리팩토링하는 과정에서 문제가 발생했다.
UpdateUserController를 리팩토링했음에도 불구하고 개인정보 수정 기능이 제대로 작동하지 않았다.

> 해결책
>> 기존 코드에서 user.update(update User)코드를 변경하지 않아서 생긴 문제였다. UserDao의 update 메소드를 사용해 쿼리문의 형태로 수정된 개인정보를 저장해야만 한다. `userDao.update(updateUser);`

