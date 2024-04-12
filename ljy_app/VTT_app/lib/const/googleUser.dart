import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';

class GoogleUser with ChangeNotifier {
  final GoogleSignIn _googleSignIn = GoogleSignIn(
    scopes: [
      'email',
      'https://www.googleapis.com/auth/contacts.readonly',
    ],
  );

  GoogleSignInAccount? _currentUser;

  GoogleSignInAccount? get currentUser => _currentUser;

  // 사용자 로그인
  Future<void> signIn() async {
    try {
      _currentUser = await _googleSignIn.signIn();
      notifyListeners();
    } catch (error) {
      print('Google sign in failed: $error');
      // 에러 처리 필요 시 추가
    }
  }

  // 사용자 로그아웃
  Future<void> signOut() async {
    await _googleSignIn.signOut();
    _currentUser = null;
    notifyListeners();
  }

  // 백그라운드에서 로그인 상태 갱신
  Future<void> signInSilently() async {
    _currentUser = await _googleSignIn.signInSilently();
    notifyListeners();
  }

  // 현재 로그인 상태에 따라 초기 사용자 설정
  void initUser() {
    _googleSignIn.onCurrentUserChanged.listen((GoogleSignInAccount? account) {
      _currentUser = account;
      notifyListeners();
    });
    signInSilently();
  }
}
