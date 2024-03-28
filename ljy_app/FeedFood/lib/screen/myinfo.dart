import 'package:flutter/material.dart';
import 'package:flutter_naver_login/flutter_naver_login.dart';

class Myinfo extends StatefulWidget {
  const Myinfo({Key? key}) : super(key: key);

  @override
  State<Myinfo> createState() => _MyinfoState();
}

class _MyinfoState extends State<Myinfo> {
  TextEditingController _usernameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();
  LoginPlatform _loginPlatform = LoginPlatform.none;

  void signInWithNaver() async {
    final NaverLoginResult result = await FlutterNaverLogin.logIn();

    if (result.status == NaverLoginStatus.loggedIn) {
      print('accessToken = ${result.accessToken}');
      print('id = ${result.account.id}');
      print('email = ${result.account.email}');
      print('name = ${result.account.name}');

      setState(() {
        _loginPlatform = LoginPlatform.naver;
      });
    }
  }

  void signOut() async {
    switch (_loginPlatform) {
      case LoginPlatform.naver:
        await FlutterNaverLogin.logOut();
        break;
      default:
        break;
    }

    setState(() {
      _loginPlatform = LoginPlatform.none;
    });
  }

  void _login() {
    String username = _usernameController.text;
    String password = _passwordController.text;

    if (username == 'your_username' && password == 'your_password') {
      print('Login successful');
    } else {
      print('Login failed');
    }
  }

  void _forgotPassword() {
    print('Forgot Password');
  }

  void _register() {
    print('Register');
  }

  Widget _loginButton(String path, VoidCallback onTap) {
    return Card(
      elevation: 18.0,
      shape: const CircleBorder(),
      clipBehavior: Clip.antiAlias,
      child: Ink.image(
        image: AssetImage('assets/img/$path.png'),
        width: 50,
        height: 50,
        child: InkWell(
          borderRadius: const BorderRadius.all(
            Radius.circular(35.0),
          ),
          onTap: onTap,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Image.asset(
              'assets/img/foodfeed_upicon.jpg',
              width: 300,
            ),
            SizedBox(height: 16),
            Text(
              '로그인',
              style: TextStyle(fontSize: 24),
            ),
            SizedBox(height: 50),
            TextBox(
              controller: _usernameController,
              labelText: '이메일을 입력하세요',
            ),
            SizedBox(height: 16),
            TextBox(
              controller: _passwordController,
              labelText: '비밀번호를 입력하세요',
              obscureText: true,
            ),
            SizedBox(height: 32),
            ElevatedButton(
              onPressed: _login,
              style: ElevatedButton.styleFrom(
                padding: EdgeInsets.symmetric(horizontal: 50),
                minimumSize: Size(double.infinity, 50),
              ),
              child: Text('이메일로 로그인'),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                TextButton(
                  onPressed: _register,
                  child: Text('회원가입', style: TextStyle(fontSize: 12)),
                ),
                TextButton(
                  onPressed: _forgotPassword,
                  child: Text('비밀번호 찾기', style: TextStyle(fontSize: 12)),
                ),
              ],
            ),
            SizedBox(height: 5),
            Divider(
              color: Colors.black,
              thickness: 0.5,
            ),
            Text("간편로그인", style: TextStyle(fontSize: 12)),
            SizedBox(height: 30),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                _loginButton('btnG', signInWithNaver),
                SizedBox(width: 10),
                _loginButton('btnKaKa', () {
                  // 빙고 로그인 처리
                }),
                SizedBox(width: 10),
                _loginButton('bingo', () {
                  // 빙고 로그인 처리
                }),
                SizedBox(width: 10),
                _loginButton('btn-app', () {
                  // 앱 로그인 처리
                }),
                SizedBox(width: 10),
                _loginButton('btn-face', () {
                  // 페이스북 로그인 처리
                }),
              ],
            ),
          ],
        ),
      ),
    );
  }
}

class TextBox extends StatelessWidget {
  final TextEditingController controller;
  final String labelText;
  final bool obscureText;

  const TextBox({
    required this.controller,
    required this.labelText,
    this.obscureText = false,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      padding: EdgeInsets.symmetric(horizontal: 16),
      decoration: BoxDecoration(
        border: Border.all(color: Colors.grey),
        borderRadius: BorderRadius.circular(0),
      ),
      child: TextFormField(
        controller: controller,
        decoration: InputDecoration(
          labelText: labelText,
          border: InputBorder.none,
        ),
        obscureText: obscureText,
      ),
    );
  }
}

enum LoginPlatform {
  facebook,
  google,
  kakao,
  naver,
  apple,
  none,
}
