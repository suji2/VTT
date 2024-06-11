import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:ljyvtt/login/googlelogin.dart';
import 'dart:convert';

class Myfav extends StatefulWidget {
  const Myfav({Key? key}) : super(key: key);

  @override
  State<Myfav> createState() => _MyfavState();
}

class _MyfavState extends State<Myfav> {
  List<String> myItems = [];

  @override
  void initState() {
    super.initState();
    fetchYouTubeVideos();
  }

  fetchYouTubeVideos() async {
    // Google API 인증 정보 설정
    GoogleSignInManager googleSignInManager = GoogleSignInManager();

    String? accessToken = await googleSignInManager.getAccessToken(); // 구글 액세스 토큰 가져오기
    String apiKey = 'YOUR_API_KEY'; // Google API 키 설정
    String apiUrl = 'https://www.googleapis.com/youtube/v3/subscriptions?part=snippet&mine=true&key=$apiKey';

    try {
      // Google API 호출하여 유튜브 구독 목록 가져오기
      http.Response response = await http.get(Uri.parse(apiUrl), headers: {
        'Authorization': 'Bearer $accessToken',
      });

      if (response.statusCode == 200) {
        // API 호출 성공 시 구독 목록 파싱
        Map<String, dynamic> jsonData = json.decode(response.body);
        List<dynamic> items = jsonData['items'];
        List subscriptions = items.map((item) => item['snippet']['title']).toList();

        setState(() {
          myItems = subscriptions; // 구독 목록 업데이트
        });
      } else {
        print('Failed to fetch subscriptions: ${response.statusCode}');
      }
    } catch (error) {
      print('Error fetching subscriptions: $error');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Favorites'),
      ),
      body: ListView.builder(
        itemCount: myItems.length,
        itemBuilder: (BuildContext context, int index) {
          return ListTile(
            title: Text(myItems[index]),
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => DetailScreen(item: myItems[index])),
              );
            },
          );
        },
      ),
    );
  }
}

class DetailScreen extends StatelessWidget {
  final String item;

  const DetailScreen({Key? key, required this.item}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('유튜브내용'),
      ),
      body: Center(
        child: Text(item),
      ),
    );
  }
}
