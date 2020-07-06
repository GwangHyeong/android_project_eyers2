![아이어즈 로고_투명 - 복사본](https://user-images.githubusercontent.com/54932560/84098058-3bbc1700-aa41-11ea-9a08-ea1a0b52ce7a.png)

학사동아리 eyers 커뮤니티 앱 개발
======================

:star: Star us on GitHub — it helps!

[eyers](http://rhkdgud61.iwinv.net/) 는 스마트미디어학과 학사 동아리 'eyers' 커뮤니티 어플로써, 동아리 선후배간의 지속적인 관계를 유지하고 발전시키기 위해 개발된 App입니다.

![그림1](https://user-images.githubusercontent.com/54932560/84098668-d2d59e80-aa42-11ea-84c2-fefe3dc7be12.png)



## Table of content

- [Installation](#installation)
- [Library In Use](#library-in-use)
- [Activity info](#activity-info)
    - [Member Register](#member-register)
    - [Login](#login)
    - [Sliding Tab](#sliding-tab)
    - [Board List](#board-list-board-comment)
    - [Member List](#member-list)
    - [FAQ](#faq)
    - [Board Delete](#board-delete)
    - [Change password](#chage-password)
    

## Installation

- [Android Strudio 3.6.3](https://developer.android.com/studio/archive?hl=ko)
- [php 7.2](https://www.php.net/downloads)
- [Maria DB 10.X](https://mariadb.com/downloads/)

## Library In Use
- [Gradient-Textview](https://github.com/tushar09/Gradient-Textview)
- [volley](https://github.com/google/volley)
- [matreial](https://developer.android.com/reference/com/google/android/material/button/MaterialButtonToggleGroup)

### Database

database structure:  

![캡처](https://user-images.githubusercontent.com/54932560/85809538-3fff6880-b793-11ea-97c8-5a86dd9d8e09.PNG)


## Activity info

### Member Register

* 회원가입
* CHECK ID 버튼 클릭해 ID중복확인.
* ID중복확인시 중복이면 가입X , 중복이 아니면 해당 ID로 가입가능
* 비밀번호,비밀번호 확인 칸이 같지 않을경우 회원가입불가x
* 비밀번호는 암호화를 통해서 DB에 저장
* 가입완료(USER 테이블에 회원정보 저장)
* UserRegister.php , RegisterActivity.class , RegisterRequest.class

![ezgif com-video-to-gif (5)](https://user-images.githubusercontent.com/54932560/85371504-490df100-b56b-11ea-83da-40069b9864ed.gif)




### Login

* 로그인
* ID 와 PW 비교후 TRUE,FALSE 반환.
* PW는 DB에 암호화 되어있으므로 해당 알고리즘의 비교 함수를 통해서 비교한다.
* 비교값이 false면 Alert창 띄우기.
* 비교값이 true면 로그인 완료.
* UserLogin.php , LoginrActivity.class , LoginRequest.class.  

![ezgif com-video-to-gif (7)](https://user-images.githubusercontent.com/54932560/85374769-3a760880-b570-11ea-9166-2cae2d1a5a6e.gif)



### Sliding Tab

* 슬라이딩 탭
* 안드로이드 스튜디오 Template에서 제공하는 TabbedActivity 변형  
* SlidingTabLayout.class , SlidingTabsBasicFragment.class , SlidingTabStrip.class  
![ezgif com-gif-maker](https://user-images.githubusercontent.com/54932560/84109064-c4947c00-aa5c-11ea-981e-7f32ac10890c.gif)  


### Board List Board Comment

* 게시판 리스트
* 머터리얼 디자인 (연필모양) 클릭시 게시글 작성가능
* 게시글 안에서 머터리얼 디자인 (연필모양) 클릭시 댓글 작성가능
* 로그인시 아이디를 가져와 작성자 자동입력.
* 로그인시 ID값을 계속 가지고 있어 댓글작성시 사용.
* 댓글 작성 완료시 작성한 댓글 바로 확인가능.
* FreeBoardDetail.php , FreeBoardList.php , FreeBoardWrite.php , FreeCommentWrite.php FreeBoardActivity.class , FreeBoardFragmentActivity.class , FreeBoardWriteActivity.class , FreeBoardListAdapter.class 

![ezgif com-video-to-gif (1)](https://user-images.githubusercontent.com/54932560/84217315-d2064080-ab06-11ea-9e9f-a53a277d541e.gif)

### Member List

* 동아리 회원 목록 이벤트
* MEMBER 테이블사용
* 기수 대로 정렬
* MemberAdapter.class , EyersMemberActivity.class , MemberActivity.class  
![ezgif com-video-to-gif (2)](https://user-images.githubusercontent.com/54932560/84218553-a20c6c80-ab09-11ea-84f2-6a0f774340f6.gif)

### FAQ

* FAQ(Frequently Asked Questions)  
* ArrayList 사용
* FAQActivity.class , FAQAdapter.class , FAQDetailActivity.class  
![ezgif com-video-to-gif (3)](https://user-images.githubusercontent.com/54932560/84219880-6c1cb780-ab0c-11ea-99d6-8c352548e0b9.gif)

### Board Delete

* board delete
* 본인이 작성한글만 삭제가능.
* 본인이 작성하지 않은 글은 삭제 불가
* listview longclickevent 사용
* FreeBoardDelete.php , FreeBoardDeleteRequest.class  

![ezgif com-video-to-gif (4)](https://user-images.githubusercontent.com/54932560/84341289-b326ad80-abdd-11ea-9b77-faa9742ba2c8.gif)  

### Change password

* Change password
* 비밀번호를 잊어 버렸을때 사용자에게 새로운 암호를 입력받는다.
* 새롭게 입력받는 암호도 암호화 시켜 저장
* user_id,user_name,user_studentnumber 값이 전부 일치하면 새로운 비밀번호 입력창 제시
* scanPw.php , newPw.php , NewPwRequest.class , ScanPwActivity.class , NewPwActivity.class   

![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/54932560/84342299-2df0c800-abe0-11ea-85e2-fff02b755e7f.gif)


### License
