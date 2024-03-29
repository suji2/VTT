import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

class Httptest extends StatefulWidget {
  @override
  _HttptestState createState() => _HttptestState();
}

class _HttptestState extends State<Httptest> {
  String jsonData = '';
  String testData = '';

  Future<void> fetchData() async {
    var url = Uri.parse('http://192.168.34.10:8080/videos');
    var response = await http.get(url);

    if (response.statusCode == 200) {
      setState(() {
        testData = response.body; // JSON 응답을 그대로 문자열로 저장
        List<dynamic> jsonData = jsonDecode(response.body);// JSON 응답을 파싱하여 데이터 변수에 저장
      });
    } else {
      setState(() {
        jsonData = '데이터 받아오기 실패: ${response.statusCode}';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('데이터 받아오기!'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            ElevatedButton(
              onPressed: () {
                fetchData(); // 버튼을 누를 때 데이터를 가져오는 함수 호출
              },
              child: Text('받아오기'),
            ),
            SizedBox(height: 20),
            Text(testData), // 데이터를 화면에 표시
          ],
        ),
      ),
    );
  }
}
