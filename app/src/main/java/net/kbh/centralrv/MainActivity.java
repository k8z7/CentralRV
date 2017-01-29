package net.kbh.centralrv;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView rv_book, rv_jang, rv_text;
    private FastCenterLLM bookManager, jangManager, textManager;
    private static RvAdapter bbAdapter, jangAdapter, textAdapter;
    static ArrayList<String> bible_full = new ArrayList<String>(Arrays.asList(new String[]{"창세기", "출애굽기", "레위기", "민수기", "신명기", "여호수아", "사사기", "룻기", "사무엘상", "사무엘하", "열왕기상", "열왕기하", "역대상", "역대하", "에스라", "느헤미야", "에스더", "욥기", "시편", "잠언", "전도서", "아가", "이사야", "예레미야", "예레미야애가", "에스겔", "다니엘", "호세아", "요엘", "아모스", "오바댜", "요나", "미가", "나훔", "하박국", "스바냐", "학개", "스가랴", "말라기", "마태복음", "마가복음", "누가복음", "요한복음", "사도행전", "로마서", "고린도전서", "고린도후서", "갈라디아서", "에베소서", "빌립보서", "골로새서", "데살로니가전서", "데살로니가후서", "디모데전서", "디모데후서", "디도서", "빌레몬서", "히브리서", "야고보서", "베드로전서", "베드로후서", "요한일서", "요한이서", "요한삼서", "유다서", "요한계시록"}));
    static ArrayList<String> jangs = new ArrayList<String>(Arrays.asList(new String[150]));
    static ArrayList<String> texts = new ArrayList<String>(Arrays.asList(new String[1500]));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookManager = new FastCenterLLM(this, LinearLayoutManager.HORIZONTAL, false); // 가로로 스크롤
        rv_book = (RecyclerView) findViewById(R.id.rv_book);
        rv_book.setHasFixedSize(true);
        rv_book.setLayoutManager(bookManager);
        bbAdapter = new RvAdapter(getApplicationContext(), bible_full, R.layout.activity_main);
        rv_book.setAdapter(bbAdapter);

        for (int i=0; i<jangs.size(); i++) jangs.set(i, String.valueOf(i+1));

        jangManager = new FastCenterLLM(this, LinearLayoutManager.HORIZONTAL, false); // 가로로 스크롤
        rv_jang = (RecyclerView) findViewById(R.id.rv_jang);
        rv_jang.setHasFixedSize(true);
        rv_jang.setLayoutManager(jangManager);
        jangAdapter = new RvAdapter(getApplicationContext(), jangs, R.layout.activity_main);
        rv_jang.setAdapter(jangAdapter);

        for (int i=0; i<texts.size(); i++) texts.set(i, "Text = 세로로 스크롤해야 ... " + String.valueOf(i+1));

        textManager = new FastCenterLLM(this); // 세로로 스크롤
        rv_text = (RecyclerView) findViewById(R.id.rv_text);
        rv_text.setHasFixedSize(true);
        rv_text.setLayoutManager(textManager);
        textAdapter = new RvAdapter(getApplicationContext(), texts, R.layout.activity_main);
        rv_text.setAdapter(textAdapter);
    }

    // 책그리드 하이라이트
    public void highBook(int pos) {
        rv_book.scrollToPosition(pos);
        rv_book.smoothScrollToPosition(pos);
        bbAdapter.setSelected_position(pos);
        bbAdapter.notifyDataSetChanged();
    }

    // 장그리드 하이라이트
    public void highJang(int pos) {
        rv_jang.scrollToPosition(pos);
        rv_jang.smoothScrollToPosition(pos);
        jangAdapter.setSelected_position(pos);
        jangAdapter.notifyDataSetChanged();
    }

    // 장그리드 하이라이트
    public void highText(int pos) {
        rv_text.scrollToPosition(pos);
        rv_text.smoothScrollToPosition(pos);
        textAdapter.setSelected_position(pos);
        textAdapter.notifyDataSetChanged();
    }

}