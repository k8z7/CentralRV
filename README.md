# CentralRV

CentralRV 프로젝트 - RecyclerView 가로/세로 가운데 정렬/[Android]<br>
/ 2017.01.30. 최초 작성

원본 CentralRV 프로젝트 - RecyclerView 가로/세로 가운데 정렬/[Android] http://blog.daum.net/andro_java/951

가로/세로 막론하고 선택된 아이템을 가운데 정렬하는 RecyclerView 프로젝트입니다.

참고 : RecyclerView 아이템뷰 CardView(디자인 골치)를 버리고 RelativeLayout 사용/[Android] http://blog.daum.net/andro_java/863

1. build.gradle(앱)

1) 코드 추가

dependencies {<br>
    ...<br>
    compile 'com.android.support:appcompat-v7:24.2.1'<br>
    compile 'com.android.support:recyclerview-v7:24.2.1'<br>
    compile 'com.android.support:cardview-v7:24.2.1'
}

끝 두 줄을 추가하는데, 카드뷰를 사용하지 않으면 그 줄은 불필요하다.

주의 : appcompat 버전과 같은 버전으로 맞추어야 된다.

2) Sync Now

그래들 싱크를 시켜야 된다.

2. card.xml

카드뷰를 사용하였다(CardView 안에 TextView 하나).

세로로 스크롤하는 경우 화면 폭을 꽉 채우기 위해서는 별도의 아이템 레이아웃을 사용할 필요도 있겠으나, 샘플이므로 하나로 두루 쓴다.

        android:textColor="#112233"

안드로이드 스튜디오 1.5.1로 작업할 때는 불필요했는데 2.2.2로 하다 보니 글자색이 희게 나와서 지정한 것이다.

3. activity_main.xml

상중하 3개의 LinearLayout 안에 각 RecyclerView 하나씩 담았다.

위의 2개는 가로로 스크롤하는 리스트이고, 마지막은 세로로 스크롤하는 리스트이다.

주의 : 가로 스크롤 리스트 가운데 정렬을 위해서는 [너비 layout_width]를 LinearLayout : match_parent, RecyclerView : wrap_content 준다.

4. FastCenterLLM.java 클래스

http://stackoverflow.com/questions/38416146/recyclerview-smoothscroll-to-position-in-the-center-android

위 소스에 computeScrollVectorForPosition 메소드 보충한 것이 전부이다.

5. RvAdapter.java 클래스

onClick 메소드에서 selected_position 갱신하기 전후에 notifyItemChanged 2번 실행하는 점을 기억하자.

먼저 선택되었던 아이템의 하이라이트를 빼고 새로 선택된 아이템의 하이라이트를 주기 때문이 아닌가 싶다.

그에 이어서 highBook/highJang/highText 메소드로 책/장/텍스트 각 해당 리스트의 선택된 아이템을 가운데로 정렬한다.

6. MainActivity.java

1) RecyclerView 그리기

3개의 RecyclerView 각각 인플레이트, 매니저 세팅, 어댑터 세팅 순서로 화면에 뿌려준다.

2) 데이터

책 66개, 장 150개, 텍스트 1500줄의 각 아이템을 만들었다.

3) 가운데 정렬

3개의 RecyclerView 각각 scrollToPosition으로 빠르게 이동하고(이것은 손 터치로 클릭할 때는 불필요하지만 코드상으로 제어할 때는 느물느물 기어가는 현상을 보지 않기 위해 필요), 이어서 smoothScrollToPosition으로 가운데 정렬을 시킨다.

smoothScrollToPosition 줄을 주석처리하고 돌려보면 가운데로 정렬되지 않을 것이다.

7. 화면회전

화면이 가로/세로 바뀌면 하이라이트가 사라지고 정렬 위치도 정가운데를 벗어나는데, 그 문제는 이 글 범위를 벗어나므로 생략한다.
