
import 'package:google_sign_in/google_sign_in.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '../login/googlelogin.dart';
import 'package:ljyvtt/http/httptoback.dart';

class Myinfo extends StatefulWidget {
  const Myinfo({Key? key}) : super(key: key);

  @override
  State<Myinfo> createState() => _MyinfoState();
}

class _MyinfoState extends State<Myinfo> {
  String? token;
  bool isLoggedIn = false; // 추가: 로그인 상태 확인을 위한 변수
  GoogleSignInManager googleAuth = GoogleSignInManager(); // 추가: GoogleAuth 인스턴스 생성
  Toback toback = Toback();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          Container(
            child: Image.asset('assets/img/loginscreen.png', fit: BoxFit.cover),
            width: MediaQuery.of(context).size.width, // 현재 화면의 너비에 맞게 조정
            height: MediaQuery.of(context).size.height - 58,
          ),
          if (isLoggedIn)
            Column(
              children: [
                const SizedBox(height: 16),
                Text('님 반갑습니다.'),
                const SizedBox(height: 200),
                ElevatedButton(
                  onPressed: () {
                    // 로그아웃 버튼이 눌렸을 때 로그아웃 처리를 해줄 수 있습니다.
                    // 로그아웃 메서드 호출
                    GoogleSignIn().signOut();
                    setState(() {
                      token = null;
                      isLoggedIn = false;
                    });
                  },
                  child: const Text('로그아웃'),
                ),
              ],
            )
          else
            Positioned(
              top: 140,
              left: 50,
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    "당신의",
                    style: TextStyle(fontFamily: "Pretendard", fontWeight: FontWeight.w700, fontSize: 35, color: Colors.white),
                  ),
                  Text(
                    "유튜브를",
                    style: TextStyle(fontFamily: "Pretendard", fontWeight: FontWeight.w700, fontSize: 35, color: Colors.white),
                  ),
                  Text(
                    "요약해보세요.",
                    style: TextStyle(fontFamily: "Pretendard", fontWeight: FontWeight.w700, fontSize: 35, color: Colors.white),
                  ),
                ],
              ),
            ),
          Positioned(
            top: 470,
            left: 100,
            child: GestureDetector(
              onTap: () async {
                // Google 로그인 처리
                GoogleSignIn().signIn();
                 await googleAuth.signIn();
                 setState(() {
                   isLoggedIn = true;
                 });
              },
              child: Image.asset(
                'assets/img/signGoogle.png',
                height: 50,
              ),
            ),
          ),
          Positioned(
            top: 550,
            left: 100,
            child: ElevatedButton(
              onPressed: () async {
                // 토큰 값을 백엔드로 전송
                await toback.fetchDataFromBackend(googleAuth.getAccessToken());
              },
              child: Text("통신"),
            ),
          ),
        ],
      ),
    );
  }
}
