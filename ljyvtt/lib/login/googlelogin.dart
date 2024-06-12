import 'package:google_sign_in/google_sign_in.dart';
import 'package:http/http.dart' as http;

class GoogleSignInManager {
  GoogleSignIn _googleSignIn = GoogleSignIn(
    scopes: [
      'email',  // 이메일 정보 접근 권한
      'https://www.googleapis.com/auth/userinfo.profile'  // 프로필 정보 접근 권한
    ],
  );

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

  // 인증 토큰 가져오기 메서드
  Future<String?> getAccessToken() async {
    try {
      // 구글 로그인 인스턴스에서 현재 사용자를 확인
      GoogleSignInAccount? user = _googleSignIn.currentUser;
      if (user != null) {
        GoogleSignInAuthentication auth = await user.authentication;
        return auth.accessToken;  // 액세스 토큰 반환
      } else {
        print('No user logged in');
        return null;
      }
    } catch (error) {
      print('Error while retrieving access token: $error');
      return null;
    }
  }

  Future<void> fetchDataFromBackend(token) async {
    token = await getAccessToken();
    var url = Uri.parse('https://192.168.0.28:8080/youtube/channel');
    try {
      // GET 요청 보내기
      var response = await http.get(
          url,
          headers: {'Authorization': 'Bearer $token'}

      );

      // 응답 처리
      if (response.statusCode == 200) {
        // 요청이 성공했을 때의 처리
        print('GET 요청 성공!');
        print('백엔드로부터 받은 데이터: ${response.body}');
      } else {
        // 요청이 실패했을 때의 처리
        print('GET 요청 실패 - 상태 코드: ${response.statusCode}');
      }
    } catch (e) {
      // 오류 처리
      print('오류 발생: $e');
    }
  }
}
