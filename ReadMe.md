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
    - [Board List](#board-list)

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

![그림1](https://user-images.githubusercontent.com/54932560/84100989-fea75300-aa47-11ea-8e43-b9d51f7b7324.png)


## Activity info

### Member Register

* 회원가입
* CHECK ID 버튼 클릭해 ID중복확인.
* ID중복확인시 중복이면 가입X , 중복이 아니면 해당 ID로 가입가능
* 비밀번호,비밀번호 확인 칸이 같지 않을경우 회원가입불가x
* 비밀번호는 암호화를 통해서 DB에 저장
* 가입완료(USER 테이블에 회원정보 저장)
* UserRegister.php , RegisterActivity.class , RegisterRequest.class

![Screenshot_20200609-110938_eyers2](https://user-images.githubusercontent.com/54932560/84102257-e553d600-aa4a-11ea-9dca-bf2c3cbca195.jpg)



### Login

* 로그인
* ID 와 PW 비교후 TRUE,FALSE 반환.
* PW는 DB에 암호화 되어있으므로 해당 알고리즘의 비교 함수를 통해서 비교한다.
* 비교값이 false면 Alert창 띄우기.
* 비교값이 true면 로그인 완료.
* UserLogin.php , LoginrActivity.class , LoginRequest.class.  

![Screenshot_20200609-110921_eyers2](https://user-images.githubusercontent.com/54932560/84102692-21d40180-aa4c-11ea-95cc-aad544fe5a20.jpg)


### Sliding Tab

* 슬라이딩 탭
* 안드로이드 스튜디오 Template에서 제공하는 TabbedActivity 변형  
* SlidingTabLayout.class , SlidingTabsBasicFragment.class , SlidingTabStrip.class  
![ezgif com-gif-maker](https://user-images.githubusercontent.com/54932560/84109064-c4947c00-aa5c-11ea-981e-7f32ac10890c.gif)  


### Board List

* 게시판 리스트
* 머터리얼 디자인 (연필모양) 클릭시 게시글 작성가능
* 로그인시 아이디를 가져와 작성자 자동입력.
* FreeBoardDetail.php , FreeBoardList.php , FreeBoardWrite.php , FreeBoardActivity.class , FreeBoardFragmentActivity.class , FreeBoardWriteActivity.class , FreeBoardListAdapter.class 
