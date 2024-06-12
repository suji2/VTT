import 'dart:async';
import 'package:ljyvtt/login/loginManager.dart';
import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:ljyvtt/http/httptoback.dart';
import 'package:http/http.dart' as http;
import 'package:shared_preferences/shared_preferences.dart';

class UserProfileDTO {
  final String email;
  final String name;

  UserProfileDTO({required this.email, required this.name});
}

class Search extends StatefulWidget {
  const Search({Key? key}) : super(key: key);

  @override
  State<Search> createState() => _SearchState();
}

class _SearchState extends State<Search> {
  GoogleSignIn _googleSignIn = GoogleSignIn(scopes: ['email']);
  Toback toback = Toback();
  bool _isLoading = false;
  bool _isLoggedIn = false;
  String _backendData = '';
  String token = '';

  @override
  void initState() {
    super.initState();
    _checkLoginStatus();
  }

  // 로그인 화면 감지.
  Future<void> _checkLoginStatus() async {
    bool isLoggedIn = await LoginManager.getLoginStatus();
    setState(() {
      _isLoggedIn = isLoggedIn;
    });
  }

  Future<void> _handleSignIn() async {
    setState(() {
      _isLoading = true;
    });
    try {
      await _googleSignIn.signIn();
      setState(() {
        _isLoggedIn = true;
      });
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('로그인 성공!')),
      );
      await LoginManager.saveLoginStatus(true); // 로그인 성공 시 상태 저장
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('로그인 실패: $error')),
      );
    } finally {
      setState(() {
        _isLoading = false;
      });
    }
  }

  Future<void> _handleSignOut() async {
    try {
      await _googleSignIn.signOut();
      setState(() {
        _isLoggedIn = false;
      });
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('로그아웃 성공!')),
      );
      await LoginManager.saveLoginStatus(false); // 로그아웃 시 상태 저장
    } catch (error) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('로그아웃 실패: $error')),
      );
    }
  }

  Future<void> fetchDataFromBackend(String token) async {
    var url = Uri.parse('http://192.168.0.28:8080/verify?token=$token');
    try {
      var response = await http.get(
        url,
      );
      if (response.statusCode == 200) {
        print('토큰 전송 성공!');
        print('서버로부터 받은 응답: ${response.body}');
      } else {
        print('토큰 전송 실패 - 상태 코드: ${response.statusCode}');
      }
    } catch (e) {
      print('오류 발생: $e');
    }
  }

  Future<void> fetchtest() async {
    var url = Uri.parse('http://192.168.0.28:8080/user/profile');
    try {
      var response = await http.get(url);
      if (response.statusCode == 200) {
        print('요청 성공:');
        print(response.body); // 서버로부터 받은 응답을 출력
      } else {
        print('요청 실패 - 상태 코드: ${response.statusCode}');
      }
    } catch (e) {
      print('HTTP 요청 중 오류 발생: $e');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Google Sign In'),
      ),
      body: Center(
        child: _isLoading
            ? CircularProgressIndicator()
            : _isLoggedIn
            ? Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: _handleSignOut,
              child: const Text('로그아웃'),
            ),
            ElevatedButton(
              onPressed: () async {
                var token = await getAccessToken();
                if (token != null) {
                  await fetchDataFromBackend(token);
                } else {
                  ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(
                          content:
                          Text('토큰을 가져오는데 실패했습니다.')));
                }
              },
              child: const Text('통신'),
            ),
            ElevatedButton(
              onPressed: fetchtest, // 버튼 클릭 시 fetchtest 메서드 호출
              child: Text('Fetch Data'),
            ),
            SizedBox(height: 20),
            Text("백엔드 데이터: $_backendData"),
            SizedBox(height: 20),
            Text("토큰값: $token"),
          ],
        )
            : ElevatedButton(
          onPressed: _handleSignIn,
          child: const Text('Sign in with Google'),
        ),
      ),
    );
  }

  Future<String?> getAccessToken() async {
    try {
      // 구글 로그인 인스턴스에서 현재 사용자를 확인
      GoogleSignInAccount? user = _googleSignIn.currentUser;
      if (user != null) {
        GoogleSignInAuthentication auth = await user.authentication;
        token = auth.accessToken!;
        return auth.accessToken; // 액세스 토큰 반환
      } else {
        print('No user logged in');
        return null;
      }
    } catch (error) {
      print('Error while retrieving access token: $error');
      return null;
    }
  }
}
