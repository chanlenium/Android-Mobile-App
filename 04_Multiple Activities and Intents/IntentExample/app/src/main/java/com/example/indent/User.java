package com.example.indent;

import java.io.Serializable;

// 자바에서 입출력에 사용되는 것은 스트림(stream)이라는 데이터 통로를 통해 이동
// 하지만 객체는 바이트형(byte)이 아니라서 스트림(stream)을 통해 파일에 저장하거나 네트워크로 전송할 수 없음
// 따라서 객체를 스트림을 통해 입출력하려면 바이트 배열로 변환하는 것이 필요한데, 이를 '직렬화' 라고 함
// By implementing Serializable interface, it is possible to access the Class using its type (Here, User type)
// e.x) User user = (User) intent.getSerializableExtra("user");
public class User implements Serializable {
    private String userName;    // 직렬화 대상
    private int age;    // 직렬화 대상
    private String email;   // 직렬화 대상

    // 생성자는 직렬화 대상이 아님
    public User(String userName, int age, String email) {
        this.userName = userName;
        this.age = age;
        this.email = email;
    }

    // method는 직렬화 대상이 아님
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
