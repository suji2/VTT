import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:codenamea/json/youtubedata.dart';

class Httptest extends StatefulWidget {
  @override
  _HttptestState createState() => _HttptestState();
}

class _HttptestState extends State<Httptest> {
  List<YoutubeData> youtubedata = [];


  Future<void> fetchData() async {
    var url = Uri.parse('http://192.168.34.10:8080/videos');
    var response = await http.get(url);

    if (response.statusCode == 200) {
      setState(() {

        List<dynamic> jsonData = jsonDecode(response.body); // JSON 응답을 파싱하여 데이터 변수에 저장
        youtubedata = jsonData.map((data) => YoutubeData.fromJson(data)).toList();
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
            // 데이터를 화면에 표시
            Expanded(
              child: ListView.builder(
                itemCount: youtubedata.length,
                itemBuilder: (context, index) {
                  return ListTile(
                    title: Text(youtubedata[index].title),
                    subtitle: Text(youtubedata[index].description),
                  );
                },
              ),
            ),
          ],
        ),
      ),
    );
  }
}
