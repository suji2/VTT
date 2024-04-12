import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:googleapis/cloudasset/v1.dart';
import 'package:provider/provider.dart';
import 'package:vtt_app/const/googleUser.dart';

class LoginScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final googleUser = Provider.of<GoogleUser>(context);

    return Scaffold(
      body: Column(
        children: <Widget>[
          if (googleUser.currentUser != null)
            Column(
              children: <Widget>[
                Text('로그인된 사용자: ${googleUser.currentUser!.email}'),
                ElevatedButton(
                  onPressed: () {
                    googleUser.signOut();
                  },
                  child: Text('로그아웃'),
                ),
              ],
            )
          else
            Stack(
              children: [
                Container(
                  child: Image.asset('assets/img/loginscreen.png',fit: BoxFit.cover),
                  width: MediaQuery.of(context).size.width, // 현재 화면의 너비에 맞게 조정
                  height: MediaQuery.of(context).size.height - 58,
                ),
                const Positioned(
                    top: 140,
                    left: 50,
                    child: Text("당신의",style: TextStyle(fontFamily:"Pretendard", fontWeight:FontWeight.w700, fontSize:35, color: Colors.white),)),
                const Positioned(
                  top: 175,
                    left: 20,
                    child: Text("유튜브를",style: TextStyle(fontFamily:"Pretendard", fontWeight:FontWeight.w700, fontSize:35, color: Colors.white),)),
                const Positioned(
                  top: 210,
                    left: 20,
                    child: Text("요약해보세요.",style: TextStyle(fontFamily:"Pretendard", fontWeight:FontWeight.w700, fontSize:35, color: Colors.white),)),
                Positioned(
                  top: 470,
                  left: 100,
                  child: GestureDetector(
                    onTap: () {
                      googleUser.signIn();
                    },
                    child: Image.asset(
                      'assets/img/signGoogle.png',
                      height: 50,
                    ),
                  ),
                ),


              ],
            ),
        ],
      ),
    );
  }
}
