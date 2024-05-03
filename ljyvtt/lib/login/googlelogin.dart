import 'package:google_sign_in/google_sign_in.dart';



class GoogleSignInManager {
  GoogleSignIn _googleSignIn = GoogleSignIn(scopes: ['email']);

  Future<void> signIn() async {
    try {
      await _googleSignIn.signIn();

      print('Logged in successfully');
    } catch (error) {
      print('Error while logging in: $error');
    }
  }

  Future<String?> getEmail() async {
    try {
      // 현재 로그인된 사용자 정보 가져오기
      if (_googleSignIn.currentUser != null) {
        String? email = _googleSignIn.currentUser!.email;
        return email;
      } else {
        print('No user logged in');
        return null;
      }
    } catch (error) {
      print('Error while retrieving email: $error');
      return null;
    }
  }
}
