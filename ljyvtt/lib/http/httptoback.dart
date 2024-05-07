import 'dart:async';

import 'package:http/http.dart' as http;

class Toback {


  Future<void> fetchDataFromBackend(token) async {
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

  Future<void> hellooo(token) async { // 유튜브 채널 목록 (json으로 받음)
    var url = Uri.parse('https://192.168.0.28:8080/youtube/channel');
    try {
      // GET 요청 보내기
      var response = await http.get(
          url//,
        //headers: {'Authorization': '%token'}
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
