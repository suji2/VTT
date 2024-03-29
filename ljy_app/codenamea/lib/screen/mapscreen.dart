import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:flutter_naver_map/flutter_naver_map.dart';
import 'package:http/http.dart' as http;

class map extends StatefulWidget {
  final String pos;
  final String word;

  const map({Key? key, required this.pos, required this.word}) : super(key: key);

  @override
  State<map> createState() => _mapState();
}

class _mapState extends State<map> {
  final TextEditingController _searchController = TextEditingController();
  NLatLng? _mapTarget;
  double x = 0;
  double y = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: AppBar(
        backgroundColor: Colors.white,
        title: TextField(
          controller: _searchController,
          autofocus: true,
          decoration: InputDecoration(
            hintText: '당신의 지역',
            border: InputBorder.none,
          ),
          onChanged: (value) {

          },
        ),
        actions: [
          IconButton(
            icon: Icon(Icons.search),
            onPressed: () {
              _getCoordinates(widget.word);
            },
          ),
        ],
      ),
      body: NaverMap(
        options: widget.pos == '1'
            ? NaverMapViewOptions(
          initialCameraPosition: NCameraPosition(
            target: _mapTarget ?? NLatLng(36.8340603, 127.1792514),
            zoom: 15,
            bearing: 0,
            tilt: 0,
          ),
        )
            : NaverMapViewOptions(
          initialCameraPosition: NCameraPosition(
            target: _mapTarget ?? NLatLng(x, y),
            zoom: 15,
            bearing: 0,
            tilt: 0,
          ),
        ),
        onMapReady: (controller) {
          print("네이버 맵 로딩됨!");
        },
      ),
    );
  }

  Future<void> _getCoordinates(String query) async {
    final clientId = 'ttznnozw7u';
    final clientSecret = 'rItzZ9dOiVCu9FyzRmygdXJo6OD1hA3rL1Kvzoac';

    final response = await http.get(
      Uri.parse(
        'https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=$query',
      ),
      headers: {
        'X-NCP-APIGW-API-KEY-ID': clientId,
        'X-NCP-APIGW-API-KEY': clientSecret,
      },
    );
    print('API 응답: ${response.body}');

    if (response.statusCode == 200) {
      final data = json.decode(response.body);

      if (data['status'] == 'OK') {
        final items = data['addresses'];
        if (items.isNotEmpty) {
          final firstItem = items[0];
          final lat = double.parse(firstItem['y']);
          final lng = double.parse(firstItem['x']);


          print('위도: $lat, 경도: $lng');

          setState(() {
            x = lat;
            y = lng;
          });
        } else {
          print('검색 결과가 없습니다.');
        }
      } else {
        print('오류 발생: ${data['status']}');
      }
    } else {
      print('오류 발생: ${response.statusCode}');
    }
  }
}
